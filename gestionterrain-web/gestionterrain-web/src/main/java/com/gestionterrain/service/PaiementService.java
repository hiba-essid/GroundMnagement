package com.gestionterrain.service;

import com.gestionterrain.controller.PaiementController.PaiementRequest;
import com.gestionterrain.entity.Paiement;
import com.gestionterrain.entity.Reservation;
import com.gestionterrain.repository.PaiementRepository;
import com.gestionterrain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class PaiementService {
    
    @Autowired
    private PaiementRepository paiementRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
    
    public Paiement findById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }
    
    public Paiement createFromRequest(PaiementRequest request) {
        // Charger la réservation complète depuis la base de données
        Reservation reservation = reservationRepository.findById(request.getReservationId())
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec ID: " + request.getReservationId()));
        
        // Créer le paiement avec la réservation chargée
        Paiement paiement = new Paiement();
        paiement.setReservation(reservation);
        paiement.setMontant(request.getMontant());
        paiement.setModePaiement(request.getModePaiement());
        
        // Parser la date si fournie, sinon utiliser maintenant
        if (request.getDatePaiement() != null && !request.getDatePaiement().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            paiement.setDatePaiement(LocalDateTime.parse(request.getDatePaiement(), formatter));
        } else {
            paiement.setDatePaiement(LocalDateTime.now());
        }
        
        paiement.setStatut(request.getStatut());
        paiement.setReference(request.getReference());
        
        return paiementRepository.save(paiement);
    }
    
    public void update(Paiement paiement) {
        // Charger la réservation depuis la base de données
        if (paiement.getReservation() != null && paiement.getReservation().getId() != null) {
            Reservation reservation = reservationRepository.findById(paiement.getReservation().getId())
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec ID: " + paiement.getReservation().getId()));
            paiement.setReservation(reservation);
        }
        
        paiementRepository.save(paiement);
    }
    
    public Paiement updateFromRequest(Long id, PaiementRequest request) {
        Paiement paiement = paiementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec ID: " + id));
        
        // Charger la réservation complète
        Reservation reservation = reservationRepository.findById(request.getReservationId())
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec ID: " + request.getReservationId()));
        
        paiement.setReservation(reservation);
        paiement.setMontant(request.getMontant());
        paiement.setModePaiement(request.getModePaiement());
        
        if (request.getDatePaiement() != null && !request.getDatePaiement().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            paiement.setDatePaiement(LocalDateTime.parse(request.getDatePaiement(), formatter));
        }
        
        paiement.setStatut(request.getStatut());
        paiement.setReference(request.getReference());
        
        return paiementRepository.save(paiement);
    }
    
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
    
    public List<Paiement> findByReservation(Long reservationId) {
        return paiementRepository.findByReservationId(reservationId);
    }
}
