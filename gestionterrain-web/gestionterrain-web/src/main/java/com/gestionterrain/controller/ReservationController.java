package com.gestionterrain.controller;

import com.gestionterrain.entity.Reservation;
import com.gestionterrain.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
        try {
            System.out.println("=== Création de réservation ===");
            System.out.println("Client ID: " + request.getClientId());
            System.out.println("Terrain ID: " + request.getTerrainId());
            System.out.println("Date début: " + request.getDateDebut());
            System.out.println("Date fin: " + request.getDateFin());
            System.out.println("Montant: " + request.getMontantTotal());
            System.out.println("Statut: " + request.getStatut());
            
            Reservation reservation = reservationService.createFromRequest(request);
            System.out.println("Réservation sauvegardée avec ID: " + reservation.getId());
            return ResponseEntity.ok("Réservation créée avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
        try {
            reservationService.updateFromRequest(id, request);
            return ResponseEntity.ok("Réservation modifiée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.ok("Réservation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Reservation>> getReservationsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(reservationService.findByClient(clientId));
    }
    
    @GetMapping("/terrain/{terrainId}")
    public ResponseEntity<List<Reservation>> getReservationsByTerrain(@PathVariable Long terrainId) {
        return ResponseEntity.ok(reservationService.findByTerrain(terrainId));
    }
    
    // Classe interne pour recevoir les données
    public static class ReservationRequest {
        private Long clientId;
        private Long terrainId;
        private String dateDebut;
        private String dateFin;
        private Double montantTotal;
        private String statut;
        
        public Long getClientId() { return clientId; }
        public void setClientId(Long clientId) { this.clientId = clientId; }
        public Long getTerrainId() { return terrainId; }
        public void setTerrainId(Long terrainId) { this.terrainId = terrainId; }
        public String getDateDebut() { return dateDebut; }
        public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
        public String getDateFin() { return dateFin; }
        public void setDateFin(String dateFin) { this.dateFin = dateFin; }
        public Double getMontantTotal() { return montantTotal; }
        public void setMontantTotal(Double montantTotal) { this.montantTotal = montantTotal; }
        public String getStatut() { return statut; }
        public void setStatut(String statut) { this.statut = statut; }
    }
}
