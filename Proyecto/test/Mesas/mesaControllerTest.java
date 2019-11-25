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

import org.json.JSONException;

import static org.hamcrest.CoreMatchers.*;

import Mesas.mesaController;
import Mesas.plato;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class mesaControllerTest {
    
    @LocalServerPort
    private int port;
    
    private mesaController controllerTest;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        controllerTest = new mesaController();
    }

    @Test
    public void testCrearMesa() throws JSONException {
        controllerTest.crearMesa();
        assertThat("IdCount cambiado",1,is(controllerTest.getIdCount()));
        assertThat("Rellenado Correctamente","[0]",is(controllerTest.getPedido(0)));
    }
    @Test
    public void testpostPedido() throws JSONException {
        controllerTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        controllerTest.postPedido(platoAux);
        assertThat("Json rellenado correctamente","[0,{\"Cantidad\":1,\"Plato\":\"PolloFrito\",\"Precio\":1.2}]",is(controllerTest.getPedido(0)));
    }
    @Test
    public void testGetPedido() throws JSONException {
        controllerTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        controllerTest.postPedido(platoAux);
        assertThat("Se devuelve el Json esperado","[0,{\"Cantidad\":1,\"Plato\":\"PolloFrito\",\"Precio\":1.2}]",is(controllerTest.getPedido(0)));
    }
    @Test 
    public void testDeletePedido() throws JSONException {
        controllerTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        controllerTest.postPedido(platoAux);
        controllerTest.deletePedido(0);
        assertThat("Borrado Correctamente","[0]",is(controllerTest.getPedido(0)));
    }
    @Test
    public void testGetIdCount() throws JSONException {
        controllerTest.crearMesa();
        assertThat("Numero de Mesas correcto",1,is(controllerTest.getIdCount()));
    }
    @Test
    public void testConetions() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = 
        new HttpEntity<String>("", headers);

        this.restTemplate.postForObject("http://localhost:" + port + "/crearmesa",request,
                String.class);

        assertThat("Crea la mesa por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
                    ,is("[0]"));

        request = 
                new HttpEntity<String>("{\"idmesa\":0,\"nombre\":\"plato0\", \"precio\":1.4, \"cantidad\":1}", headers);
        
        this.restTemplate.postForObject("http://localhost:" + port + "/hacerpedido",request,
            String.class);

        assertThat("Inserta el plato por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
        ,is("[0,{\"Cantidad\":1,\"Plato\":\"plato0\",\"Precio\":1.4}]"));
        
        this.restTemplate.delete("http://localhost:" + port + "/borrapedido/0");

        assertThat("Borra el pedido por HTTP",this.restTemplate.getForObject("http://localhost:" + port + "/verpedido/0",String.class)
        ,is("[0]"));
    }
}
