package com.banking.virement.entity;

import com.banking.virement.enums.StatutVirement;
import com.banking.virement.enums.TypeVirement;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "virements")
@Data
public class Virement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    
    @Column(name = "compte_source", nullable = false)
    private String compteSource;
    
    @Column(name = "compte_destination", nullable = false)
    private String compteDestination;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;
    
    @Column(nullable = false)
    private String motif;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeVirement type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutVirement statut;
    
    @Column(name = "date_execution")
    private LocalDateTime dateExecution;
    
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        if (dateExecution == null) {
            dateExecution = LocalDateTime.now();
        }
        if (statut == null) {
            statut = StatutVirement.INITIE;
        }
    }
}
