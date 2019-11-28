package Mesas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;


import static org.hamcrest.CoreMatchers.*;

import Mesas.mesaController;

//Le decimos que al correr este tes arranque la aplicacion para poder hacer las consultas
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class mesaControllerTest {
    
    //El puerto en el que corre nuestra test
    @LocalServerPort
    private int port;
    
    private mesaController controllerTest;

    //Variable para poder hacer peticiones REST
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        controllerTest = new mesaController();
    }
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

        //Realizamos el post para crear mesa y comprobamos que la salida es la correcta
        this.restTemplate.postForObject("http://localhost:" + port + "/crearmesa",request,
                String.class);

        assertThat("Crea la mesa por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
                    ,is("[0]"));

        //Cambiamo la request y le ponemos como contenido el json que queremos que lea la app
        request = 
                new HttpEntity<String>("{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        
        //Realizamos el post y comprobamos que la salida es la correcta
        this.restTemplate.postForObject("http://localhost:" + port + "/hacerpedido",request,
            String.class);

        assertThat("Inserta el plato por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
        ,is("[0,{\"Cantidad\":1,\"Plato\":\"plato0\",\"Precio\":1.4}]"));
        
        //Hacemos el delete y comprobamos que la salida es correcta
        this.restTemplate.delete("http://localhost:" + port + "/borrapedido/0");
        
        assertThat("Borra el pedido por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
        ,is("[0]"));
    }
}
