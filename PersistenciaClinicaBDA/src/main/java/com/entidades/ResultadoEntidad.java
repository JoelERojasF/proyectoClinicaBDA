/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;

/**
 *
 * @author Oscar
 */
public class ResultadoEntidad {
    private int idResultado;
    private double valorResultado;
    private String observacion;
    private int idPrueba;
    private int idParametro; 

    public ResultadoEntidad() {
    }

    public ResultadoEntidad(double valorResultado, String observacion, int idPrueba, int idParametro) {
        this.valorResultado = valorResultado;
        this.observacion = observacion;
        this.idPrueba = idPrueba;
        this.idParametro = idParametro;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
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

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }


}