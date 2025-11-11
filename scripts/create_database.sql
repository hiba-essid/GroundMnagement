-- Script de création de la base de données pour l'application Gestion Terrain
-- Ecole Nationale d'Ingénieurs de Tunis
-- Année Universitaire: 2025-2026
-- Module: Programmation JEE

-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestionterrain;
USE gestionterrain;

-- Suppression des tables si elles existent (ordre important pour les clés étrangères)
DROP TABLE IF EXISTS paiement;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS terrain;

-- Table terrain
CREATE TABLE terrain (
    id_terrain INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    capacite INT NOT NULL,
    localisation VARCHAR(100) NOT NULL
);

-- Table client
CREATE TABLE client (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    telephone VARCHAR(8) NOT NULL,
    date_inscription DATE NOT NULL
);

-- Table reservation
CREATE TABLE reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT NOT NULL,
    id_terrain INT NOT NULL,
    date_reservation DATE NOT NULL,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL,
    FOREIGN KEY (id_client) REFERENCES client(id_client) ON DELETE CASCADE,
    FOREIGN KEY (id_terrain) REFERENCES terrain(id_terrain) ON DELETE CASCADE
);

-- Table paiement
CREATE TABLE paiement (
    id_paiement INT AUTO_INCREMENT PRIMARY KEY,
    id_reservation INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    date_paiement DATE NOT NULL,
    mode_paiement VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation) ON DELETE CASCADE
);

-- Insertion des données de test
-- Terrains
INSERT INTO terrain (nom, type, capacite, localisation) VALUES
('Terrain Principal', 'Football', 50, 'Stade Municipal'),
('Terrain A', 'Basketball', 30, 'Centre Sportif'),
('Terrain B', 'Football', 40, 'Complexe Sportif'),
('Court Tennis', 'Tennis', 4, 'Club de Tennis');

-- Clients
INSERT INTO client (nom, email, telephone, date_inscription) VALUES
('Ahmed Ben Ali', 'ahmed.benali@email.com', '12345678', '2024-01-15'),
('Fatma Trigui', 'fatma.trigui@email.com', '23456789', '2024-02-20'),
('Mohamed Sassi', 'mohamed.sassi@email.com', '34567890', '2024-03-10'),
('Leila Khemiri', 'leila.khemiri@email.com', '45678901', '2023-12-05'),
('Sami Bouaziz', 'sami.bouaziz@email.com', '56789012', '2025-01-30');

-- Réservations
INSERT INTO reservation (id_client, id_terrain, date_reservation, heure_debut, heure_fin) VALUES
(1, 1, '2025-03-15', '14:00:00', '16:00:00'),  -- ID 1 (sera modifiée en 202)
(2, 2, '2025-03-20', '10:00:00', '12:00:00'),
(3, 1, '2025-03-25', '16:00:00', '18:00:00'),
(4, 3, '2024-05-10', '09:00:00', '11:00:00'),
(5, 4, '2022-12-15', '15:00:00', '17:00:00'),  -- À supprimer (avant 2023)
(1, 2, '2022-11-20', '08:00:00', '10:00:00'),  -- À supprimer (avant 2023)
(2, 1, '2025-04-10', '13:00:00', '15:00:00'),
(3, 3, '2025-03-28', '19:00:00', '21:00:00');

-- Paiements
INSERT INTO paiement (id_reservation, montant, date_paiement, mode_paiement) VALUES
(1, 50.00, '2025-03-15', 'especes'),     -- Réservation qui sera modifiée en "virement"
(2, 30.00, '2025-03-20', 'carte'),
(3, 50.00, '2025-03-25', 'carte'),
(4, 40.00, '2024-05-10', 'especes'),
(5, 25.00, '2022-12-15', 'virement'),
(6, 30.00, '2022-11-20', 'carte'),
(7, 50.00, '2025-04-10', 'especes'),
(8, 40.00, '2025-03-28', 'virement');

-- Mise à jour de l'ID de réservation pour le test (simulation de l'ID 202)
-- Dans un environnement réel, on utiliserait une séquence ou on modifierait l'AUTO_INCREMENT
UPDATE reservation SET id_reservation = 202 WHERE id_reservation = 1;

-- Requêtes de test pour vérifier les données
-- 1. Vérifier tous les terrains
SELECT 'TERRAINS:' as info;
SELECT * FROM terrain;

-- 2. Vérifier tous les clients
SELECT 'CLIENTS:' as info;
SELECT * FROM client ORDER BY date_inscription DESC;

-- 3. Vérifier toutes les réservations
SELECT 'RESERVATIONS:' as info;
SELECT r.id_reservation, c.nom as client, t.nom as terrain, r.date_reservation, 
       r.heure_debut, r.heure_fin 
FROM reservation r 
JOIN client c ON r.id_client = c.id_client 
JOIN terrain t ON r.id_terrain = t.id_terrain 
ORDER BY r.date_reservation;

-- 4. Vérifier tous les paiements
SELECT 'PAIEMENTS:' as info;
SELECT p.id_paiement, r.id_reservation, p.montant, p.date_paiement, p.mode_paiement
FROM paiement p 
JOIN reservation r ON p.id_reservation = r.id_reservation
ORDER BY p.date_paiement;