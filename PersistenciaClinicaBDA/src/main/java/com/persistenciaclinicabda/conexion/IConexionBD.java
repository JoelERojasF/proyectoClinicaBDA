/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.persistenciaclinicabda.conexion;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author oscar
 */
public interface IConexionBD {
 Connection crearConexion() throws SQLException;
}