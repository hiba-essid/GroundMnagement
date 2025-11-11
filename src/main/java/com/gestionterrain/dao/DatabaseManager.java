package com.gestionterrain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitaire pour la gestion de l'EntityManagerFactory
 */
public class DatabaseManager {
    
    private static EntityManagerFactory emf;
    
    /**
     * Initialise l'EntityManagerFactory
     */
    public static void init() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("GestionTerrainPU");
        }
    }
    
    /**
     * Ferme l'EntityManagerFactory
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    /**
     * Crée un nouvel EntityManager
     * 
     * @return EntityManager
     */
    public static EntityManager createEntityManager() {
        if (emf == null || !emf.isOpen()) {
            init();
        }
        return emf.createEntityManager();
    }
    
    /**
     * Vérifie si l'EntityManagerFactory est ouvert
     * 
     * @return true si ouvert, false sinon
     */
    public static boolean isOpen() {
        return emf != null && emf.isOpen();
    }
}