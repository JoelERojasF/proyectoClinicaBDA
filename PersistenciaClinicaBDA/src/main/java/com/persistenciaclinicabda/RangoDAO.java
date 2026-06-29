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
    
    public RangoEntidad buscarRango(ParametroEntidad parametro,String sexo, int edad) throws SQLException {
        EntityManager em = conexionBD.crearConexion();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<RangoEntidad> cq = cb.createQuery(RangoEntidad.class);

        Root<RangoEntidad> rango = cq.from(RangoEntidad.class);

        cq.where(cb.equal(rango.get("parametro"),parametro),cb.equal(rango.get("sexoAplica"),sexo),cb.lessThanOrEqualTo(rango.get("edadInicial"),edad),cb.greaterThanOrEqualTo(rango.get("edadFinal"),edad) );

        List<RangoEntidad> lista= em.createQuery(cq).getResultList();

        return lista.isEmpty()? null: lista.get(0);
    }
}