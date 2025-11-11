package com.gestionterrain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité Reservation - Représente une réservation de terrain
 */
@Entity
@Table(name = "reservation")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Integer idReservation;
    
    @Column(name = "date_reservation", nullable = false)
    private LocalDate dateReservation;
    
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;
    
    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;
    
    // Relations ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_terrain", nullable = false)
    private Terrain terrain;
    
    // Relation OneToMany avec Paiement
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();
    
    // Constructeurs
    public Reservation() {
    }
    
    public Reservation(LocalDate dateReservation, LocalTime heureDebut, LocalTime heureFin, 
                      Client client, Terrain terrain) {
        this.dateReservation = dateReservation;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.client = client;
        this.terrain = terrain;
    }
    
    // Getters et Setters
    public Integer getIdReservation() {
        return idReservation;
    }
    
    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
    
    public LocalDate getDateReservation() {
        return dateReservation;
    }
    
    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }
    
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Terrain getTerrain() {
        return terrain;
    }
    
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    
    public List<Paiement> getPaiements() {
        return paiements;
    }
    
    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
    
    // Méthodes utilitaires
    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
        paiement.setReservation(this);
    }
    
    public void removePaiement(Paiement paiement) {
        paiements.remove(paiement);
        paiement.setReservation(null);
    }
    
    // Méthode pour vérifier si la réservation est valide (heure de fin après heure de début)
    public boolean isValid() {
        return heureDebut != null && heureFin != null && heureFin.isAfter(heureDebut);
    }
    
    // Méthode pour calculer la durée de réservation en heures
    public double getDureeHeures() {
        if (!isValid()) return 0;
        return java.time.Duration.between(heureDebut, heureFin).toMinutes() / 60.0;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", dateReservation=" + dateReservation +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", client=" + (client != null ? client.getNom() : "null") +
                ", terrain=" + (terrain != null ? terrain.getNom() : "null") +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return idReservation != null && idReservation.equals(that.idReservation);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}