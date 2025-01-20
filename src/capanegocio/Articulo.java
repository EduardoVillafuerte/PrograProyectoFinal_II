
package capanegocio;

public class Articulo {

    private String nombre;
    private Categoria categoria;
    private int cantidadDisponible;
    private TipoAlerta prioridad;
    public static final int LIMITEBAJO = 10;
    public static final int LIMITEMEDIO = 20;
    public static final int LIMITEALTO = 35;

    public Articulo(String nombre, Categoria categoria, int cantidadDisponible, TipoAlerta prioridad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
