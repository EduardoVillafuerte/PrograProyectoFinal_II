package capanegocio;

import capapersistencia.ConexionSQL;
import static capanegocio.Articulo.LIMITEBAJO;
import static capanegocio.Articulo.LIMITEMEDIO;
import static capanegocio.Articulo.LIMITEALTO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;


public class Hotel {

    ConexionSQL cn = new ConexionSQL();
    private List<Integer> dias;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Articulo> articulos;
    private ResultSet rs;
    private static final String RUTA_HABITACIONES = "C:/Programacion UDLA/Programacion II/PrograProyectoFinal_II/src/capanegocio/habitaciones";


    public Hotel(){
        dias = new ArrayList<Integer>();
        clientes = new ArrayList<Cliente>();
        empleados = new ArrayList<Empleado>();
        articulos = new ArrayList<Articulo>();
    }

    public String autenticarUsuario(String usuario, String contrasenia){
        return cn.loginUsuario(usuario,contrasenia);
    }

    public void agregarCliente(Cliente cliente){
        if(buscarCliente(cliente.getCedula()) == null){
            cn.agregarPersonas("clientes", cliente,null);
        }
        else
            JOptionPane.showMessageDialog(null, "El cliente ya existe", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public DefaultTableModel visualizarClientes(DefaultTableModel modelTablaClientes ){
        try{
            rs = cn.obetenrDatos("clientes");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String cedula = rs.getString("cedula");
                String apellido = rs.getString("apellido");
                clientes.add(new Cliente(cedula,nombre,apellido));
                modelTablaClientes.addRow(new Object[] {nombre,apellido,cedula});
            }

            return modelTablaClientes;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return modelTablaClientes;
        }
    }

    public Cliente buscarCliente(String cedula){
        for(Cliente cliente: clientes){
            System.out.println(cedula);
            if(cliente.getCedula().equals(cedula)){
                return cliente;
            }
        }
        return null;
    }

    public void eliminarCliente(String cedula){
        for (Cliente c : clientes){
            if(c.getCedula().equals(cedula)) {
                clientes.remove(c);
                cn.modificarTablaPersonas(cedula, "clientes");
                return;
            }
        }
    }

    public void agregarEmpleado(String cedula, String nombre, String apellido, String cargo){
        Empleado empleado = new Empleado(cedula,nombre,apellido,tipoEmpleado(cargo));
        if(buscarEmpleado(empleado.getCedula()) == null){
            cn.agregarPersonas("empleados", null, empleado);
        }else
            JOptionPane.showMessageDialog(null, "El cliente ya existe", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public DefaultTableModel visualizarEmpleado(DefaultTableModel modelTablaEmpleados) {
        try{
            rs = cn.obetenrDatos("Empleados");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                empleados.add(new Empleado(cedula,nombre,apellido,tipoEmpleado(cargo)));
                modelTablaEmpleados.addRow(new Object[] {nombre,apellido,cedula,tipoEmpleado(cargo)});
            }
            return modelTablaEmpleados;
        }catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        
        return modelTablaEmpleados;
    }
    
    public CargoEmpleado tipoEmpleado(String cargo){
        return switch (cargo) {
            case "CONSERJE" -> CargoEmpleado.CONSERJE;
            case "RECEPCION" -> CargoEmpleado.RECEPCION;
            default -> CargoEmpleado.RECEPCION;
        };
    }
    
    public Empleado buscarEmpleado(String cedula){
        for(Empleado empleado: empleados){
            if(empleado.getCedula().equals(cedula)){
                return empleado;
            }
        }
        return null;
    }

    public void eliminarEmpleado(String cedula){
        for (Empleado e : empleados){
            if(e.getCedula().equals(cedula)) {
                cn.modificarTablaPersonas(cedula, "empleados");        
                empleados.remove(e);
                return;
            }
        }
    }

    public DefaultTableModel visualizarArticulos(DefaultTableModel modelTablaArticulos){
        try{
            articulos.clear();
            rs = cn.obetenrDatos("ProductoEmpleados");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                String prioridad = rs.getString("tipoPrioridad");
                String categoria = rs.getString("Categoria");
                articulos.add(new Articulo(nombre,0,tipoCategoria(categoria),cantidad,tipoPrioridad(prioridad)));
                modelTablaArticulos.addRow(new Object[] {nombre, categoria,cantidad,prioridad});
            }
            return modelTablaArticulos;
        }catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return modelTablaArticulos;
    }
    
    public Articulo buscarArticulo(String nombre){
        for(Articulo articulo : articulos){
            if(articulo.getNombre().equals(nombre)){
                return articulo;
            }
        }
        return null;
    }

    public void agregarArticulo(String nombre, float precio ,String categoria, int cantidad) {
        if(buscarArticulo(nombre) == null){
            try{
                cn.agregarArticulos("ProductoEmpleados", new Articulo(nombre, precio, tipoCategoria(categoria), cantidad, alertas(cantidad)));
            }
            catch(Exception e){
                System.out.println("error "+e.getMessage());
            }
        }else{JOptionPane.showMessageDialog(null,"El articulo ya se encuetra registrado");}

    }

    public void eliminarArticulo(String nombre) {
        for (Articulo a : articulos){
            if(a.getNombre().equals(nombre)) {
                cn.eliminarArticulo(nombre, "ProductoEmpleados");
                articulos.remove(a);
                return;
            }
        }
    }

    public TipoAlerta alertas(int cantidad){
        if(cantidad <= LIMITEBAJO){
            return TipoAlerta.ALTA;
        } else if (cantidad > LIMITEBAJO && cantidad <= LIMITEMEDIO) {
            return TipoAlerta.MEDIA;
        } else if (cantidad > LIMITEMEDIO && cantidad < LIMITEALTO) {
            return TipoAlerta.BAJA;
        }
        else {
            return TipoAlerta.NA;
        }
    }
    
    public List<Articulo> getInventario(String tabla){
        String nombre,tipoPrioridad,categoria;
        int cantidad;
        float precio;
        try{
            ResultSet rs = cn.obetenrDatos(tabla);
            while(rs.next()){
                nombre = rs.getString("nombre");
                precio = rs.getFloat("precio");
                cantidad = rs.getInt("cantidad");
                tipoPrioridad = rs.getString("tipoPrioridad");
                categoria = rs.getString("Categoria");
                articulos.add(new Articulo(nombre,precio, tipoCategoria(categoria) ,cantidad, tipoAlerta(tipoPrioridad)));
            }
            return articulos;
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }        
        return null;
    }
   
    public Categoria tipoCategoria(String categoria){
        return switch (categoria) {
            case "ALIMENTO" -> Categoria.ALIMENTO;
            case "ACCESORIO_BANIO" -> Categoria.ACCESORIO_BANIO;
            case "LIMPIEZA" -> Categoria.LIMPIEZA;
            case "ROPA_CAMA" -> Categoria.ROPA_CAMA;
            default -> Categoria.NA;
        };
    }
    
    public TipoAlerta tipoPrioridad(String categoria){
        return switch (categoria) {
            case "ALTA" -> TipoAlerta.ALTA;
            case "MEIA" -> TipoAlerta.MEDIA;
            case "BAJA" -> TipoAlerta.BAJA;
            default -> TipoAlerta.NA;
        };
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
        //Cliente clienteObj = buscarCliente(cliente);
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
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("\"C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\factura_"+random+".txt"))) {
            writer.write(factura.toString());
            System.out.println("Factura guardada correctamente en 'factura.txt'.");
        } catch (Exception e) {
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }
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
    
