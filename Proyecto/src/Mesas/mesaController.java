package Mesas;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Mesas.plato;

//Decimos que es un controlador REST
@RestController
public class mesaController {

    //Aquí se realiza la inyección de dependencias
    @Autowired
    private platoRepository repository;

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }

    // Le especificamos que path llamara a esta funcion
    // Value id quiere decir que leera esa variable de la url
    @GetMapping(value = "/pedido/get/{id}")
    public String getPedido(@PathVariable(value = "id") final int id) {
        return repository.findByIdmesa(id).toString();
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    @PostMapping(value = "/pedido/post")
    public void postPedido(@RequestBody final plato pedido) throws JSONException {
        repository.save(pedido);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    @DeleteMapping(value= "/pedido/delete/{id}")
    public void deletePedido(@PathVariable(value= "id") final int id){
        repository.deletePlatoByIdmesa(id);
    }
}
