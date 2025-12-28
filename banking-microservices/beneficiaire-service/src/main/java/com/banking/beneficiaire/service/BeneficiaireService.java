package com.banking.beneficiaire.service;

import com.banking.beneficiaire.dto.BeneficiaireDTO;
import com.banking.beneficiaire.entity.Beneficiaire;
import com.banking.beneficiaire.exception.ResourceNotFoundException;
import com.banking.beneficiaire.exception.ValidationException;
import com.banking.beneficiaire.repository.BeneficiaireRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaireService {
    
    private final BeneficiaireRepository repository;
    private final ModelMapper modelMapper;
    
    public BeneficiaireDTO createBeneficiaire(BeneficiaireDTO dto) {
        // Vérifier l'unicité du RIB
        if (repository.existsByRibAndClientId(dto.getRib(), dto.getClientId())) {
            throw new ValidationException("Un bénéficiaire avec ce RIB existe déjà");
        }
        
        Beneficiaire beneficiaire = modelMapper.map(dto, Beneficiaire.class);
        beneficiaire = repository.save(beneficiaire);
        return modelMapper.map(beneficiaire, BeneficiaireDTO.class);
    }
    
    public List<BeneficiaireDTO> getBeneficiairesByClient(Long clientId) {
        return repository.findByClientId(clientId).stream()
            .map(b -> modelMapper.map(b, BeneficiaireDTO.class))
            .collect(Collectors.toList());
    }
    
    public BeneficiaireDTO getBeneficiaireById(Long id) {
        Beneficiaire beneficiaire = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bénéficiaire non trouvé"));
        return modelMapper.map(beneficiaire, BeneficiaireDTO.class);
    }
    
    public BeneficiaireDTO updateBeneficiaire(Long id, BeneficiaireDTO dto) {
        Beneficiaire beneficiaire = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bénéficiaire non trouvé"));
        
        // Vérifier si le RIB est modifié et s'il est unique
        if (!beneficiaire.getRib().equals(dto.getRib()) && 
            repository.existsByRibAndClientId(dto.getRib(), dto.getClientId())) {
            throw new ValidationException("Ce RIB est déjà utilisé");
        }
        
        modelMapper.map(dto, beneficiaire);
        beneficiaire = repository.save(beneficiaire);
        return modelMapper.map(beneficiaire, BeneficiaireDTO.class);
    }
    
    public void deleteBeneficiaire(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Bénéficiaire non trouvé");
        }
        repository.deleteById(id);
    }
}
