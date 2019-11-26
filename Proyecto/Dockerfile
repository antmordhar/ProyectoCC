#Desde una imagen con OpenJdk8
FROM openjdk:8-jdk-alpine
#Creamos la variable de entorno necesaria para el server de Spring Boot
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
#Definimos el puerto que usara el servicio
EXPOSE 8080
#Ejecutamos el jar de nuestro proyecto.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar
