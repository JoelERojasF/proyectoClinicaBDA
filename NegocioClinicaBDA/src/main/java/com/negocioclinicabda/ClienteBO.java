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
        
        if (nombres == null || nombres.trim().isEmpty()) {
            System.out.println("Error: El nombre es obligatorio.");
            return false;
        }
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            System.out.println("Error: El apellido paterno es obligatorio.");
            return false;
        }

        ClienteEntidad cliente = new ClienteEntidad(nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, tipoSangre);

        try {
            clienteDAO.guardarCliente(cliente);
            System.out.println("Éxito: ¡Cliente guardado correctamente en la base de datos!");
            return true;
        } catch (Exception e) {
            System.out.println("Error interno al guardar en BD: " + e.getMessage());
            return false;
        }
    }
}