@echo off
echo ========================================
echo Starting Full Stack Bank Management System
echo ========================================
echo.
echo Starting Backend Server...
start "Backend Server" cmd /k "cd backend && mvn spring-boot:run"
timeout /t 5 /nobreak > nul
echo.
echo Starting Frontend...
start "Frontend" cmd /k "cd frontend && npm install && npm start"
echo.
echo ========================================
echo Both servers are starting!
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo ========================================
pause
