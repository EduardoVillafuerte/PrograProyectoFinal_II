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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;


public class Hotel {
    Cliente cliente = new Cliente();
    ConexionSQL cn = new ConexionSQL();
    private List<Integer> dias;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<String> asignacionesRecepcion;
    private List<String> asignacionesConserje;
    private List<Articulo> articulos;
    private ResultSet rs;

    public Hotel(){
        dias = new ArrayList<Integer>();
        clientes = new ArrayList<Cliente>();
        empleados = new ArrayList<Empleado>();
        articulos = new ArrayList<Articulo>();
        asignacionesConserje = new ArrayList<String>();
        asignacionesRecepcion = new ArrayList<String>();
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

    public void agregarArticulo(String nombre, float precio ,Categoria categoria, int cantidad, TipoAlerta prioridad) {
        if(buscarArticulo(nombre) == null){
            articulos.add(new Articulo(nombre, precio, categoria, cantidad, prioridad));
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
    
    public List<Articulo> getInventario(String tabla, int rol){
        String nombre,tipoPrioridad;
        int cantidad;
        float precio;
        if(rol == 1){
            try{
                ResultSet rs = cn.obetenrDatos(tabla);
                while(rs.next()){
                    nombre = rs.getString("nombre");
                    precio = rs.getFloat("precio");
                    cantidad = rs.getInt("cantidad");
                    tipoPrioridad = rs.getString("tipoPrioridad"); 
                    articulos.add(new Articulo(nombre,precio, Categoria.ALIMENTO ,cantidad, tipoAlerta(tipoPrioridad)));
                }
                return articulos;
            }catch(Exception e){
                System.out.println("error: "+e.getMessage());

            }
            
        }
        
        return null;
    }
    
    public TipoAlerta tipoAlerta(String alertaString){
        return switch (alertaString) {
            case "LimiteAlto" -> TipoAlerta.ALTA;
            case "LimiteMedio" -> TipoAlerta.MEDIA;
            case "LimiteBajo" -> TipoAlerta.BAJA;
            default -> TipoAlerta.NA;
        };
    }
   
    public static void guardarFactura(String nombreCliente, List<Articulo> articulosAgregados, float compraTotal) {
        Fecha fecha = new Fecha();
        
        StringBuilder factura = new StringBuilder();
        factura.append("*****************************************************\n");
        factura.append("                     FACTURA                         \n");
        factura.append("*****************************************************\n");
        factura.append("Cliente: ").append(nombreCliente).append("\n");
        factura.append("Fecha: ").append(fecha.getHoy()).append("\n");
        factura.append("\n");
        factura.append("Detalle de compra:\n");
        factura.append("-----------------------------------------------------\n");
        factura.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Cantidad", "Precio", "Subtotal"));
        factura.append("-----------------------------------------------------\n");

        for(Articulo articulo: articulosAgregados){
            double subtotal = articulo.getCantidadDisponible() * articulo.getPrecio();
            factura.append(String.format("%-20s %-10d $%-9.2f $%-9.2f\n", articulo.getNombre(), articulo.getCantidadDisponible(),articulo.getPrecio(), subtotal));
        }

        factura.append("---------------------------------------------\n");
        factura.append(String.format("Total: $%.2f\n", compraTotal));
        factura.append("*****************************************************\n");
        factura.append("              ¡Gracias por su compra!                \n");
        factura.append("*****************************************************\n");
        
        Number random = Math.round(Math.random()*1000);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("factura_"+random+".txt"))) {
            writer.write(factura.toString());
            System.out.println("Factura guardada correctamente en 'factura.txt'.");
        } catch (Exception e) {
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }
    }
    
    public void cerrarRecursos(){
        cn.cerrarRecursos();
    }
    
    public List<Integer> getDias(){
        try{
            rs = cn.obetenrDatos("meses");
            while(rs.next()){
                dias.add(rs.getInt("dia"));
            }
            return dias;
        }catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
        return null;
    }
    
    public List<Factura> getFacturas(String cliente){
        String rutaCarpeta = "C:/Programacion UDLA/Programacion II/PrograProyectoFinal_II/";
        File carpeta = new File(rutaCarpeta);
        List<Factura> facturas = new ArrayList<>();
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        String nombreCliente = "";
                        String fecha = "";
                        String valorTotal = "";

                        while ((linea = br.readLine()) != null) {
                            if (linea.startsWith("Cliente:")) {
                                nombreCliente = linea.replace("Cliente:", "").trim();
                            } else if (linea.startsWith("Fecha:")) {
                                fecha = linea.replace("Fecha:", "").trim();
                            } else if (linea.startsWith("Total: $")) {
                                valorTotal = linea.replace("Total: $", "").trim();
                            }
                        }
                        String nombreSinExtension = archivo.getName().replaceFirst("\\.txt$", "");
                        if(cliente.equals(nombreCliente))
                            facturas.add(new Factura(fecha, nombreSinExtension,valorTotal));
                    } catch (Exception e) {
                        System.err.println("Error leyendo el archivo " + archivo.getName() + ": " + e.getMessage());
                    }
                }
            }
            return facturas;
        } else {
            System.err.println("La ruta especificada no es una carpeta válida.");
        }
        return null;
    }
    
    public void guardarCambiosInventario(List<Articulo> articulo, String tabla){
        cn.guardarCambiosInventario(articulo,tabla);
    }

}
