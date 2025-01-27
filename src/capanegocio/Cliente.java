package capanegocio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{

    private List<Factura> facturas = new ArrayList<Factura>();
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private String pass;
    private String correo;
    private String celular;
    
    public Cliente(String cedula, String nombre, String apellido, String pass, String correo, String celular) {
        super(cedula, nombre, apellido);
        this.pass = pass;
        this.correo = correo;
        this.celular = celular;
    }
    
    public Cliente(){}
    
    public Factura buscarFactura(Factura factura){
        for(Factura factu: facturas){
            if(factu.equals(factura)){
                return factura;
            }
        }
        return null;
    }
    
    public String getPass(){
        return this.pass;
    }
    
    public String getCorreo(){
        return this.correo;
    }
    
    public String getCelular(){
        return this.celular;
    }
    
    public void agregarFactura(Factura factura){
        StringBuilder factur = new StringBuilder();
        factur.append("*****************************************************\n");
        factur.append("                     FACTURA                         \n");
        factur.append("*****************************************************\n");
        factur.append("Cedula: ").append(cedula).append("\n");
        factur.append("Cliente: ").append(nombre).append("\n");
        factur.append("Fecha: ").append(factura.getFecha()).append("\n");
        factur.append("\n");
        factur.append("Detalle de compra:\n");
        factur.append("-----------------------------------------------------\n");
        factur.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Cantidad", "Precio", "Subtotal"));
        factur.append("-----------------------------------------------------\n");

        for(Articulo articulo: factura.getArticulos()){
            double subtotal = articulo.getCantidadDisponible() * articulo.getPrecio();
            factur.append(String.format("%-20s %-10d $%-9.2f $%-9.2f\n", articulo.getNombre(), articulo.getCantidadDisponible(),articulo.getPrecio(), subtotal));
        }

        factur.append("---------------------------------------------\n");
        factur.append(String.format("Total: $%.2f\n", factura.getTotal()));
        factur.append("*****************************************************\n");
        factur.append("              ¡Gracias por su compra!                \n");
        factur.append("*****************************************************\n");
        
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\facturas\\factura_"+factura.getNumFac()+".txt"))) {
            writer.write(factur.toString());
            System.out.println("Factura guardada correctamente en 'factura.txt'.");
            facturas.add(factura);

        } catch (Exception e) {
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }        
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