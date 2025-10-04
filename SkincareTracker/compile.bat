@echo off
echo Compiling Skincare Tracker Java Application...
echo.

REM Create output directory
if not exist "out" mkdir out

REM Compile all Java files
javac -d out -sourcepath src/main/java src/main/java/com/skincare/SkincareTrackerApp.java

if %errorlevel% == 0 (
    echo ✅ Compilation successful!
    echo.
    echo To run the application, use:
    echo java -cp out com.skincare.SkincareTrackerApp
    echo.
    echo Or simply run: run.bat
) else (
    echo ❌ Compilation failed!
    echo Please check that Java JDK is installed and in your PATH.
)

pause
