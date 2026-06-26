/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author le0jx
 */
@Entity
@Table(name = "analisis")
public class ServicioAnalisisEntidad implements Serializable {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analisis")
    private int idAnalisis;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_muestra", nullable = false)
    private MuestraEntidad muestra;
    
public ServicioAnalisisEntidad() {
    }

    public ServicioAnalisisEntidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public MuestraEntidad getMuestra() {
        return muestra;
    }

    public void setMuestra(MuestraEntidad muestra) {
        this.muestra = muestra;
    }
}