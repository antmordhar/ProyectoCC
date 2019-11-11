# Instala los requisitos del proyecto y lo testea
firstinstall:
	sudo apt update
	# Instalar maven
	sudo apt install maven
	#Instalar OpenJDK8
	sudo apt install openjdk-8-jdk
	#Nos movemos a la carpeta del proyecto
	#Limpiamos e Instalamos las dependencias con maven
	cd ./Proyecto && mvn clean install
	
#Instala las dependencias y testea
install:
	#Limpiamos e Instalamos las dependencias con maven

	cd ./Proyecto && mvn clean install
	
# Ejecutar los test unitarios y de cobertura
testlocal:
	#Nos movemos a la carpeta del proyecto
	#Corremos los test de Junit y covertura
	#Para esto es necesario estar con el jdk8 o inferior
	export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && cd ./Proyecto && mvn clean package && mvn cobertura:cobertura

# Ejecutar los test unitarios y de cobertura
testexterno:
	#Nos movemos a la carpeta del proyecto
	#Corremos los test de Junit y covertura
	mvn clean package && mvn cobertura:cobertura
		
# Limpiar el directorio de las carpetas y ficheros que se generan
# tras la ejecución:
clean:
	#Nos movemos a la carpeta del proyecto
	#Limpiamos las dependencias
	cd ./Proyecto && mvn clean
	
	