package capanegocio;

import capapersistencia.ConexionSQL;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.*;

import static capanegocio.Articulo.LIMITEBAJO;
import static capanegocio.Articulo.LIMITEMEDIO;
import static capanegocio.Articulo.LIMITEALTO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;


public class Hotel {
    Cliente cliente = new Cliente();
    ConexionSQL cn = new ConexionSQL();
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<String> asignacionesRecepcion;
    private List<String> asignacionesConserje;
    private List<Articulo> articulos;

    public Hotel(){
        clientes = new ArrayList<Cliente>();
        empleados = new ArrayList<Empleado>();
        articulos = new ArrayList<Articulo>();
        asignacionesConserje = new ArrayList<String>();
        asignacionesRecepcion = new ArrayList<String>();
        articulos.add(new Articulo("Escoba",Categoria.LIMPIEZA,100,TipoAlerta.NA));
        articulos.add(new Articulo("Chicles",Categoria.ALIMENTO,7,TipoAlerta.ALTA));
        articulos.add(new Articulo("Dulces",Categoria.ALIMENTO,18,TipoAlerta.MEDIA));
        articulos.add(new Articulo("Almohadas",Categoria.ROPA_CAMA,53,TipoAlerta.NA));
        articulos.add(new Articulo("Trapeador",Categoria.LIMPIEZA,32,TipoAlerta.BAJA));
        articulos.add(new Articulo("Edredones",Categoria.ROPA_CAMA,12,TipoAlerta.MEDIA));
        articulos.add(new Articulo("Jabón",Categoria.ACCESORIO_BANIO,100,TipoAlerta.NA));
        articulos.add(new Articulo("Toallas",Categoria.ACCESORIO_BANIO,10,TipoAlerta.ALTA));
        asignacionesConserje.add("Gestión de residuos y reciclaje");
        asignacionesConserje.add("Limpieza de oficinas del primer piso");
        asignacionesConserje.add("Limpieza de la cafetería y áreas de descanso");
        asignacionesConserje.add("Mantenimiento de áreas comunes (pasillos y baños)");
        asignacionesConserje.add("Supervisión de suministros de limpieza y reparaciones menores");
        asignacionesRecepcion.add("Organización de agendas y citas de gerentes");
        asignacionesRecepcion.add("Coordinación de salas de reuniones y eventos");
        asignacionesRecepcion.add("Atención al cliente y registro de visitantes");
        asignacionesRecepcion.add("Gestión de correspondencia y llamadas telefónicas");
        asignacionesRecepcion.add("Gestión de solicitudes de acceso a áreas restringidas");
    }

    public ResultSet autenticarUsuario(String usuario, String contrasenia){
        return cn.loginUsuario(usuario,contrasenia);
    }

    public String agregarCliente(Cliente cliente){
        clientes.add(cliente);
        return "Se agrego con exito";
    }

    public List<Cliente> visualizarClientes() throws Exception {
        if(clientes.isEmpty()){
            throw new Exception("No existe ningun cliente");
        }
        return clientes;
    }

    public Cliente buscarCliente(String cedula){
        for(Cliente cliente: clientes){
            if(cliente.getCedula().equals(cedula)){
                return cliente;
            }
        }
        return null;
    }

    public String modificarCliente(String nombre, String apellido,Cliente cliente){
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        return "Realizado";
    }

    public String eliminarCliente(String cedula){
        for (Cliente c : clientes){
            if(c.getCedula().equals(cedula)) {
                clientes.remove(c);
                return "Se removio el cliente con cedula: "+cedula;
            }
        }
        return "";
    }

