package Mesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Mesas.mesa;

@RestController
public class mesaController {
    private mesa mesa= new mesa(0);

    @GetMapping("/getpedido")
    public JSONArray getPedido() {
        return mesa.getPedido();
    }

    @PostMapping("/postpedido")
    public void postPedido(@RequestParam(value="nombre",defaultValue="Test") String nombre, 
                            @RequestParam(value="precio",defaultValue="0")float precio, 
                            @RequestParam(value="cantidad",defaultValue="0")int cantidad) throws JSONException {
        mesa.aniadirPlato(nombre,precio,cantidad);
    }
    //Falta un delete pedido por implementar
}