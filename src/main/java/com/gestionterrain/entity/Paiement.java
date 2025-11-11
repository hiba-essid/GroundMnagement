package com.gestionterrain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entité Paiement - Représente un paiement pour une réservation
 */
@Entity
@Table(name = "paiement")
public class Paiement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Integer idPaiement;
    
    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;
    
    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement;
    
    @Column(name = "mode_paiement", length = 50, nullable = false)
    private String modePaiement;
    
    // Relation ManyToOne avec Reservation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;
    
    // Constructeurs
    public Paiement() {
        this.datePaiement = LocalDate.now();
    }
    
    public Paiement(BigDecimal montant, String modePaiement) {
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.datePaiement = LocalDate.now();
    }
    
    public Paiement(Double montant, String modePaiement) {
        this.montant = BigDecimal.valueOf(montant);
        this.modePaiement = modePaiement;
        this.datePaiement = LocalDate.now();
    }
    
    public Paiement(BigDecimal montant, String modePaiement, LocalDate datePaiement) {
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.datePaiement = datePaiement;
    }
    
    public Paiement(BigDecimal montant, String modePaiement, Reservation reservation) {
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.reservation = reservation;
        this.datePaiement = LocalDate.now();
    }
    
    public Paiement(Double montant, String modePaiement, Reservation reservation) {
        this.montant = BigDecimal.valueOf(montant);
        this.modePaiement = modePaiement;
        this.reservation = reservation;
        this.datePaiement = LocalDate.now();
    }
    
    // Getters et Setters
    public Integer getIdPaiement() {
        return idPaiement;
    }
    
    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }
    
    public BigDecimal getMontant() {
        return montant;
    }
    
    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
    
    public LocalDate getDatePaiement() {
        return datePaiement;
    }
    
    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }
    
    public String getModePaiement() {
        return modePaiement;
    }
    
    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    // Méthodes utilitaires
    public boolean isEspeces() {
        return "especes".equalsIgnoreCase(modePaiement);
    }
    
    public boolean isCarte() {
        return "carte".equalsIgnoreCase(modePaiement);
    }
    
    public boolean isVirement() {
        return "virement".equalsIgnoreCase(modePaiement);
    }
    
    // Liste des modes de paiement valides
    public static String[] getModesPaiementValides() {
        return new String[]{"especes", "carte", "virement"};
    }
    
    public static boolean isModePaiementValide(String mode) {
        if (mode == null) return false;
        for (String modeValide : getModesPaiementValides()) {
            if (modeValide.equalsIgnoreCase(mode)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Paiement{" +
                "idPaiement=" + idPaiement +
                ", montant=" + montant +
                ", datePaiement=" + datePaiement +
                ", modePaiement='" + modePaiement + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return idPaiement != null && idPaiement.equals(paiement.idPaiement);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}