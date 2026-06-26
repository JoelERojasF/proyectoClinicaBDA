/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.ParametroEntidad;
import com.persistenciaclinicabda.ParametroDAO;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
/**
 *
 * @author oscar
 */
public class ParametroBO {
    
    private ParametroDAO parametroDAO;
    IConexionBD conexion = new ConexionBD();

    public ParametroBO() {
        this.parametroDAO = new ParametroDAO(conexion);
    }

    public boolean registrarParametro(String nombre, int ordenAparicion, String descripcion, String unidadMedida, int idAnalisis) {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre del parámetro es obligatorio.");
            return false;
        }
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            System.out.println("Error: La unidad de medida es obligatoria.");
            return false;
        }
        if (idAnalisis <= 0) {
            System.out.println("Error: Debe seleccionar un análisis válido.");
            return false;
        }
        
        ParametroEntidad parametro = new ParametroEntidad(nombre, ordenAparicion, descripcion, unidadMedida);

        try {
            parametroDAO.guardarParametro(parametro, idAnalisis);
            System.out.println("Éxito: Parámetro guardado correctamente con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar parámetro con JPA: " + e.getMessage());
            return false;
        }
    }
    public java.util.List<com.entidades.ServicioAnalisisEntidad> obtenerListaAnalisis() {
        try {
            return new com.persistenciaclinicabda.ServicioAnalisisDAO(conexion).obtenerTodosLosAnalisis();
        } catch (Exception e) {
            System.out.println("Error al consultar análisis: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }
}