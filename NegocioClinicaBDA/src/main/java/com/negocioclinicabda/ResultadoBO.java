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
import com.persistenciaclinicabda.ParametroDAO;
import com.persistenciaclinicabda.PruebaLaboratorioDAO;
import com.persistenciaclinicabda.RangoDAO;
import com.persistenciaclinicabda.ResultadoDAO;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.util.Date; 
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
/**
 *
 * @author le0jx
 */
public class ResultadoBO {
    private IConexionBD conexion = new ConexionBD();
    private ResultadoDAO resultadoDAO = new ResultadoDAO(conexion);
    private RangoDAO rangoDAO = new RangoDAO(conexion);
    private ParametroDAO parametroDAO = new ParametroDAO(conexion);

    public List<ParametroEntidad> obtenerParametrosDeAnalisis(int idAnalisis) {
        try {
            return parametroDAO.obtenerParametrosPorAnalisis(idAnalisis);
        } catch (Exception e) {
            System.err.println("Error al consultar parámetros del análisis: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    public RangoEntidad obtenerRangoSugerido(ParametroEntidad parametro, String sexo, int edad) {
        try {
            return rangoDAO.buscarRangoParametro(parametro.getIdParametro(), sexo, edad);
        } catch (Exception e) {
            System.err.println("Error al consultar rango: " + e.getMessage());
            return null;
        }
    }

    public boolean registrarResultado(int idPrueba, int idParametro, double valor, String observacion) {
        ResultadoEntidad resultado = new ResultadoEntidad();
        resultado.setValorResultado(valor);
        resultado.setObservacion(observacion);

        try {
            resultadoDAO.guardarResultado(resultado, idPrueba, idParametro);
            System.out.println("Éxito: Resultado registrado correctamente con JPA.");
            return true;
        } catch (Exception e) {
            System.err.println("Error en BD al guardar resultado con JPA: " + e.getMessage());
            return false;
        }
    }
    
    public String generarReporte(List<Integer> idsPrueba) throws Exception { 

        List<ResultadoEntidad> resultados = resultadoDAO.buscarResultadosPorPruebas(idsPrueba);

        if (resultados.isEmpty()) {
            return "No existen resultados";
        }

        validarMismoPaciente(resultados);

        ClienteEntidad cliente = resultados.get(0).getPrueba().getCliente();
        int edad = calcularEdad(cliente.getFechaNacimiento());

        StringBuilder sb = new StringBuilder();
        escribirEncabezado(sb, cliente, edad);

        Map<Integer, List<ResultadoEntidad>> agrupados = resultados.stream()
                .collect(Collectors.groupingBy(r -> r.getPrueba().getIdPrueba()));

        for (List<ResultadoEntidad> prueba : agrupados.values()) {
            agregarPrueba(sb, prueba, cliente, edad);
        }

        return sb.toString();
    }
    
    public String generarReportePorCliente(int idCliente) {
        try {
            PruebaLaboratorioDAO pruebaDAO = new PruebaLaboratorioDAO(conexion);
            List<Integer> idsPrueba = pruebaDAO.obtenerTodasPruebas().stream()
                    .filter(p -> p.getCliente().getIdCliente() == idCliente)
                    .map(PruebaLaboratorioEntidad::getIdPrueba)
                    .collect(Collectors.toList());

            if (idsPrueba.isEmpty()) {
                return "Este cliente no tiene pruebas registradas.";
            }

            return generarReporte(idsPrueba);
        } catch (Exception e) {
            System.err.println("Error al generar reporte por cliente: " + e.getMessage());
            return "Ocurrió un error al generar el reporte.";
        }
    }

    private void escribirEncabezado(StringBuilder sb, ClienteEntidad cliente, int edad) {
        sb.append("===== REPORTE CLINICO =====\n\n");
        sb.append("Paciente: ").append(cliente.getNombres()).append(" ").append(cliente.getApellidoPaterno()).append(" ").append(cliente.getApellidoMaterno()).append("\n");
        sb.append("Edad: ").append(edad).append("\n");
        sb.append("Sexo: ").append(cliente.getSexo()).append("\n");
        sb.append("Tipo sangre: ").append(cliente.getTipoSangre()).append("\n\n");
    }

    private void agregarPrueba(StringBuilder sb, List<ResultadoEntidad> resultados, ClienteEntidad cliente, int edad) throws Exception {
        PruebaLaboratorioEntidad prueba = resultados.get(0).getPrueba();
        DoctorEntidad doctor = prueba.getDoctor();

        sb.append("===================\n");
        sb.append("Folio: ").append(prueba.getIdPrueba()).append("\n");
        sb.append("Fecha: ").append(prueba.getFechaHoraGeneracion()).append("\n");

        if (doctor != null) {
            sb.append("Doctor: ").append(doctor.getNombres()).append(" ").append(doctor.getApellidoPaterno()).append("\n");
        }

        for (ResultadoEntidad r : resultados) {
            ParametroEntidad p = r.getParametro();
            ServicioAnalisisEntidad a = p.getAnalisis();

            RangoEntidad rango = obtenerRangoSugerido(p, cliente.getSexo(), edad);

            sb.append("\nAnalisis: ").append(a.getNombre()).append("\n");
            sb.append("Muestra: ").append(a.getMuestra().getNombre()).append("\n");
            sb.append("Parametro: ").append(p.getNombre()).append("\n");
            sb.append("Resultado: ").append(r.getValorResultado()).append(" ").append(p.getUnidadMedida()).append("\n");

            if (rango != null) {
                sb.append("Rango normal: ").append(rango.getRangoInicial()).append(" - ").append(rango.getRangoFinal()).append("\n");
            }

            if (r.getObservacion() != null && !r.getObservacion().trim().isEmpty()) {
                sb.append("Observacion: ").append(r.getObservacion()).append("\n");
            }
        }
    }

    private int calcularEdad(Date fecha) {
        if (fecha == null) return 0;
        LocalDate fechaNac = new java.sql.Date(fecha.getTime()).toLocalDate();
        if (fechaNac.getYear() > 3000) {
            fechaNac = fechaNac.withYear(fechaNac.getYear() - 1900);
        }
        
        return Period.between(fechaNac, LocalDate.now()).getYears();
    }

    private void validarMismoPaciente(List<ResultadoEntidad> lista) {
        Set<Integer> ids = lista.stream().map(r -> r.getPrueba().getCliente().getIdCliente()).collect(Collectors.toSet());

        if (ids.size() > 1) {
            throw new IllegalArgumentException("Las pruebas pertenecen a pacientes distintos");
        }
    }
    
    public boolean exportarReporteAPDF(int idCliente, String rutaDestino) {
        try {
            String textoReporte = generarReportePorCliente(idCliente);
            PdfWriter writer = new PdfWriter(rutaDestino);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);            
            document.add(new Paragraph(textoReporte));
            document.close();
            
            System.out.println("Éxito: PDF generado en " + rutaDestino);
            return true;
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            return false;
        }
    }
}