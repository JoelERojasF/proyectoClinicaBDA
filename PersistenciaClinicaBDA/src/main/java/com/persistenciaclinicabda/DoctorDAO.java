/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.DoctorEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author oscar
 */
public class DoctorDAO {
    
    private EntityManagerFactory emf;

    public DoctorDAO() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaPU");
    }

    public void guardarDoctor(DoctorEntidad doctor) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doctor);
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