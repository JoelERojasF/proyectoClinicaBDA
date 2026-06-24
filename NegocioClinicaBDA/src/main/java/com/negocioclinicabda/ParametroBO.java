/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.ParametroDAO;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author oscar
 */
public class ParametroBO {
    
    private ParametroDAO parametroDAO;

    public ParametroBO() {
        this.parametroDAO = new ParametroDAO();
    }

    public boolean registrarParametro(String nombre, int ordenAparicion, String descripcion, String unidadMedida, int idAnalisis) {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre del parámetro es obligatorio.");
            return false;
        }
        if (idAnalisis <= 0) {
            System.out.println("Error: Debe seleccionar un análisis válido.");
            return false;
        }

        ParametroEntidad parametro = new ParametroEntidad(nombre, ordenAparicion, descripcion, unidadMedida, idAnalisis);

        try {
            parametroDAO.guardarParametro(parametro);
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar parámetro: " + e.getMessage());
            return false;
        }
    }

    public List<ServicioAnalisisEntidad> obtenerListaAnalisis() {
        try {
            return parametroDAO.obtenerTodosLosAnalisis();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}