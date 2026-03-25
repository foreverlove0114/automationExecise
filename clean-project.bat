@echo off
echo Cleaning Maven project...
cd /d %~dp0
if exist target (
    echo Deleting target directory...
    rmdir /s /q target
    echo Target directory deleted successfully!
) else (
    echo Target directory does not exist.
)
echo.
echo Now rebuild the project in your IDE or run: mvn clean install
pause
