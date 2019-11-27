#Desde una imagen con maven
FROM adoptopenjdk/maven-openjdk8
#Copiamos los pom.xml y el src
COPY ./pom.xml /
COPY ./Proyecto/src /Proyecto/src
COPY ./Proyecto/pom.xml /Proyecto
RUN ls -l ./Proyecto/
RUN ls -l ./
#Ejecutamos el comando para empaquetar nuestro proyecto
RUN mvn clean package
#VOLUME /tmp

RUN ls -l ./Proyecto/target/
COPY ./Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
#Ejecutamos el jar de nuestro proyecto.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]