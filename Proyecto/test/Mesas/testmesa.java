package Mesas;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class testmesa {
	private mesa mesatest;
	
	@Before
	public void setup(){
		mesatest=new mesa();
	}

	@Test
	public void testConstruction() throws JSONException {
		int nummesa=mesatest.getNummesa();
		assertThat("ID mesa Correcto",0,is(not(mesatest.getNummesa())));
		
		int nummesatest=mesatest.getNummesa();
		JSONArray Jtest = new JSONArray();
		Jtest.put(nummesatest);
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testGetNummesa() {
		int nummesa=mesatest.getNummesa();
		assertThat("ID mesa Correcto",0,is(not(mesatest.getNummesa())));
	}
	@Test
	public void testGetPedido() throws JSONException {
		JSONArray Jtest = new JSONArray();
		Jtest.put(mesatest.getNummesa());
		JSONAssert.assertEquals(Jtest,mesatest.getPedido(),  JSONCompareMode.LENIENT);
	}
	@Test
	public void testaniadirPlato() throws JSONException {
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
