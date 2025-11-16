package com.gestionterrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@JsonIgnoreProperties({"paiements"})
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "terrain_id", nullable = false)
    private Terrain terrain;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;
    
    @Column(name = "date_fin", nullable = false)
    private LocalDateTime dateFin;
    
    @Column(name = "montant_total", nullable = false)
    private Double montantTotal;
    
    @Column(nullable = false)
    private String statut; // Confirmée, En attente, Annulée
    
    @OneToMany(mappedBy = "reservation")
    private List<Paiement> paiements = new ArrayList<>();
    
    // Constructeurs
    public Reservation() {}
    
    public Reservation(Terrain terrain, Client client, LocalDateTime dateDebut, 
                      LocalDateTime dateFin, Double montantTotal, String statut) {
        this.terrain = terrain;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montantTotal = montantTotal;
        this.statut = statut;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Terrain getTerrain() {
        return terrain;
    }
    
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public LocalDateTime getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
    
    public Double getMontantTotal() {
        return montantTotal;
    }
    
    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public List<Paiement> getPaiements() {
        return paiements;
    }
    
    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
}
