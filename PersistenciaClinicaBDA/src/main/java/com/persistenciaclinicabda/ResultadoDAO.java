/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda;

import com.entidades.ParametroEntidad;
import com.entidades.PruebaLaboratorioEntidad;
import com.entidades.ResultadoEntidad;
import com.persistenciaclinicabda.conexion.IConexionBD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author le0jx
 */
public class ResultadoDAO {
    private final IConexionBD conexionBD;

    public ResultadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    public List<ResultadoEntidad> buscarResultadosPorPruebas(List<Integer> idsPrueba) throws SQLException {
        EntityManager em = conexionBD.crearConexion();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ResultadoEntidad> cq = cb.createQuery(ResultadoEntidad.class);

        Root<ResultadoEntidad> resultado = cq.from(ResultadoEntidad.class);

        Join<ResultadoEntidad, PruebaLaboratorioEntidad> prueba = resultado.join("prueba");

        Join<ResultadoEntidad, ParametroEntidad> parametro= resultado.join("parametro");

        resultado.fetch("prueba");

        resultado.fetch("parametro");

        cq.select(resultado).distinct(true).where(prueba.get("idPrueba").in(idsPrueba)).orderBy(cb.asc(prueba.get("idPrueba")),cb.asc(parametro.get("ordenAparicion")));

        return em.createQuery(cq).getResultList();
    }
}

