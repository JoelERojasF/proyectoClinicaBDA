/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistenciaclinicabda.conexion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author oscar
 */
public class ConexionBD implements IConexionBD {

    @Override
    public EntityManager crearConexion() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClinicaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}