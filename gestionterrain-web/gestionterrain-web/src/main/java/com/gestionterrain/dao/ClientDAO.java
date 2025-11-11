package com.gestionterrain.dao;

import com.gestionterrain.entity.Client;
import com.gestionterrain.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientDAO {
    
    public void save(Client client) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(client);
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
    
    public void update(Client client) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(client);
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
            Client client = em.find(Client.class, id);
            if (client != null) {
                em.remove(client);
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
    
    public Client findById(Long id) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Client> findAll() {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Client findByEmail(String email) {
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            List<Client> clients = em.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getResultList();
            return clients.isEmpty() ? null : clients.get(0);
        } finally {
            em.close();
        }
    }
}
