/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;
import com.entidades.ClienteEntidad;
import com.entidades.ParametroEntidad;
import com.entidades.RangoEntidad;
import com.persistenciaclinicabda.conexion.IConexionBD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
/**
 *
 * @author oscar
 */
public class RangoDAO {

    private final IConexionBD conexionBD;

    public RangoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void guardarRango(RangoEntidad rango, int idParametro) throws Exception {
        EntityManager em = conexionBD.crearConexion();
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
    
    public RangoEntidad buscarRangoParametro(int idParametro, String sexoPaciente, int edadPaciente) throws SQLException {
        EntityManager em = conexionBD.crearConexion();
        try {
            System.out.println("-> DAO BUSCANDO RANGO:");
            System.out.println("   ID Parametro: " + idParametro);
            System.out.println("   Edad del Paciente: " + edadPaciente);
            System.out.println("   Sexo del Paciente: '" + sexoPaciente + "'");
            
            String jpql = "SELECT r FROM RangoEntidad r WHERE r.parametro.idParametro = :idParam " +
                          "AND :edad >= r.edadInicial AND :edad <= r.edadFinal " +
                          "AND (r.sexoAplica = :sexo OR r.sexoAplica = 'Ambos')";
            
            List<RangoEntidad> resultados = em.createQuery(jpql, RangoEntidad.class)
                    .setParameter("idParam", idParametro)
                    .setParameter("edad", edadPaciente)
                    .setParameter("sexo", sexoPaciente)
                    .getResultList();
                    
            System.out.println("   Rangos encontrados en BD: " + resultados.size());
            
            return resultados.stream().findFirst().orElse(null);
            
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}