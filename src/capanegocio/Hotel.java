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
    Fecha fecha = new Fecha();
    private ResultSet rs;
    private List<Integer> dias;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Articulo> articulos;
    private List<Factura> facturas;
    private List<String> habitaciones;
    private static final String RUTA_HABITACIONES = "C:/Programacion UDLA/Programacion II/PrograProyectoFinal_II/src/capanegocio/habitaciones";


    public Hotel(){
        dias = new ArrayList<Integer>();
        clientes = new ArrayList<Cliente>();
        empleados = new ArrayList<Empleado>();
        articulos = new ArrayList<Articulo>();
        habitaciones = new ArrayList<String>();
        facturas = new ArrayList<Factura>();
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
        modelTablaClientes.setRowCount(0);
        for(Cliente cliente: obtenerClientes()){
            modelTablaClientes.addRow(new Object[] {cliente.getNombre(), cliente.getApellido(), cliente.getCedula()});
        }
        return modelTablaClientes;
    }

    public List<Cliente> obtenerClientes(){
        clientes.clear();
        try{
            rs = cn.obetenrDatos("clientes");
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String cedula = rs.getString("cedula");
                String apellido = rs.getString("apellido");
                String pass = "";
                String correo = rs.getString("correo");
                String celular = rs.getString("celular");
                clientes.add(new Cliente(cedula,nombre,apellido,pass,correo,celular));
            }
            return clientes;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }
    
    public Cliente buscarCliente(String cedula){
        for(Cliente cliente: clientes){
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
            rs = cn.obetenrDatos("ProductoUsuarios");
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
                cn.agregarArticulos("ProductoUsuarios", new Articulo(nombre, precio, tipoCategoria(categoria), cantidad, alertas(cantidad)));
            }
            catch(Exception e){
                System.out.println("error "+e.getMessage());
            }
        }else{JOptionPane.showMessageDialog(null,"El articulo ya se encuetra registrado");}

    }

    public void eliminarArticulo(String nombre) {
        for (Articulo a : articulos){
            if(a.getNombre().equals(nombre)) {
                cn.eliminarArticulo(nombre, "ProductoUsuarios");
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
   
    public void guardarFactura(String cedula, List<Articulo> articulosAgregados, float compraTotal) {
        Cliente cliente = buscarCliente(cedula);
        Number random = Math.round(Math.random()*1000);
        cliente.agregarFactura(new Factura(fecha.getHoy(), random, compraTotal, articulosAgregados ));
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
    
    public DefaultTableModel getFacturas(String cedula,DefaultTableModel modelTablaFactura){
        modelTablaFactura.setRowCount(0);
        try{
            for(Factura factura : obtenerTodasFacturas(cedula)){
                modelTablaFactura.addRow( new Object[] {factura.getFecha(),factura.getnombre(),factura.getTotalStr()});
            }
        }catch(Exception e){System.out.println("No hay facturas "+e.getMessage());}
        return modelTablaFactura;
    }
    
    public List<Factura> obtenerTodasFacturas(String cedula){
        facturas.clear();
        String rutaCarpeta = "C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\facturas";
        File carpeta = new File(rutaCarpeta);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        String cedulaCliente = "";
                        String fecha = "";
                        String valorTotal = "";
                        while ((linea = br.readLine()) != null) {
                            if (linea.startsWith("Cedula:")) {
                                cedulaCliente = linea.replace("Cedula: ", "").trim();
                            } else if (linea.startsWith("Fecha:")) {
                                fecha = linea.replace("Fecha: ", "").trim();
                           } else if (linea.startsWith("Total: $")) {
                                valorTotal = linea.replace("Total: $", "").trim();
                            }
                        }
                        String nombreSinExtension = archivo.getName().replaceFirst("\\.txt$", "");
                        if(cedula.equals(cedulaCliente))
                            facturas.add(new Factura(fecha,nombreSinExtension,valorTotal));
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
    
    public DefaultTableModel getFacturasPorFecha(String cedula, String mesStr, String dia, DefaultTableModel modelTablaFactura){
        for (Factura facturas : obtenerTodasFacturas(cedula)) {
            String mesFactura = facturas.getFecha().substring(3, 5);
            String diaFactura = facturas.getFecha().substring(0, 2);

            if (mesStr.equals(mesFactura)) {
                if (dia.equals(diaFactura)) {
                    modelTablaFactura.addRow(new Object[] {
                        facturas.getFecha(),
                        facturas.getnombre(),
                        facturas.getTotalStr()
                    });
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

    public void cancelar(String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, String cedula) {
        modificarDisponibilidad(habitacion, mesInicio, diaInicio, mesFin, diaFin, false, cedula);
    }
    
    public void modificarDisponibilidad(String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, boolean esReserva, String cedula) {
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
                    if(totalDias < 0){
                        JOptionPane.showMessageDialog(null, "Error, compruebe los meses y los dias", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    guardarReserva(cedula, habitacion, mesInicio, diaInicio, mesFin, diaFin, totalDias);
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
    
    public DefaultComboBoxModel<String> getHabitaciones(){
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
    
    public DefaultTableModel getReservas(DefaultTableModel modelTablaReservas,String cedula){
        modelTablaReservas.setRowCount(0);
        File carpeta = new File("C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\habitaciones\\reservas");
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("reservas" + cedula) && name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            if (linea.startsWith("Cedula: " + cedula)) {
                                String[] partes = linea.split(", ");
                                String habitacion = partes[1].split(": ")[1];
                                String inicio = partes[2].split(": ")[1];
                                String fin = partes[3].split(": ")[1];
                                int totalDias = Integer.parseInt(partes[4].split(": ")[1]);
                                String fechaReserva = partes[5].split(": ")[1];
                                String cliente = buscarCliente(cedula).getNombre();
                                modelTablaReservas.addRow(new Object[] {cedula, cliente, habitacion,inicio ,fin,fechaReserva,totalDias});
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

    public void cancelarReserva(String cedula, String habitacion, String fechaInicio, String fechaFin) {
        String[] partesInicio = fechaInicio.split(" ");
        String mesInicio = partesInicio[0];
        int diaInicio = Integer.parseInt(partesInicio[1]);
        String[] partesFin = fechaFin.split(" ");
        String mesFin = partesFin[0];
        int diaFin = Integer.parseInt(partesFin[1]);
        eliminarReserva(cedula, habitacion, mesInicio + " " + diaInicio);
        cancelar(habitacion, mesInicio, diaInicio, mesFin, diaFin, cedula);
    }

    public void eliminarReserva(String cedula, String habitacion, String inicioReserva) {
        File archivoReservas = new File("C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\habitaciones\\reservas\\reservas" + cedula + ".txt");
        if (archivoReservas.exists()) {
            try {
                List<String> lineas = Files.readAllLines(archivoReservas.toPath());
                List<String> nuevasLineas = new ArrayList<>();
                boolean eliminada = false;

                for (String linea : lineas) {
                    if (!linea.contains("Habitación: " + habitacion) || !linea.contains("Inicio: " + inicioReserva)) {
                        nuevasLineas.add(linea);
                    } else {
                        eliminada = true;
                    }
                }

                if (eliminada) {
                    Files.write(archivoReservas.toPath(), nuevasLineas);
                    System.out.println("Reserva eliminada exitosamente.");
                } else {
                    System.err.println("Reserva no encontrada para eliminar.");
                }
            } catch (IOException e) {
                System.err.println("Error al eliminar la reserva: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo de reservas no existe para el cliente: " + cedula);
        }
    }

    public void guardarReserva(String cliente, String habitacion, String mesInicio, int diaInicio, String mesFin, int diaFin, int totalDias) {
        File archivoReservas = new File(RUTA_HABITACIONES+"/reservas/", "reservas"+cliente+".txt");
        Fecha fecha = new Fecha();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoReservas, true))) {
            writer.write("Cedula: " + cliente);
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
