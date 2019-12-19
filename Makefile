# Instala los requisitos del proyecto y lo testea
firstinstall:
	sudo apt update
	# Instalar maven
	sudo apt install maven
	#Instalar OpenJDK8. Cambiar al 7 si se prefiere esta version
	sudo apt install openjdk-8-jdk
	#Instalamos docker
	sudo apt-get install docker-ce docker-ce-cli containerd.io
	#Descargamos la imagen de mongo
	docker pull mongo
	
#Instala las dependencias y testea
install:
	cd ./Mesas && mvn clean package -DskipTests
	cd ./Cocina && 	mvn clean package -DskipTests
	cd ./Camarero && mvn clean package -DskipTests
	cd ./APIService && mvn clean package -DskipTests

# Ejecutar los test unitarios y de cobertura
#Para esto es necesario estar con el jdk8 o inferior e indicarselo a maven.
#Primero encendemos los servicios
#Creamos las variables de entorno
#Lanzamos los tests

testmesa:
	mongod --fork --syslog
	nohup java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	cd ./Mesas && export HOST=localhost && export HOST_COCINA=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
	
testcocina:
	mongod --fork --syslog
	java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./Cocina && export HOST=localhost && export HOST_CAMARERO=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
testcamarero:
	mongod --fork --syslog
	cd ./Camarero  && export HOST=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn test cobertura:cobertura 
	killall mongod

testapi:
	mongod --fork --syslog
	java -jar ./Mesas/target/RestauranProject-0.0.1-SNAPSHOT.jar &
	java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./APIService  && export HOST=localhost && export HOST_MESAS=localhost && export HOST_COCINA=localhost && export HOST_CAMARERO=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn test cobertura:cobertura 
	killall mongod

#Limpia archivos de builds anteriores
clean:
	cd ./Mesas && mvn clean 
	cd ./Cocina && 	mvn clean 
	cd ./Camarero && mvn clean 
	cd ./APIService && mvn clean 
	
#PRECAUCION BORRA TODOS LAS IMAGENES QUE NO SE ESTEN USANDO 
#Crea la arquitectura para que funcione el rest
creardocker:
	docker system prune 
	docker network create net
	docker build --rm -f "Mesas/Dockerfile" -t proyectoccmesas:latest "Mesas"	
	docker create --name mesas --network net --publish 8080:8080 proyectoccmesas
	docker create --name mongomesas --network net --publish 27017:27017 mongo:latest
	docker build --rm -f "Cocina/Dockerfile" -t proyectocccocina:latest "Cocina"
	docker create --name cocina --network net --publish 8081:8081 proyectocccocina
	docker create --name mongococina --network net --publish 27018:27017 mongo:latest
	docker build --rm -f "Camarero/Dockerfile" -t proyectocccamarero:latest "Camarero"
	docker create --name camarero --network net --publish 8082:8082 proyectocccamarero
	docker create --name mongocamarero --network net --publish 27019:27017 mongo:latest
	docker build --rm -f "APIService/Dockerfile" -t proyectoccapi:latest "APIService"
	docker create --name api --network net --publish 8069:8069 proyectoccapi

#Corre el docker
correrdocker:
	docker start mongomesas
	docker start mongococina
	docker start mongocamarero
	docker start mesas
	docker start cocina
	docker start camarero
	docker start api
#Para el docker
parardocker:
	docker stop mongomesas
	docker stop mongococina
	docker stop mongocamarero
	docker stop mesas
	docker stop cocina
	docker stop camarero
	docker stop api

#Ejecuta las pruebas de carga
testcarga:
	bzt ./TestsConexion/quick_test.yml -report

	
	