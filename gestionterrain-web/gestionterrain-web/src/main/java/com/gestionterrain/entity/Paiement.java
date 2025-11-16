package com.gestionterrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiements")
public class Paiement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    @JsonIgnoreProperties({"paiements", "client", "terrain"})
    private Reservation reservation;
    
    @Column(nullable = false)
    private Double montant;
    
    @Column(name = "mode_paiement", nullable = false)
    private String modePaiement; // Espèces, Carte bancaire, Virement
    
    @Column(name = "date_paiement", nullable = false)
    private LocalDateTime datePaiement;
    
    @Column(nullable = false)
    private String statut; // Payé, En attente, Remboursé
    
    @Column
    private String reference;
    
    // Constructeurs
    public Paiement() {}
    
    public Paiement(Reservation reservation, Double montant, String modePaiement, 
                   LocalDateTime datePaiement, String statut) {
        this.reservation = reservation;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.datePaiement = datePaiement;
        this.statut = statut;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    public Double getMontant() {
        return montant;
    }
    
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
    public String getModePaiement() {
        return modePaiement;
    }
    
    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }
    
    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }
    
    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
}
