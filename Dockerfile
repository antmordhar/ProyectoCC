#Desde una imagen con maven
FROM maven AS MAVEN_TOOL_CHAIN
#Copiamos los pom.xml y el src
COPY ./pom.xml /tmp/
COPY ./Proyecto/src /tmp/Proyecto/src
COPY ./Proyecto/pom.xml /tmp/Proyecto
#Vamos a la carpeta tmp
WORKDIR /tmp/
#Ejecutamos el comando para empaquetar nuestro proyecto
RUN mvn clean package

#Creamos la variable de entorno necesaria para el server de Spring Boot
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
#Definimos el puerto que usara el servicio
EXPOSE 8080
#Ejecutamos el jar de nuestro proyecto.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar
