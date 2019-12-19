package API;

import org.json.JSONException;
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

import API.plato;

//Decimos que es un controlador REST que servira como nuestra API
@RestController
public class APIController {

    private RestTemplate restTemplate=new RestTemplate();

    private int portmesas=8080;
    private int portcocina=8081;
    private int portcamarero=8082;

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }
    //Funciones de Mesas
    @GetMapping(value = "restaurant/pedido/get/{id}")
    public String getPedido(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + portmesas + "/pedido/get/"+id,String.class);
    }

    @PostMapping(value = "restaurant/pedido/post")
    public void postPedido(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + portmesas + "/pedido/post",request,String.class);
    }

    @DeleteMapping(value= "restaurant/pedido/delete/{id}")
    public void deletePedido(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST")+":" + portmesas + "/pedido/delete/"+id);
    }
    
    @PostMapping(value= "restaurant/pedido/send/{id}")
    public void finishPedido(@PathVariable(value= "id") final int id){
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity<String>("", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + portmesas + "/pedido/send/"+id,request,String.class);    
    }
    //Funciones de Cocina
    @GetMapping(value = "restaurant/cocina/get/{id}")
    public String getCocina(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + portcocina + "/cocina/get/"+id,String.class);
    }

    @PostMapping(value = "restaurant/cocina/post")
    public void postCocina(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + portcocina + "/cocina/post",request,String.class);
    }

    @DeleteMapping(value= "restaurant/cocina/delete/{id}")
    public void deleteCocina(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST")+":" + portcocina + "/cocina/delete/"+id);
    }

    @PostMapping(value= "restaurant/cocina/send/{id}")
    public void sendCocina(@PathVariable(value= "id") final int id){
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity<String>("", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + portcocina + "/cocina/send/"+id,request,String.class);    
    }
    //Funciones de Camarero
    @GetMapping(value = "restaurant/camarero/get/{id}")
    public String getCamarero(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + portcamarero + "/camarero/get/"+id,String.class);
    }

    @GetMapping(value = "restaurant/camarero/price/{id}")
    public String priceCamarero(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST")+":" + portcamarero + "/camarero/price/"+id,String.class);
    }

    @PostMapping(value = "restaurant/camarero/post")
    public void postCamarero(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST")+":" + portcamarero + "/camarero/post",request,String.class);
    }

    @DeleteMapping(value= "restaurant/camarero/delete/{id}")
    public void deleteCamarero(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST")+":" + portcamarero + "/camarero/delete/"+id);
    }
    
}
