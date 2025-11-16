package com.gestionterrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "terrains")
@JsonIgnoreProperties({"reservations"})
public class Terrain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String type; // Football, Basketball, Tennis, etc.
    
    @Column(name = "tarif_heure", nullable = false)
    private Double tarifHeure;
    
    @Column(nullable = false)
    private String statut; // Disponible, Occupé, Maintenance
    
    @Column(length = 500)
    private String description;
    
    @OneToMany(mappedBy = "terrain", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
    
    // Constructeurs
    public Terrain() {}
    
    public Terrain(String nom, String type, Double tarifHeure, String statut) {
        this.nom = nom;
        this.type = type;
        this.tarifHeure = tarifHeure;
        this.statut = statut;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Double getTarifHeure() {
        return tarifHeure;
    }
    
    public void setTarifHeure(Double tarifHeure) {
        this.tarifHeure = tarifHeure;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    @Override
    public String toString() {
        return nom + " (" + type + ")";
    }
}
