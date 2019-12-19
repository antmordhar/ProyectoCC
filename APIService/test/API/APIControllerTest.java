package API;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
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

        //Prueba de HELLO------------------------------------------------------------------------------------------------------------------------------
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",String.class)
        ,is("Hello World!"));
    }
}
