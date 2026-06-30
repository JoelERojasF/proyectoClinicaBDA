/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.persistenciaclinicabda.conexion;
import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public interface IConexionBD {
 EntityManager crearConexion() throws SQLException;
}