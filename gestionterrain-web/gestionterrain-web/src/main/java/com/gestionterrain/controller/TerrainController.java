package com.gestionterrain.controller;

import com.gestionterrain.entity.Terrain;
import com.gestionterrain.service.TerrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
public class TerrainController {
    
    @Autowired
    private TerrainService terrainService;
    
    @GetMapping
    public ResponseEntity<List<Terrain>> getAllTerrains() {
        return ResponseEntity.ok(terrainService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable Long id) {
        Terrain terrain = terrainService.findById(id);
        if (terrain != null) {
            return ResponseEntity.ok(terrain);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<String> createTerrain(@RequestBody Terrain terrain) {
        try {
            terrainService.save(terrain);
            return ResponseEntity.ok("Terrain créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTerrain(@PathVariable Long id, @RequestBody Terrain terrain) {
        try {
            terrain.setId(id);
            terrainService.update(terrain);
            return ResponseEntity.ok("Terrain modifié avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTerrain(@PathVariable Long id) {
        try {
            terrainService.delete(id);
            return ResponseEntity.ok("Terrain supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Terrain>> getTerrainsByType(@PathVariable String type) {
        return ResponseEntity.ok(terrainService.findByType(type));
    }
    
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Terrain>> getTerrainsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(terrainService.findByStatut(statut));
    }
}
