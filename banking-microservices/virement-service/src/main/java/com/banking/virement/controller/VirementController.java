package com.banking.virement.controller;

import com.banking.virement.dto.VirementRequestDTO;
import com.banking.virement.dto.VirementResponseDTO;
import com.banking.virement.enums.StatutVirement;
import com.banking.virement.service.VirementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/virements")
@RequiredArgsConstructor
@Tag(name = "Virement", description = "API de gestion des virements")
public class VirementController {
    
    private final VirementService service;
    
    @PostMapping
    @Operation(summary = "Initier un virement")
    public ResponseEntity<VirementResponseDTO> initierVirement(@Valid @RequestBody VirementRequestDTO request) {
        VirementResponseDTO response = service.initierVirement(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Récupérer les virements d'un client")
    public ResponseEntity<List<VirementResponseDTO>> getVirementsByClient(@PathVariable Long clientId) {
        List<VirementResponseDTO> virements = service.getVirementsByClient(clientId);
        return ResponseEntity.ok(virements);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un virement par ID")
    public ResponseEntity<VirementResponseDTO> getVirement(@PathVariable Long id) {
        VirementResponseDTO virement = service.getVirementById(id);
        return ResponseEntity.ok(virement);
    }
    
    @PostMapping("/{id}/annuler")
    @Operation(summary = "Annuler un virement")
    public ResponseEntity<Void> annulerVirement(@PathVariable Long id) {
        service.annulerVirement(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/client/{clientId}/statut/{statut}")
    @Operation(summary = "Récupérer les virements par statut")
    public ResponseEntity<List<VirementResponseDTO>> getVirementsByStatut(
            @PathVariable Long clientId,
            @PathVariable StatutVirement statut) {
        List<VirementResponseDTO> virements = service.getVirementsByStatut(clientId, statut);
        return ResponseEntity.ok(virements);
    }
}
