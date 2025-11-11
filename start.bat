@echo off
echo ============================================
echo   GESTION TERRAIN - SCRIPT DE DEMARRAGE
echo ============================================
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
mvn compile
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
mvn test
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
mvn exec:java
pause
goto menu

:clean
echo.
echo [NETTOYAGE DU PROJET...]
mvn clean
echo SUCCES: Projet nettoye
pause
goto menu

:package
echo.
echo [CREATION DU JAR...]
mvn package
if %errorlevel% neq 0 (
    echo ERREUR: Package a echoue
) else (
    echo SUCCES: JAR cree dans target/
)
pause
goto menu

:all
echo.
echo [CLEAN + COMPILE + TEST...]
mvn clean compile test
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
