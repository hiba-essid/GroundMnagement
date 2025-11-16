package com.gestionterrain.service;

import com.gestionterrain.controller.ReservationController.ReservationRequest;
import com.gestionterrain.entity.Client;
import com.gestionterrain.entity.Reservation;
import com.gestionterrain.entity.Terrain;
import com.gestionterrain.repository.ClientRepository;
import com.gestionterrain.repository.ReservationRepository;
import com.gestionterrain.repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private TerrainRepository terrainRepository;
    
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    
    public Reservation createFromRequest(ReservationRequest request) {
        // Charger le client complet
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec ID: " + request.getClientId()));
        
        // Charger le terrain complet
        Terrain terrain = terrainRepository.findById(request.getTerrainId())
            .orElseThrow(() -> new RuntimeException("Terrain non trouvé avec ID: " + request.getTerrainId()));
        
        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setTerrain(terrain);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        reservation.setDateDebut(LocalDateTime.parse(request.getDateDebut(), formatter));
        reservation.setDateFin(LocalDateTime.parse(request.getDateFin(), formatter));
        reservation.setMontantTotal(request.getMontantTotal());
        reservation.setStatut(request.getStatut());
        
        return reservationRepository.save(reservation);
    }
    
    public Reservation updateFromRequest(Long id, ReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec ID: " + id));
        
        // Charger le client et terrain complets
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec ID: " + request.getClientId()));
        Terrain terrain = terrainRepository.findById(request.getTerrainId())
            .orElseThrow(() -> new RuntimeException("Terrain non trouvé avec ID: " + request.getTerrainId()));
        
        reservation.setClient(client);
        reservation.setTerrain(terrain);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        reservation.setDateDebut(LocalDateTime.parse(request.getDateDebut(), formatter));
        reservation.setDateFin(LocalDateTime.parse(request.getDateFin(), formatter));
        reservation.setMontantTotal(request.getMontantTotal());
        reservation.setStatut(request.getStatut());
        
        return reservationRepository.save(reservation);
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
