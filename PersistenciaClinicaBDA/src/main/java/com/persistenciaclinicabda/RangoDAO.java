/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.RangoEntidad;
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
public class RangoDAO {
    
    private IConexionBD conexion;

    public RangoDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarRango(RangoEntidad rango) throws SQLException {
        String sql = "INSERT INTO rangos_evaluacion (sexo_aplica, edad_inicial, edad_final, rango_inicial, rango_final, id_parametro) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, rango.getSexoAplica());
            comando.setInt(2, rango.getEdadInicial());
            comando.setInt(3, rango.getEdadFinal());
            comando.setDouble(4, rango.getRangoInicial());
            comando.setDouble(5, rango.getRangoFinal());
            comando.setInt(6, rango.getIdParametro());
            
            comando.executeUpdate();
        }
    }

    public List<ParametroEntidad> obtenerTodosLosParametros() throws SQLException {
        List<ParametroEntidad> lista = new ArrayList<>();
        String sql = "SELECT * FROM parametros";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {
            
            while (rs.next()) {
                ParametroEntidad parametro = new ParametroEntidad();
                parametro.setIdParametro(rs.getInt(1));
                parametro.setNombre(rs.getString(2));
                lista.add(parametro);
            }
        }
        return lista;
    }
}