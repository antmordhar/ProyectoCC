# Instala los requisitos del proyecto y lo testea
firstinstall:
	sudo apt update
	# Instalar maven
	sudo apt install maven
	#Instalar OpenJDK8. Cambiar al 7 si se prefiere esta version
	sudo apt install openjdk-8-jdk
	#Limpiamos e Instalamos las dependencias con maven
	mvn clean package
	
#Instala las dependencias y testea
install:
	#Limpiamos e Instalamos las dependencias con maven
	mvn clean package
#Limpia las dependencia de la build anterior y crea el docker
creardocker:
	mvm clean package dockerfile:build
#Corre el docker
correrdocker:
	docker run --rm -p 8080:8080 -d antmordhar/restaurantproject:latest
# Ejecutar los test unitarios y de cobertura
test:
	#Corremos los test de Junit y covertura
	#Para esto es necesario estar con el jdk8 o inferior e indicarselo a maven.
	export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 &&  mvn clean package && mvn cobertura:cobertura
#Ejecuta las pruebas del contenedor de docker
testcontenedor:
	#Nos movemos a la carpeta de los test
	#Le damos permisos de ejecuciión
	#Ejecutamos el script
	#El script descargara el contenedor 
	cd ./TestsConexion && chmod +x ./docker.sh && ./docker.sh
#Ejecuta las pruebas de acceso a la url
testurl:
	#Nos movemos a la carpeta de los test
	#Le damos permisos de ejecuciión
	#Ejecutamos el script
	cd ./TestsConexion && chmod +x ./docker.sh && ./heroku.sh
# Limpiar el directorio de las carpetas y ficheros que se generan
# tras la ejecución:
clean:
	#Limpiamos las dependencias
	mvn clean
	
	