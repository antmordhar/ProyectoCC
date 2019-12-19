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
    @Autowired
    private platoRepository repository;

    private RestTemplate restTemplate=new RestTemplate();

    private int port=8081;

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
    
    //Le especificamos que path llamara a esta funcion
    //Value id quiere decir que leera esa variable de la url
    @PostMapping(value= "/pedido/send/{id}")
    public String finishPedido(@PathVariable(value= "id") final int id){
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<plato> pedido=repository.findByIdmesa(id);
        for(plato plato:pedido){
            request = 
            new HttpEntity<String>("{\"idmesa\":"+plato.getIDmesa()+",\"nombre\":\""+plato.getNombre()+"\", \"precio\":"+plato.getPrecio()+", \"cantidad\":"+plato.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + port + "/cocina/post",request,String.class);
        }  
        return "OK"; 
    }
}