    public String agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
        return "Se agrego con exito";
    }

    public List<Empleado> visualizarEmpleado() throws Exception {
        if(empleados.isEmpty()){
            throw new Exception("No existe ningun cliente");
        }
        return empleados;
    }

    public Empleado buscarEmpleado(String cedula){
        for(Empleado empleado: empleados){
            if(empleado.getCedula().equals(cedula)){
                return empleado;
            }
        }
        return null;
    }

    public String modificarEmpleado(String nombre, String apellido, CargoEmpleado cargo, Empleado empleado){
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCargo(cargo);
        empleado.setDisponibilidad(true);
        empleado.setAsignaciones(new ArrayList<>());
        return "Realizado";

    }

    public String eliminarEmpleado(String cedula){
        for (Empleado e : empleados){
            if(e.getCedula().equals(cedula)) {
                empleados.remove(e);
                return "Se removio el empleado con cedula: "+cedula;
            }
        }
        return "";
    }

    public void visualizarAsignaciones(Empleado empleado) {
        if(empleado.getCargo() == CargoEmpleado.CONSERJE){
            System.out.println("Listdo de asignaciones conserje\n");
            for (int i = 0; i < 5; i++) {
                System.out.println(i+1 +". "+ asignacionesConserje.get(i));
            }
        }
        else {
            System.out.println("Listado de asignaciones recepcion:\n");
            for (int i = 0; i < 5; i++) {
                System.out.println(i+1 +". "+ asignacionesRecepcion.get(i));
            }
        }
    }

    public String asignarTarea(int opcion, Empleado empleado){
        if(empleado.getCargo() == CargoEmpleado.CONSERJE){
            empleado.agregarAsignacione(EstadoAsignacion.PENDIENTE,asignacionesConserje.get(opcion-1));
        }
        else {
            empleado.agregarAsignacione(EstadoAsignacion.PENDIENTE,asignacionesRecepcion.get(opcion-1));
        }
        return "Listo";
    }
