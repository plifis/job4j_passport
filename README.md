����������� ��� �������� ���������. 
� �������� ������� ��������� ������������ Kafka. 
����: Java 8+, Spring Boot, Liquibase, PostgreSQL,Lombok, Kafka, Docker, Kubernetes.

���������� ����� ���� ���������� � ������� docker compose.
��� �����:
1. �������������� ��������� �������� ������� ������ � �������:
mvn install.
2. ������� docker ��������� � ������� �������:
docker build -t passports .
3. � ��������� docker-compose �������:
docker-compose up

![Screenshot](images/passport_1.png)
![Screenshot](images/passport_2.png)

��� �� ���������� ����� ���� ���������� � Kubernetes.
��� �����:
1. ���������� Secret �� ����� postgresdb-secret.yml (������� ������� ������ ��� ����������� � ��) ��������: kubectl apply -f postgresdb-secret.yml
2. ���������� ConfigMap �� ����� postgresdb-configmap.yml (����� ��� ����������� � �� � �������� ��) ��������: kubectl apply -f postgresdb-configmap.yml
3. ������� Deployment ��� �� �� ����� postgresdb-deployment.yml (�������� ���������� � ������ ��� ������ � ��) ��������: kubectl apply -f postgresdb-deployment.yml
4. ������� Deployment ��� ������ ���������� �� ����� passports-deployment.yml ��������: kubectl apply -f passports-deployment.yml
5. ��������� ������ ������ ���������� � ������ ����� ����������� ����� � ������� �������: minikube service passport-service
