package Mesas;

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

import Mesas.plato;

//Decimos que es un controlador REST
@RestController
public class mesaController {

    // Aquí se realiza la inyección de dependencias
    //Se autoinstancia al arrancar el servicio
    //Para mas informacion de como funciona visita la clase correspondiente
    @Autowired
    private platoRepository repository;

    //Delcaramos un objeto para poder hacer las peticiones rest
    private RestTemplate restTemplate=new RestTemplate();

    //puerto del servicio cocina a la que haremos peticiones
    private int port=8081;

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }

    // Le especificamos que path llamara a esta funcion
    // Value id quiere decir que leera esa variable de la url
    // Devuelve una lista de platos
    @GetMapping(value = "/pedido/get/{id}")
    public String getPedido(@PathVariable(value = "id") final int id) {
        return repository.findByIdmesa(id).toString();
    }

    // Le especificamos que path llamara a esta funcion
    // Request body hara que busque como cuerpo de la peticion un JSON con las
    // variables que tenga la clase plato
    // Guarda las mesas en la base de datos
    @PostMapping(value = "/pedido/post")
    public void postPedido(@RequestBody final plato pedido) throws JSONException {
        repository.save(pedido);
    }

    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //Borra las mesas de la base de datos que contengan el id pasado
    @DeleteMapping(value= "/pedido/delete/{id}")
    public void deletePedido(@PathVariable(value= "id") final int id){
        repository.deletePlatoByIdmesa(id);
    }
    
    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    //Esta funcion sera llamada cuando la mesa mande el pedido
    @PostMapping(value= "/pedido/send/{id}")
    public String finishPedido(@PathVariable(value= "id") final int id){
        //creamos una request
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //buscamos los platos de la id indicada
        List<plato> pedido=repository.findByIdmesa(id);
        //para cada plato le mando una peticion a cocina para que me guarde el plato
        for(plato plato:pedido){
            request = 
            new HttpEntity<String>("{\"idmesa\":"+plato.getIDmesa()+",\"nombre\":\""+plato.getNombre()+"\", \"precio\":"+plato.getPrecio()+", \"cantidad\":"+plato.getPrecio()+"}", headers);
            //System.getev busca una variable con el nombre dado
            this.restTemplate.postForObject("http://"+System.getenv("HOST_COCINA")+":" + port + "/cocina/post",request,String.class);
        }  
        return "OK"; 
    }
}