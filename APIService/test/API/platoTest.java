package API;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

//Clase de test para Plato
public class platoTest {
	private plato platotest;
	
	@Before
	public void setup(){
		platotest=new plato(0,"PolloFrito",1.4, 1);
	}
	
	//Test del constructor
	@Test
	public void testplato() {
		assertThat("ID mesa Correcto",0,is(platotest.getIDmesa()));
        assertThat("Plato Correcto","PolloFrito",is(platotest.getNombre()));
        assertThat("Precio Correcto",1.4,is(platotest.getPrecio()));
        assertThat("Cantidad Correcta",1,is(platotest.getCantidad()));
	}
	//Tests de los gets
	@Test
	public void testGetIDmesa() {
		assertThat("getID Correcto",0,is(platotest.getIDmesa()));
    }
	@Test
	public void testGetNombre() {
        assertThat("GetNombre Correcto","PolloFrito",is(platotest.getNombre()));
    }
	@Test
	public void testGetPrecio() {
        assertThat("GetPrecio Correcto",1.4,is(platotest.getPrecio()));
    }
	@Test
	public void testGetCantidad() {
        assertThat("GetCantidad Correcta",1,is(platotest.getCantidad()));
	}
	//Tests de los sets
	@Test
	public void testSetIDmesa() {
        platotest.setIDmesa(1);
		assertThat("SetID mesa Correcto",1,is(platotest.getIDmesa()));
    }

	@Test
	public void testSetNombre() {
        platotest.setNombre("PolloAsado");
        assertThat("SetNombre Correcto","PolloAsado",is(platotest.getNombre()));
    }
	@Test
	public void testsetPrecio() {
        platotest.setPrecio(2.0);
        assertThat("SetPrecio Correcto",2.0,is(platotest.getPrecio()));
    }
	@Test
	public void testsetCantidad() {
        platotest.setCantidad(2);
        assertThat("SetCantidad Correcta",2,is(platotest.getCantidad()));
	}

}
