package Mesas;

public class plato{

    private int idmesa;
    private String nombre;
    private double precio;
    private int cantidad;

    public plato(int idmesa,String nombre,double precio,int cantidad){
        this.idmesa=idmesa;
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
    }

    public plato(){}

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
}