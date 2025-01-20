package capanegocio;

import java.util.Arrays;

public class Habitacion {

    private int numHabitacion;
    private TipoHabitacion tipoHabitacion;
    private double precioNoche;
    private EstadoHabitacion[] dias; // Los 30 días con estados.

    public Habitacion(int numHabitacion, double precioNoche, TipoHabitacion tipoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.precioNoche = precioNoche;
        this.tipoHabitacion = tipoHabitacion;
        this.dias = new EstadoHabitacion[30];
        Arrays.fill(dias, EstadoHabitacion.DISPONIBLE); // Inicializar todos los días como DISPONIBLE.
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

    public EstadoHabitacion[] getDias() {
        return dias;
    }

    public boolean reservarDias(int diaInicio, int diaFin) {
        if (diaInicio < 1 || diaFin > 30 || diaInicio > diaFin) {
            return false; // Rango de días inválido.
        }

        for (int i = diaInicio - 1; i < diaFin; i++) {
            if (dias[i] != EstadoHabitacion.DISPONIBLE) {
                return false;
            }
        }

        for (int i = diaInicio - 1; i < diaFin; i++) {
            dias[i] = EstadoHabitacion.OCUPADA;
        }

        return true;
    }

    public boolean cancelarReserva(int diaInicio, int diaFin) {
        if (diaInicio < 1 || diaFin > 30 || diaInicio > diaFin) {
            return false;
        }

        for (int i = diaInicio - 1; i < diaFin; i++) {
            if (dias[i] == EstadoHabitacion.OCUPADA) {
                dias[i] = EstadoHabitacion.DISPONIBLE;
            }
        }

        return true;
    }

    public boolean estaDisponible(int diaInicio, int diaFin) {
        if (diaInicio < 1 || diaFin > 30 || diaInicio > diaFin) {
            return false; // Rango de días inválido.
        }

        for (int i = diaInicio - 1; i < diaFin; i++) {
            if (dias[i] != EstadoHabitacion.DISPONIBLE) { // Si algún día no está disponible, retornar false.
                return false;
            }
        }

        return true;
    }


}
