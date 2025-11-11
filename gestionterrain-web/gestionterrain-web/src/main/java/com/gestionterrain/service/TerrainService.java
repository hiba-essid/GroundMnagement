package com.gestionterrain.service;

import com.gestionterrain.entity.Terrain;
import com.gestionterrain.repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainService {
    
    @Autowired
    private TerrainRepository terrainRepository;
    
    public List<Terrain> findAll() {
        return terrainRepository.findAll();
    }
    
    public Terrain findById(Long id) {
        return terrainRepository.findById(id).orElse(null);
    }
    
    public void save(Terrain terrain) {
        terrainRepository.save(terrain);
    }
    
    public void update(Terrain terrain) {
        terrainRepository.save(terrain);
    }
    
    public void delete(Long id) {
        terrainRepository.deleteById(id);
    }
    
    public List<Terrain> findByType(String type) {
        return terrainRepository.findByType(type);
    }
    
    public List<Terrain> findByStatut(String statut) {
        return terrainRepository.findByStatut(statut);
    }
}
