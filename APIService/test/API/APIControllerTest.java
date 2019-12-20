package API;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class APIControllerTest {
    
    // El puerto en el que corre nuestra test
    @LocalServerPort
    private int port;

    // Variable para poder hacer peticiones REST
    @Autowired
    private TestRestTemplate restTemplate;

    //Test para las conexiones en local de la app
    @Test
    public void testConetions() throws Exception {
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = 
        new HttpEntity<String>("", headers);

        //Prueba de MESAS------------------------------------------------------------------------------------------------------------------------------
        //Prueba de POST && GET------------------------------------------------------------------------------------------------------------------------------
        request = 
                new HttpEntity<String>("{\"idmesa\":69,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/pedido/post",request,
            String.class);
        assertThat("Inserta el pedido por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/pedido/get/69",String.class)
        ,is(not("[]")));
        //Prueba de SEND------------------------------------------------------------------------------------------------------------------------------
        request = 
        new HttpEntity<String>("", headers);
         assertThat("Sendea pedido Correctamente",this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/pedido/send/69",request,
             String.class),is("OK"));
        //Prueba de DELETE------------------------------------------------------------------------------------------------------------------------------
        request = new HttpEntity<String>("", headers);
        this.restTemplate.delete("http://"+System.getenv("HOST")+":" + port + "restaurant/pedido/delete/69");
        
        // assertThat("Borra el pedido por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/pedido/get/69",String.class)
        // ,is("[]"));

        // //Prueba de COCINA------------------------------------------------------------------------------------------------------------------------------
        // //Prueba de POST && GET------------------------------------------------------------------------------------------------------------------------------
        // request = 
        //         new HttpEntity<String>("{\"idmesa\":69,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        // this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/cocina/post",request,
        //     String.class);
        // assertThat("Inserta cocina por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/cocina/get/69",String.class)
        // ,is(not("[]")));
        // //Prueba de SEND------------------------------------------------------------------------------------------------------------------------------
        // request = 
        // new HttpEntity<String>("", headers);
        //  assertThat("Sendea cocina Correctamente",this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/cocina/send/69",request,
        //      String.class),is("OK"));
        // //Prueba de DELETE------------------------------------------------------------------------------------------------------------------------------
        // request = new HttpEntity<String>("", headers);
        // this.restTemplate.delete("http://"+System.getenv("HOST")+":" + port + "restaurant/cocina/delete/69");
        // assertThat("Borra la cocina por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/cocina/get/69",String.class)
        // ,is("[]"));
        // //Prueba de CAMARERO------------------------------------------------------------------------------------------------------------------------------
        // //Prueba de POST && GET------------------------------------------------------------------------------------------------------------------------------
        // request = 
        //         new HttpEntity<String>("{\"idmesa\":69,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        // this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/camarero/post",request,
        //     String.class);
        // assertThat("Inserta camarero por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/camarero/get/69",String.class)
        // ,is(not("[]")));
        // //Prueba de SEND------------------------------------------------------------------------------------------------------------------------------         
        // assertThat(this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/camarero/price/69",String.class),is(not("0")));
        // //Prueba de DELETE------------------------------------------------------------------------------------------------------------------------------
        // request = new HttpEntity<String>("", headers);
        // this.restTemplate.delete("http://"+System.getenv("HOST")+":" + port + "restaurant/camarero/delete/69");
        // assertThat("Borra el camarero por HTTP",this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + port + "restaurant/camarero/get/69",String.class)
        // ,is("[]"));
        //Prueba de HELLO------------------------------------------------------------------------------------------------------------------------------
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",String.class)
        ,is("Hello World!"));
    }
}
