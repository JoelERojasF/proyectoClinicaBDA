/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.negocioclinicabda.MuestraBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author oscar
 */
public class FrmRegistrarMuestra extends JFrame {

    private JTextField txtNombre;
    private JButton btnGuardar;
    private MuestraBO muestraBO;

    public FrmRegistrarMuestra() {
        muestraBO = new MuestraBO();
        
        setTitle("Catálogo - Tipo de Muestra");
        setSize(300, 150);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel(" Nombre (ej. Sangre):"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel(""));
        btnGuardar = new JButton("Guardar Muestra");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                boolean exito = muestraBO.registrarMuestra(nombre);
                
                if (exito) {
                    JOptionPane.showMessageDialog(FrmRegistrarMuestra.this, "¡Muestra registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    txtNombre.setText("");
                } else {
                    JOptionPane.showMessageDialog(FrmRegistrarMuestra.this, "Error al registrar la muestra.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}