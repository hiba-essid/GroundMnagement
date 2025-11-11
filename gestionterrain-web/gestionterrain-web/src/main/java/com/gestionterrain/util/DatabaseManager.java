package com.gestionterrain.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
    
    private static EntityManagerFactory emf;
    
    public static void init() {
        try {
            emf = Persistence.createEntityManagerFactory("GestionTerrainPU");
            System.out.println("✅ Connexion à la base de données réussie!");
        } catch (Exception e) {
            System.err.println("❌ Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static EntityManager getEntityManager() {
        if (emf == null) {
            init();
        }
        return emf.createEntityManager();
    }
    
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("✅ Connexion à la base de données fermée");
        }
    }
}
