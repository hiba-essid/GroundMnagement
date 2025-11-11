package com.gestionterrain.dao;

import com.gestionterrain.entity.Paiement;
import com.gestionterrain.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.util.List;

public class PaiementDAO {
    
    public void save(Paiement paiement) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(paiement);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void update(Paiement paiement) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(paiement);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void delete(Long id) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Paiement paiement = em.find(Paiement.class, id);
            if (paiement != null) {
                em.remove(paiement);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public Paiement findById(Long id) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.find(Paiement.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Paiement> findAll() {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Paiement p", Paiement.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Paiement findByReservation(Long reservationId) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            List<Paiement> paiements = em.createQuery("SELECT p FROM Paiement p WHERE p.reservation.id = :reservationId", Paiement.class)
                    .setParameter("reservationId", reservationId)
                    .getResultList();
            return paiements.isEmpty() ? null : paiements.get(0);
        } finally {
            em.close();
        }
    }
}
