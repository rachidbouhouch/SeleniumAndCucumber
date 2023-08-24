FROM adoptopenjdk/openjdk11:latest
COPY ./target/ExercicesTestAutomation-1.0-SNAPSHOT.jar ExercicesTestAutomation-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "demo1-0.0.1-SNAPSHOT.jar"]
