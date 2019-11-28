package Mesas;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.json.JSONException;

import Mesas.mesas;
import Mesas.plato;

//Clase de test para mesas
public class mesasTest {
    
    
    private mesas mesasTest;

    @Before
    public void setup() {
        mesasTest = new mesas();
    }

    @Test
    public void testCrearMesa() throws JSONException {
        mesasTest.crearMesa();
        assertThat("IdCount cambiado",1,is(mesasTest.getIdCount()));
        assertThat("Rellenado Correctamente","[0]",is(mesasTest.getPedido(0)));
    }
    @Test
    public void testpostPedido() throws JSONException {
        mesasTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        mesasTest.hacerPedido(platoAux);
        assertThat("Json rellenado correctamente","[0,{\"Cantidad\":1,\"Plato\":\"PolloFrito\",\"Precio\":1.2}]",is(mesasTest.getPedido(0)));
    }
    @Test
    public void testGetPedido() throws JSONException {
        mesasTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        mesasTest.hacerPedido(platoAux);
        assertThat("Se devuelve el Json esperado","[0,{\"Cantidad\":1,\"Plato\":\"PolloFrito\",\"Precio\":1.2}]",is(mesasTest.getPedido(0)));
    }
    @Test 
    public void testDeletePedido() throws JSONException {
        mesasTest.crearMesa();
        plato platoAux = new plato(0,"PolloFrito", 1.2, 1);
        mesasTest.hacerPedido(platoAux);
        mesasTest.borrarPedido(0);
        assertThat("Borrado Correctamente","[0]",is(mesasTest.getPedido(0)));
    }
    @Test
    public void testGetIdCount() throws JSONException {
        mesasTest.crearMesa();
        assertThat("Numero de Mesas correcto",1,is(mesasTest.getIdCount()));
    }
}
