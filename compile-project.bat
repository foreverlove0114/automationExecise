@echo off
echo ====================================
echo Compiling Maven Project...
echo ====================================
cd /d %~dp0

echo.
echo Step 1: Cleaning target directory...
if exist target (
    rmdir /s /q target
    echo Target cleaned successfully!
) else (
    echo Target directory does not exist.
)

echo.
echo Step 2: Compiling test classes...
call mvn clean test-compile -DskipTests

echo.
echo ====================================
echo Compilation Status:
echo ====================================
if %ERRORLEVEL% EQU 0 (
    echo SUCCESS! Project compiled successfully.
) else (
    echo FAILURE! Check compilation errors above.
)
echo.
pause
