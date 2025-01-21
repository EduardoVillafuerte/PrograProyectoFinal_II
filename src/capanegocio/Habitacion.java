package capanegocio;

public class Habitacion {

    private int numHabitacion;
    private TipoHabitacion tipoHabitacion;
    private double precioNoche;

    public Habitacion(int numHabitacion, double precioNoche, TipoHabitacion tipoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.precioNoche = precioNoche;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Habitacion(){}

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

}
