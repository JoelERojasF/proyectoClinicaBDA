/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;

import com.entidades.ClienteEntidad;
import com.entidades.DoctorEntidad;
import com.entidades.PruebaLaboratorioEntidad;
import com.persistenciaclinicabda.conexion.IConexionBD;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;

/**
 *
 * @author le0jx
 */
public class PruebaLaboratorioDAO {
    
    private final IConexionBD conexionBD;

    public PruebaLaboratorioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    public void guardarPruebaLaboratorio(PruebaLaboratorioEntidad prueba, int idCliente, int idDoctor) throws SQLException{
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            ClienteEntidad cliente = em.getReference(ClienteEntidad.class, idCliente);
            prueba.setCliente(cliente);
            
            if(idDoctor > 0){
            DoctorEntidad doctor = em.getReference(DoctorEntidad.class, idDoctor);
            prueba.setDoctor(doctor);
            }
            
            em.persist(prueba);
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
    
    public java.util.List<com.entidades.PruebaLaboratorioEntidad> obtenerTodasPruebas() throws Exception {
        EntityManager em = conexionBD.crearConexion();
        try {
            return em.createQuery("SELECT a FROM PruebaLaboratorioEntidad a", PruebaLaboratorioEntidad.class).getResultList();
        } finally {
            em.close();
        }
    }
}
