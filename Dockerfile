#Esto se ejecutara cada vez que se haga un push a github

#Descargamos una imagen con Maven y con JDK8
#La imagen con Open JDK 8 105MB pero hay que descargar y configuar Maven
#La imagen con Maven pesa 635MB incluye varios JDK, incluido el 8
#Esta imagen que usamos pesa 387 y viene con lo justo y necesario preintalado por lo que nos facilita el trabajo 
FROM adoptopenjdk/maven-openjdk8
#Copiamos los pom.xml y el src a la imagen para poder generar el jar
#Copiamos los archivos necesarios solo, para evitar un aumento del tamaño innecesario
COPY pom.xml /
COPY Proyecto/src /Proyecto/src
COPY Proyecto/pom.xml /Proyecto
#Ejecutamos el comando para empaquetar nuestro proyecto y generar el jar
RUN mvn clean package
#Para finalizar corremos la aplicación
#se realiza desde el CMD para que se use como comando run del heroku.yml
#Se debe especificar -Dserver.port=$PORT para que heroku encuentre el puerto
#java $JAVA_OPTS es una variable de entorno estandart que usan algunos servidores como, en nuestro caso, el tomcat que usa spring
#Djava.security.egd=file:/dev/./urandom Acelera el arranque del servidor
CMD [ "sh", "-c", "java $JAVA_OPTS -Dserver.port=8080 -Djava.security.egd=file:/dev/./urandom -jar /Proyecto/target/RestauranProject-0.0.1-SNAPSHOT.jar" ]

#Información sacada de https://docs.docker.com/get-started/
#Información sacada de https://docs.docker.com/engine/reference/builder/
#Información sacada de https://devcenter.heroku.com/articles/build-docker-images-heroku-yml
#Información sacada de https://blog.codecentric.de/en/2019/08/spring-boot-heroku-docker-jdk11/