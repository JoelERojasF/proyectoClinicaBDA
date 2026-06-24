/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.negocioclinicabda;
import com.entidades.ClienteEntidad;
import com.persistenciaclinicabda.ClienteDAO;
import java.sql.Date;
/**
 *
 * @author oscar
 */
public class ClienteBO {
    
    private ClienteDAO clienteDAO;

    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean registrarCliente(String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String sexo, String tipoSangre) {
        
        if (nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            System.out.println("Error: Nombre y apellido paterno son obligatorios.");
            return false;
        }

        ClienteEntidad cliente = new ClienteEntidad(nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, tipoSangre);

        try {
            clienteDAO.guardarCliente(cliente);
            System.out.println("Éxito: Cliente registrado en la BD con JPA.");
            return true;
        } catch (Exception e) {
            System.out.println("Error en BD al guardar cliente con JPA: " + e.getMessage());
            return false;
        }
    }
}