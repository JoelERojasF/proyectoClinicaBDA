/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.MuestraEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public class MuestraDAO {
    
    private IConexionBD conexion;

    public MuestraDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarMuestra(MuestraEntidad muestra) throws SQLException {
        String sql = "INSERT INTO tipos_muestra (nombre) VALUES (?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, muestra.getNombre());
            comando.executeUpdate();
        }
    }
    public java.util.List<MuestraEntidad> obtenerTodasLasMuestras() throws SQLException {
        java.util.List<MuestraEntidad> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM tipos_muestra"; 
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql);
             java.sql.ResultSet rs = comando.executeQuery()) {
            
            while (rs.next()) {
                MuestraEntidad muestra = new MuestraEntidad();
                muestra.setIdMuestra(rs.getInt(1)); 
                muestra.setNombre(rs.getString(2)); 
                lista.add(muestra);
            }
        }
        return lista;
    }
}