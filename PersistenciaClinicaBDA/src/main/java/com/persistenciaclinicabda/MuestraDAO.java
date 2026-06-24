/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.MuestraEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
/**
 *
 * @author oscar
 */
public class MuestraDAO {
    
    private EntityManagerFactory emf;

    public MuestraDAO() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaPU");
    }

    public void guardarMuestra(MuestraEntidad muestra) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(muestra);
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

    public List<MuestraEntidad> obtenerTodasLasMuestras() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT m FROM MuestraEntidad m";
            return em.createQuery(jpql, MuestraEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}