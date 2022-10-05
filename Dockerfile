FROM openjdk
WORKDIR shortcut
ADD target/job4j_passport-0.0.1-SNAPSHOT-exec.jar app.jar
ENTRYPOINT java -jar app.jar