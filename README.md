Микросервис для проверки паспортов. 
В качестве брокера сообщений используется Kafka. 
Стэк: Java 8+, Spring Boot, Liquibase, PostgreSQL,Lombok, Kafka, Docker.

Приложение может быть развернуто с помощью docker compose.
Для этого:
1. предварительно неоходимо локально собрать проект с помощью:
mvn install.
2. собрать docker контейнер с помощью команды:
docker build -t passports .
3. и запустить docker-compose командо:
docker-compose up

![Screenshot](images/passport_1.PNG)
![Screenshot](images/passport_2.PNG)
