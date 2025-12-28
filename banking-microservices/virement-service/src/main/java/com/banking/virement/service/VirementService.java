package com.banking.virement.service;

import com.banking.virement.dto.VirementRequestDTO;
import com.banking.virement.dto.VirementResponseDTO;
import com.banking.virement.entity.Virement;
import com.banking.virement.enums.StatutVirement;
import com.banking.virement.exception.ResourceNotFoundException;
import com.banking.virement.exception.ValidationException;
import com.banking.virement.repository.VirementRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VirementService {
    
    private final VirementRepository repository;
    private final ModelMapper modelMapper;
    
    @Transactional
    public VirementResponseDTO initierVirement(VirementRequestDTO request) {
        // Validation du montant
        if (request.getMontant().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Le montant doit être supérieur à 0");
        }
        
        // Validation: le compte source et destination ne peuvent pas être identiques
        if (request.getCompteSource().equals(request.getCompteDestination())) {
            throw new ValidationException("Le compte source et le compte destination doivent être différents");
        }
        
        Virement virement = modelMapper.map(request, Virement.class);
        virement.setStatut(StatutVirement.INITIE);
        
        // Simulation de traitement (dans un vrai système, on appellerait un service de compte)
        // Pour le moment, on simule un virement réussi après 2 secondes
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                virement.setStatut(StatutVirement.REUSSI);
                repository.save(virement);
            } catch (InterruptedException e) {
                virement.setStatut(StatutVirement.ECHEC);
                repository.save(virement);
            }
        }).start();
        
        virement = repository.save(virement);
        return modelMapper.map(virement, VirementResponseDTO.class);
    }
    
    public List<VirementResponseDTO> getVirementsByClient(Long clientId) {
        return repository.findByClientId(clientId).stream()
            .map(v -> modelMapper.map(v, VirementResponseDTO.class))
            .collect(Collectors.toList());
    }
    
    public VirementResponseDTO getVirementById(Long id) {
        Virement virement = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Virement non trouvé"));
        return modelMapper.map(virement, VirementResponseDTO.class);
    }
    
    @Transactional
    public void annulerVirement(Long id) {
        Virement virement = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Virement non trouvé"));
        
        if (virement.getStatut() != StatutVirement.INITIE && virement.getStatut() != StatutVirement.EN_COURS) {
            throw new ValidationException("Seuls les virements initiés ou en cours peuvent être annulés");
        }
        
        virement.setStatut(StatutVirement.ANNULE);
        repository.save(virement);
    }
    
    public List<VirementResponseDTO> getVirementsByStatut(Long clientId, StatutVirement statut) {
        return repository.findByClientIdAndStatut(clientId, statut).stream()
            .map(v -> modelMapper.map(v, VirementResponseDTO.class))
            .collect(Collectors.toList());
    }
}
