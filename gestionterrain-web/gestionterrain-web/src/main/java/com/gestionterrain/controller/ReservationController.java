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
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        try {
            reservationService.save(reservation);
            return ResponseEntity.ok("Réservation créée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        try {
            reservation.setId(id);
            reservationService.update(reservation);
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
}
