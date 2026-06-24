/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ParametroEntidad;
import com.entidades.RangoEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author oscar
 */
public class RangoDAO {

    private EntityManagerFactory emf;

    public RangoDAO() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaPU");
    }

    public void guardarRango(RangoEntidad rango, int idParametro) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            ParametroEntidad parametroRef = em.getReference(ParametroEntidad.class, idParametro);
            rango.setParametro(parametroRef);
            
            em.persist(rango);
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
}