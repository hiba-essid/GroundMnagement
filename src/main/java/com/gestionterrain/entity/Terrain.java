package com.gestionterrain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité Terrain - Représente un terrain de sport
 */
@Entity
@Table(name = "terrain")
public class Terrain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_terrain")
    private Integer idTerrain;
    
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;
    
    @Column(name = "type", length = 50, nullable = false)
    private String type;
    
    @Column(name = "capacite", nullable = false)
    private Integer capacite;
    
    @Column(name = "localisation", length = 100, nullable = false)
    private String localisation;
    
    // Relation OneToMany avec Reservation
    @OneToMany(mappedBy = "terrain", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();
    
    // Constructeurs
    public Terrain() {
    }
    
    public Terrain(String nom, String type, Integer capacite, String localisation) {
        this.nom = nom;
        this.type = type;
        this.capacite = capacite;
        this.localisation = localisation;
    }
    
    // Getters et Setters
    public Integer getIdTerrain() {
        return idTerrain;
    }
    
    public void setIdTerrain(Integer idTerrain) {
        this.idTerrain = idTerrain;
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
    
    public Integer getCapacite() {
        return capacite;
    }
    
    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }
    
    public String getLocalisation() {
        return localisation;
    }
    
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    // Méthodes utilitaires
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setTerrain(this);
    }
    
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.setTerrain(null);
    }
    
    @Override
    public String toString() {
        return "Terrain{" +
                "idTerrain=" + idTerrain +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", capacite=" + capacite +
                ", localisation='" + localisation + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terrain terrain = (Terrain) o;
        return idTerrain != null && idTerrain.equals(terrain.idTerrain);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}