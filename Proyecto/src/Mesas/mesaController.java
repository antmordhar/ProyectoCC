package Mesas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Mesas.mesa;
import Mesas.plato;

@RestController
public class mesaController {
    private int idcount=0;
    private List<mesa> mesas=new ArrayList<mesa>();

    @GetMapping(value= "/verpedido/{id}")
    public String getPedido(@PathVariable(value= "id") int id) {
        return mesas.get(id).getPedido().toString();
    }
    @PostMapping(value= "/hacerpedido")
    public void postPedido(@RequestBody plato pedido) throws JSONException { 
        mesas.get(pedido.getIDmesa()).aniadirPlato(pedido.getNombre(),pedido.getPrecio(),pedido.getCantidad());
    }
    @PostMapping(value= "/crearmesa")
    public void crearMesa() throws JSONException { 
        mesa mesa = new mesa(idcount);
        mesas.add(mesa);
        idcount++;
    }
    @DeleteMapping(value= "/borrapedido/{id}")
    public void deletePedido(@PathVariable(value= "id") int id){
        mesas.get(id).limpiarPlatos();
    }
    public int getIdCount(){
        return idcount;
    }
}