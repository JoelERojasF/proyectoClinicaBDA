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
@Table(name = "rangos_evaluacion")
public class RangoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rango")
    private int idRango;

    @Column(name = "sexo_aplica", nullable = false)
    private String sexoAplica;

    @Column(name = "edad_inicial", nullable = false)
    private int edadInicial;

    @Column(name = "edad_final", nullable = false)
    private int edadFinal;

    @Column(name = "rango_inicial", nullable = false, precision = 10, scale = 2)
    private double rangoInicial;

    @Column(name = "rango_final", nullable = false, precision = 10, scale = 2)
    private double rangoFinal;

    @ManyToOne
    @JoinColumn(name = "id_parametro", nullable = false)
    private ParametroEntidad parametro;

    public RangoEntidad() {
    }

    public RangoEntidad(String sexoAplica, int edadInicial, int edadFinal, double rangoInicial, double rangoFinal) {
        this.sexoAplica = sexoAplica;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public String getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(String sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public int getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    public int getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    public double getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(double rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public double getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(double rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }

}