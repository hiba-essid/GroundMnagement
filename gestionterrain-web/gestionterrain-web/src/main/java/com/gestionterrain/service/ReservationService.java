package com.gestionterrain.service;

import com.gestionterrain.entity.Reservation;
import com.gestionterrain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
    
    public List<Reservation> findByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }
    
    public List<Reservation> findByTerrain(Long terrainId) {
        return reservationRepository.findByTerrainId(terrainId);
    }
}
