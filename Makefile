SHELL := /bin/bash
# Instala los requisitos del proyecto y lo testea
# Instalar maven
#Instalar OpenJDK8. Cambiar al 7 si se prefiere esta version
#Instalamos docker
#Descargamos la imagen de mongo
firstinstall:
	sudo apt update
	sudo apt install maven
	sudo apt install openjdk-8-jdk
	sudo apt install -y mongodb
	
#Instala las dependencias y genera los jar
install:
	cd ./Mesas && mvn clean package -DskipTests
	cd ./Cocina && 	mvn clean package -DskipTests
	cd ./Camarero && mvn clean package -DskipTests
	cd ./APIService && mvn clean package -DskipTests

#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
#ATENCION: Asegurarse de que los servicios no estan escuchando en el puerto antes de ejecutar otro test.
#ATENCION: usar: kill -9 $(sudo lsof -t -i:80xx)
testmesa:
	mongod --fork --syslog
	java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	cd ./Mesas && mvn cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
#ATENCION: Asegurarse de que los servicios no estan escuchando en el puerto antes de ejecutar otro test.
#ATENCION: usar: kill -9 $(sudo lsof -t -i:80xx)
testcocina:
	mongod --fork --syslog
	java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./Cocina && mvn cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
#ATENCION: Asegurarse de que los servicios no estan escuchando en el puerto antes de ejecutar otro test.
#ATENCION: usar: kill -9 $(sudo lsof -t -i:80xx)
testcamarero:
	mongod --fork --syslog
	cd ./Camarero  &&  mvn cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
#ATENCION: Asegurarse de que los servicios no estan escuchando en el puerto antes de ejecutar otro test.
#ATENCION: usar: kill -9 $(sudo lsof -t -i:80xx)
testapi:
	mongod --fork --syslog
	java -jar ./Mesas/target/RestauranProject-0.0.1-SNAPSHOT.jar &
	java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./APIService &&  mvn cobertura:cobertura 
	killall mongod

#Limpia archivos de builds anteriores
clean:
	cd ./Mesas && mvn clean 
	cd ./Cocina && 	mvn clean 
	cd ./Camarero && mvn clean 
	cd ./APIService && mvn clean 
	
#Crea la arquitectura para que funcione el rest
creardocker:
	docker-compose build
#Corre el docker con docker compose
#ATENCION: Asegurarse de que los servicios no estan escuchando en el puerto antes de ejecutar otro test.
#ATENCION: usar: kill -9 $(sudo lsof -t -i:80xx)
correrdocker:
	docker-compose up
#Para el docker con el docker compose
parardocker:
	docker-compose down
#Ejecuta las pruebas de carga con taurus
testcarga:
	bzt ./TestsConexion/test.yml -report

	
	