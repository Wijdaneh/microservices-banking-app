package com.banking.beneficiaire.controller;

import com.banking.beneficiaire.dto.BeneficiaireDTO;
import com.banking.beneficiaire.service.BeneficiaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaires")
@RequiredArgsConstructor
@Tag(name = "Beneficiaire", description = "API de gestion des bénéficiaires")
public class BeneficiaireController {
    
    private final BeneficiaireService service;
    
    @PostMapping
    @Operation(summary = "Créer un bénéficiaire")
    public ResponseEntity<BeneficiaireDTO> createBeneficiaire(@Valid @RequestBody BeneficiaireDTO dto) {
        BeneficiaireDTO created = service.createBeneficiaire(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Récupérer les bénéficiaires d'un client")
    public ResponseEntity<List<BeneficiaireDTO>> getByClient(@PathVariable Long clientId) {
        List<BeneficiaireDTO> beneficiaires = service.getBeneficiairesByClient(clientId);
        return ResponseEntity.ok(beneficiaires);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un bénéficiaire par ID")
    public ResponseEntity<BeneficiaireDTO> getBeneficiaire(@PathVariable Long id) {
        BeneficiaireDTO beneficiaire = service.getBeneficiaireById(id);
        return ResponseEntity.ok(beneficiaire);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un bénéficiaire")
    public ResponseEntity<BeneficiaireDTO> updateBeneficiaire(
            @PathVariable Long id, 
            @Valid @RequestBody BeneficiaireDTO dto) {
        BeneficiaireDTO updated = service.updateBeneficiaire(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un bénéficiaire")
    public ResponseEntity<Void> deleteBeneficiaire(@PathVariable Long id) {
        service.deleteBeneficiaire(id);
        return ResponseEntity.noContent().build();
    }
}
