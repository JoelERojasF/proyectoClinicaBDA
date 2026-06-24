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
@Table(name = "parametros")
public class ParametroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private int idParametro;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "orden_aparicion", nullable = false)
    private int ordenAparicion;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "unidad_medida", nullable = false, length = 50)
    private String unidadMedida;

    @ManyToOne
    @JoinColumn(name = "id_analisis", nullable = false)
    private ServicioAnalisisEntidad analisis;

    public ParametroEntidad() {
    }


    public ParametroEntidad(String nombre, int ordenAparicion, String descripcion, String unidadMedida) {
        this.nombre = nombre;
        this.ordenAparicion = ordenAparicion;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
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

    public ServicioAnalisisEntidad getAnalisis() {
        return analisis;
    }

    public void setAnalisis(ServicioAnalisisEntidad analisis) {
        this.analisis = analisis;
    }
}