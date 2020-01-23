# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Base de Datos/Inyeccion de Dependencias
 
Como bases de datos para los microservicios se ha usado **MongoDB**.
 
Para la **inyección de dependencias** se ha usado una interfaz que extiende  **Spring Data Mongo Repository**. Esto proporciona todas los métodos CRUD para trabajar con nuestro modelo *plato*. Además "auto implementa" los métodos que declares en la interfaz en dependencia de su nombre y el objeto que devuelvan. Para realizar esto se parsea el nombre del método que declaremos buscando el nombre de las variables de nuestro modelo y de un conjunto de reglas que forman las queries.

El **acceso a la base de datos** se hace desde **Spring Data Mongo Repository**. Esta clase, como se ha explicado antes, tiene implementado todos los metodos CRUD de acceso a la base de datos. Ademas facilita la implementación de nuevos metodos de manera que el usuario no tenga que hacer las consultas a manos en el código.
 
Para trabajar con el repositorio solo hace falta realizar la **inyección de dependencias** en la clase que lo necesite. Esto se lleva a cabo con la anotación **@Autowired** que realizara automaticamente el wire entre el bean de nuestro servicio y el bean del repositorio.

Hacer **Wire** es el acto de combinar y comunicar bean dentro del contenedor propio que proporciona **Spring**. Los **Beans** son objetos que son instanciados, montados y gestionados por el **contenedor IoC de Spring**. 

Finalmente podemos hacer uso de esta tecnologia llamando a los métodos que hayamos declarado en el repositorio o a los que ya vienen implementados por defecto.
 
Para más información:
 
* [Documentación Spring Mongo Repository](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.repositories)
* [Repositorio para platos](https://github.com/antmordhar/ProyectoCC/blob/master/Mesas/src/Mesas/platoRepository.java)
* [Clase con injección de dependencias](https://github.com/antmordhar/ProyectoCC/blob/master/Mesas/src/Mesas/mesaController.java)
 
 [Volver al Index](https://antmordhar.github.io/ProyectoCC/)