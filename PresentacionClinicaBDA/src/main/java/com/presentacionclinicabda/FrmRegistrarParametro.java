/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.entidades.ServicioAnalisisEntidad;
import com.negocioclinicabda.ParametroBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author oscar
 */
public class FrmRegistrarParametro extends JFrame {

    private JTextField txtNombre, txtOrden, txtDescripcion, txtUnidadMedida;
    private JComboBox<ComboItem> cmbAnalisis;
    private JButton btnGuardar;
    private ParametroBO parametroBO;

    public FrmRegistrarParametro() {
        parametroBO = new ParametroBO();
        
        setTitle("Catálogo - Nuevo Parámetro");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel(" Nombre (ej. Glucosa):"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel(" Orden de Aparición (ej. 1):"));
        txtOrden = new JTextField();
        add(txtOrden);

        add(new JLabel(" Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel(" Unidad de Medida (ej. mg/dL):"));
        txtUnidadMedida = new JTextField();
        add(txtUnidadMedida);

        add(new JLabel(" Pertenece al Análisis:"));
        cmbAnalisis = new JComboBox<>();
        cargarAnalisisEnCombo(); 
        add(cmbAnalisis);

        add(new JLabel(""));
        btnGuardar = new JButton("Guardar Parámetro");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void cargarAnalisisEnCombo() {
        List<ServicioAnalisisEntidad> lista = parametroBO.obtenerListaAnalisis();
        for (ServicioAnalisisEntidad analisis : lista) {
            cmbAnalisis.addItem(new ComboItem(analisis.getIdAnalisis(), analisis.getNombre()));
        }
    }

    private void guardarDatos() {
        try {
            String nombre = txtNombre.getText();
            int orden = Integer.parseInt(txtOrden.getText()); 
            String descripcion = txtDescripcion.getText();
            String unidad = txtUnidadMedida.getText();
            
            ComboItem itemSeleccionado = (ComboItem) cmbAnalisis.getSelectedItem();
            
            if (itemSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe registrar un análisis primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idAnalisis = itemSeleccionado.getId();

            boolean exito = parametroBO.registrarParametro(nombre, orden, descripcion, unidad, idAnalisis);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Parámetro registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombre.setText("");
                txtOrden.setText("");
                txtDescripcion.setText("");
                txtUnidadMedida.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el parámetro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El 'Orden de Aparición' debe ser un número.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class ComboItem {
        private int id;
        private String nombre;

        public ComboItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() { return id; }

        @Override
        public String toString() {
            return nombre; 
        }
    }
}