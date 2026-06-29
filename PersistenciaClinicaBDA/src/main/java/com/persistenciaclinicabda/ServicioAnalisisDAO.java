/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.MuestraEntidad;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.conexion.IConexionBD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public class ServicioAnalisisDAO {
    
    private final IConexionBD conexionBD;

    public java.util.List<ServicioAnalisisEntidad> listaAnalisis;

    public ServicioAnalisisDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void guardarAnalisis(ServicioAnalisisEntidad analisis, int idMuestra) throws Exception {
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            
            MuestraEntidad muestraRef = em.getReference(MuestraEntidad.class, idMuestra);
            analisis.setMuestra(muestraRef);
            em.persist(analisis);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public java.util.List<com.entidades.ServicioAnalisisEntidad> obtenerTodosLosAnalisis() throws Exception {
        EntityManager em = conexionBD.crearConexion();
        try {
            return em.createQuery("SELECT a FROM ServicioAnalisisEntidad a", ServicioAnalisisEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}