# 🏟️ Système de Gestion de Terrains de Sport

## 📋 Description du Projet

Application JEE pour la gestion de réservations de terrains de sport. Le système permet de gérer les terrains, les clients, les réservations et les paiements avec une base de données MySQL et JPA/Hibernate.

## 🎯 Fonctionnalités Principales

### Gestion des Entités
- **Terrains** : Ajout, consultation, statistiques
- **Clients** : Inscription, gestion des profils
- **Réservations** : Création, consultation, recherche
- **Paiements** : Enregistrement et suivi

### 7 Opérations Principales (Tests Automatisés)
1. ✅ **Afficher les clients** ayant réservé des terrains de type "Football"
2. ✅ **Modifier le mode de paiement** d'une réservation
3. ✅ **Lister les réservations** d'un mois spécifique
4. ✅ **Afficher les statistiques** de réservations par terrain
5. ✅ **Supprimer les anciennes réservations** (avant une date donnée)
6. ✅ **Identifier les terrains jamais réservés**
7. ✅ **Calculer le montant total** des paiements par client

## 🛠️ Technologies Utilisées

- **Java 11** - Langage de programmation
- **Maven 3.9+** - Gestion des dépendances
- **JPA 2.2** - Persistence API
- **Hibernate 5.6.15** - ORM Framework
- **MySQL 8.0** - Base de données
- **JUnit 5.9.2** - Tests unitaires
- **Log4j2 2.20.0** - Logging

## 📁 Structure du Projet

```
gestion-terrain/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/gestionterrain/
│   │   │       ├── dao/
│   │   │       │   └── DatabaseManager.java
│   │   │       ├── entity/
│   │   │       │   ├── Client.java
│   │   │       │   ├── Paiement.java
│   │   │       │   ├── Reservation.java
│   │   │       │   └── Terrain.java
│   │   │       └── Main.java
│   │   └── resources/
│   │       ├── META-INF/
│   │       │   └── persistence.xml
│   │       └── log4j2.xml
│   └── test/
│       └── java/
│           └── com/gestionterrain/
│               └── GestionTerrainTest.java
├── pom.xml
└── README_COMPLET.md
```

## 🚀 Installation et Configuration

### Prérequis
- Java 11 ou supérieur
- Maven 3.6+
- MySQL 8.0+
- Git (optionnel)

### Étape 1: Configuration de MySQL

```sql
-- Démarrer le service MySQL
-- Créer la base de données
CREATE DATABASE gestionterrain;

-- Vérifier la création
SHOW DATABASES;
```

### Étape 2: Configuration de l'Application

Modifiez le fichier `src/main/resources/META-INF/persistence.xml` :

```xml
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="VOTRE_MOT_DE_PASSE"/>
```

### Étape 3: Compilation

```bash
cd gestion-terrain
mvn clean compile
```

## 🎮 Utilisation

### Exécuter les Tests

```bash
# Tous les tests
mvn test

# Test spécifique
mvn test -Dtest=GestionTerrainTest#testOperation1
```

### Lancer l'Application Console

```bash
# Méthode 1: Via Maven
mvn exec:java -Dexec.mainClass="com.gestionterrain.Main"

# Méthode 2: Via Maven exec plugin
mvn exec:java

# Méthode 3: Compiler et exécuter
mvn clean package
java -cp target/gestion-terrain-1.0.0.jar com.gestionterrain.Main
```

### Menu de l'Application

```
╔══════════════════════ MENU PRINCIPAL ═══════════════════╗
║  1. Lister tous les terrains                           ║
║  2. Lister tous les clients                            ║
║  3. Lister toutes les réservations                     ║
║  4. Ajouter un terrain                                 ║
║  5. Ajouter un client                                  ║
║  6. Créer une réservation                              ║
║  7. Rechercher réservations par mois                   ║
║  8. Afficher les statistiques                          ║
║  0. Quitter                                            ║
╚═════════════════════════════════════════════════════════╝
```

## 📊 Modèle de Données

### Diagramme des Relations

```
Client (1) ----< (N) Reservation (N) >---- (1) Terrain
                        |
                        | (1)
                        |
                        v
                        | (N)
                    Paiement
```

### Entités

#### Terrain
- `id_terrain` (PK, AUTO_INCREMENT)
- `nom` (VARCHAR 50, NOT NULL)
- `type` (VARCHAR 50, NOT NULL)
- `capacite` (INT, NOT NULL)
- `localisation` (VARCHAR 100, NOT NULL)

#### Client
- `id_client` (PK, AUTO_INCREMENT)
- `nom` (VARCHAR 50, NOT NULL)
- `email` (VARCHAR 50, UNIQUE, NOT NULL)
- `telephone` (VARCHAR 8, NOT NULL)
- `date_inscription` (DATE, NOT NULL)

#### Reservation
- `id_reservation` (PK, AUTO_INCREMENT)
- `date_reservation` (DATE, NOT NULL)
- `heure_debut` (TIME, NOT NULL)
- `heure_fin` (TIME, NOT NULL)
- `id_client` (FK → Client)
- `id_terrain` (FK → Terrain)

#### Paiement
- `id_paiement` (PK, AUTO_INCREMENT)
- `montant` (DECIMAL 10,2, NOT NULL)
- `date_paiement` (DATE, NOT NULL)
- `mode_paiement` (VARCHAR 50, NOT NULL)
- `id_reservation` (FK → Reservation)

## 🧪 Tests

### Résultats des Tests

```
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Couverture des Tests

- ✅ CRUD sur toutes les entités
- ✅ Relations JPA (OneToMany, ManyToOne)
- ✅ Requêtes JPQL complexes
- ✅ Transactions et rollback
- ✅ Nettoyage automatique de la base

## 📝 Commandes Maven Utiles

```bash
# Nettoyer le projet
mvn clean

# Compiler
mvn compile

# Tester
mvn test

# Empaqueter (JAR)
mvn package

# Installer dans le dépôt local
mvn install

# Afficher l'arbre des dépendances
mvn dependency:tree

# Vérifier les mises à jour
mvn versions:display-dependency-updates
```

## 🔧 Résolution des Problèmes

### Erreur: MySQL Connection Failed

```bash
# Vérifier le service MySQL
net start mysql80

# Tester la connexion
mysql -u root -p
```

### Erreur: Duplicate Entry

Les données de test sont insérées à chaque exécution. Le nettoyage automatique de la base de données est maintenant géré par la méthode `cleanDatabase()`.

### Erreur: Maven Command Not Found

```bash
# Ajouter Maven au PATH
# Windows: Paramètres → Variables d'environnement
# Ajouter: C:\Program Files\apache-maven-X.X.X\bin
```

## 📈 Évolutions Futures

- [ ] Interface graphique (JavaFX ou Web)
- [ ] API REST
- [ ] Système de notifications
- [ ] Rapports PDF
- [ ] Système de facturation
- [ ] Authentification et autorisation
- [ ] Dashboard avec graphiques

## 👥 Contribution

Ce projet a été développé dans le cadre de l'examen de Programmation JEE 2025-2026.

## 📄 Licence

Projet académique - Tous droits réservés

## 📞 Support

Pour toute question ou problème:
1. Vérifiez la documentation
2. Consultez les logs dans la console
3. Examinez les messages d'erreur MySQL

---

**Version:** 1.0.0  
**Date:** Novembre 2025  
**Statut:** ✅ Production Ready
