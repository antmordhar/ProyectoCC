package Mesas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import Mesas.mesa;
import Mesas.plato;

//Clase que almacena las mesas 
public class mesas {

    private List<mesa> mesas=new ArrayList<mesa>();
    private int idcount=0;
	 
    public String getPedido(int indice){
       return mesas.get(indice).getPedido().toString();
    }

    public void hacerPedido(plato pedido) throws JSONException {
        mesas.get(pedido.getIDmesa()).aniadirPlato(pedido.getNombre(),pedido.getPrecio(),pedido.getCantidad());
    }

    public void crearMesa(){
        mesa mesa = new mesa(idcount);
        mesas.add(mesa);
        idcount++;
    }
    
    public void borrarPedido(int indice){
        mesas.get(indice).limpiarPlatos();
    }

    public int getIdCount(){
        return idcount;
    }
}
