/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.DoctorEntidad;
import com.persistenciaclinicabda.conexion.IConexionBD;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author oscar
 */
public class DoctorDAO {
    
    private final IConexionBD conexionBD;

    public DoctorDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void guardarDoctor(DoctorEntidad doctor) throws Exception {
        EntityManager em = conexionBD.crearConexion();
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
    
    public List<DoctorEntidad> obtenerTodosLosDoctores() throws Exception {
        EntityManager em = conexionBD.crearConexion();
        try {
            return em.createQuery("SELECT d FROM DoctorEntidad d", DoctorEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}