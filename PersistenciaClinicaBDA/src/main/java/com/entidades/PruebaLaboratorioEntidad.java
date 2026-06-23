/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;
import java.sql.Timestamp;
/**
 *
 * @author Oscar
 */
public class PruebaLaboratorioEntidad {
    private int idPrueba;
    private Timestamp fechaHoraGeneracion; 
    private int idCliente; 
    private int idDoctor; 

    public PruebaLaboratorioEntidad() {
    }
    
    public PruebaLaboratorioEntidad(int idCliente, int idDoctor) {
        this.idCliente = idCliente;
        this.idDoctor = idDoctor;
    }

    public PruebaLaboratorioEntidad(Timestamp fechaHoraGeneracion, int idCliente, int idDoctor) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
        this.idCliente = idCliente;
        this.idDoctor = idDoctor;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Timestamp getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public void setFechaHoraGeneracion(Timestamp fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    
}