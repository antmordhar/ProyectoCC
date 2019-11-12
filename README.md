# Gestión Restaurante [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Arquitectura
La idea básica de este microservicio es ayudar a la gestión de las comandas en un restaurante.
Para ello se van a crear 3 **microservicios**:

* **Mesas** 
* **Cocina** 
* **Camareros** 
  
## **Tecnologías** que se van a usar

* **Java**
* **Spring Boot** 
* **Spring Cloud**
* **MongoDB**
* **Apache Kafka**
* **Apache ZooKeeper**

Para ver una descripción extendida de la **arquitectura** y las **tecnologías** que se van a usar pulse [aquí](https://antmordhar.github.io/ProyectoCC/Documentacion/arquitectura).

---
## Prerequisitos

Para el correcto funcionamiento del proyecto sera necesario tener:

* **OpenJDK 8**
* **Maven**
  
## Herramienta de construcción

Como herramienta de construcción se ha usado MakeFile. Se han configurado 4 Objetivos:

>make firstinstall

Este comando instalará maven,openjdk-8-jdk y las dependencias del proyecto. Después pasará los tests unitarios.

>make install

Instalará solo las dependencias del proyecto y limpia las dependencias y archivos creados por builds anteriores en la carpeta /Proyecto/target. Tras esto pasara los test unitarios.

>make test

Corre los test unitarios y los de cobertura del código del proyecto. Si faltaran dependencias las instalará.

Los test unitarios están desarrollados con **JUnit**. Maven usa **SureFire** para generar el reporte final de pasar los tests.
Por otra parte los test de cobertura del código se pasan con **cobertura**. Tras pasarse los tests se podran ver los resultados en:
* **Unitarios**: /Proyecto/target/surefire-reports/ 
* **Cobertura**: /Proyecto/target/site 

>make clean

Limpia las dependencias y archivos creados por builds anteriores en la carpeta /Proyecto/target

Para más información vea [Makefile](https://github.com/antmordhar/ProyectoCC/blob/master/Makefile)

## Integración continua

Para la integracion continua se usara **Travis-CI** y **GitHub-Actions**.

* **Travis-CI** : Pasa los test unitarios y de cobertura al Proyecto. Ejecuta los test para  Open JDK 8 y la distro que se usa es la Xenial. Y finalmente envia los datos del test de cobertura a **CodeCov**

Para más información vea [.travis.yml](https://github.com/antmordhar/ProyectoCC/blob/master/.travis.yml)

* **GitHub-Actions** : Permite la rapida configuración de test de integración sin necesidad de software de terceros. En nuestro caso estará pasando los test para las versiones del Open JDK de la 6 a la 13 para Windows, Linux y Mac-OS.

Para más información vea [maven.yml](https://github.com/antmordhar/ProyectoCC/blob/master/.github/workflows/maven.yml)


[Volver al Index](https://antmordhar.github.io/ProyectoCC/)