#!/bin/bash

echo "==============================================="
echo "  Lancement Application Web - Gestion Terrains"
echo "==============================================="
echo ""

# Vérifier si MySQL est démarré
echo "Vérification de MySQL..."
if ! pgrep -x mysqld > /dev/null; then
    echo "MySQL n'est pas démarré. Tentative de démarrage..."
    sudo systemctl start mysql || sudo service mysql start
    if [ $? -ne 0 ]; then
        echo "ERREUR: Impossible de démarrer MySQL!"
        echo "Veuillez démarrer MySQL manuellement."
        exit 1
    fi
else
    echo "MySQL est déjà démarré."
fi

echo ""
echo "Lancement de l'application Spring Boot..."
echo "L'application sera accessible sur: http://localhost:8080"
echo ""

mvn spring-boot:run
