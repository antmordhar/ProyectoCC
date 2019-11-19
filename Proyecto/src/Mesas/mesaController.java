package Mesas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Mesas.mesa;
import Mesas.plato;

@RestController
public class mesaController {
    int idcount=0;
    private List<mesa> mesas=new ArrayList<mesa>();

    @GetMapping(value= "/getpedido")
    public String getPedido(@RequestParam(value= "id") int id) {
        return mesas.get(id).getPedido().toString();
    }
    @PostMapping(value= "/postpedido")
    public ResponseEntity<String> postPedido(@RequestBody plato pedido) throws JSONException { 
        System.out.println(pedido.getIDmesa());
        System.out.println(pedido.getNombre());
        System.out.println(pedido.getPrecio());
        System.out.println(pedido.getCantidad());
        mesas.get(pedido.getIDmesa()).aniadirPlato(pedido.getNombre(),pedido.getPrecio(),pedido.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping(value= "/crearmesa")
    public ResponseEntity<String> crearMesa() throws JSONException { 
        mesa mesa = new mesa(idcount);
        mesas.add(mesa);
        idcount++;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //Falta un delete pedido por implementar
}