# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Herramienta de construcción
buildtool: Makefile

Pese a estar usando **Maven** como herramienta de construcción principal se ha decidido usar Makefile para acortar y simplificar el uso de los comandos de Maven. Se han configurado un conjunto de Objetivos:

~~~shell
make firstinstall
~~~

* Este comando instalará maven,openjdk-8-jdk,mongodb y las dependencias del proyecto.

~~~shell
make install
~~~

* Instalará solo las dependencias del proyecto y limpia las dependencias y archivos creados por builds anteriores en la carpeta /Proyecto/target. Tras esto construirá los jar. Esta orden no pasa los tests unitarios.

~~~shell
make testmesa
make testcocina
make testcamarero
make testapi
~~~

* Corre los tests unitarios y los de cobertura correspondientes al servicio que se haya indicado. Si faltaran dependencias las instalará.

Para algunos servicios levanta y cierra las bases de datos y otros servicios de los que dependen automaticamente.

* Los test unitarios están desarrollados con **JUnit**. Maven usa **SureFire** para generar el reporte final de pasar los tests.
Por otra parte los tests de cobertura del código se pasan con **cobertura**. Tras pasarse los tests se podrán ver los resultados en:

  * **Unitarios**: /{Servicio}/target/surefire-reports/
  * **Cobertura**: /{Servicio}/target/site
  
~~~shell
make clean
~~~

* Limpia todas las dependencias y archivos creados por builds anteriores. En resumen borrara la carpeta target de todos los servicios.

~~~shell
make creardocker
~~~

* Usa el docker compose para crear la arquitectura de nuestro sistema con la ayuda de los dockerfiles de cada servicio.
* Para mas información sobre el docker-compose pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/docker-compose.yml)

  
~~~shell
make correrdocker
~~~

* Usa el docker compose para arrancar la arquitectura de nuestro sistema, si no estuviera creada la creará y después la arrancará.
* Para mas información sobre el docker-compose pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/docker-compose.yml)

~~~shell
make parardocker
~~~

* Usa el docker compose para arrancar la arquitectura de nuestro sistema, si no estuviera creada la creará y después la arrancará.
* Para mas información sobre el docker-compose pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/docker-compose.yml)

~~~shell
make testcarga
~~~

* Corre un script con taurus que comprueba la carga que nuestro servidor puede aguantar.
* Los servicios necesitan estar arrancados y con las bases de datos conectadas correctamente.
* Para ver que hace el script pulse [aquí](https://github.com/antmordhar/ProyectoCC/blob/master/TestsConexion/test.yml)
> Para correr los test a un servicio en concreto se deberá modificar el apartado "scenario" del apartado "execution" dentro de test.yml. Los escenarios estan ya creados (LoadTestAll,LoadTestCamarero,LoadTestCocina,LoadTestMesas)


Para más información vea [Makefile](https://github.com/antmordhar/ProyectoCC/blob/master/Makefile)

[Volver al Index](https://antmordhar.github.io/ProyectoCC/)