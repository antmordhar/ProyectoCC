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

## Rest

---

## Docker

---

## Heroku

---


[Volver al Index](https://antmordhar.github.io/ProyectoCC/)