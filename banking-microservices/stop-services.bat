@echo off
echo Stopping all services...

taskkill /F /IM java.exe >nul 2>&1
docker-compose down

echo All services stopped.
pause
