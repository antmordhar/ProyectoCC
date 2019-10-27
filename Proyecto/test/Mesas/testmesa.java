package Mesas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class testmesa {
	private mesa mesatest;
	@Test
	public void testConstruction() {
		mesatest= new mesa();
		assertNotEquals("ID mesa Correcto",0,mesatest.getNummesa());
		
		int nummesatest=mesatest.getNummesa();
		JSONArray Jtest = new JSONArray();
		Jtest.put(nummesatest);
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testGetNummesa() {
		mesatest= new mesa();
		assertNotEquals("ID mesa Correcto",0,mesatest.getNummesa());
	}
	@Test
	public void testGetPedido() {
		mesatest= new mesa();
		JSONArray Jtest = new JSONArray();
		Jtest.put(mesatest.getNummesa());
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testaniadirPlato() {
		mesatest= new mesa();
		mesatest.aniadirPlato("PolloFrito", 5, 2);
		JSONArray Jtest = new JSONArray();
		Jtest.put(mesatest.getNummesa());
		JSONObject nuevoped = new JSONObject();
		nuevoped.put("plato", "PolloFrito");
		nuevoped.put("precio", 5);
		nuevoped.put("Cantidad", 2);
		Jtest.put(nuevoped);
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	
}