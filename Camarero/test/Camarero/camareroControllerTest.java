package Camarero;

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

//Le decimos que al correr este tes arranque la aplicacion para poder hacer las consultas
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class camareroControllerTest {

    // El puerto en el que corre nuestra test
    @LocalServerPort
    private int port;

    // Variable para poder hacer peticiones REST
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
        this.restTemplate.postForObject("http://localhost:" + port + "/camarero/post",request,
            String.class);

        assertThat("Inserta el plato por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/camarero/get/69",String.class)
        ,is(not("[]")));
        
        //Prueba de SEND------------------------------------------------------------------------------------------------------------------------------         
        //Mockeamos la respuesta

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/camarero/price/69",String.class),is(not("0")));
        
        //Prueba de DELETE------------------------------------------------------------------------------------------------------------------------------
        //Creamos el request
                request = new HttpEntity<String>("", headers);
        //Hacemos el delete y comprobamos que la salida es correcta
        this.restTemplate.delete("http://localhost:" + port + "/camarero/delete/69");
        
        assertThat("Borra el camarero por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/camarero/get/69",String.class)
        ,is("[]"));
        
        //Prueba de HELLO------------------------------------------------------------------------------------------------------------------------------
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",String.class)
        ,is("Hello World!"));
    }
}
