package Mesas;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class testmesa {
	private mesa mesatest;
	@Test
	public void testConstruction() throws JSONException {
		mesatest= new mesa();
		int nummesa=mesatest.getNummesa();
		assertEquals("ID mesa Correcto",nummesa,mesatest.getNummesa());
		
		int nummesatest=mesatest.getNummesa();
		JSONArray Jtest = new JSONArray();
		Jtest.put(nummesatest);
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testGetNummesa() {
		mesatest= new mesa();
		int nummesa=mesatest.getNummesa();
		assertEquals("ID mesa Correcto",nummesa,mesatest.getNummesa());
	}
	@Test
	public void testGetPedido() throws JSONException {
		mesatest= new mesa();
		JSONArray Jtest = new JSONArray();
		Jtest.put(mesatest.getNummesa());
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testaniadirPlato() throws JSONException {
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
