/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.ServicioAnalisisDAO;
/**
 *
 * @author oscar
 */
public class ServicioAnalisisBO {
    
    private ServicioAnalisisDAO analisisDAO;

    public ServicioAnalisisBO() {
        this.analisisDAO = new ServicioAnalisisDAO();
    }

    public boolean registrarAnalisis(String nombre, String descripcion, int idMuestra) {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre del análisis es obligatorio.");
            return false;
        }
        if (idMuestra <= 0) {
            System.out.println("Error: Debe seleccionar una muestra válida.");
            return false;
        }

        ServicioAnalisisEntidad analisis = new ServicioAnalisisEntidad(nombre, descripcion, idMuestra);

        try {
            analisisDAO.guardarAnalisis(analisis);
            System.out.println("Éxito: Análisis guardado correctamente en la BD.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en la BD al guardar el análisis: " + e.getMessage());
            return false;
        }
    }
}