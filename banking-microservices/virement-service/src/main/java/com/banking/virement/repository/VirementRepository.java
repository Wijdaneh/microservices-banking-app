package com.banking.virement.repository;

import com.banking.virement.entity.Virement;
import com.banking.virement.enums.StatutVirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {
    
    List<Virement> findByClientId(Long clientId);
    
    List<Virement> findByClientIdAndStatut(Long clientId, StatutVirement statut);
    
    List<Virement> findByDateCreationBetween(LocalDateTime start, LocalDateTime end);
    
    List<Virement> findByCompteSource(String compteSource);
    
    List<Virement> findByCompteDestination(String compteDestination);
}
