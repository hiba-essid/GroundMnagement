# Système de Gestion de Terrains de Sport - Web 🌐

Application web moderne avec Spring Boot et interface responsive pour la gestion complète de terrains de sport.

## 🎯 Fonctionnalités

### Interface Web Moderne
- ✅ Design responsive et moderne
- ✅ Navigation par onglets
- ✅ Formulaires modaux
- ✅ API REST complète
- ✅ Notifications en temps réel

### Modules
1. **Gestion des Terrains** - CRUD complet
2. **Gestion des Clients** - Inscription et suivi
3. **Gestion des Réservations** - Planning et disponibilité
4. **Gestion des Paiements** - Suivi financier

## 🛠️ Technologies

### Backend
- **Spring Boot 2.7.18** - Framework Java
- **Spring Data JPA** - ORM
- **MySQL 8** - Base de données
- **Hibernate** - Persistence

### Frontend
- **HTML5** - Structure
- **CSS3** - Design moderne et responsive
- **JavaScript (Vanilla)** - Logique frontend
- **Fetch API** - Appels REST

## 📦 Prérequis

- Java JDK 11+
- MySQL 8.0+
- Maven 3.6+

## ⚙️ Installation et lancement

### 1. Configurer MySQL

Assurez-vous que MySQL est démarré :

**Windows:**
```cmd
net start MySQL80
```

**Linux/Mac:**
```bash
sudo systemctl start mysql
```

### 2. Configuration (optionnel)

Le fichier `application.properties` est déjà configuré avec :
- **Port:** 8080
- **Base de données:** gestionterrain (créée automatiquement)
- **Utilisateur:** root
- **Mot de passe:** root

Pour modifier la configuration, éditez :
```
src/main/resources/application.properties
```

### 3. Lancer l'application

```bash
cd gestionterrain-web
mvn spring-boot:run
```

### 4. Accéder à l'application

Ouvrez votre navigateur et accédez à :
```
http://localhost:8080
```

## 📡 API REST Endpoints

### Terrains
- `GET /api/terrains` - Liste tous les terrains
- `GET /api/terrains/{id}` - Détails d'un terrain
- `POST /api/terrains` - Créer un terrain
- `PUT /api/terrains/{id}` - Modifier un terrain
- `DELETE /api/terrains/{id}` - Supprimer un terrain

### Clients
- `GET /api/clients` - Liste tous les clients
- `GET /api/clients/{id}` - Détails d'un client
- `POST /api/clients` - Créer un client
- `PUT /api/clients/{id}` - Modifier un client
- `DELETE /api/clients/{id}` - Supprimer un client

### Réservations
- `GET /api/reservations` - Liste toutes les réservations
- `GET /api/reservations/{id}` - Détails d'une réservation
- `POST /api/reservations` - Créer une réservation
- `PUT /api/reservations/{id}` - Modifier une réservation
- `DELETE /api/reservations/{id}` - Supprimer une réservation

### Paiements
- `GET /api/paiements` - Liste tous les paiements
- `GET /api/paiements/{id}` - Détails d'un paiement
- `POST /api/paiements` - Créer un paiement
- `PUT /api/paiements/{id}` - Modifier un paiement
- `DELETE /api/paiements/{id}` - Supprimer un paiement

## 📁 Structure du projet

```
gestionterrain-web/
├── src/main/java/com/gestionterrain/
│   ├── controller/           # Controllers REST
│   │   ├── TerrainController.java
│   │   ├── ClientController.java
│   │   ├── ReservationController.java
│   │   ├── PaiementController.java
│   │   └── HomeController.java
│   ├── service/              # Services métier
│   │   ├── TerrainService.java
│   │   ├── ClientService.java
│   │   ├── ReservationService.java
│   │   └── PaiementService.java
│   ├── dao/                  # Accès aux données
│   ├── entity/               # Entités JPA
│   ├── config/               # Configuration
│   └── GestionTerrainApplication.java
├── src/main/resources/
│   ├── static/               # Fichiers statiques
│   │   ├── index.html
│   │   ├── css/style.css
│   │   └── js/app.js
│   ├── META-INF/
│   │   └── persistence.xml
│   └── application.properties
└── pom.xml
```

## 🎨 Captures d'écran

### Interface principale
- Navigation par onglets intuitive
- Tableaux de données avec actions
- Formulaires modaux élégants
- Design moderne et coloré

## 🔧 Développement

### Hot Reload

Spring Boot DevTools est inclus pour le rechargement automatique :
- Modifiez le code Java
- L'application redémarre automatiquement

### Logs

Les logs sont configurés dans `application.properties` :
- Spring Web: INFO
- Hibernate: INFO
- Application: DEBUG

## 🚀 Build Production

### Créer un JAR exécutable

```bash
mvn clean package
```

### Lancer le JAR

```bash
java -jar target/gestion-terrain-web-1.0-SNAPSHOT.jar
```

## 📱 Responsive Design

L'interface s'adapte automatiquement :
- 📱 Mobile (< 768px)
- 💻 Tablette (768px - 1024px)
- 🖥️ Desktop (> 1024px)

## 🔐 Sécurité

Pour un environnement de production :
1. Changez les identifiants MySQL
2. Activez HTTPS
3. Ajoutez Spring Security
4. Configurez CORS correctement

## 🐛 Résolution des problèmes

### Port 8080 déjà utilisé

Changez le port dans `application.properties` :
```properties
server.port=8081
```

### Erreur de connexion MySQL

Vérifiez :
- MySQL est démarré
- Les identifiants sont corrects
- Le firewall n'bloque pas le port 3306

## 📝 Licence

Projet éducatif et de démonstration.

## 👨‍💻 Auteur

MiniMax Agent

---

**Bonne utilisation ! 🎉**
