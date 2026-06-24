/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;
import jakarta.persistence.*;
/**
 *
 * @author OscaR
 */
@Entity
@Table(name = "tipos_muestra")
public class MuestraEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_muestra")
    private int idMuestra;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    public MuestraEntidad() {
    }

    public MuestraEntidad(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}