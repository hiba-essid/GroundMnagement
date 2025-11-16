@echo off
cd /d "%~dp0"
echo ============================================
echo   GESTION TERRAIN - SCRIPT DE DEMARRAGE
echo ============================================
echo.

REM Verifier Maven
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo ERREUR: Maven n'est pas installe ou pas dans le PATH
    echo.
    echo Veuillez installer Maven: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Se placer dans le module web
if not exist "gestionterrain-web\gestionterrain-web\pom.xml" (
    echo ERREUR: Fichier pom.xml introuvable
    echo Assurez-vous d'executer ce script depuis la racine du projet
    pause
    exit /b 1
)

cd gestionterrain-web\gestionterrain-web
echo Repertoire de travail: %CD%
echo.

:menu
echo.
echo MENU PRINCIPAL:
echo 1. Compiler le projet
echo 2. Executer les tests
echo 3. Lancer l'application
echo 4. Nettoyer le projet
echo 5. Package (créer le JAR)
echo 6. Tout faire (clean + compile + test)
echo 0. Quitter
echo.
set /p choice="Votre choix: "

if "%choice%"=="1" goto compile
if "%choice%"=="2" goto test
if "%choice%"=="3" goto run
if "%choice%"=="4" goto clean
if "%choice%"=="5" goto package
if "%choice%"=="6" goto all
if "%choice%"=="0" goto end
goto menu

:compile
echo.
echo [COMPILATION EN COURS...]
call mvn compile
if %errorlevel% neq 0 (
    echo ERREUR: La compilation a echoue
) else (
    echo SUCCES: Compilation terminee
)
pause
goto menu

:test
echo.
echo [EXECUTION DES TESTS...]
call mvn test
if %errorlevel% neq 0 (
    echo ERREUR: Les tests ont echoue
) else (
    echo SUCCES: Tous les tests sont passes
)
pause
goto menu

:run
echo.
echo [LANCEMENT DE L'APPLICATION...]
echo Compilation et creation du JAR...
call mvn clean package -DskipTests -q
if %errorlevel% neq 0 (
    echo ERREUR: La compilation a echoue
    pause
    goto menu
)
echo.
echo Lancement de l'application sur le port 8081...
echo Appuyez sur Ctrl+C pour arreter l'application
echo.
start cmd /k "java -jar target\gestion-terrain-web-1.0-SNAPSHOT.jar"
timeout /t 3 >nul
echo.
echo Application lancee dans une nouvelle fenetre
echo Acces: http://localhost:8081
pause
goto menu

:clean
echo.
echo [NETTOYAGE DU PROJET...]
call mvn clean
echo SUCCES: Projet nettoye
pause
goto menu

:package
echo.
echo [CREATION DU JAR...]
call mvn package -DskipTests
if %errorlevel% neq 0 (
    echo ERREUR: Package a echoue
) else (
    echo SUCCES: JAR cree dans target\gestion-terrain-web-1.0-SNAPSHOT.jar
    echo.
    echo Pour lancer: java -jar target\gestion-terrain-web-1.0-SNAPSHOT.jar
)
pause
goto menu

:all
echo.
echo [CLEAN + COMPILE + TEST...]
call mvn clean compile test
if %errorlevel% neq 0 (
    echo ERREUR: Processus a echoue
) else (
    echo SUCCES: Projet compile et teste
)
pause
goto menu

:end
echo.
echo Au revoir!
pause
exit
