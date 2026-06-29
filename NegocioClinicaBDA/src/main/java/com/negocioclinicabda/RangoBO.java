/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.RangoEntidad;
import com.persistenciaclinicabda.RangoDAO;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
/**
 *
 * @author oscar
 */
public class RangoBO {
    
    private RangoDAO rangoDAO;
    IConexionBD conexion = new ConexionBD();

    public RangoBO() {
        this.rangoDAO = new RangoDAO(conexion);
    }

    public boolean registrarRango(String sexoAplica, int edadInicial, int edadFinal, double rangoInicial, double rangoFinal, int idParametro) {
        
        if (idParametro <= 0) {
            System.out.println("Error: Debe seleccionar un parámetro válido.");
            return false;
        }

        RangoEntidad rango = new RangoEntidad(sexoAplica, edadInicial, edadFinal, rangoInicial, rangoFinal);

        try {
            rangoDAO.guardarRango(rango, idParametro);
            System.out.println("Éxito: Rango guardado correctamente con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar rango con JPA: " + e.getMessage());
            return false;
        }
    }
    
    public java.util.List<com.entidades.ParametroEntidad> obtenerListaParametros() {
        try {
            return new com.persistenciaclinicabda.ParametroDAO(conexion).obtenerTodosLosParametros();
        } catch (Exception e) {
            System.out.println("Error al consultar parámetros: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }
}