/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;

/**
 *
 * @author Oscar
 */
public class RangoEntidad {
    private int idRango;
    private String sexoAplica;
    private int edadInicial;
    private int edadFinal;
    private double rangoInicial; 
    private double rangoFinal;
    private int idParametro;

    public RangoEntidad() {
    }

    public RangoEntidad(String sexoAplica, int edadInicial, int edadFinal, double rangoInicial, double rangoFinal, int idParametro) {
        this.sexoAplica = sexoAplica;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.idParametro = idParametro;
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

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    
}