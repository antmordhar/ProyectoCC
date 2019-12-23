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

    //Delcaramos un objeto para poder hacer las peticiones rest
    private RestTemplate restTemplate=new RestTemplate();

    //puerto de los demas servicios la que les haremos peticiones
    private int portmesas=8080;
    private int portcocina=8081;
    private int portcamarero=8082;

    // Mensaje por defecto de la aplicación
    @GetMapping(value = "/")
    public String helloWorld() {
        return "Hello World!";
    }
    //Funciones de Mesas-----------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "restaurant/pedido/{id}")
    public String getPedido(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST_MESAS")+":" + portmesas + "/pedido/"+id,String.class);
    }
    @PostMapping(value = "restaurant/pedido")
    public void postPedido(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST_MESAS")+":" + portmesas + "/pedido",request,String.class);
    }
    @DeleteMapping(value= "restaurant/pedido/{id}")
    public void deletePedido(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST_MESAS")+":" + portmesas + "/pedido/"+id);
    }
    @PostMapping(value= "restaurant/pedido/{id}")
    public String finishPedido(@PathVariable(value= "id") final int id){
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity<String>("", headers);
        this.restTemplate.postForObject("http://"+System.getenv("HOST_MESAS")+":" + portmesas + "/pedido/"+id,request,String.class); 
        return "OK";   
    }
    //Funciones de Cocina-----------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "restaurant/cocina/{id}")
    public String getCocina(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST_COCINA")+":" + portcocina + "/cocina/"+id,String.class);
    }
    @PostMapping(value = "restaurant/cocina")
    public void postCocina(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST_COCINA")+":" + portcocina + "/cocina",request,String.class);
    }
    @DeleteMapping(value= "restaurant/cocina/{id}")
    public void deleteCocina(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST_COCINA")+":" + portcocina + "/cocina/"+id);
    }
    @PostMapping(value= "restaurant/cocina/{id}")
    public String sendCocina(@PathVariable(value= "id") final int id){
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity<String>("", headers);
        this.restTemplate.postForObject("http://"+System.getenv("HOST_COCINA")+":" + portcocina + "/cocina/"+id,request,String.class); 
        return "OK";   
    }
    //Funciones de Camarero-----------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "restaurant/camarero/{id}")
    public String getCamarero(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST_CAMARERO")+":" + portcamarero + "/camarero/"+id,String.class);
    }
    @GetMapping(value = "restaurant/camarero/price/{id}")
    public String priceCamarero(@PathVariable(value = "id") final int id) {
        return this.restTemplate.getForObject("http://"+System.getenv("HOST_CAMARERO")+":" + portcamarero + "/camarero/price/"+id,String.class);
    }
    @PostMapping(value = "restaurant/camarero")
    public void postCamarero(@RequestBody final plato pedido) throws JSONException {
        HttpEntity<String> request;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = 
            new HttpEntity<String>("{\"idmesa\":"+pedido.getIDmesa()+",\"nombre\":\""+pedido.getNombre()+"\", \"precio\":"+pedido.getPrecio()+", \"cantidad\":"+pedido.getPrecio()+"}", headers);
         this.restTemplate.postForObject("http://"+System.getenv("HOST_CAMARERO")+":" + portcamarero + "/camarero",request,String.class);
    }
    @DeleteMapping(value= "restaurant/camarero/{id}")
    public void deleteCamarero(@PathVariable(value= "id") final int id){
        this.restTemplate.delete("http://"+System.getenv("HOST_CAMARERO")+":" + portcamarero + "/camarero/"+id);
    }
}
