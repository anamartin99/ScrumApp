# Utilizar la imagen oficial de OpenJDK 21
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/ScrumApp2-0.0.1-SNAPSHOT.jar Scrum-App-2.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "Scrum-App-2.jar"]