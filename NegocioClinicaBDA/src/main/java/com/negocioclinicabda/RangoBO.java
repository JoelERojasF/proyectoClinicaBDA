/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.RangoEntidad;
import com.persistenciaclinicabda.RangoDAO;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author oscar
 */
public class RangoBO {
    
    private RangoDAO rangoDAO;

    public RangoBO() {
        this.rangoDAO = new RangoDAO();
    }

    public boolean registrarRango(String sexoAplica, int edadInicial, int edadFinal, double rangoInicial, double rangoFinal, int idParametro) {
        if (sexoAplica == null || sexoAplica.trim().isEmpty()) {
            System.out.println("Error: El sexo al que aplica es obligatorio.");
            return false;
        }
        if (idParametro <= 0) {
            System.out.println("Error: Debe seleccionar un parámetro válido.");
            return false;
        }

        RangoEntidad rango = new RangoEntidad(sexoAplica, edadInicial, edadFinal, rangoInicial, rangoFinal, idParametro);

        try {
            rangoDAO.guardarRango(rango);
            System.out.println("Éxito: Rango guardado correctamente.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar rango: " + e.getMessage());
            return false;
        }
    }

    public List<ParametroEntidad> obtenerListaParametros() {
        try {
            return rangoDAO.obtenerTodosLosParametros();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}