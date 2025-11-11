package com.gestionterrain.dao;

import com.gestionterrain.entity.Reservation;
import com.gestionterrain.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationDAO {
    
    public void save(Reservation reservation) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reservation);
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
    
    public void update(Reservation reservation) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(reservation);
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
            Reservation reservation = em.find(Reservation.class, id);
            if (reservation != null) {
                em.remove(reservation);
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
    
    public Reservation findById(Long id) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findAll() {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findByClient(Long clientId) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Reservation r WHERE r.client.id = :clientId", Reservation.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findByTerrain(Long terrainId) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Reservation r WHERE r.terrain.id = :terrainId", Reservation.class)
                    .setParameter("terrainId", terrainId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
