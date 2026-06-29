/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;

import com.entidades.PruebaLaboratorioEntidad;
import com.persistenciaclinicabda.PruebaLaboratorioDAO;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Timestamp;

/**
 *
 * @author le0jx
 */
public class PruebaLaboratorioBO {
    private PruebaLaboratorioDAO pruebaDAO;
    IConexionBD conexion = new ConexionBD();

    public PruebaLaboratorioBO() {
        this.pruebaDAO = new PruebaLaboratorioDAO(conexion);
    }
    
    
    public boolean registrarPrueba(Timestamp fechaHora, int Idcliente, int IdDoctor) {
        
        PruebaLaboratorioEntidad prueba = new PruebaLaboratorioEntidad(fechaHora);

        try{
            pruebaDAO.guardarPruebaLaboratorio(prueba, Idcliente, IdDoctor);
            System.out.println("Éxito: Análisis guardado correctamente con JPA.");
            return true;  
         } catch (Exception e) {
            System.out.println("Error en BD al guardar prueba de laboratorio con JPA: " + e.getMessage());
            return false;
        }
    }
    
    public java.util.List<com.entidades.PruebaLaboratorioEntidad> obtenerTodasPruebas(){
        try {
            return pruebaDAO.obtenerTodasPruebas();
        } catch (Exception e) {
            System.out.println("Error al consultar pruebas: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    } 
    
}
