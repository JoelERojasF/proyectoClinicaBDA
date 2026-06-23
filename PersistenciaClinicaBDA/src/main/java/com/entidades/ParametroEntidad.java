/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;

/**
 *
 * @author Oscar
 */
public class ParametroEntidad {
    private int idParametro;
    private String nombre;
    private int ordenAparicion;
    private String descripcion;
    private String unidadMedida;
    private int idAnalisis; 

    public ParametroEntidad() {
    }

    public ParametroEntidad(String nombre, int ordenAparicion, String descripcion, String unidadMedida, int idAnalisis) {
        this.nombre = nombre;
        this.ordenAparicion = ordenAparicion;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.idAnalisis = idAnalisis;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrdenAparicion() {
        return ordenAparicion;
    }

    public void setOrdenAparicion(int ordenAparicion) {
        this.ordenAparicion = ordenAparicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    
}