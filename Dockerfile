#Desde una imagen con maven
FROM adoptopenjdk/maven-openjdk8
#Copiamos los pom.xml y el src
COPY ./pom.xml /tmp/
COPY ./Proyecto/src /tmp/Proyecto/src
COPY ./Proyecto/pom.xml /tmp/Proyecto

WORKDIR /tmp/
#Ejecutamos el comando para empaquetar nuestro proyecto
RUN mvn clean package

RUN ls -l ./Proyecto/target/
COPY /tmp/Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
#Ejecutamos el jar de nuestro proyecto.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]