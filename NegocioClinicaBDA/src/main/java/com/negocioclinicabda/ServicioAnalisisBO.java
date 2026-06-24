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

        ServicioAnalisisEntidad analisis = new ServicioAnalisisEntidad(nombre, descripcion);

        try {
            analisisDAO.guardarAnalisis(analisis, idMuestra);
            System.out.println("Éxito: Análisis guardado correctamente con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar análisis con JPA: " + e.getMessage());
            return false;
        }
    }
}