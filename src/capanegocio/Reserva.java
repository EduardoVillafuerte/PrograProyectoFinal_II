package capanegocio;

import java.util.ArrayList;
import java.util.Arrays;

public class Reserva {
    private int numReserva;
    private Fecha fechaReserva;
    private Fecha fechaInicio;
    private Fecha fechaFin;
    private int habitacion;
    private int diasTotal;

    public Reserva(int habitacion, Fecha fechaFin, Fecha fechaInicio, Fecha fechaReserva, int numReserva, int diasTotal) {
        this.habitacion = habitacion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.fechaReserva = fechaReserva;
        this.numReserva = numReserva;
        this.diasTotal = diasTotal;
    }

}
