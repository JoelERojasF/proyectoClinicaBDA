/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.entidades.MuestraEntidad;
import com.negocioclinicabda.MuestraBO;
import com.negocioclinicabda.ServicioAnalisisBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author oscar
 */
public class FrmRegistrarAnalisis extends JFrame {

    private JTextField txtNombre, txtDescripcion;
    private JComboBox<ComboItem> cmbMuestras;
    private JButton btnGuardar;
    
    private ServicioAnalisisBO analisisBO;
    private MuestraBO muestraBO;

    public FrmRegistrarAnalisis() {
        analisisBO = new ServicioAnalisisBO();
        muestraBO = new MuestraBO();
        
        setTitle("Catálogo - Nuevo Análisis");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Nombre del Análisis:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel(" Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel(" Requiere Muestra de:"));
        cmbMuestras = new JComboBox<>();
        cargarMuestrasEnCombo(); 
        add(cmbMuestras);

        add(new JLabel("")); 
        btnGuardar = new JButton("Guardar Análisis");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void cargarMuestrasEnCombo() {
        List<MuestraEntidad> listaMuestras = muestraBO.obtenerListaMuestras();
        for (MuestraEntidad muestra : listaMuestras) {
            cmbMuestras.addItem(new ComboItem(muestra.getIdMuestra(), muestra.getNombre()));
        }
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        
        ComboItem itemSeleccionado = (ComboItem) cmbMuestras.getSelectedItem();
        
        if (itemSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe registrar una muestra primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idMuestra = itemSeleccionado.getId();

        boolean exito = analisisBO.registrarAnalisis(nombre, descripcion, idMuestra);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Análisis registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombre.setText("");
            txtDescripcion.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el análisis.", "Error", JOptionPane.ERROR_MESSAGE);
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