/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.negocioclinicabda.DoctorBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author oscar
 */
public class FrmRegistrarDoctor extends JFrame {

    private JTextField txtNombres, txtPaterno, txtMaterno, txtEspecialidad, txtCedula;
    private JComboBox<String> cmbSexo;
    private JButton btnGuardar;
    private DoctorBO doctorBO;

    public FrmRegistrarDoctor() {
        doctorBO = new DoctorBO();
        
        setTitle("Catálogo - Registrar Médico");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel(" Nombre(s):"));
        txtNombres = new JTextField();
        add(txtNombres);

        add(new JLabel(" Apellido Paterno:"));
        txtPaterno = new JTextField();
        add(txtPaterno);

        add(new JLabel(" Apellido Materno:"));
        txtMaterno = new JTextField();
        add(txtMaterno);

        add(new JLabel(" Sexo:"));
        cmbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        add(cmbSexo);

        add(new JLabel(" Especialidad:"));
        txtEspecialidad = new JTextField();
        add(txtEspecialidad);

        add(new JLabel(" Cédula Profesional:"));
        txtCedula = new JTextField();
        add(txtCedula);

        add(new JLabel(""));
        btnGuardar = new JButton("Guardar Médico");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        String nombres = txtNombres.getText();
        String paterno = txtPaterno.getText();
        String materno = txtMaterno.getText();
        String sexo = cmbSexo.getSelectedItem().toString();
        String especialidad = txtEspecialidad.getText();
        String cedula = txtCedula.getText();

        boolean exito = doctorBO.registrarDoctor(nombres, paterno, materno, sexo, especialidad, cedula);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Médico registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombres.setText(""); txtPaterno.setText(""); txtMaterno.setText("");
            txtEspecialidad.setText(""); txtCedula.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. Verifique los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}