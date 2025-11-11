@echo off
echo ================================
echo  VERIFICATION GESTION TERRAIN
echo  Windows 10/11 - Examen JEE
echo ================================
echo.

echo [1/5] Verification de Java...
java -version
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Java n'est pas installe ou non accessible
    echo Telechargez Java 11+ depuis: https://www.oracle.com/java/technologies/
    pause
    exit /b 1
) else (
    echo ✅ Java detected
)
echo.

echo [2/5] Verification de Maven...
mvn -version
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven n'est pas installe ou non accessible
    echo Telechargez Maven depuis: https://maven.apache.org/download.cgi
    pause
    exit /b 1
) else (
    echo ✅ Maven detected
)
echo.

echo [3/5] Verification de MySQL...
echo Tentative de connexion MySQL...
mysql -u root -p -e "SHOW DATABASES;" 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ MySQL non accessible ou mot de passe incorrect
    echo Assurez-vous que MySQL est demarre et que le mot de passe est correct
    echo Vous pouvez modifier le mot de passe dans persistence.xml
) else (
    echo ✅ MySQL accessible
)
echo.

echo [4/5] Verification du projet Maven...
if exist "pom.xml" (
    echo ✅ Fichier pom.xml trouve
) else (
    echo ❌ Fichier pom.xml introuvable
    echo Assurez-vous d'etre dans le repertoire gestion-terrain
)
echo.

echo [5/5] Test de compilation Maven...
echo Execution: mvn clean compile
mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ❌ Compilation echouee
    echo Verifiez les dependances et la configuration
) else (
    echo.
    echo ✅ Compilation reussie
)
echo.

echo ================================
echo  VERIFICATION TERMINEE
echo ================================
echo.
echo Prochaines etapes:
echo 1. Configurez le mot de passe MySQL dans persistence.xml
echo 2. Creer la base de donnees: CREATE DATABASE gestionterrain;
echo 3. Executer les tests: mvn test
echo.
pause