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
	cd ./Mesas && mvn clean package
	cd ./Cocina && 	mvn clean package
	cd ./Camarero && mvn clean package
	cd ./APIService && mvn clean package

# Ejecutar los test unitarios y de cobertura
test:
	#Corremos los test de Junit y covertura
	#Para esto es necesario estar con el jdk8 o inferior e indicarselo a maven.
	cd ./Mesas && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn clean package cobertura:cobertura 
	cd ./Cocina && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn clean package cobertura:cobertura 
	cd ./Camarero && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn clean package cobertura:cobertura 
	cd ./APIService && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn clean package cobertura:cobertura 

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

	
	