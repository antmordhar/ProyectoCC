package Cocina;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Clase que servira como base para el cuerpo de nuestros JSON en la peticion hacerpedido
//Document indica donde guardara este objeto mongo
@Document(collection = "platos")
public class plato{

    @Id
    private String id;

    private int idmesa;
    private String nombre;
    private double precio;
    private int cantidad;

    //Constructor
    public plato(int idmesa,String nombre,double precio,int cantidad){
        this.idmesa=idmesa;
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
    }
    //Constructor por defecto
    public plato(){}

    //Geters
    public int getIDmesa(){
        return idmesa;
    }
    public String getNombre(){
        return this.nombre;
    }
    public double getPrecio(){
        return this.precio;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    //Seters
    public void setIDmesa(int idmesa){
        this.idmesa=idmesa;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setPrecio(double precio){
        this.precio=precio;
    }
    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
    //Metodo to string
    @Override
    public String toString(){
        String r = "";
        
        r +="idmesa: " + this.idmesa +
            " Nombre: " + this.nombre +
            " precio: " + this.precio + 
            " cantidad: " + this.cantidad ;

        return r ;
    }
}