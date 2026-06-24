/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;
import jakarta.persistence.*;
/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "resultados")
public class ResultadoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private int idResult;

    @Column(name = "valor_resultado", nullable = false, precision = 10, scale = 2)
    private double valorResultado;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_prueba", nullable = false)
    private PruebaLaboratorioEntidad prueba;

    @ManyToOne
    @JoinColumn(name = "id_parametro", nullable = false)
    private ParametroEntidad parametro;

    public ResultadoEntidad() {
    }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public double getValorResultado() {
        return valorResultado;
    }

    public void setValorResultado(double valorResultado) {
        this.valorResultado = valorResultado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public PruebaLaboratorioEntidad getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaLaboratorioEntidad prueba) {
        this.prueba = prueba;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }

}