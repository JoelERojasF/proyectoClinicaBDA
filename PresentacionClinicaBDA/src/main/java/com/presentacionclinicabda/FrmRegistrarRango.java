/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.presentacionclinicabda;
import com.entidades.ParametroEntidad;
import com.negocioclinicabda.RangoBO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author oscar
 */
public class FrmRegistrarRango extends JFrame {

    private JTextField txtEdadInicial, txtEdadFinal, txtRangoInicial, txtRangoFinal;
    private JComboBox<String> cmbSexo;
    private JComboBox<ComboItem> cmbParametros;
    private JButton btnGuardar;
    private RangoBO rangoBO;

    public FrmRegistrarRango() {
        rangoBO = new RangoBO();
        
        setTitle("Catálogo - Nuevo Rango de Referencia");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel(" Sexo Aplica:"));
        cmbSexo = new JComboBox<>(new String[]{"Ambos", "Masculino", "Femenino"});
        add(cmbSexo);

        add(new JLabel(" Edad Inicial (Años):"));
        txtEdadInicial = new JTextField();
        add(txtEdadInicial);

        add(new JLabel(" Edad Final (Años):"));
        txtEdadFinal = new JTextField();
        add(txtEdadFinal);

        add(new JLabel(" Valor Mínimo (Rango Inicial):"));
        txtRangoInicial = new JTextField();
        add(txtRangoInicial);

        add(new JLabel(" Valor Máximo (Rango Final):"));
        txtRangoFinal = new JTextField();
        add(txtRangoFinal);

        add(new JLabel(" Pertenece al Parámetro:"));
        cmbParametros = new JComboBox<>();
        cargarParametrosEnCombo();
        add(cmbParametros);

        add(new JLabel("")); 
        btnGuardar = new JButton("Guardar Rango");
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void cargarParametrosEnCombo() {
        List<ParametroEntidad> lista = rangoBO.obtenerListaParametros();
        for (ParametroEntidad p : lista) {
            cmbParametros.addItem(new ComboItem(p.getIdParametro(), p.getNombre()));
        }
    }

    private void guardarDatos() {
        try {
            String sexo = cmbSexo.getSelectedItem().toString();
            int edadIni = Integer.parseInt(txtEdadInicial.getText());
            int edadFin = Integer.parseInt(txtEdadFinal.getText());
            double rangoIni = Double.parseDouble(txtRangoInicial.getText());
            double rangoFin = Double.parseDouble(txtRangoFinal.getText());
            
            ComboItem item = (ComboItem) cmbParametros.getSelectedItem();
            if (item == null) {
                JOptionPane.showMessageDialog(this, "Debe registrar un parámetro primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int idParametro = item.getId();

            boolean exito = rangoBO.registrarRango(sexo, edadIni, edadFin, rangoIni, rangoFin, idParametro);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Rango registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtEdadInicial.setText("");
                txtEdadFinal.setText("");
                txtRangoInicial.setText("");
                txtRangoFinal.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el rango.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revisa los campos numéricos (Edad y Valores).", "Error de Formato", JOptionPane.WARNING_MESSAGE);
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
        public String toString() { return nombre; }
    }
}