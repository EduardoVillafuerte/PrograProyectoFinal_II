package capanegocio;


public class Reserva {
    private String fechaReserva;
    private String fechaInicio;
    private String fechaFin;
    private String habitacion;
    private String diasTotal;

    public Reserva(String habitacion, String fechaFin, String fechaInicio, String fechaReserva, String diasTotal) {
        this.habitacion = habitacion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.fechaReserva = fechaReserva;
        this.diasTotal = diasTotal;
    }

}
