@echo off
echo Starting Banking Microservices...

echo 1. Starting PostgreSQL...
docker-compose up -d postgres
timeout /t 10

echo 2. Starting Discovery Service...
start cmd /k "cd discovery-service && mvn spring-boot:run"
timeout /t 15

echo 3. Starting Config Service...
start cmd /k "cd config-service && mvn spring-boot:run"
timeout /t 10

echo 4. Starting Gateway Service...
start cmd /k "cd gateway-service && mvn spring-boot:run"
timeout /t 10

echo 5. Starting Beneficiaire Service...
start cmd /k "cd beneficiaire-service && mvn spring-boot:run"
timeout /t 5

echo 6. Starting Virement Service...
start cmd /k "cd virement-service && mvn spring-boot:run"
timeout /t 5

echo 7. Starting Chatbot Service...
start cmd /k "cd chatbot-service && mvn spring-boot:run"

echo.
echo All services started!
echo.
echo Access URLs:
echo - Eureka: http://localhost:8761
echo - Gateway: http://localhost:8080
echo - Beneficiaire API: http://localhost:8081
echo - Virement API: http://localhost:8082
echo - Chatbot API: http://localhost:8083
pause
