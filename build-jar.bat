@echo off
setlocal

echo === Building JAR using Maven ===
call mvnw clean package

echo.

:: Find the most recent JAR in the target folder
for /f "delims=" %%F in ('dir /b /o:-d target\*.jar 2^>nul') do (
    set "JAR_NAME=%%F"
    goto :found
)

echo No JAR file was found in the target folder.
goto :eof

:found
echo === Build Complete! ===
echo JAR File: target\%JAR_NAME%
echo Full Path: %CD%\target\%JAR_NAME%

pause
