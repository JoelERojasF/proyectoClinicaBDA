/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.DoctorEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public class DoctorDAO {
    
    private IConexionBD conexion;

    public DoctorDAO() {
        this.conexion = new ConexionBD();
    }

    public void guardarDoctor(DoctorEntidad doctor) throws SQLException {
        String sql = "INSERT INTO doctores (nombres, apellido_paterno, apellido_materno, sexo, especialidad, cedula) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.crearConexion();
             PreparedStatement comando = conn.prepareStatement(sql)) {
            
            comando.setString(1, doctor.getNombres());
            comando.setString(2, doctor.getApellidoPaterno());
            comando.setString(3, doctor.getApellidoMaterno());
            comando.setString(4, doctor.getSexo());
            comando.setString(5, doctor.getEspecialidad());
            comando.setString(6, doctor.getCedula());
            
            comando.executeUpdate();
        }
    }
}