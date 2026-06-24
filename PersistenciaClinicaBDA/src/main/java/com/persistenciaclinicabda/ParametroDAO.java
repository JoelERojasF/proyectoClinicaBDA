/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author oscar
 */
public class ParametroDAO {
    
    private IConexionBD conexion;

    public ParametroDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarParametro(ParametroEntidad parametro) throws SQLException {
        String sql = "INSERT INTO parametros (nombre, orden_aparicion, descripcion, unidad_medida, id_analisis) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, parametro.getNombre());
            comando.setInt(2, parametro.getOrdenAparicion());
            comando.setString(3, parametro.getDescripcion());
            comando.setString(4, parametro.getUnidadMedida());
            comando.setInt(5, parametro.getIdAnalisis());
            
            comando.executeUpdate();
        }
    }

    public List<ServicioAnalisisEntidad> obtenerTodosLosAnalisis() throws SQLException {
        List<ServicioAnalisisEntidad> lista = new ArrayList<>();
        String sql = "SELECT * FROM analisis"; 
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {
            
            while (rs.next()) {
                ServicioAnalisisEntidad analisis = new ServicioAnalisisEntidad();
                analisis.setIdAnalisis(rs.getInt(1));
                analisis.setNombre(rs.getString(2));
                lista.add(analisis);
            }
        }
        return lista;
    }
}