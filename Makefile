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
testmesa:
	mongod --fork --syslog
	export HOST_CAMARERO=localhost && nohup java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	cd ./Mesas && export HOST=localhost && export HOST_COCINA=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
testcocina:
	mongod --fork --syslog
	localhost && java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./Cocina && export HOST=localhost && export HOST_CAMARERO=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
testcamarero:
	mongod --fork --syslog
	cd ./Camarero  && export HOST=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn test cobertura:cobertura 
	killall mongod
#Arranca la base de datos
#Exporta las variables de entorno
#Ejecuta los servicios necesarios para pasar los tests
#Ejecuta los test del microservicio
#Para la base de datos
testapi:
	mongod --fork --syslog
	export HOST_COCINA=localhost && java -jar ./Mesas/target/RestauranProject-0.0.1-SNAPSHOT.jar &
	export HOST_CAMARERO=localhost && java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./APIService && export HOST=localhost && export HOST_MESAS=localhost && export HOST_COCINA=localhost && export HOST_CAMARERO=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn test cobertura:cobertura 
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
correrdocker:
	docker-compose up
#Para el docker con el docker compose
parardocker:
	docker-compose down

#Ejecuta las pruebas de carga con taurus
#Hay mas pruebas, se encuentran el la carpeta TestConexion
testcarga:
	bzt ./TestsConexion/test.yml -report
testcargamesas:
	bzt ./TestsConexion/testmesas.yml -report
testcargacocina:
	bzt ./TestsConexion/testcocina.yml -report
testcargacamarero:
	bzt ./TestsConexion/testcamarero.yml -report

	
	