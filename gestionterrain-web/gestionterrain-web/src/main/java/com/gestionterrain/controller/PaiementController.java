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
    public ResponseEntity<String> createPaiement(@RequestBody Paiement paiement) {
        try {
            paiementService.save(paiement);
            return ResponseEntity.ok("Paiement créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        try {
            paiement.setId(id);
            paiementService.update(paiement);
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
