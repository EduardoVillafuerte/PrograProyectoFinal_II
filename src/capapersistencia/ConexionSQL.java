/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capapersistencia;

/**
 *
 * @author Eddy
 */

import java.sql.*;

public class ConexionSQL {
    private static final String URL = "jdbc:sqlserver://EDDY-DESKTOP;databaseName=PlazaHotel;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "eddy";
    private static final String PASS = "1234";

    Connection conexion = null;
    PreparedStatement stmt = null;
    Statement stmtc = null;
    ResultSet rs = null;

    public ResultSet loginUsuario(String usuario, String contrasenia) {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            String sql = "SELECT rol,[user] FROM Users WHERE [user] = ? AND password = ?";
            stmt = conexion.prepareStatement(sql);

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);

            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }

        return null;
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
    
    public void sumarArticulos(int cantidad, String nombre, int cantidadReal, String tabla){
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE " + tabla + " SET cantidad = ? WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                cantidadReal += cantidad;
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

