/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.persistenciaclinicabda;

import com.entidades.ClienteEntidad;
import com.persistenciaclinicabda.conexion.ConexionBD;
import com.persistenciaclinicabda.conexion.IConexionBD;
import java.sql.Date;

/**
 *
 * @author le0jx
 */
public class PersistenciaClinicaBDA {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        IConexionBD conexion = new ConexionBD();
        ClienteDAO c = new ClienteDAO(conexion);
        c.guardarCliente(new ClienteEntidad("jose", "perez", "zasueta", new Date(2003, 18, 9), "masculino", "O+"));
    }
}
