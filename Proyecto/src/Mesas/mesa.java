package Mesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mesa {

	private final int  nummesa;
	JSONArray pedido;
	 
	public mesa(int id) {
		this.nummesa=id;
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
		nuevoped.put("Plato", nombre);
		nuevoped.put("Precio", precio);
		nuevoped.put("Cantidad", cantidad);
		pedido.put(nuevoped);
		
	}

	//Falta limpiar pedido por implementar
	
}
