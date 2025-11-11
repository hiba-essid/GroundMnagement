package com.gestionterrain.dao;

import com.gestionterrain.entity.Terrain;
import com.gestionterrain.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.util.List;

public class TerrainDAO {
    
    public void save(Terrain terrain) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(terrain);
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
    
    public void update(Terrain terrain) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(terrain);
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
            Terrain terrain = em.find(Terrain.class, id);
            if (terrain != null) {
                em.remove(terrain);
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
    
    public Terrain findById(Long id) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.find(Terrain.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Terrain> findAll() {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Terrain t", Terrain.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Terrain> findByType(String type) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Terrain t WHERE t.type = :type", Terrain.class)
                    .setParameter("type", type)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Terrain> findByStatut(String statut) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Terrain t WHERE t.statut = :statut", Terrain.class)
                    .setParameter("statut", statut)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
