@echo off
echo Starting Skincare Tracker Application...
echo.

REM Check if compiled classes exist
if not exist "out\com\skincare\SkincareTrackerApp.class" (
    echo ❌ Application not compiled yet!
    echo Please run compile.bat first.
    pause
    exit /b 1
)

REM Run the application
java -cp out com.skincare.SkincareTrackerApp

echo.
echo Application ended. Thank you for using Skincare Tracker!
pause
