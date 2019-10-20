# Proyecto
## Gestión Restaurante

Se contaran con 3 **microservicios** los cuales podrán ser accedidos a traves del **ApiGatewway**: 
* **Mesas:**  Este servicio podrá ser usado a traves de la **ApiGateway** y se le debera pasar la comanda en forma de Json. Las mesas serán las encargadas de enviar esta comanda a **Cocina**.Además, también tendrán un control de los pedidos de los clientes en una base de datos.
* **Cocina:** Este microservicio podrá ser usado a traves de la **ApiGateway** de dos maneras.
    * Para solicitar el Menu, que será devuelto en forma de Json.
    * Para avisar a los **Camareros** de que una comanda está lista.

    El servicio contara con una base de datos en la que se guardara el menu.
* **Camareros:** Este microservicio podrá usarse desde la **ApiGateway**. Su objetivo es recibir los avisos de las comandas de **Cocina**. 
    * Para ello los se enviaran avisos a traves de la **ApiGateway** cuando haya una comanda nueva. 
    * Para avisar de que una comanda ya ha sido atendida se podrá usar la **ApiGateway** y mandar un comunicado a todos los camareros.

Su arquitectura se corresponderia con la siguiente **imagen**:
![Arquitectura](/MicroservicesArchitecture.png)

La arquitectura esta basada en una aquitectura **dirigida por eventos**. Los microservicios se comunicarán entre ellos a traves del **EventStore** y con el exterior a traves del **ApiGateway**

* Como **lenguajes** para la creación de microservicios se usara **Java** junto con los frameworks **Spring Boot** y **Spring Cloud**. 
* Para el manejador de eventos se usara **Kafka**.
* Para las bases de datos se usara **MongoDB**.
* Y por último se usara **Json** para el envio de información en las comuniciones.

[Volver al Index](https://antmordhar.github.io/ProyectoCC/)