/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;

import com.entidades.ClienteEntidad;
import com.entidades.DoctorEntidad;
import com.entidades.ParametroEntidad;
import com.entidades.PruebaLaboratorioEntidad;
import com.entidades.RangoEntidad;
import com.entidades.ResultadoEntidad;
import com.entidades.ServicioAnalisisEntidad;
import com.persistenciaclinicabda.RangoDAO;
import com.persistenciaclinicabda.ResultadoDAO;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author le0jx
 */
public class ResultadoBO {
    private IConexionBD conexion = new ConexionBD();
    private ResultadoDAO resultadoDAO = new ResultadoDAO(conexion);
    private RangoDAO rangoDAO = new RangoDAO(conexion);
    
    public String generarReporte(
            List<Integer> idsPrueba) throws SQLException {

        List<ResultadoEntidad> resultados= resultadoDAO.buscarResultadosPorPruebas(idsPrueba);

        if (resultados.isEmpty()) {
            return "No existen resultados";
        }

        validarMismoPaciente(resultados);

        ClienteEntidad cliente= resultados.get(0).getPrueba().getCliente();

        int edad = calcularEdad(cliente.getFechaNacimiento());

        StringBuilder sb = new StringBuilder();

        escribirEncabezado(sb,cliente,edad);

        Map<Integer, List<ResultadoEntidad>> agrupados = resultados.stream().collect(Collectors.groupingBy(r-> r.getPrueba().getIdPrueba()));

        for (List<ResultadoEntidad> prueba : agrupados.values()) {
            agregarPrueba(sb,prueba,cliente,edad);
        }

        return sb.toString();
    }

    private void escribirEncabezado(StringBuilder sb, ClienteEntidad cliente, int edad) {
        sb.append("===== REPORTE CLINICO =====\n\n" );

        sb.append("Paciente: ").append(cliente.getNombres()).append(" ").append(cliente.getApellidoPaterno()).append(" ").append(cliente.getApellidoMaterno()).append("\n");

        sb.append("Edad: ").append(edad).append("\n");

        sb.append("Sexo: ").append(cliente.getSexo()).append("\n");

        sb.append("Tipo sangre: ").append(cliente.getTipoSangre()).append("\n\n");
    }

    private void agregarPrueba(StringBuilder sb, List<ResultadoEntidad> resultados, ClienteEntidad cliente, int edad) throws SQLException {
        PruebaLaboratorioEntidad prueba = resultados.get(0).getPrueba();

        DoctorEntidad doctor = prueba.getDoctor();

        sb.append("===================\n");

        sb.append("PRUEBA ").append(prueba.getIdPrueba()).append("\n");

        sb.append("Fecha: ").append(prueba.getFechaHoraGeneracion()).append("\n");

        if (doctor != null) {
            sb.append("Doctor: ").append( doctor.getNombres()).append(" ").append(doctor.getApellidoPaterno()).append("\n");
        }

        for (ResultadoEntidad r : resultados) {
            ParametroEntidad p = r.getParametro();

            ServicioAnalisisEntidad a = p.getAnalisis();

            RangoEntidad rango= rangoDAO.buscarRango(p, cliente.getSexo(), edad);

            sb.append("\nAnalisis: ").append(a.getNombre()).append("\n");

            sb.append("Muestra: ").append(a.getMuestra().getNombre()).append("\n");

            sb.append("Parametro: ").append(p.getNombre()).append("\n");

            sb.append("Resultado: ").append(r.getValorResultado()).append(" ").append(p.getUnidadMedida()).append("\n");

            if (rango != null) {
                sb.append("Rango normal: ").append(rango.getRangoInicial()).append(" - ").append(rango.getRangoFinal()).append("\n");
            }

            if (r.getObservacion() != null) {
                sb.append("Observacion: ").append(r.getObservacion()).append("\n");
            }
        }

    }

    private int calcularEdad(Date fecha) {
        return Period.between(fecha.toLocalDate(),LocalDate.now()).getYears();
    }

    private void validarMismoPaciente(List<ResultadoEntidad> lista) {
        Set<Integer> ids= lista.stream().map(r-> r.getPrueba().getCliente().getIdCliente()).collect(Collectors.toSet());

        if (ids.size() > 1) {
            throw new IllegalArgumentException("Las pruebas pertenecen a pacientes distintos");
        }
    }
    
    
}
