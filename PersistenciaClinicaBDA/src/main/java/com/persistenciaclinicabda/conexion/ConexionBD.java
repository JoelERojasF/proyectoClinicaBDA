/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author oscar
 */
public class ConexionBD implements IConexionBD {
    private final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/laboratorio_salud_total";
    private final String USUARIO = "root";
    private final String PASSWORD = "OSKI";

    @Override
    public Connection crearConexion() throws SQLException {
        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
            return conexion;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, "Error al conectar a la base de datos", ex);
            throw ex; 
        }
    }
}