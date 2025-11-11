package com.gestionterrain.repository;

import com.gestionterrain.entity.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
    List<Terrain> findByType(String type);
    List<Terrain> findByStatut(String statut);
}
