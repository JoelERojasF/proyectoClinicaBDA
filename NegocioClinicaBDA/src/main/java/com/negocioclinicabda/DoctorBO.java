/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.DoctorEntidad;
import com.persistenciaclinicabda.DoctorDAO;
/**
 *
 * @author oscar
 */
public class DoctorBO {
    
    private DoctorDAO doctorDAO;

    public DoctorBO() {
        this.doctorDAO = new DoctorDAO();
    }

    public boolean registrarDoctor(String nombres, String apellidoPaterno, String apellidoMaterno, String sexo, String especialidad, String cedula) {
        
        if (nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            System.out.println("Error: El nombre y apellido paterno son obligatorios.");
            return false;
        }
        if (cedula == null || cedula.trim().isEmpty()) {
            System.out.println("Error: La cédula profesional es obligatoria.");
            return false;
        }

        DoctorEntidad doctor = new DoctorEntidad(nombres, apellidoPaterno, apellidoMaterno, sexo, especialidad, cedula);

        try {
            doctorDAO.guardarDoctor(doctor);
            System.out.println("Éxito: Médico registrado en la BD con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar médico con JPA: " + e.getMessage());
            return false;
        }
    }
}