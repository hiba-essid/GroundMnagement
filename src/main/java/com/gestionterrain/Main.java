package com.gestionterrain;

import com.gestionterrain.dao.DatabaseManager;
import com.gestionterrain.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principale de l'application Gestion Terrain
 * Interface console pour gérer les réservations de terrains de sport
 * 
 * @author Gestion Terrain Team
 * @version 1.0
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;
    private static EntityTransaction transaction;
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("     SYSTÈME DE GESTION DE TERRAINS DE SPORT");
        System.out.println("═══════════════════════════════════════════════════════");
        
        DatabaseManager.init();
        
        try {
            boolean continuer = true;
            while (continuer) {
                afficherMenu();
                int choix = lireChoix();
                
                switch (choix) {
                    case 1:
                        listerTousLesTerrains();
                        break;
                    case 2:
                        listerTousLesClients();
                        break;
                    case 3:
                        listerToutesLesReservations();
                        break;
                    case 4:
                        ajouterTerrain();
                        break;
                    case 5:
                        ajouterClient();
                        break;
                    case 6:
                        creerReservation();
                        break;
                    case 7:
                        rechercherReservationsParMois();
                        break;
                    case 8:
                        afficherStatistiques();
                        break;
                    case 0:
                        continuer = false;
                        System.out.println("\n✓ Au revoir !");
                        break;
                    default:
                        System.out.println("\n✗ Choix invalide. Veuillez réessayer.");
                }
                
                if (continuer) {
                    System.out.println("\nAppuyez sur Entrée pour continuer...");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        } finally {
            DatabaseManager.close();
            scanner.close();
        }
    }
    
    private static void afficherMenu() {
        System.out.println("\n╔══════════════════════ MENU PRINCIPAL ═══════════════════╗");
        System.out.println("║  1. Lister tous les terrains                           ║");
        System.out.println("║  2. Lister tous les clients                            ║");
        System.out.println("║  3. Lister toutes les réservations                     ║");
        System.out.println("║  4. Ajouter un terrain                                 ║");
        System.out.println("║  5. Ajouter un client                                  ║");
        System.out.println("║  6. Créer une réservation                              ║");
        System.out.println("║  7. Rechercher réservations par mois                   ║");
        System.out.println("║  8. Afficher les statistiques                          ║");
        System.out.println("║  0. Quitter                                            ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        System.out.print("Votre choix: ");
    }
    
    private static int lireChoix() {
        try {
            int choix = Integer.parseInt(scanner.nextLine());
            return choix;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void listerTousLesTerrains() {
        System.out.println("\n═══ LISTE DES TERRAINS ═══");
        em = DatabaseManager.createEntityManager();
        try {
            TypedQuery<Terrain> query = em.createQuery("SELECT t FROM Terrain t ORDER BY t.nom", Terrain.class);
            List<Terrain> terrains = query.getResultList();
            
            if (terrains.isEmpty()) {
                System.out.println("Aucun terrain trouvé.");
            } else {
                for (Terrain t : terrains) {
                    System.out.printf("ID: %d | %s (%s) | Capacité: %d | Localisation: %s%n",
                            t.getIdTerrain(), t.getNom(), t.getType(), t.getCapacite(), t.getLocalisation());
                }
            }
        } finally {
            em.close();
        }
    }
    
    private static void listerTousLesClients() {
        System.out.println("\n═══ LISTE DES CLIENTS ═══");
        em = DatabaseManager.createEntityManager();
        try {
            TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ORDER BY c.nom", Client.class);
            List<Client> clients = query.getResultList();
            
            if (clients.isEmpty()) {
                System.out.println("Aucun client trouvé.");
            } else {
                for (Client c : clients) {
                    System.out.printf("ID: %d | %s | Email: %s | Tél: %s | Inscrit le: %s%n",
                            c.getIdClient(), c.getNom(), c.getEmail(), c.getTelephone(), c.getDateInscription());
                }
            }
        } finally {
            em.close();
        }
    }
    
    private static void listerToutesLesReservations() {
        System.out.println("\n═══ LISTE DES RÉSERVATIONS ═══");
        em = DatabaseManager.createEntityManager();
        try {
            TypedQuery<Reservation> query = em.createQuery(
                    "SELECT r FROM Reservation r ORDER BY r.dateReservation DESC, r.heureDebut", 
                    Reservation.class);
            List<Reservation> reservations = query.getResultList();
            
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation trouvée.");
            } else {
                for (Reservation r : reservations) {
                    System.out.printf("ID: %d | Date: %s | %s-%s | Client: %s | Terrain: %s%n",
                            r.getIdReservation(), r.getDateReservation(),
                            r.getHeureDebut(), r.getHeureFin(),
                            r.getClient().getNom(), r.getTerrain().getNom());
                }
            }
        } finally {
            em.close();
        }
    }
    
    private static void ajouterTerrain() {
        System.out.println("\n═══ AJOUTER UN TERRAIN ═══");
        
        System.out.print("Nom du terrain: ");
        String nom = scanner.nextLine();
        
        System.out.print("Type (Football, Basketball, Tennis, etc.): ");
        String type = scanner.nextLine();
        
        System.out.print("Capacité: ");
        int capacite = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Localisation: ");
        String localisation = scanner.nextLine();
        
        em = DatabaseManager.createEntityManager();
        transaction = em.getTransaction();
        
        try {
            transaction.begin();
            
            Terrain terrain = new Terrain(nom, type, capacite, localisation);
            em.persist(terrain);
            
            transaction.commit();
            System.out.println("\n✓ Terrain ajouté avec succès ! (ID: " + terrain.getIdTerrain() + ")");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("✗ Erreur lors de l'ajout: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    private static void ajouterClient() {
        System.out.println("\n═══ AJOUTER UN CLIENT ═══");
        
        System.out.print("Nom du client: ");
        String nom = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Téléphone (8 chiffres): ");
        String telephone = scanner.nextLine();
        
        em = DatabaseManager.createEntityManager();
        transaction = em.getTransaction();
        
        try {
            transaction.begin();
            
            Client client = new Client(nom, email, telephone);
            em.persist(client);
            
            transaction.commit();
            System.out.println("\n✓ Client ajouté avec succès ! (ID: " + client.getIdClient() + ")");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("✗ Erreur lors de l'ajout: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    private static void creerReservation() {
        System.out.println("\n═══ CRÉER UNE RÉSERVATION ═══");
        
        // Afficher les clients disponibles
        listerTousLesClients();
        System.out.print("\nID du client: ");
        int idClient = Integer.parseInt(scanner.nextLine());
        
        // Afficher les terrains disponibles
        listerTousLesTerrains();
        System.out.print("\nID du terrain: ");
        int idTerrain = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Date de réservation (AAAA-MM-JJ): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Heure de début (HH:MM): ");
        LocalTime heureDebut = LocalTime.parse(scanner.nextLine());
        
        System.out.print("Heure de fin (HH:MM): ");
        LocalTime heureFin = LocalTime.parse(scanner.nextLine());
        
        em = DatabaseManager.createEntityManager();
        transaction = em.getTransaction();
        
        try {
            transaction.begin();
            
            Client client = em.find(Client.class, idClient);
            Terrain terrain = em.find(Terrain.class, idTerrain);
            
            if (client == null || terrain == null) {
                System.out.println("\n✗ Client ou terrain introuvable.");
                return;
            }
            
            Reservation reservation = new Reservation(date, heureDebut, heureFin, client, terrain);
            em.persist(reservation);
            
            transaction.commit();
            System.out.println("\n✓ Réservation créée avec succès ! (ID: " + reservation.getIdReservation() + ")");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("✗ Erreur lors de la création: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    private static void rechercherReservationsParMois() {
        System.out.println("\n═══ RECHERCHER RÉSERVATIONS PAR MOIS ═══");
        
        System.out.print("Mois (1-12): ");
        int mois = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Année (ex: 2025): ");
        int annee = Integer.parseInt(scanner.nextLine());
        
        em = DatabaseManager.createEntityManager();
        try {
            String jpql = "SELECT r FROM Reservation r WHERE MONTH(r.dateReservation) = :mois " +
                         "AND YEAR(r.dateReservation) = :annee ORDER BY r.dateReservation";
            TypedQuery<Reservation> query = em.createQuery(jpql, Reservation.class);
            query.setParameter("mois", mois);
            query.setParameter("annee", annee);
            
            List<Reservation> reservations = query.getResultList();
            
            if (reservations.isEmpty()) {
                System.out.println("\nAucune réservation trouvée pour cette période.");
            } else {
                System.out.println("\nRéservations trouvées: " + reservations.size());
                for (Reservation r : reservations) {
                    System.out.printf("  - %s | %s-%s | Client: %s | Terrain: %s%n",
                            r.getDateReservation(), r.getHeureDebut(), r.getHeureFin(),
                            r.getClient().getNom(), r.getTerrain().getNom());
                }
            }
        } finally {
            em.close();
        }
    }
    
    private static void afficherStatistiques() {
        System.out.println("\n═══ STATISTIQUES ═══");
        
        em = DatabaseManager.createEntityManager();
        try {
            // Nombre total de terrains
            Long nbTerrains = em.createQuery("SELECT COUNT(t) FROM Terrain t", Long.class)
                    .getSingleResult();
            System.out.println("Nombre de terrains: " + nbTerrains);
            
            // Nombre total de clients
            Long nbClients = em.createQuery("SELECT COUNT(c) FROM Client c", Long.class)
                    .getSingleResult();
            System.out.println("Nombre de clients: " + nbClients);
            
            // Nombre total de réservations
            Long nbReservations = em.createQuery("SELECT COUNT(r) FROM Reservation r", Long.class)
                    .getSingleResult();
            System.out.println("Nombre de réservations: " + nbReservations);
            
            // Terrain le plus réservé
            System.out.println("\nTop 3 des terrains les plus réservés:");
            String jpql = "SELECT t.nom, COUNT(r) as nb FROM Terrain t " +
                         "LEFT JOIN t.reservations r GROUP BY t.idTerrain, t.nom " +
                         "ORDER BY nb DESC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setMaxResults(3);
            List<Object[]> resultats = query.getResultList();
            
            for (int i = 0; i < resultats.size(); i++) {
                Object[] row = resultats.get(i);
                System.out.printf("  %d. %s: %d réservations%n", (i+1), row[0], row[1]);
            }
            
        } finally {
            em.close();
        }
    }
}
