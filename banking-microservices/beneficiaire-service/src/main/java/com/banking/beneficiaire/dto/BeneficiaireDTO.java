package com.banking.beneficiaire.dto;

import com.banking.beneficiaire.enums.TypeBeneficiaire;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class BeneficiaireDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;
    
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 100, message = "Le prénom doit contenir entre 2 et 100 caractères")
    private String prenom;
    
    @NotBlank(message = "Le RIB est obligatoire")
    @Pattern(regexp = "^[A-Z]{2}\d{23}$", message = "Format RIB invalide")
    private String rib;
    
    @NotNull(message = "Le type est obligatoire")
    private TypeBeneficiaire type;
    
    @NotNull(message = "L'ID client est obligatoire")
    private Long clientId;
    
    private String createdAt;
    private String updatedAt;
}
