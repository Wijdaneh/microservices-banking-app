package com.banking.virement.dto;

import com.banking.virement.enums.TypeVirement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VirementRequestDTO {
    
    @NotNull(message = "L'ID client est obligatoire")
    private Long clientId;
    
    @NotBlank(message = "Le compte source est obligatoire")
    private String compteSource;
    
    @NotBlank(message = "Le compte destination est obligatoire")
    private String compteDestination;
    
    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal montant;
    
    @NotBlank(message = "Le motif est obligatoire")
    private String motif;
    
    @NotNull(message = "Le type est obligatoire")
    private TypeVirement type;
    
    private LocalDateTime dateExecution;
}
