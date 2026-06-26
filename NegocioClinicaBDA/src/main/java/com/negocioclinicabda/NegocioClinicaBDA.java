/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.negocioclinicabda;
import com.entidades.ClienteEntidad;
import com.persistenciaclinicabda.ClienteDAO;
import java.sql.Date;
/**
 *
 * @author le0jx
 */
public class NegocioClinicaBDA {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ClienteBO c = new ClienteBO();
        c.registrarCliente("juan", "rodriguez", "hernandez", new Date(2008, 28, 4), "masculino", "O-");
    }
}
