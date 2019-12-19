package Camarero;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Camarero.plato;

//Decimos que es un controlador REST
@RestController
public class camareroController {

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
    @GetMapping(value = "/camarero/get/{id}")
    public String getCamarero(@PathVariable(value = "id") final int id) {
        return repository.findByIdmesa(id).toString();
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    @PostMapping(value = "/camarero/post")
    public void postCamarero(@RequestBody final plato pedido) throws JSONException {
        repository.save(pedido);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    @DeleteMapping(value= "/camarero/delete/{id}")
    public void deleteCamarero(@PathVariable(value= "id") final int id){
        repository.deletePlatoByIdmesa(id);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    @GetMapping(value= "/camarero/price/{id}")
    public double priceCamarero(@PathVariable(value= "id") final int id){
        List<plato> pedido=repository.findByIdmesa(id);
        double precio=0.0;
        for(plato plato:pedido){
            precio+=plato.getPrecio()*plato.getCantidad();
        }
        return precio;
    }
}
