/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.ServicioAnalisisEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author oscar
 */
public class ParametroDAO {

    private EntityManagerFactory emf;

    public ParametroDAO() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaPU");
    }

    public void guardarParametro(ParametroEntidad parametro, int idAnalisis) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            ServicioAnalisisEntidad analisisRef = em.getReference(ServicioAnalisisEntidad.class, idAnalisis);
            
            parametro.setAnalisis(analisisRef);
            
            em.persist(parametro);
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
    public java.util.List<com.entidades.ParametroEntidad> obtenerTodosLosParametros() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM ParametroEntidad p", com.entidades.ParametroEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}