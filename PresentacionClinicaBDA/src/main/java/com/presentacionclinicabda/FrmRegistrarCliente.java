/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.negocioclinicabda.ClienteBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
/**
 *
 * @author oscar
 */
public class FrmRegistrarCliente extends JFrame {

    private JTextField txtNombres, txtPaterno, txtMaterno, txtFechaNac, txtSangre;
    private JComboBox<String> cmbSexo;
    private JButton btnGuardar;
    private ClienteBO clienteBO;

    public FrmRegistrarCliente() {
        clienteBO = new ClienteBO();
        
        setTitle("Registrar Nuevo Cliente - Salud Total");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel(" Nombres:"));
        txtNombres = new JTextField();
        add(txtNombres);

        add(new JLabel(" Apellido Paterno:"));
        txtPaterno = new JTextField();
        add(txtPaterno);

        add(new JLabel(" Apellido Materno:"));
        txtMaterno = new JTextField();
        add(txtMaterno);

        add(new JLabel(" Fecha Nacimiento (AAAA-MM-DD):"));
        txtFechaNac = new JTextField();
        add(txtFechaNac);

        add(new JLabel(" Sexo:"));
        cmbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        add(cmbSexo);

        add(new JLabel(" Tipo de Sangre (ej. O+):"));
        txtSangre = new JTextField();
        add(txtSangre);

        add(new JLabel(""));
        btnGuardar = new JButton("Guardar Cliente");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        try {
            String nombres = txtNombres.getText();
            String paterno = txtPaterno.getText();
            String materno = txtMaterno.getText();
            String fechaTexto = txtFechaNac.getText();
            String sexo = cmbSexo.getSelectedItem().toString();
            String sangre = txtSangre.getText();

            Date fechaNacimiento = Date.valueOf(fechaTexto);

            boolean exito = clienteBO.registrarCliente(nombres, paterno, materno, fechaNacimiento, sexo, sangre);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Cliente registrado exitosamente en la Base de Datos!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar. Revisa la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Usa AAAA-MM-DD", "Error de Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCajas() {
        txtNombres.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtFechaNac.setText("");
        txtSangre.setText("");
    }
}