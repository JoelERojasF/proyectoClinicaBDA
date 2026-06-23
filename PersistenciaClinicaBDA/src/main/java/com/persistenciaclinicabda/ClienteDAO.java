/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ClienteEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public class ClienteDAO {
    
    private IConexionBD conexion;

    public ClienteDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarCliente(ClienteEntidad cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombres, apellido_paterno, apellido_materno, fecha_nacimiento, sexo, tipo_sangre) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, cliente.getNombres());
            comando.setString(2, cliente.getApellidoPaterno());
            comando.setString(3, cliente.getApellidoMaterno());
            comando.setDate(4, cliente.getFechaNacimiento());
            comando.setString(5, cliente.getSexo());
            comando.setString(6, cliente.getTipoSangre());
            
            comando.executeUpdate();
        }
    }
}