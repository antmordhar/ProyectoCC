# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## **Tecnologías** que se van a usar:

### Para la creación de microservicios se usará **Java**.
>Esta elección se ha hecho pensando que java es un lenguaje con una gran cantidad de usuarios y que tiene una gran cantidad de recursos y librerias.

>Por último, java posee una estructura de código organizada y fácilmente entendible lo cual en las etapas más avanzadas del proyecto facilitará su mantenimiento y cambios

### Los frameworks para microservicios serán frameworks **Spring Boot** y **Spring Cloud**. 
>**Spring Boot** se ha elegido por que es uno de los framework de creación de microservicios con mayor soporte de java. Este framework esta pensado para facilitar el desarrollo, Test Unitarios y test de integración.
 
>**Spring Cloud** da soluciones a ciertos problemas que se presentan al trabajar en sistemas distribuidos. Estos problemas son por ejemplo, gestión de configuraciones, descubrimiento de servicios y sesiones distribuidas. Además tiene una integración perfecta con Spring.

### Para las bases de datos se usará **MongoDB**.
> Esto es por que es una base de datos no relacional por lo que facilitará las tareas de los servicios al trabajar con las bases de datos debido a que se almacenarán listas de platos con sus cantidades y precios.

### Para cola  de eventos se usará **Apache Kafka**.
>**Kafka** se usara con vistas a proveer de una mayor escalabilidad a los microservicios en un futuro debido a que destaca sobre otras tecnologías como JMS, RabbitMQ y AMQP por su mayor rapidez al responder y las cantidades de datos que puede procesar.

>Este posee un sistema de suscripción y publicación de mensajes que es rápido, escalable y resistente a fallos, y que es lo que estaremos usando en concreto de Kafka. 

### Para la configuración distribuida se usará **Apache ZooKeeper**.
>**Zookeeper** se usara para la configuración distribuida. Se ha elegido este frente a otros como etcd3, por que está escrito en java y posee una API fácil de usar para la coordinacióon de servicios distribuidos.

[Volver al Index](https://antmordhar.github.io/ProyectoCC/)