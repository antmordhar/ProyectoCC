# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Arquitectura
La idea básica de este microservicio es ayudar a la gestión de las comandas en un restaurante.
Para ello se van a crear 3 **microservicios**:

* **Mesas** 
* **Cocina** 
* **Camareros** 

Para ver una descripción extendida de la **arquitectura** pulse [aquí](https://antmordhar.github.io/ProyectoCC/Documentacion/arquitectura).

---
  
## Tecnologías que se van a usar

* **Java**
* **Spring Boot** 
* **Spring Cloud**
* **MongoDB**
* **Apache Kafka**
* **Apache ZooKeeper**

Para ver una descripción extendida de las **tecnologías** que se usan pulse [aquí](https://antmordhar.github.io/ProyectoCC/Documentacion/tecnologias).

---

## Integración continua

Para la integracion continua se usara **Travis-CI** y **GitHub-Actions**. 

Para ver mas información sobre la integración continua pulse [aquí](https://antmordhar.github.io/ProyectoCC/Documentacion/integracion)

---

## Prerrequisitos

Para el correcto funcionamiento del proyecto sera necesario tener:

* **OpenJDK 8**
* **Maven**
* **Docker** (si e desea correr la imagen en local)

---
  
## Herramienta de construcción

> buildtool: Makefile

Como herramienta de construcción se ha usado MakeFile. Se han configurado 8 Objetivos:

>make firstinstall

Este comando instalará maven,openjdk-8-jdk y las dependencias del proyecto. Después pasará los tests unitarios.

>make install

Instalará solo las dependencias del proyecto y limpia las dependencias y archivos creados por builds anteriores en la carpeta /Proyecto/target. Tras esto pasara los tests unitarios.

>make creardocker

Ejecuta el comando para que se limpien los archivos creados por builds anteriores empaquete de nuevo la aplicación y cree el contenedor ejecutando el Dockerfile.

>make correrdocker

Ejecuta el comando para que corra el contenedor localmente en el puerto 8080.

>make test

Corre los tests unitarios y los de cobertura del código del proyecto. Si faltaran dependencias las instalará.

Los test unitarios están desarrollados con **JUnit**. Maven usa **SureFire** para generar el reporte final de pasar los tests.
Por otra parte los tests de cobertura del código se pasan con **cobertura**. Tras pasarse los tests se podran ver los resultados en:
* **Unitarios**: /Proyecto/target/surefire-reports/ 
* **Cobertura**: /Proyecto/target/site 

>make testcontenedor

Corre un script que te crea el contenedor con el Dockerfiles y le realiza pruebas de conexión en local. Para mas información sobre lo que hace el script pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/TestsConexion/docker.sh)

>make testurl

Corre un script que hace hace las peticiones al servicio desplegado en Heroku. Para ver que hace el script pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/TestsConexion/heroku.sh)

>make clean

Limpia las dependencias y archivos creados por builds anteriores en la carpeta /Proyecto/target

Para más información vea [Makefile](https://github.com/antmordhar/ProyectoCC/blob/master/Makefile)

---

## Microservicio Mesas

Se ha implementado un microservicio el cual tiene una API REST. Los comandos las peticiones que se le pueden hacer son las siguientes:

* **GET: /verpedido/{id}**
* **POST: /crearmesa**
* **POST: /hacerpedido** 
  * Para este comando es necesario pasarle un JSON como cuerpo con la siguiente structura:
  ~~~ JSON
  "{"idmesa":0,
    "nombre":"plato0", 
    "precio":1.4, 
    "cantidad":1}"
  ~~~
* **DELETE: /borrapedido/{id}**

El microservicio cuenta con 5 clases:
  
* **app** : Es el main de nuestro microservicio y la clase que lo ejecuta
* **mesa** : Guarda los pedidos de una mesa
* **mesas** : Guarda un conjunto de mesas. Servira luego para conectar la base de datos
* **mesasController** : Hace de API REST. Tiene un objeto del tipo mesas.
* **plato** : Sirve como estructura para leer el cuerpo de JSON para la petición de hacerpedido

El microservicio está estructurado por capas como podemos ver en la siguiente imagen:

![Arquitectura](./Documentacion/mesaservice.png)

La capa de la API rest es la encargada de recibir las peticiones al microservicio y enviar desde este las respuestas pertinentes. Por otro lado es la capa de la Lógica de Negocio la que se encarga se encarga de las operaciones con los datos, crearlos, almacenarlos, procesarlos, etc.

---

## Docker

El microservicio ha sido containerizado con un [Dockerfile](https://github.com/antmordhar/ProyectoCC/blob/master/Dockerfile). La url del mismo es la siguiente: https://hub.docker.com/repository/docker/antmordhar/restaurantproject .

Como imagen base para la construccion de la imagen del servicio se ha usado: https://hub.docker.com/r/adoptopenjdk/maven-openjdk8/. En esa imagen se encuentra Maven y OpenJDK8 que son necesarios para la contrucción y ejecución del proyecto. 

Frente a esta imagen se compararon 2 más:

* https://hub.docker.com/r/rawmind/alpine-jdk8
* https://hub.docker.com/_/maven

Pero finalmente se decidio usar la anterior en funcion a su peso y su facilidad de utilización:

![Comparativa](./Documentacion/comparativa.png)

Como se puede ver el peso de Maven es demasiado elevado. Esto es por que contiene varias versiones de los JDK. Por otro lado OpenJDK8 Apline es mas liviana pero hay que instalarle Maven por lo que dificulta el proceso.

Finalmente la imagen del servicio pesa lo siguiente:

![Project](./Documentacion/project.png)

Para construir la imagen podemos usar uno de los siguientes comandos:

>make creardocker

Limpia los archivos de la build anterior empaqueta y containeriza el proyecto.

>mvn dockerfile:build

Usando el plugin de spotify Maven puede crear también la imagen del proyecto.

>docker build --rm -f "Dockerfile" -t antmordhar/restaurantproject:latest .

La forma normal de hacerlo y la propuesta por la documentación de Docker.

Para probar la imagen en local podemos uno de los siguientes comandos:

> make correrdocker

Comando acortado a traves del make

> docker run --rm -p 8080:8080 -d antmordhar/restaurantproject:latest

Comando completo propuesto por la documentación de Docker.

Para comprobar que funciona de manera facil se puede ejecutar el comando:

> make testcontenedor

Que correra un script con un conjunto de pruebas.

Además Docker Hub esta configurado para que cada vez que se haga un push a nuestro repositorio, si pasa los test de integración, se suba la imagen.

---

## Heroku

La imagen del servicio esta subida a Heroku. La url de la misma es la siguiente: https://restauranprojectcc.herokuapp.com/

Para comprobar si funciona puede seguir las instrucciones que se detallan en la sección Microservicio Mesas o simplemente correr el siguiente comando:

> make testurl

Que correra un conjunto de pruebas contra la url.

Se ha elegido Heroku como PaaS(Platform as a Service) por su sencillez a la hora del despligue. También por la compatibilidad con Dockerfile y la facilidad de configuración de la ejecución de este a la hora del despligue. Y por último pero no menos importante por que nos da un margen de uso gratuito que nos permite hacer las pruebas que deseemos en el microservicio.

Para realizar el despliegue la imagen a Heroku se ha configurado el archivo [heroku.yml](https://github.com/antmordhar/ProyectoCC/blob/master/heroku.yml). En el se especifica a Heroku que use el Dockerfile para construir la imagen para luego desplegarla.

Al no crear un Procfile ni especificar un run en el heroku.yml heroku usara el comando CMD del dockerfile en sustitución de este.

Finalmente, al igual que Docker Hub se ha configurado Heroku para que cada vez que se haga un push en el repositorio y pase los tests de integración se realice el proceso de despliegue. De esta forma todo se realiza de manera mas cómoda y automatizada.

Si quisieramos replicar el proceso de manera manual el proceso tendriamos que instalar heroku-CLI.

Luego ejecutar los siguientes instrucciones:

> heroku create restauranprojectcc

Crea una aplicación en heroku y añado un remote a git para poder pushear a heroku.

> heroku stack:set container

Esto le indicará a Heroku que se va a trabajar con contenedores Docker.

>git push heroku master

Finalmente se pushea a heroku en docde se construira y se desplegará la imagen de nuestro proyecto.

---

[Volver al Index](https://antmordhar.github.io/ProyectoCC/)