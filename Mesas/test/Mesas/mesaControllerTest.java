package Mesas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;



import static org.hamcrest.CoreMatchers.*;

//Le decimos que al correr este test arranque la aplicacion para poder hacer las peticiones
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class mesaControllerTest {

    // El puerto en el que corre nuestra test lo autasignara spring
    @LocalServerPort
    private int port;

    // Variable para poder hacer peticiones REST, se autoinstanciara al iniciar la clase
    @Autowired
    private TestRestTemplate restTemplate;

    //Test para las conexiones en local de la app
    @Test
    public void testConetions() throws Exception {

        //Creamos un objeto headers que servira para especificar los headers de nuestras peticiones
        HttpHeaders headers = new HttpHeaders();
        //Le decimos que estara tratando con un JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Creamos un objeto HTTP entity y lo inicializamos con los headers
        HttpEntity<String> request = 
        new HttpEntity<String>("", headers);
        
        //Prueba de POST && GET------------------------------------------------------------------------------------------------------------------------------
        //Cambiamo la request y le ponemos como contenido el json que queremos que lea la app
        request = 
                new HttpEntity<String>("{\"idmesa\":69,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        
        //Realizamos el post y comprobamos que la salida es la correcta
        this.restTemplate.postForObject(System.getenv("HOST")+ port + "/pedido",request,
            String.class);

        assertThat("Inserta el plato por HTTP",this.restTemplate.getForObject(System.getenv("HOST")+ port + "/pedido/69",String.class)
        ,is(not("[]")));
        
        //Prueba de SEND------------------------------------------------------------------------------------------------------------------------------

        request = 
        new HttpEntity<String>("", headers);

        //Realizamos el send y comprobamos que la salida es la correcta
        //Para esto necesitamos que el otro servicio este arrancado
         assertThat("Sendea Correctamente",this.restTemplate.postForObject(System.getenv("HOST")+ port + "/pedido/69",request,
             String.class),is("OK"));
        
        //Prueba de DELETE------------------------------------------------------------------------------------------------------------------------------
        //Creamos el request
                request = new HttpEntity<String>("", headers);
        //Hacemos el delete y comprobamos que la salida es correcta
        this.restTemplate.delete(System.getenv("HOST")+ port + "/pedido/69");
        
        assertThat("Borra el pedido por HTTP",this.restTemplate.getForObject(System.getenv("HOST")+ port + "/pedido/69",String.class)
        ,is("[]"));
        
        //Prueba de HELLO------------------------------------------------------------------------------------------------------------------------------
        assertThat(this.restTemplate.getForObject(System.getenv("HOST")+ port + "/",String.class)
        ,is("Hello World!"));
    }
}
