/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capapersistencia;

/**
 *
 * @author Eddy
 */

import capanegocio.Articulo;
import capanegocio.Cliente;
import capanegocio.Empleado;
import java.sql.*;
import java.util.List;

public class ConexionSQL {
    private static final String URL = "jdbc:sqlserver://EDDY-DESKTOP;databaseName=PlazaHotel;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "eddy";
    private static final String PASS = "1234";

    Connection conexion = null;
    PreparedStatement stmt = null;
    Statement stmtc = null;
    ResultSet rs = null;

    public String loginUsuario(String usuario, String contrasenia) {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            String sql = "select cedula, nombre from clientes where nombre = ? AND pass = ?";
            stmt = conexion.prepareStatement(sql);

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("cedula");
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }

        return null;
    }
    
    public void agregarPersonas(String tabla,Cliente cliente, Empleado empleado){
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            String sql;
            try{
                
                if(cliente != null){
                    sql = "insert into " + tabla +" (cedula,nombre,apellido,pass,correo,celular) values (?,?,?,?,?,?)";
                    PreparedStatement stmtq = conexion.prepareStatement(sql);
                    stmtq.setString(1, cliente.getCedula());
                    stmtq.setString(2, cliente.getNombre());
                    stmtq.setString(3, cliente.getApellido());
                    stmtq.setString(4, cliente.getPass());
                    stmtq.setString(5, cliente.getCorreo());
                    stmtq.setString(6, cliente.getCelular());       
                    int filasAfectadas = stmtq.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("La tabla ha sido actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró el artículo con el nombre especificado.");
                    }
                }else{
                    sql = "insert into " + tabla +" (cedula,nombre,apellido,cargo) values (?,?,?,?)";
                    PreparedStatement stmtq = conexion.prepareStatement(sql);
                    stmtq.setString(1, empleado.getCedula());
                    stmtq.setString(2, empleado.getNombre());
                    stmtq.setString(3, empleado.getApellido());
                    stmtq.setString(4, empleado.getCargo().toString());
                    int filasAfectadas = stmtq.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("La tabla ha sido actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró el artículo con el nombre especificado.");
                    }
                }
            }catch(Exception e){
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public void agregarArticulos(String tabla,Articulo articulo){
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            String sql;
            try{
                sql =  "insert into " + tabla +" (nombre,cantidad,tipoPrioridad,Categoria,precio) values (?, ?, ?, ?, ?)";
                PreparedStatement stmtq = conexion.prepareStatement(sql);
                stmtq.setString(1, articulo.getNombre());
                stmtq.setInt(2, articulo.getCantidadDisponible());
                stmtq.setString(3, articulo.getPrioridad().toString());
                stmtq.setString(4, articulo.getCategoria().toString());
                stmtq.setFloat(5, (float) articulo.getPrecio());
                int filasAfectadas = stmtq.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("La tabla ha sido actualizada correctamente.");
                } else {
                    System.out.println("No se encontró el artículo con el nombre especificado.");
                }
            }catch(Exception e){
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public void eliminarArticulo(String nombre, String tabla){
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM "+tabla+ " WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("La tabla ha sido actualizada correctamente.");
                } else {
                    System.out.println("No se encontró el artículo con el nombre especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public ResultSet obetenrDatos(String nombreTabla) throws SQLException {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            String sql = "SELECT * FROM "+nombreTabla;
            stmtc = conexion.createStatement();
            rs = stmtc.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }

        return null;
    }
    
    public void modificarTablaPersonas(String cedula, String tabla){
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM "+tabla+ " WHERE cedula = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, cedula);
                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("La tabla ha sido actualizada correctamente.");
                } else {
                    System.out.println("No se encontró el artículo con el nombre especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public void modificarTablaArticulos(int cantidadReal, int cantidad, String nombre, String tabla){
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE " + tabla + " SET cantidad = ? WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                cantidadReal -= cantidad;
                stmt.setInt(1, cantidadReal);
                stmt.setString(2, nombre);
                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("La tabla ha sido actualizada correctamente.");
                } else {
                    System.out.println("No se encontró el artículo con el nombre especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public void guardarCambiosInventario(List<Articulo> articulo, String tabla){
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
            for(Articulo articulos: articulo){
                String sql = "UPDATE " + tabla + " SET cantidad = ? WHERE nombre = ?";
                try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                    stmt.setInt(1, articulos.getCantidadDisponible());
                    stmt.setString(2, articulos.getNombre());
                    int filasAfectadas = stmt.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("La tabla ha sido actualizada correctamente.");
                    } else {
                        System.out.println("No se encontró el artículo con el nombre especificado.");
                    }
                }   
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
    }
    
    public void cerrarRecursos(){
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    
}

