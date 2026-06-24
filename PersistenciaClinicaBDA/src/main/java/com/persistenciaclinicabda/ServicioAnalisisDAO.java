/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.MuestraEntidad;
import com.entidades.ServicioAnalisisEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author oscar
 */
public class ServicioAnalisisDAO {
    
    private EntityManagerFactory emf;

    public java.util.List<ServicioAnalisisEntidad> obtenerTodosLosAnalisis;

    public ServicioAnalisisDAO() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaPU");
    }

    public void guardarAnalisis(ServicioAnalisisEntidad analisis, int idMuestra) throws Exception {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM ServicioAnalisisEntidad a", com.entidades.ServicioAnalisisEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}