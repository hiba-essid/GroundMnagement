package com.gestionterrain.controller;

import com.gestionterrain.entity.Paiement;
import com.gestionterrain.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    
    @Autowired
    private PaiementService paiementService;
    
    @GetMapping
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        Paiement paiement = paiementService.findById(id);
        if (paiement != null) {
            return ResponseEntity.ok(paiement);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<String> createPaiement(@RequestBody PaiementRequest request) {
        try {
            System.out.println("=== Création de paiement ===");
            System.out.println("Reservation ID: " + request.getReservationId());
            System.out.println("Montant: " + request.getMontant());
            System.out.println("Mode: " + request.getModePaiement());
            System.out.println("Date: " + request.getDatePaiement());
            System.out.println("Statut: " + request.getStatut());
            System.out.println("Reference: " + request.getReference());
            
            Paiement paiement = paiementService.createFromRequest(request);
            System.out.println("Paiement sauvegardé avec ID: " + paiement.getId());
            return ResponseEntity.ok("Paiement créé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du paiement:");
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    // Classe interne pour recevoir les données
    public static class PaiementRequest {
        private Long reservationId;
        private Double montant;
        private String modePaiement;
        private String datePaiement;
        private String statut;
        private String reference;
        
        public Long getReservationId() { return reservationId; }
        public void setReservationId(Long reservationId) { this.reservationId = reservationId; }
        public Double getMontant() { return montant; }
        public void setMontant(Double montant) { this.montant = montant; }
        public String getModePaiement() { return modePaiement; }
        public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
        public String getDatePaiement() { return datePaiement; }
        public void setDatePaiement(String datePaiement) { this.datePaiement = datePaiement; }
        public String getStatut() { return statut; }
        public void setStatut(String statut) { this.statut = statut; }
        public String getReference() { return reference; }
        public void setReference(String reference) { this.reference = reference; }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePaiement(@PathVariable Long id, @RequestBody PaiementRequest request) {
        try {
            paiementService.updateFromRequest(id, request);
            return ResponseEntity.ok("Paiement modifié avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaiement(@PathVariable Long id) {
        try {
            paiementService.delete(id);
            return ResponseEntity.ok("Paiement supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
}
