/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;

/**
 *
 * @author le0jx
 */
public class ServicioAnalisisEntidad {
    private int idAnalisis;
    private String nombre;
    private String descripcion;
    private int idMuestra; 
    public ServicioAnalisisEntidad() {
    }

    public ServicioAnalisisEntidad(String nombre, String descripcion, int idMuestra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idMuestra = idMuestra;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }

    
}