FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ScrumApp2-0.0.1-SNAPSHOT.jar Scrum-App-2.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Scrum-App-2.jar"]