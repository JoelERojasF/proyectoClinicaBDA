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
            System.out.println("Error: El nombre de la muestra no puede estar vacío.");
            return false;
        }

        MuestraEntidad muestra = new MuestraEntidad(nombre);

        try {
            muestraDAO.guardarMuestra(muestra);
            System.out.println("Éxito: Tipo de muestra guardado en BD.");
            return true;
        } catch (Exception e) {
            System.out.println("Error interno en BD al guardar muestra: " + e.getMessage());
            return false;
        }
    }
    public java.util.List<MuestraEntidad> obtenerListaMuestras() {
        try {
            return muestraDAO.obtenerTodasLasMuestras();
        } catch (Exception e) {
            System.out.println("Error al cargar muestras desde BD: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}