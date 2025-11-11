package com.gestionterrain.service;

import com.gestionterrain.entity.Paiement;
import com.gestionterrain.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    
    @Autowired
    private PaiementRepository paiementRepository;
    
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
    
    public Paiement findById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }
    
    public void save(Paiement paiement) {
        paiementRepository.save(paiement);
    }
    
    public void update(Paiement paiement) {
        paiementRepository.save(paiement);
    }
    
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
    
    public List<Paiement> findByReservation(Long reservationId) {
        return paiementRepository.findByReservationId(reservationId);
    }
}
