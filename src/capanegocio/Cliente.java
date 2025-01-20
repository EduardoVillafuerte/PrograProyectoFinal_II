package capanegocio;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{

    private List<Factura> facturas;
    private List<Reserva> reservas;

    public Cliente(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
        this.facturas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public Cliente(){}

    public String agregarFactura(Factura factura){
        facturas.add(factura);
        return "";
    }

    public String agregarReserva(Reserva reserva){
        reservas.add(reserva);
        return "";
    }

    public String eliminarFactura(Factura factura) {
        facturas.remove(factura);
        return "Factura eliminada";
    }

    public String eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        return "Reserva eliminada";
    }
}