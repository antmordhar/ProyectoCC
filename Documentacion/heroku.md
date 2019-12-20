# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Heroku

La imagen del servicio está subida a Heroku. La url de la misma es la siguiente: https://restauranprojectcc.herokuapp.com/

Se ha elegido Heroku como PaaS(Platform as a Service) por su sencillez a la hora del despliegue. También por la compatibilidad con Dockerfile y la facilidad de configuración de la ejecución de este a la hora del despliegue. Y por último pero no menos importante por que nos da un margen de uso gratuito que nos permite hacer las pruebas que deseemos en el micro servicio.

Para realizar el despliegue la imagen a Heroku se ha configurado el archivo [heroku.yml](https://github.com/antmordhar/ProyectoCC/blob/master/heroku.yml). En él se especifica a Heroku que use el Dockerfile para construir la imagen para luego desplegarla.

Al no crear un Procfile ni especificar un run en el heroku.yml heroku usara el comando CMD del dockerfile en sustitución de este.

Finalmente, al igual que Docker Hub, se ha configurado Heroku para que cada vez que se haga un push en el repositorio y pase los tests de integración se realice el proceso de despliegue. De esta forma todo se realiza de manera más cómoda y automatizada.

Si quisieramos replicar el proceso de manera manual el proceso tendriamos que instalar heroku-CLI.

Luego ejecutar los siguientes instrucciones:

~~~shell
  heroku create <nombreAplicación>
~~~

* Crea una aplicación en heroku y añado un remote a git para poder pushear a heroku.

~~~shell
  heroku stack:set container
~~~

* Esto le indicará a Heroku que se va a trabajar con contenedores Docker. El stack en Heroku es la imagen de sistema operativo que usa. Por defecto usa Heroku 18.

~~~shell
 git push heroku master
~~~

* Finalmente se pushea a heroku en donde se construirá y se desplegará la imagen de nuestro proyecto.

> Actualmente la funcionalidad del proyecto subido a heroku no es completa debida la dependencia que tiene con los demas servicios. Esto esta actualmente bajo busqueda de soluciones.

[Volver al Index](https://antmordhar.github.io/ProyectoCC/)