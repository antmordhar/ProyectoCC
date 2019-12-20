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
	export HOST_CAMARERO=localhost && nohup java -jar ./Cocina/target/RestauranProjectCocina-0.0.1-SNAPSHOT.jar &
	cd ./Mesas && export HOST=localhost && export HOST_COCINA=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
	
testcocina:
	mongod --fork --syslog
	localhost && java -jar ./Camarero/target/RestauranProjectCamarero-0.0.1-SNAPSHOT.jar &
	cd ./Cocina && export HOST=localhost && export HOST_CAMARERO=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && mvn test cobertura:cobertura 
	killall mongod
testcamarero:
	mongod --fork --syslog
	cd ./Camarero  && export HOST=localhost && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn test cobertura:cobertura 
	killall mongod

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
#Corre el docker
correrdocker:
	docker-compose up
#Para el docker
parardocker:
	docker-compose down

#Ejecuta las pruebas de carga
testcarga:
	bzt ./TestsConexion/quick_test.yml -report

	
	