-- Insérer des données de test dans la base gestionterrain

USE gestionterrain;

-- Insertion de clients
INSERT INTO clients (nom, prenom, email, telephone, adresse) VALUES
('Alami', 'Mohammed', 'mohammed.alami@email.com', '0612345678', '123 Rue Hassan II, Casablanca'),
('Bennani', 'Fatima', 'fatima.bennani@email.com', '0623456789', '456 Avenue Mohammed V, Rabat'),
('Chakir', 'Ahmed', 'ahmed.chakir@email.com', '0634567890', '789 Boulevard Zerktouni, Marrakech'),
('Darif', 'Samira', 'samira.darif@email.com', '0645678901', '321 Rue de la Liberté, Fès'),
('El Idrissi', 'Youssef', 'youssef.elidrissi@email.com', '0656789012', '654 Avenue des FAR, Tanger');

-- Insertion de terrains
INSERT INTO terrains (nom, type, tarif_heure, tarifHeure, statut, description) VALUES
('Terrain Central A', 'Football', 150.00, 150.00, 'Disponible', 'Terrain de football gazon synthétique, éclairage nocturne'),
('Terrain Central B', 'Football', 150.00, 150.00, 'Disponible', 'Terrain de football gazon naturel, vestiaires inclus'),
('Court de Tennis 1', 'Tennis', 80.00, 80.00, 'Disponible', 'Court de tennis en terre battue'),
('Court de Tennis 2', 'Tennis', 80.00, 80.00, 'Maintenance', 'Court de tennis dur, en rénovation'),
('Terrain Basket', 'Basketball', 100.00, 100.00, 'Disponible', 'Terrain de basketball couvert'),
('Terrain Volley', 'Volleyball', 90.00, 90.00, 'Disponible', 'Terrain de volleyball avec filet professionnel');

-- Insertion de réservations
INSERT INTO reservations (client_id, terrain_id, date_debut, date_fin, montant_total, statut) VALUES
(1, 1, '2025-11-12 10:00:00', '2025-11-12 12:00:00', 300.00, 'Confirmée'),
(2, 3, '2025-11-12 14:00:00', '2025-11-12 15:00:00', 80.00, 'Confirmée'),
(3, 5, '2025-11-13 09:00:00', '2025-11-13 11:00:00', 200.00, 'En attente'),
(4, 1, '2025-11-13 16:00:00', '2025-11-13 18:00:00', 300.00, 'Confirmée'),
(5, 6, '2025-11-14 10:00:00', '2025-11-14 12:00:00', 180.00, 'Confirmée');

-- Insertion de paiements
INSERT INTO paiements (reservation_id, montant, mode_paiement, date_paiement, statut, reference) VALUES
(1, 300.00, 'Carte bancaire', '2025-11-11 09:00:00', 'Payé', 'PAY-2025-001'),
(2, 80.00, 'Espèces', '2025-11-11 10:30:00', 'Payé', 'PAY-2025-002'),
(4, 300.00, 'Virement', '2025-11-11 11:00:00', 'Payé', 'PAY-2025-003'),
(5, 180.00, 'Carte bancaire', '2025-11-11 13:00:00', 'Payé', 'PAY-2025-004');

SELECT 'Données insérées avec succès!' as Message;
SELECT COUNT(*) as NombreClients FROM clients;
SELECT COUNT(*) as NombreTerrains FROM terrains;
SELECT COUNT(*) as NombreReservations FROM reservations;
SELECT COUNT(*) as NombrePaiements FROM paiements;
