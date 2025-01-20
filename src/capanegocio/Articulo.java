
package capanegocio;

import java.util.List;

public class Articulo {

    private String nombre;
    private double precio;
    private Categoria categoria;
    private int cantidadDisponible;
    private TipoAlerta prioridad;
    public static final int LIMITEBAJO = 10;
    public static final int LIMITEMEDIO = 20;
    public static final int LIMITEALTO = 35;

    public Articulo(String nombre, double precio, Categoria categoria, int cantidadDisponible, TipoAlerta prioridad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
        this.prioridad = prioridad;
    }
    
    public Articulo(String nombre, int cantidad, float precio) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
        actualizarPrioridad();
    }

    public  TipoAlerta getPrioridad(){
        return  prioridad;
    }
    public void setPrioridad(TipoAlerta prioridad) {
        this.prioridad = prioridad;
    }

    private void actualizarPrioridad() {
        if (cantidadDisponible <= LIMITEBAJO) {
            prioridad = TipoAlerta.ALTA;
        } else if (cantidadDisponible <= LIMITEMEDIO) {
            prioridad = TipoAlerta.MEDIA;
        } else if (cantidadDisponible < LIMITEALTO) {
            prioridad = TipoAlerta.BAJA;
        } else {
            prioridad = TipoAlerta.NA;
        }
    }

}
