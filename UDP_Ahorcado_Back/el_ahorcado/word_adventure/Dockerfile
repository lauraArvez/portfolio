# imagen base de Java
FROM openjdk:17-jdk-slim

# Crea un directorio en el contenedor para la aplicación
WORKDIR /usr/src/app

# Copia el archivo JAR de la aplicación en el contenedor
COPY target/word_adventure-0.0.1-SNAPSHOT.jar ./app.jar

# Expone el puerto en el que tu aplicación UDP va a funcionar
# UDP típicamente usa puertos entre 1024-65535, ajústalo al puerto que uses
EXPOSE 5432/udp

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]