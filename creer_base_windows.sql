-- Script SQL simplifie pour Windows
-- Ecole Nationale d'Ingenieurs de Tunis
-- Gestion Terrain - Examen JEE 2025-2026

-- 1. Creer la base de donnees
CREATE DATABASE IF NOT EXISTS gestionterrain;
USE gestionterrain;

-- 2. Verifier que la base existe
SHOW DATABASES;

-- 3. Afficher les tables (doit etre vide pour l'instant)
SHOW TABLES;

-- 4. Message de confirmation
SELECT 'Base de donnees gestionterrain prete!' as message;
SELECT 'Vous pouvez maintenant executer les tests Maven.' as next_step;