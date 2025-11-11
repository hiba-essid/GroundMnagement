# 🏟️ Système de Gestion de Terrains de Sport

Application web de gestion de terrains de sport développée avec Spring Boot et MySQL.

## 📋 Fonctionnalités

- **Gestion des clients** : Ajouter, modifier, supprimer et consulter les clients
- **Gestion des terrains** : Gérer différents types de terrains (Football, Tennis, Basketball, Volleyball)
- **Gestion des réservations** : Créer et suivre les réservations de terrains
- **Gestion des paiements** : Enregistrer et gérer les paiements des réservations
- **Interface web moderne** : Interface utilisateur intuitive et responsive

## 🛠️ Technologies Utilisées

- **Backend** :
  - Java 25
  - Spring Boot 2.7.18
  - Spring Data JPA
  - Hibernate 5.6.15.Final
  - MySQL 8

- **Frontend** :
  - HTML5
  - CSS3
  - JavaScript (Vanilla)

- **Build** :
  - Maven 3.x

## 📦 Prérequis

- Java JDK 11 ou supérieur
- MySQL 8.0 ou supérieur
- Maven 3.x

## 🚀 Installation et Démarrage

### 1. Cloner le dépôt
```bash
git clone <votre-repo-url>
cd gestion-terrain
```

### 2. Créer la base de données
```bash
mysql -u root -p < scripts\create_database.sql
```

### 3. Insérer des données de test (optionnel)
```bash
mysql -u root -p < scripts\insert_test_data.sql
```

### 4. Configurer l'application
Modifier `gestionterrain-web/gestionterrain-web/src/main/resources/application.properties` si nécessaire :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestionterrain
spring.datasource.username=root
spring.datasource.password=root
```

### 5. Compiler et lancer l'application
```bash
cd gestionterrain-web\gestionterrain-web
mvn clean package
java -jar target\gestion-terrain-web-1.0-SNAPSHOT.jar
```

Ou utilisez le script batch :
```bash
run.bat
```

### 6. Accéder à l'application
Ouvrez votre navigateur sur : **http://localhost:8081**

## 📁 Structure du Projet

```
gestion-terrain/
├── gestionterrain-web/          # Module web Spring Boot
│   └── gestionterrain-web/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/gestionterrain/
│       │   │   │       ├── config/         # Configuration Spring
│       │   │   │       ├── controller/     # REST Controllers
│       │   │   │       ├── dao/            # Repositories Spring Data JPA
│       │   │   │       ├── entity/         # Entités JPA
│       │   │   │       └── service/        # Services métier
│       │   │   └── resources/
│       │   │       ├── application.properties
│       │   │       └── static/            # Frontend (HTML/CSS/JS)
│       │   └── test/
│       └── pom.xml
├── scripts/                      # Scripts SQL
│   ├── create_database.sql
│   └── insert_test_data.sql
└── README.md
```

## 🔌 API REST Endpoints

### Clients
- `GET /api/clients` - Liste tous les clients
- `GET /api/clients/{id}` - Récupère un client par ID
- `POST /api/clients` - Crée un nouveau client
- `PUT /api/clients/{id}` - Met à jour un client
- `DELETE /api/clients/{id}` - Supprime un client

### Terrains
- `GET /api/terrains` - Liste tous les terrains
- `GET /api/terrains/{id}` - Récupère un terrain par ID
- `POST /api/terrains` - Crée un nouveau terrain
- `PUT /api/terrains/{id}` - Met à jour un terrain
- `DELETE /api/terrains/{id}` - Supprime un terrain

### Réservations
- `GET /api/reservations` - Liste toutes les réservations
- `GET /api/reservations/{id}` - Récupère une réservation par ID
- `POST /api/reservations` - Crée une nouvelle réservation
- `PUT /api/reservations/{id}` - Met à jour une réservation
- `DELETE /api/reservations/{id}` - Supprime une réservation

### Paiements
- `GET /api/paiements` - Liste tous les paiements
- `GET /api/paiements/{id}` - Récupère un paiement par ID
- `POST /api/paiements` - Crée un nouveau paiement
- `PUT /api/paiements/{id}` - Met à jour un paiement
- `DELETE /api/paiements/{id}` - Supprime un paiement

## 🗄️ Modèle de Données

### Client
- `id` : Identifiant unique
- `nom` : Nom du client
- `prenom` : Prénom du client
- `email` : Adresse email (unique)
- `telephone` : Numéro de téléphone
- `adresse` : Adresse postale

### Terrain
- `id` : Identifiant unique
- `nom` : Nom du terrain
- `type` : Type (Football, Basketball, Tennis, Volleyball)
- `tarifHeure` : Tarif par heure en MAD
- `statut` : Statut (Disponible, Occupé, Maintenance)
- `description` : Description détaillée

### Reservation
- `id` : Identifiant unique
- `client` : Référence au client
- `terrain` : Référence au terrain
- `dateDebut` : Date et heure de début
- `dateFin` : Date et heure de fin
- `montantTotal` : Montant total de la réservation
- `statut` : Statut (Confirmée, En attente, Annulée)

### Paiement
- `id` : Identifiant unique
- `reservation` : Référence à la réservation
- `montant` : Montant du paiement
- `modePaiement` : Mode (Espèces, Carte bancaire, Virement)
- `datePaiement` : Date du paiement
- `statut` : Statut (Payé, En attente, Remboursé)
- `reference` : Référence du paiement

## 👥 Auteurs

Développé avec ❤️ pour la gestion de terrains de sport

## 📄 Licence

Ce projet est sous licence MIT.
