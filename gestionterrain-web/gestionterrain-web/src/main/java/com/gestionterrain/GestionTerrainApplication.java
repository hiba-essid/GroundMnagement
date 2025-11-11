package com.gestionterrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionTerrainApplication {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("     SYSTÈME DE GESTION DE TERRAINS DE SPORT - WEB");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();
        
        SpringApplication.run(GestionTerrainApplication.class, args);
        
        System.out.println();
        System.out.println("✅ Application démarrée avec succès!");
        System.out.println("🌐 Accédez à l'application sur: http://localhost:8081");
        System.out.println();
    }
}
