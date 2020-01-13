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
    //Al arrancar el servicio automaticamente se pondra en comunicacion el bean de repository
    //con el bean del controller.
    //las configuraciones necesarias se realizaran automaticamente gracias a la notación @autowired
    //Para mas informacion de como se accede a los datos consulta la clase del repositorio
    //o puede ser revisado en la propia documentación
    @Autowired
    private platoRepository repository;

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }

    // Le especificamos que path llamara a esta funcion
    // Value id quiere decir que leera esa variable de la url
    // Devuelve una lista de platos
    @GetMapping(value = "/camarero/{id}")
    public String getCamarero(@PathVariable(value = "id") final int id) {
        return repository.findByIdmesa(id).toString();
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    // Guarda las mesas en la base de datos
    @PostMapping(value = "/camarero")
    public void postCamarero(@RequestBody final plato pedido) throws JSONException {
        repository.save(pedido);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //Borra las mesas de la base de datos que contengan el id pasado
    @DeleteMapping(value= "/camarero/{id}")
    public void deleteCamarero(@PathVariable(value= "id") final int id){
        repository.deletePlatoByIdmesa(id);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //Devuelve el precio total de todos los platos de una mesa
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
