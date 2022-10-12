FROM maven:3.8-openjdk-17 as maven
WORKDIR /app
COPY . /app
RUN mvn install

FROM openjdk:17.0.2-jdk
WORKDIR /app
COPY --from=maven /app/target/job4j_passport-0.0.1-SNAPSHOT-exec.jar app.jar
CMD java -jar app.jar