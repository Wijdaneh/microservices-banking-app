package com.banking.virement.dto;

import com.banking.virement.enums.StatutVirement;
import com.banking.virement.enums.TypeVirement;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VirementResponseDTO {
    
    private Long id;
    private Long clientId;
    private String compteSource;
    private String compteDestination;
    private BigDecimal montant;
    private String motif;
    private TypeVirement type;
    private StatutVirement statut;
    private LocalDateTime dateExecution;
    private LocalDateTime dateCreation;
}
