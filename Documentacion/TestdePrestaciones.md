# Gestión Restaurante 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Language](https://img.shields.io/badge/Language-java-red.svg)](https://www.java.com/)
[![Framework](https://img.shields.io/badge/Framework-spring-green.svg)](https://spring.io/)
[![Build Status](https://travis-ci.org/antmordhar/ProyectoCC.svg?branch=master)](https://travis-ci.org/antmordhar/ProyectoCC)
[![codecov](https://codecov.io/gh/antmordhar/ProyectoCC/branch/master/graph/badge.svg)](https://codecov.io/gh/antmordhar/ProyectoCC)

## Test de Prestaciones
 
Recursos utilizados:
* Intel(R) Core(TM) i7-5700HQ CPU @ 2.70GHz
* 8GB DDR3 1600MT/s Kingston MSI16D3LS1MNG/8G

* Ubuntu 18.04.3 LTS
 
Para la medición de las prestaciones de nuestro sistema se ha usado [Taurus](https://gettaurus.org/). Taurus es una herramienta open source que nos permite pasar tests con JMeter,Selenium y otras herramientas fácilmente. Para ello solo es necesario la creación de un fichero yml donde se encontrarán las instrucciones para la ejecución del test.
 
En nuestro caso usaremos JMeter como herramienta para pasar los tests a nuestros servicios. Los ficheros yml creados para los tests son los siguientes:
 
Prestaciones: testvacio.yml

[Test](https://github.com/antmordhar/ProyectoCC/blob/master/TestsConexion/test.yml): ./TestsConexion/test.yml
 
### 1. Estado inicial del Servicio Mesas

En la siguiente imagen podemos ver el resultado que el servicio mesas obtuvo al pasar el test que carga el servicio con peticiones de 10 usuarios durante 1 minuto con el servidor de **Tomcat7** embebido que proporciona Spring:
 
![Mesainicial](./pic/mesainicial.png)
 
A continuación veremos como el ancho banda del servidor Tomcat capa a 1.1MB mas o menos.
 
* **Tomcat**
![Mesaperdida](./pic/mesas20.png)
![Mesaperdida](./pic/mesas30.png)
![Mesaperdida](./pic/mesas40.png)
![Mesaperdida](./pic/mesas50.png)
![Mesaperdida](./pic/mesas100.png)
![Mesaperdida](./pic/mesas200.png)
 
Para intentar conseguir una mejora de las prestaciones cambiaremos el servidor predeterminado de Spring, Tomcat7 por otros y veremos la carga que soportan:
 
* **Jetty**
![Mesaperdida](./pic/jetty10.png)
![Mesaperdida](./pic/jetty100.png)
![Mesaperdida](./pic/jetty200.png)
 
Podemos ver como Jetty da un mejor rendimiento con altas cargas de trabajo, pero le cuesta más arrancar al principio.
 
* **Undertow**
![Mesaperdida](./pic/under10.png)
![Mesaperdida](./pic/under100.png)
![Mesaperdida](./pic/under200.png)
 
Como podemos ver Undertow tiene un buen rendimiento en el arranque, sin embargo se ralentiza con cargas altas de usuarios
 
* **Reactor Netty**
![Mesaperdida](./pic/flux10.png)
![Mesaperdida](./pic/flux100.png)
![Mesaperdida](./pic/flux200.png)
 
Reactor Netty tiene un alto rendimiento con cargas bajas de usuarios y un rendimiento alto con altas cargas. Sin embargo tarda algo más en llegar a su tope de ancho de banda.
 
Con estos resultados delante podríamos aventurarnos a decir que Reactor Netty es el mayor competidor de Tomcat en términos de velocidad y aguante de carga. Sin embargo estos resultados están en local y sin bases de datos. A continuación se procede a la realización de las mismas pruebas con el estado del proyecto actual.
 
### 2. Estado actual del proyecto
 
Para estas pruebas, los servicios han sido containerizados con Docker y se ha implementado la conexión con las base de datos, las cuales también se encuentran en un contenedor Docker. Además se ha establecido una conexión entre ellos mediante REST para poder simular con la máxima fidelidad, que podemos alcanzar actualmente, el proceso de trabajo que realizan entre ellos.
El estado actual del proyecto se puede ver en la siguiente imagen:
 
![Estado](./pic/estadoTests.png)
 
El despliegue de las imágenes y, por tanto, pruebas de carga será realizado en local. Las pruebas se realizarán con 10, 20 y 30 usuarios respectivamente para cada servicio con cada servidor. Esto es debido a que la velocidad al usar las bases de datos se reduce. En un primer momento se usó una única base de datos para todos los servicio pero,  para ajustarnos más a la arquitectura inicial del sistema y aumentar la velocidad de éste, actualmente se usa una por servicio.
 
* **Tomcat**
 * Mesas
![Test](./pic/mt10.png)
![Test](./pic/mt20.png)
![Test](./pic/mt30.png)
 * Cocina
![Test](./pic/ct10.png)
![Test](./pic/ct20.png)
![Test](./pic/ct30.png)
 * Camarero
![Test](./pic/cct10.png)
![Test](./pic/cct20.png)
![Test](./pic/cct30.png)
 * Todos
![Test](./pic/at10.png)
![Test](./pic/at20.png)
![Test](./pic/at30.png)
 * Resultados
   * Peticiones por segundo media 1286 Hits/s
   * Ancho de banda medio 225 KiB/s
   * Tiempo de respuesta medio 13 ms
   * Error medio 0%
* **Jetty**
 * Mesas
![Test](./pic/mj10.png)
![Test](./pic/mj20.png)
![Test](./pic/mj30.png)
 * Cocina
![Test](./pic/cj10.png)
![Test](./pic/cj20.png)
![Test](./pic/cj30.png)
 * Camarero
![Test](./pic/ccj10.png)
![Test](./pic/ccj20.png)
![Test](./pic/ccj30.png)
 * Todos
![Test](./pic/aj10.png)
![Test](./pic/aj20.png)
![Test](./pic/aj30.png)
 * Resultados
   * Peticiones por segundo media 1393 Hits/s
   * Ancho de banda medio 111 KiB/s
   * Tiempo de respuesta medio 13 ms
   * Error medio 0%
* **Undertow**
 * Mesas
![Test](./pic/mu10.png)
![Test](./pic/mu20.png)
![Test](./pic/mu30.png)
 * Cocina
![Test](./pic/cu10.png)
![Test](./pic/cu20.png)
![Test](./pic/cu30.png)
 * Camarero
![Test](./pic/ccu10.png)
![Test](./pic/ccu20.png)
![Test](./pic/ccu30.png)
 * Todos
![Test](./pic/au10.png)
![Test](./pic/au20.png)
![Test](./pic/au30.png)
 * Resultados
   * Peticiones por segundo media 1493 Hits/s
   * Ancho de banda medio 229 KiB/s
   * Tiempo de respuesta medio 12 ms
   * Error medio 0%
* **Reactor Netty**
 * Mesas
![Test](./pic/rm10.png)
![Test](./pic/rm20.png)
![Test](./pic/rm30.png)
 * Cocina
![Test](./pic/rc10.png)
![Test](./pic/rc20.png)
![Test](./pic/rc30.png)
 * Camarero
![Test](./pic/rcc10.png)
![Test](./pic/rcc20.png)
![Test](./pic/rcc30.png)
 * Todos
![Test](./pic/ra10.png)
![Test](./pic/ra20.png)
![Test](./pic/ra30.png)
 * Resultados
   * Peticiones por segundo media 1428 Hits/s
   * Ancho de banda medio 119 KiB/s
   * Tiempo de respuesta medio 13 ms
   * Error medio 0%
 
### Discusión

 Como podemos ver al usar la base de datos, los servidores, son hasta 6 veces más lentos de media que en las versiones previas.
 
El servicio camarero es más rápido que los demás servicios debido a que este no necesita mandar peticiones a ningún otro servicio, es totalmente independiente. Por lo que es una buena muestra de la velocidad que tendría un servicio por sí solo en los diferentes servidores.
 
Al hacer el test de carga a todos los servicios a la vez se puede ver una clara disminución de la velocidad de respuesta del servidor tardando hast el doble por petición.
 
Finalmente atendiendo a los resultados obtenidos:
* Tomcat
  * Peticiones por segundo media 1286 Hits/s
  * Ancho de banda medio 225 KiB/s
  * Tiempo de respuesta medio 13 ms
  * Error medio 0%
* Jetty
  * Peticiones por segundo media 1393 Hits/s
  * Ancho de banda medio 111 KiB/s
  * Tiempo de respuesta medio 13 ms
  * Error medio 0%
* Undertow
  * Peticiones por segundo media 1493 Hits/s
  * Ancho de banda medio 229 KiB/s
  * Tiempo de respuesta medio 12 ms
  * Error medio 0%
* Reactor Netty
  * Peticiones por segundo media 1428 Hits/s
  * Ancho de banda medio 119 KiB/s
  * Tiempo de respuesta medio 13 ms
  * Error medio 0%
 
Podemos ver como. el tiempo de respuesta y la tasa de error que nos proporcionan todos los servidores es la misma.(Los decimales han sido omitidos). Por lo que para la elección del servidor se ha atendido a las peticiones por segundo aceptadas y a su rendimiento al hacer los test de carga de todo el sistema al conjunto. Teniendo esto en cuenta los principales competidores son Reactor Netty y Undertow. Sin embargo **Undertow** ha demostrado una mejor competencia a la hora del test de carga total, por lo que se ha elegido este finalmente.
 
Java, al ser un lenguaje compilado, proporciona una mayor velocidad que los lenguajes interpretados. Además los servidores embebidos que proporciona Spring Boot están configurados por defecto para sacar un buen partido del servidor. Por ejemplo en nuestra elección final, Undertow, está configurado para que use todos los procesadores que estén disponibles y use 8 *workers threads* por cada procesador. Además usará toda la memoria que esté disponible para la Java Virtual Machine.
 
Finalmente en cuanto a la base de datos, Mongo, como hemos comentado anteriormente, se desplegó 3 veces, para que haya una base de datos para cada servicio. Cosa que, consecuentemente, aceleró la respuesta de peticiones del sistema.

### **Test Finales**
 Para finalizar el testeo de los servicios. Se han realizado unas ultimas pruebas de estos sobre el servidor escogido, **Undertow**. En ellas se ha limitado el numero de peticiones post y delete que se le hacian a los servicios. Esto imita mejor un entorno de ejecución estandar donde las consultas sobre datos son mucho mas frecuentes que la inserción o borrado de estos. Los resultados obtenidos son los siguientes:

![Test](./pic/FinalResult10.png)
![Test](./pic/FinalResult20.png)
![Test](./pic/FinalResult30.png)

* Peticiones por segundo media 1425 Hits/s
* Ancho de banda medio 279 KiB/s
* Tiempo de respuesta medio 13 ms
* Error medio 0%

Con esto podemos concluir que el servidor efectivamente cumple los requisitos mínimos que se esperaban de el.


[Volver al Index](https://antmordhar.github.io/ProyectoCC/)