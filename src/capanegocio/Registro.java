package capanegocio;

public class Registro {
    private Reserva reserva;
    private EstadoRegistro estadoRegistro;
    private Fecha fechaCheckin;
    private Fecha fechaCheckOut;

    public Registro(Reserva reserva) {
        this.reserva = reserva;
        this.estadoRegistro = EstadoRegistro.CHECK_IN;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public EstadoRegistro getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(EstadoRegistro estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Fecha getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(Fecha fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public Fecha getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Fecha fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public void realizarCheckin(Fecha fecha) {
        this.fechaCheckin = fecha;
        this.estadoRegistro = EstadoRegistro.CHECK_IN;
    }

    public void realizarCheckOut(Fecha fecha) {
        this.fechaCheckOut = fecha;
        this.estadoRegistro = EstadoRegistro.CHECK_OUT;
    }
}
