#Desde una imagen con maven
FROM maven AS MAVEN_TOOL_CHAIN
#Copiamos El pom.xml y el src
COPY pom.xml /tmp/
COPY src /tmp/src/
#Vamos a la carpeta tmp
WORKDIR /tmp/
#Ejecutamos el comando para empaquetar nuestro proyecto
RUN mvn clean package

#Desde una imagen con OpenJdk8
FROM openjdk:8-jdk-alpine
#Creamos la variable de entorno necesaria para el server de Spring Boot
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
#Definimos el puerto que usara el servicio
EXPOSE 8080
#Ejecutamos el jar de nuestro proyecto.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/target/RestauranProject-0.0.1-SNAPSHOT.jar
