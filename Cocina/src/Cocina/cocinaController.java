package Cocina;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Cocina.plato;

//Decimos que es un controlador REST
@RestController
public class cocinaController {

    // Aquí se realiza la inyección de dependencias
    //Se autoinstancia al arrancar el servicio
    //Para mas informacion de como funciona visita la clase correspondiente
    @Autowired
    private platoRepository repository;
    
    //Delcaramos un objeto para poder hacer las peticiones rest
    private RestTemplate restTemplate=new RestTemplate();

    //puerto deñ servicio camarero al que haremos peticiones
    private int port=8082;
    
    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }

    // Le especificamos que path llamara a esta funcion
    // Value id quiere decir que leera esa variable de la url
    // Devuelve una lista de platos
    @GetMapping(value = "/cocina/{id}")
    public String getCocina(@PathVariable(value = "id") final int id) {
        return repository.findByIdmesa(id).toString();
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    // Guarda las mesas en la base de datos
    @PostMapping(value = "/cocina")
    public void postCocina(@RequestBody final plato pedido) throws JSONException {
        repository.save(pedido);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //Borra las mesas de la base de datos que contengan el id pasado
    @DeleteMapping(value= "/cocina/{id}")
    public void deleteCocina(@PathVariable(value= "id") final int id){
        repository.deletePlatoByIdmesa(id);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //esta funcioon sera llamada cuando los platos esten listos
    @PostMapping(value= "/cocina/{id}")
    public String sentCocina(@PathVariable(value= "id") final int id){
        //creamos una request
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //buscamos los platos de la id indicada
        List<plato> pedido=repository.findByIdmesa(id);
        //para cada plato le mando una peticion a camarero para que me guarde el plato
        for(plato plato:pedido){
            request = 
            new HttpEntity<String>("{\"idmesa\":"+plato.getIDmesa()+",\"nombre\":\""+plato.getNombre()+"\", \"precio\":"+plato.getPrecio()+", \"cantidad\":"+plato.getPrecio()+"}", headers);
            //System.getev busca una variable con el nombre dado
            this.restTemplate.postForObject("http://"+System.getenv("HOST_CAMARERO")+":" + port + "/camarero",request,String.class);
        }  
        return "OK";  
    }
}
