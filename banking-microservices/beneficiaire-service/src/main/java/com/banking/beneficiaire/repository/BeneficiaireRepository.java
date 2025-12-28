package com.banking.beneficiaire.repository;

import com.banking.beneficiaire.entity.Beneficiaire;
import com.banking.beneficiaire.enums.TypeBeneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
    
    List<Beneficiaire> findByClientId(Long clientId);
    
    Optional<Beneficiaire> findByRib(String rib);
    
    List<Beneficiaire> findByType(TypeBeneficiaire type);
    
    boolean existsByRibAndClientId(String rib, Long clientId);
}
