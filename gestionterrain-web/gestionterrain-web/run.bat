@echo off
setlocal enabledelayedexpansion

echo ===============================================
echo   Lancement Application Web - Gestion Terrains
echo ===============================================
echo.

REM --- Verify Java is available ---
echo [1/4] Verification de Java...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERREUR: Java n'est pas trouve sur le PATH.
    echo Installez un JDK 11+ et configurez JAVA_HOME et PATH.
    pause
    exit /b 1
)
echo     Java OK

REM --- Verify Maven is available ---
echo [2/4] Verification de Maven...
where mvn >nul 2>&1
if errorlevel 1 (
    echo ERREUR: Maven n'est pas trouve sur le PATH.
    echo Installez Maven et ajoutez-le au PATH.
    pause
    exit /b 1
)
echo     Maven OK

REM --- Check MySQL service ---
echo [3/4] Verification de MySQL...
set MYSQL_OK=0

sc query MySQL80 2>nul | find "RUNNING" >nul
if not errorlevel 1 (
    echo     MySQL80 est deja demarre.
    set MYSQL_OK=1
    goto mysql_done
)

sc query MySQL 2>nul | find "RUNNING" >nul
if not errorlevel 1 (
    echo     MySQL est deja demarre.
    set MYSQL_OK=1
    goto mysql_done
)

echo     MySQL n'est pas demarre. Tentative de demarrage...
net start MySQL80 >nul 2>&1
if not errorlevel 1 (
    echo     MySQL80 demarre avec succes.
    set MYSQL_OK=1
    goto mysql_done
)

net start MySQL >nul 2>&1
if not errorlevel 1 (
    echo     MySQL demarre avec succes.
    set MYSQL_OK=1
    goto mysql_done
)

echo     ATTENTION: Impossible de demarrer MySQL.
echo     L'application pourrait ne pas fonctionner correctement.
echo.

:mysql_done

REM --- Build and run ---
echo [4/4] Compilation et lancement de l'application...
echo.
echo     L'application sera accessible sur: http://localhost:8081
echo     Appuyez sur Ctrl+C pour arreter l'application
echo.

cd /d "%~dp0"
mvn spring-boot:run

endlocal
pause
