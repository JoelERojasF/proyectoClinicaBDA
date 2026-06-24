/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.MuestraEntidad;
import com.persistenciaclinicabda.MuestraDAO;
/**
 *
 * @author oscar
 */
public class MuestraBO {
    
    private MuestraDAO muestraDAO;

    public MuestraBO() {
        this.muestraDAO = new MuestraDAO();
    }

    public boolean registrarMuestra(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre de la muestra es obligatorio.");
            return false;
        }

        MuestraEntidad muestra = new MuestraEntidad(nombre);

        try {
            muestraDAO.guardarMuestra(muestra);
            System.out.println("Éxito: Muestra registrada en BD con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar muestra: " + e.getMessage());
            return false;
        }
    }
    public java.util.List<com.entidades.MuestraEntidad> obtenerListaMuestras() {
        try {
            return muestraDAO.obtenerTodasLasMuestras();
        } catch (Exception e) {
            System.out.println("Error al consultar muestras: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }
}