package Mesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mesa {

	private int  nummesa;
	JSONArray pedido;
	 
	@SuppressWarnings("deprecation")
	public mesa() {
		this.nummesa=(int)(Math.random() * 50 + 1);;
		pedido = new JSONArray();
		pedido.put(new Integer(this.nummesa));
	 }
	public int getNummesa() {
		return nummesa;
	}
	
	public JSONArray getPedido(){
		return this.pedido;
	}
	
	public void aniadirPlato(String nombre,float precio,int cantidad) throws JSONException {
		JSONObject nuevoped = new JSONObject();
		nuevoped.put("plato", nombre);
		nuevoped.put("precio", precio);
		nuevoped.put("Cantidad", cantidad);
		pedido.put(nuevoped);
		
	}
	
}
