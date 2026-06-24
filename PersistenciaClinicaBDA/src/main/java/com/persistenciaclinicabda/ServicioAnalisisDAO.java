/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public class ServicioAnalisisDAO {
    
    private IConexionBD conexion;

    public ServicioAnalisisDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarAnalisis(ServicioAnalisisEntidad analisis) throws SQLException {
        String sql = "INSERT INTO analisis (nombre, descripcion, id_muestra) VALUES (?, ?, ?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, analisis.getNombre());
            comando.setString(2, analisis.getDescripcion());
            comando.setInt(3, analisis.getIdMuestra()); 
            
            comando.executeUpdate();
        }
    }
}