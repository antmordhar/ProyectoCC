package Mesas;

import org.json.JSONException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Mesas.mesas;
import Mesas.plato;

//Decimos que es un controlador REST
@RestController
public class mesaController {
    private final mesas mismesas = new mesas();

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }

    // Le especificamos que path llamara a esta funcion
    // Value id quiere decir que leera esa variable de la url
    @GetMapping(value = "/verpedido/{id}")
    public String getPedido(@PathVariable(value = "id") final int id) {
        return mismesas.getPedido(id);
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    @PostMapping(value = "/hacerpedido")
    public void postPedido(@RequestBody final plato pedido) throws JSONException {
        mismesas.hacerPedido(pedido);
    }
    //Le especificamos que path llamara a esta funcion
    @PostMapping(value= "/crearmesa")
    public void crearMesa(){ 
        mismesas.crearMesa();
    }
    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    @DeleteMapping(value= "/borrapedido/{id}")
    public void deletePedido(@PathVariable(value= "id") final int id){
        mismesas.borrarPedido(id);
    }
}