//
//    public String modificarTarea(Empleado empleado, EstadoAsignacion estadoAsignacion){
//        if(empleado.getCargo() == CargoEmpleado.CONSERJE){
//            for(String asignacionTarea: asignacionesRecepcion){
//                if(asignacionTarea.equals(empleado))
//            }
//        }
//        else {
//            System.out.println("Listado de asignaciones recepcion:\n");
//            for (int i = 0; i < 5; i++) {
//                System.out.println(i+1 +". "+ asignacionesRecepcion.get(i));
//            }
//        }
//        empleado.agregarAsignacione(estadoAsignacion,);
//        return "Listo";
//    }

    public StringBuilder mostrarArticulos(){
        StringBuilder sb = new StringBuilder("Articulos: \n");
        sb.append("Descripcion:      Categoria:       Canitdad: \n");
        sb.append("---------------------------------------------\n");
        for(Articulo articulo : articulos){
            sb.append(articulo.getNombre()+" "+articulo.getCategoria().toString()+" "+articulo.getCantidadDisponible()+"\n");
        }
        return sb;
    }

    public Articulo buscarArticulo(String nombre){
        for(Articulo articulo : articulos){
            if(articulo.getNombre().equals(nombre)){
                return articulo;
            }
        }
        return null;
    }

    public void agregarArticulo(String nombre, Categoria categoria, int cantidad, TipoAlerta prioridad) {
        if(buscarArticulo(nombre) == null){
            articulos.add(new Articulo(nombre, categoria, cantidad, prioridad));
        }
        else {
            JOptionPane.showMessageDialog(null,"El articulo ya se encuetra registrado");
        }
    }

    public void eliminarArticulo(String nombre) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(null,"Artículo eliminado: " + articulos.get(i).getNombre());
                articulos.remove(i);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Artículo no encontrado: " + nombre);
    }

    public void modificarArticulo(Articulo articulo, String nombre, Categoria categoria, TipoAlerta prioridad, int cantidad){
        try {
            articulo.setNombre(nombre);
            articulo.setCategoria(categoria);
            articulo.setPrioridad(prioridad);
            articulo.setCantidadDisponible(cantidad);
            JOptionPane.showMessageDialog(null, "Realizado");
            System.out.println(articulo.getNombre());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se encuentra el producto a modificar");
        }
    }

    public void retirarArticulo(String nombre, int cantidad){
        Articulo articulo = buscarArticulo(nombre);
        if(articulo != null) {
            if (cantidad > articulo.getCantidadDisponible()) {
                JOptionPane.showMessageDialog(null, "No hay suficientes articulos");
            } else {
                int cantidadTotal = articulo.getCantidadDisponible() - cantidad;
                alertas(cantidadTotal, articulo);
                articulo.setCantidadDisponible(cantidadTotal);
                JOptionPane.showMessageDialog(null, "Se realizo con exito");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No se encontro el articulo");
    }

    public void alertas(int cantidad, Articulo articulo){
        if(cantidad <= LIMITEBAJO){
            articulo.setPrioridad(TipoAlerta.ALTA);
            JOptionPane.showMessageDialog(null,"La prioridad de este articulo es: "+TipoAlerta.ALTA);
        } else if (cantidad > LIMITEBAJO && cantidad <= LIMITEMEDIO) {
            articulo.setPrioridad(TipoAlerta.MEDIA);
            JOptionPane.showMessageDialog(null,"La prioridad de este articulo es: "+TipoAlerta.MEDIA);
        } else if (cantidad > LIMITEMEDIO && cantidad < LIMITEALTO) {
            articulo.setPrioridad(TipoAlerta.BAJA);
            JOptionPane.showMessageDialog(null,"La prioridad de este articulo es: "+TipoAlerta.BAJA);
        }
        else {
            articulo.setPrioridad(TipoAlerta.NA);
            JOptionPane.showMessageDialog(null,"El articulo no tiene prioridad");
        }
    }

    public void comprarArticulo(String nombre, int cantidad){
        Articulo articulo = buscarArticulo(nombre);
        if(articulo != null){
            articulo.setCantidadDisponible(articulo.getCantidadDisponible()+cantidad);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se encontro el articulo");
        }
    }

    public String agregarReserva(int numReserva, Fecha fechaReserva, Fecha fechaInicio, Fecha fechaFin, Habitacion habitacion){
        int diasTotal = fechaFin.getDia() - fechaInicio.getDia();
        cliente.agregarReserva(new Reserva(habitacion,fechaReserva,fechaInicio,fechaFin, numReserva,diasTotal));
        return "se registro la reserva";
    }
    public String agregarFactura(Cliente cliente, Reserva reserva, MetodoPago metodoPago){
        GregorianCalendar gc = new GregorianCalendar();
        int anio = gc.get(GregorianCalendar.YEAR);
        int mes = gc.get(GregorianCalendar.MONTH);
        int dia = gc.get(GregorianCalendar.DAY_OF_MONTH);
        double montTotal = reserva.getDiasTotal()* reserva.getHabitacion().getPrecioNoche();
        cliente.agregarFactura(new Factura((int)(Math.random()*100),cliente,new Fecha(dia,mes,anio),montTotal,metodoPago));
        return "se registro la reserva";
    }
    
    public ResultSet getInventario(String tabla){
        try{
            ResultSet rs = cn.obetenrDatos(tabla);
            return rs;
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        return null;
    }
   
    public static void guardarFactura(String nombreCliente, String fecha, List<String> productos, List<Integer> cantidades, List<Float> precios, List<Integer> cantidadesReales, float compraTotal) {
        int contador = 0;
        for(int i = 0; i < productos.size(); i++){
            contador++;
        }
        
        // Crear contenido de la factura
        StringBuilder factura = new StringBuilder();
        factura.append("*****************************************************\n");
        factura.append("         FACTURA            \n");
        factura.append("*****************************************************\n");
        factura.append("Cliente: ").append(nombreCliente).append("\n");
        factura.append("Fecha: ").append(fecha).append("\n");
        factura.append("\n");
        factura.append("Detalle de compra:\n");
        factura.append("-----------------------------------------------------\n");
        factura.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Cantidad", "Precio", "Subtotal"));
        factura.append("-----------------------------------------------------\n");

        for (int i = 0; i < productos.size(); i++) {
            float subtotal = cantidades.get(i) * precios.get(i);
            String producto = productos.get(i);
            int cantidad = cantidades.get(i);
            float precio = precios.get(i);
            factura.append(String.format("%-20s %-10d $%-9.2f $%-9.2f\n", producto, cantidad, precio, subtotal));
            ConexionSQL csn = new  ConexionSQL();
        }
        

        factura.append("---------------------------------------------\n");
        factura.append(String.format("Total: $%.2f\n", compraTotal));
        factura.append("*****************************************************\n");
        factura.append("       ¡Gracias por su compra!         \n");
        factura.append("*****************************************************\n");
        
        Number random = Math.round(Math.random()*1000);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("factura_"+random+".txt"))) {
            writer.write(factura.toString());
            System.out.println("Factura guardada correctamente en 'factura.txt'.");
        } catch (Exception e) {
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }
    }
    
    public void sumarArticulo( int cantidadReal, int cantidad ,String producto, String tabla ){
        System.out.println(producto);
        cn.sumarArticulos(cantidad, producto, cantidadReal, tabla);
    }
    
    public void restarArticulos( int cantidadReal, int cantidad ,String producto, String tabla){
        cn.modificarTablaArticulos( cantidadReal, cantidad , producto, "ProductoUsuarios");
    }
    
    public void cerrarRecursos(){
        cn.cerrarRecursos();
    }
    
}