    public DefaultComboBoxModel<String> getHabitaciones(){
        List<String> habitaciones = new ArrayList<>();
        String rutaCarpeta = "C:/Programacion UDLA/Programacion II/PrograProyectoFinal_II/src/capanegocio/habitaciones/";
        File carpeta = new File(rutaCarpeta);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    String nombreSinExtension = archivo.getName().replaceFirst("\\.txt$", "");
                    habitaciones.add(nombreSinExtension);
                }
            }
            
            DefaultComboBoxModel<String> modelTablaHabitaciones = new DefaultComboBoxModel<>();
            for(String habitacion : habitaciones){
                modelTablaHabitaciones.addElement(habitacion);
            }
            
            return modelTablaHabitaciones;
        } else {
            System.err.println("La ruta especificada no es una carpeta válida.");
        }
        return null;
    }
    
    public DefaultTableModel getFacturas(String cliente,DefaultTableModel modelTablaFactura){
        try{
            for(Factura factura : obtenerTodasFcturas(cliente)){
                modelTablaFactura.addRow( new Object[] {factura.getFechaEmision(),factura.getNumFactura(),factura.getMontoTotal()});
            }
        }catch(Exception e){System.out.println("No hay facturas "+e.getMessage());}
        return modelTablaFactura;
    }
    
    public List<Factura> obtenerTodasFcturas(String cliente){
                
        String rutaCarpeta = "C:/Programacion UDLA/Programacion II/PrograProyectoFinal_II/";
        File carpeta = new File(rutaCarpeta);
        List<Factura> facturas = new ArrayList<>();
        //if (carpeta.exists() && carpeta.isDirectory()) {
            //File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
            //if (archivos != null) {
                //for (File archivo : archivos) {
                    //try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    //    String linea;
                    //    String nombreCliente = "";
                    //    String fecha = "";
                    //    String valorTotal = "";
                    //    while ((linea = br.readLine()) != null) {
                    //        if (linea.startsWith("Cliente:")) {
                    //            nombreCliente = linea.replace("Cliente:", "").trim();
                    //        } else if (linea.startsWith("Fecha:")) {
                    //            fecha = linea.replace("Fecha:", "").trim();
                    //        } else if (linea.startsWith("Total: $")) {
                    //            valorTotal = linea.replace("Total: $", "").trim();
                    //        }
                    //    }
                    //    String nombreSinExtension = archivo.getName().replaceFirst("\\.txt$", "");
                    //    if(cliente.equals(nombreCliente))
                            //facturas.add(new Factura(fecha, nombreSinExtension,valorTotal));
                    //} catch (Exception e) {
                    //    System.err.println("Error leyendo el archivo " + archivo.getName() + ": " + e.getMessage());
                    //}
               //}
            //}
            //return facturas;
        //} else {
        //    System.err.println("La ruta especificada no es una carpeta válida.");
        //}
        return null;
    }
    
    public DefaultTableModel getFacturasPorFecha(String cliente, String mesStr, String dia, int fin, DefaultTableModel modelTablaFactura){
        for(Factura facturas : obtenerTodasFcturas(cliente)){
            if(mesStr.equals(facturas.getFechaEmision().substring(3,fin))){
                if(dia.equals(facturas.getFechaEmision().substring(0,2))){
                    modelTablaFactura.addRow( new Object[] {facturas.getFechaEmision(),facturas.getNumFactura(),facturas.getMontoTotal()});                
                }
            }    
        }
        return modelTablaFactura;
    }
    
    public void guardarCambiosInventario(List<Articulo> articulo, String tabla){
        cn.guardarCambiosInventario(articulo,tabla);
    }

    public void reservar(String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, String cliente) {
        modificarDisponibilidad(habitacion, mesInicio, diaInicio, mesFin, diaFin, true, cliente);
    }

    public void cancelar(String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, String cliente) {
        modificarDisponibilidad(habitacion, mesInicio, diaInicio, mesFin, diaFin, false, cliente);
    }
    
    public void modificarDisponibilidad(String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, boolean esReserva, String cliente) {
        List<String> meses = obtenerMesesOrdenados();
        int indiceMesInicio = meses.indexOf(mesInicio);
        int indiceMesFin = meses.indexOf(mesFin);

        try {
            File archivo = new File(RUTA_HABITACIONES, habitacion + ".txt");

            if (archivo.exists()) {
                List<String> lineas = Files.readAllLines(archivo.toPath());
                List<String> nuevasLineas = new ArrayList<>();
                boolean disponibilidad = true;
                List<String> diasOcupados = new ArrayList<>();
                int totalDias = 0;

                for (String linea : lineas) {
                    String[] partes = linea.split(":");
                    String nombreMes = partes[0];
                    String[] dias = partes[1].split(",");

                    List<String> diasDisponibles = new ArrayList<>(Arrays.asList(dias));
                    int indiceMes = meses.indexOf(nombreMes);

                    if (indiceMes >= indiceMesInicio && indiceMes <= indiceMesFin) {
                        int diaInicioActual = (indiceMes == indiceMesInicio) ? diaInicio : 1;
                        int diaFinActual = (indiceMes == indiceMesFin) ? diaFin : diasDisponibles.size();

                        for (int dia = diaInicioActual; dia <= diaFinActual; dia++) {
                            if (esReserva && !diasDisponibles.contains(String.valueOf(dia))) {
                                disponibilidad = false;
                                diasOcupados.add(nombreMes + " " + dia);
                            }
                        }

                        if (!disponibilidad) break;

                        if (esReserva) {
                            for (int dia = diaInicioActual; dia <= diaFinActual; dia++) {
                                diasDisponibles.remove(String.valueOf(dia));
                            }
                            totalDias += (diaFinActual - diaInicioActual + 1);
                        } else {
                            for (int dia = diaInicioActual; dia <= diaFinActual; dia++) {
                                if (!diasDisponibles.contains(String.valueOf(dia))) {
                                    diasDisponibles.add(String.valueOf(dia));
                                }
                            }
                            diasDisponibles.sort(Comparator.comparingInt(Integer::parseInt));
                        }
                    }

                    nuevasLineas.add(nombreMes + ":" + String.join(",", diasDisponibles));
                }

                if (!disponibilidad) {
                    String mensajeError = "Los siguientes días ya están ocupados:\n" + String.join(", ", diasOcupados);
                    JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Files.write(archivo.toPath(), nuevasLineas);

                if (esReserva) {
                    guardarReserva(cliente, habitacion, mesInicio, diaInicio, mesFin, diaFin, totalDias);
                    JOptionPane.showMessageDialog(null, "Reserva realizada con éxito para la habitación: " + habitacion, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cancelación realizada con éxito para la habitación: " + habitacion, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                System.out.println("El archivo de la habitación especificada no existe: " + habitacion);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    public DefaultTableModel getReservas(DefaultTableModel modelTablaReservas,String cliente){
        File carpeta = new File("C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\habitaciones\\reservas");
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("reservas" + cliente) && name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            if (linea.startsWith("Cliente: " + cliente)) {
                                String[] partes = linea.split(", ");
                                String habitacion = partes[1].split(": ")[1];
                                String inicio = partes[2].split(": ")[1];
                                String fin = partes[3].split(": ")[1];
                                int totalDias = Integer.parseInt(partes[4].split(": ")[1]);
                                String fechaReserva = partes[5].split(": ")[1];
                                modelTablaReservas.addRow(new Object[] {cliente,habitacion,inicio ,fin,fechaReserva,totalDias});
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error leyendo archivo: " + archivo.getName() + " - " + e.getMessage());
                    }
                }
            }
        } else {
            System.err.println("La ruta especificada no es una carpeta válida.");
        }
        return modelTablaReservas;
    }

    public void cancelarReserva(String cliente, String habitacion, String fechaInicio, String fechaFin) {
        String[] partesInicio = fechaInicio.split(" ");
        String mesInicio = partesInicio[0];
        int diaInicio = Integer.parseInt(partesInicio[1]);
        String[] partesFin = fechaFin.split(" ");
        String mesFin = partesFin[0];
        int diaFin = Integer.parseInt(partesFin[1]);
        eliminarReserva(cliente, habitacion, mesInicio + " " + diaInicio);
        cancelar(habitacion, mesInicio, diaInicio, mesFin, diaFin, cliente);
    }

    public void eliminarReserva(String cliente, String habitacion, String diaInicio) {
        File carpeta = new File(RUTA_HABITACIONES+"/reservas/");

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("reservas" + cliente) && name.endsWith(".txt"));

            if (archivos != null) {
                for (File archivo : archivos) {
                    try {
                        List<String> nuevasLineas = new ArrayList<>();
                        boolean eliminado = false;

                        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                if (linea.startsWith("Cliente: " + cliente) &&
                                    linea.contains("Habitación: " + habitacion) &&
                                    linea.contains("Inicio: " + diaInicio)) {
                                    eliminado = true;
                                } else {
                                    nuevasLineas.add(linea);
                                }
                            }
                        }

                        if (eliminado) {
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                                for (String linea : nuevasLineas) {
                                    bw.write(linea);
                                    bw.newLine();
                                }
                                System.out.println("Línea eliminada del archivo: " + archivo.getName());
                            }
                        } else {
                            System.out.println("No se encontró la línea en el archivo: " + archivo.getName());
                        }

                    } catch (IOException e) {
                        System.err.println("Error procesando archivo: " + archivo.getName() + " - " + e.getMessage());
                    }
                }
            }
        } else {
            System.err.println("La ruta especificada no es una carpeta válida.");
        }
    }
    
    public void guardarReserva(String cliente, String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, int totalDias) {
        File archivoReservas = new File(RUTA_HABITACIONES+"/reservas/", "reservas"+cliente+habitacion+".txt");
        Fecha fecha = new Fecha();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoReservas, true))) {
            writer.write("Cliente: " + cliente);
            writer.write(", Habitación: " + habitacion);
            writer.write(", Inicio: " + mesInicio + " " + diaInicio);
            writer.write(", Fin: " + mesFin + " " + diaFin);
            writer.write(", Total de días: " + totalDias);
            writer.write(", Reserva: " + fecha.getHoy());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
        }
    }

    public List<String> obtenerMesesOrdenados() {
        return Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    }
    
    public DefaultTableModel generarModeloTabla(String numeroHabitacion) {
        String nombreArchivo = numeroHabitacion + ".txt";
        File archivo = new File(RUTA_HABITACIONES, nombreArchivo);
        
        DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Mes", "Días Disponibles"}, 0
        );
        
        try {
            List<String> lineas = Files.readAllLines(archivo.toPath());

            for (String linea : lineas) {
                String[] partes = linea.split(":");
                String mes = partes[0];
                String[] dias = partes[1].split(",");
                StringBuilder diasMostrar = new StringBuilder();
                List<Integer> diasm = getDias();
                for (int i = 1; i <= diasm.get(mesInt(mes)); i++) {
                    if (Arrays.asList(dias).contains(String.valueOf(i))) {
                        diasMostrar.append(i).append(" | ");
                    } else {
                        diasMostrar.append("X | ");
                    }
                }

                modeloTabla.addRow(new Object[]{mes, diasMostrar.toString().trim()});
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return modeloTabla;
    }
    
    public int mesInt(String mes){
        return switch(mes){
            case "Enero" -> 0;
            case "Febrero" -> 1;
            case "Marzo" -> 2;
            case "Abril" -> 3;
            case "Mayo" -> 4;
            case "Junio" -> 5;
            case "Julio" -> 6;
            case "Agosto" -> 7;
            case "Septiempre" -> 8;
            case "Octubre" -> 9;
            case "Noviembre" -> 10;
            case "Diciembre" -> 11;
            default ->0;
        };
    }
    
}
