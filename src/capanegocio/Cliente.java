package capanegocio;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{

    private List<Factura> facturas;
    private List<Reserva> reservas;
    
    public Cliente(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
        this.facturas = new ArrayList<Factura>();
        this.reservas = new ArrayList<Reserva>();
    }
    
    public Factura buscarFactura(){
        return null;
    }
    
    public Reserva buscarReserva(){
        return null;
    }
    
    public Factura agregarFactura(){
        return null;
    }
   
    public Reserva agregarReserva(){
        return null;
    }
    
    public Factura buscarFactura(Factura factura){
        for(Factura factu: facturas){
            if(factu.equals(factura)){
                return factura;
            }
        }
        return null;
    }
    
    public void agregarFactura(Factura factura){
        facturas.add(factura);
    }
    
    public Reserva buscarReserva(Reserva reserva){
        for(Reserva reserv: reservas){
            if(reserv.equals(reserva)){
                return reserv;
            }
        }
        return null;
    }
    
    public void agregarReserva(Reserva reserva){
        reservas.add(reserva);
    }
}