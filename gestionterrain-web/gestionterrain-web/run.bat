@echo off
setlocal enabledelayedexpansion
echo [DEBUG] Mode debug active
echo ===============================================
echo   Lancement Application Web - Gestion Terrains
echo ===============================================
echo.

echo [DEBUG] STEP 1: Verify Java is available
REM --- Verify Java is available ---
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERREUR: Java n'est pas trouve sur le PATH.
    echo Installez un JDK (11+) et configurez JAVA_HOME et PATH.
    pause
    exit /b 1
)
echo Java OK
echo [DEBUG] STEP 1 done

echo [DEBUG] STEP 2: Verify Maven is available
REM --- Verify Maven is available ---
mvn -v >nul 2>&1
if %errorlevel% neq 0 (
    echo ERREUR: Maven n'est pas trouve sur le PATH.
    echo Installez Maven et ajoutez-le au PATH.
    pause
    exit /b 1
)
echo Maven OK
echo [DEBUG] STEP 2 done

echo [DEBUG] STEP 3: Detect MySQL service
REM --- Check common MySQL service names and whether running ---
set MYSQL_SERVICE_FOUND=0
sc query MySQL80 | find "RUNNING" >nul 2>&1
if not errorlevel 1 (
    set MYSQL_SERVICE=MySQL80
    set MYSQL_SERVICE_FOUND=1
)
if "%MYSQL_SERVICE_FOUND%"=="0" (
    sc query MySQL | find "RUNNING" >nul 2>&1
    if not errorlevel 1 (
        set MYSQL_SERVICE=MySQL
        set MYSQL_SERVICE_FOUND=1
    )
)
if "%MYSQL_SERVICE_FOUND%"=="0" (
    sc query mysql | find "RUNNING" >nul 2>&1
    if not errorlevel 1 (
        set MYSQL_SERVICE=mysql
        set MYSQL_SERVICE_FOUND=1
    )
)
if "%MYSQL_SERVICE_FOUND%"=="0" (
    sc query mysqld | find "RUNNING" >nul 2>&1
    if not errorlevel 1 (
        set MYSQL_SERVICE=mysqld
        set MYSQL_SERVICE_FOUND=1
    )
)

if "%MYSQL_SERVICE_FOUND%"=="1" (
    echo MySQL est deja demarre (service: %MYSQL_SERVICE%).
) else (
    echo MySQL ne semble pas demarre. Tentative de demarrage automatique (MySQL80, puis MySQL)...
    net start MySQL80 >nul 2>&1
    if not errorlevel 1 (
        set MYSQL_SERVICE=MySQL80
        set MYSQL_SERVICE_FOUND=1
    ) else (
        net start MySQL >nul 2>&1
        if not errorlevel 1 (
            set MYSQL_SERVICE=MySQL
            set MYSQL_SERVICE_FOUND=1
        )
    )
    if not "%MYSQL_SERVICE_FOUND%"=="1" (
        echo AVERTISSEMENT: Impossible de demarrer MySQL automatiquement.
        echo Si vous n'utilisez pas MySQL, vous pouvez continuer et lancer l'application en passant des proprietes de datasource.
        echo Appuyez sur C pour continuer sans demarrer MySQL, ou sur une autre touche pour annuler.
        choice /c C /n /m "Continue (C) or Abort?"
        if errorlevel 2 (
            echo Annulation.
            pause
            exit /b 1
        )
    ) else (
        echo MySQL demarre (service: %MYSQL_SERVICE%).
    )

echo [DEBUG] STEP 3 done
)

echo.
echo Lancement de l'application Spring Boot...
echo L'application sera accessible sur: http://localhost:8080
echo.

mvn spring-boot:run

endlocal
pause
