/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entidades;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "pruebas_laboratorio")
public class PruebaLaboratorioEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prueba")
    private int idPrueba;

    @Column(name = "fecha_hora_generacion", insertable = false, updatable = false)
    private Timestamp fechaHoraGeneracion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntidad cliente;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private DoctorEntidad doctor;

    public PruebaLaboratorioEntidad() {
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

    public ClienteEntidad getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntidad cliente) {
        this.cliente = cliente;
    }

    public DoctorEntidad getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntidad doctor) {
        this.doctor = doctor;
    }

}