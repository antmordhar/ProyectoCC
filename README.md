# Proyecto
## Gestión Restaurante

[Ir a arquitectura](https://antmordhar.github.io/ProyectoCC/Hito0/Documentacion/arquitectura)

**Tecnologías** que se van a usar:

Para la creación de microservicios se usará **Java**.
>Esta elección se ha hecho pensando que java es un lenguaje con una gran cantidad de usuarios y que tiene una gran cantidad de recursos y librerias. Ademas, java permite ejecutarse independientemente de la plataforma que haya por debajo.

>Por último, java posee una estructura de código organizada y fácilmente entendible lo cual en las etapas más avanzadas del proyecto facilitará su mantenimiento y cambios.

Los frameworks para microservicios serán frameworks **Spring Boot** y **Spring Cloud**. 
>**Spring Boot** se ha elegido por que es uno de los framework de creación de microservicios con mayor soporte de java. Este framework esta pensado para facilitar el desarrollo, Test Unitarios y test de integración.
 
>**Spring Cloud** da soluciones a ciertos problemas que se presentan al trabajar en sistemas distribuidos. Estos problemas son por ejemplo, gestión de configuraciones, descubrimiento de servicios y sesiones distribuidas. Además tiene una integración perfecta con Spring.

Para el manejador de eventos se usará **Apache Kafka**.
>**Kafka** se usara con vistas a proveer de una mayor escalabilidad a los microservicios en un futuro debido a que destaca sobre otras tecnologías como JMS, RabbitMQ y AMQP por su mayor rapidez al responder y las cantidades de datos que puede procesar.

>Este posee un sistema de suscripción y publicación de mensajes que es rápido, escalable y resistente a fallos, y que es lo que estaremos usando en concreto de Kafka. 

Para la configuración distribuida se usará **Apache ZooKeeper**.
>**Zookeeper** se usara para la configuración distribuida. Se ha elegido este frente a otros como etcd3 por que está escrito en java y posee una API fácil de usar para la coordinacion de servicios distribuidos.

Para las bases de datos se usará **MongoDB**.
> Esto es por que es una base de datos no relacional y se estructura como un **JSON** por lo que facilitará las tareas de los servicios al trabajar con las bases de datos y enviar mensajes entre los servicios.

Para la comunicación con el **ApiGateway** se usarán peticiones **HTTP**.
>Es una solución simple para comunicarse con nuestro conjunto de microservicios ya sea desde la parte cliente o desde otros microservicios.

Y por último se usara **Json** para el envío de información en las comunicaciones.
> **JSON** se ha elegido por encima de **XML** debido a la facilidad de escritura de este, además se entiende más fácilmente. Otra de las ventajas es que es más rápido parsearlo que **XML** y por último mejora la compatibilidad con **MongoDB**
