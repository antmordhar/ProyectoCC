#Desde una imagen con maven
FROM maven AS MAVEN_TOOL_CHAIN
#Copiamos los pom.xml y el src
COPY pom.xml /tmp/
COPY /Proyecto/src /tmp/Proyecto/src
COPY /Proyecto/pom.xml /tmp/Proyecto
#Vamos a la carpeta tmp
WORKDIR /tmp/
#Ejecutamos el comando para empaquetar nuestro proyecto
RUN mvn clean package
