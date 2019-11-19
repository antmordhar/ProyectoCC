package Mesas;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.json.JSONException;

import static org.hamcrest.CoreMatchers.*;

import Mesas.mesaController;
import Mesas.plato;

public class mesaControllerTest {
    private mesaController controllerTest;

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
}
