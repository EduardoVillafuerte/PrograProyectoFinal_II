package capanegocio;

import java.util.ArrayList;
import java.util.Arrays;

public class Reserva {
    private int numReserva;
    private Fecha fechaReserva;
    private Fecha fechaInicio;
    private Fecha fechaFin;
    private Habitacion habitacion;
    private int diasTotal;

    public Reserva(Habitacion habitacion, Fecha fechaFin, Fecha fechaInicio, Fecha fechaReserva, int numReserva, int diasTotal) {
        this.habitacion = habitacion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.fechaReserva = fechaReserva;
        this.numReserva = numReserva;
        this.diasTotal = diasTotal;
    }

    public int getDiasTotal() {
        return diasTotal;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }
}
