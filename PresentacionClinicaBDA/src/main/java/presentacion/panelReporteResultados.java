/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

import com.entidades.ParametroEntidad;
import com.entidades.PruebaLaboratorioEntidad;
import com.entidades.RangoEntidad;
import com.entidades.ServicioAnalisisEntidad;
import com.negocioclinicabda.PruebaLaboratorioBO;
import com.negocioclinicabda.ResultadoBO;
import com.negocioclinicabda.ServicioAnalisisBO;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author le0jx
 */
public class panelReporteResultados extends javax.swing.JPanel {
    private frmMain frame;
    private final PruebaLaboratorioBO pruebaBO = new PruebaLaboratorioBO();
    private final ServicioAnalisisBO analisisBO = new ServicioAnalisisBO();
    private final ResultadoBO resultadoBO = new ResultadoBO();

    private List<ParametroEntidad> parametrosActuales = java.util.Collections.emptyList();

    /**
     * Creates new form panelResultados
     */
    public panelReporteResultados(frmMain frame) {
        initComponents();
        this.frame = frame;
        actualizarPanel();
    }

    private void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaParametros.getModel();
        modelo.setRowCount(0);
        parametrosActuales = java.util.Collections.emptyList();
    }
    
    private void cargarParametrosEnTabla() {
        ComboItem itemPrueba = (ComboItem) comboBoxPrueba.getSelectedItem();
        ComboItem itemAnalisis = (ComboItem) comboBoxAnalisis.getSelectedItem();

        if (itemPrueba == null || itemAnalisis == null) {
            limpiarTabla();
            return;
        }

        PruebaLaboratorioEntidad prueba = buscarPruebaPorId(itemPrueba.getId());
        if (prueba == null) {
            limpiarTabla();
            return;
        }

        String sexo = prueba.getCliente().getSexo();
        int edad = calcularEdad(prueba.getCliente().getFechaNacimiento());

        parametrosActuales = resultadoBO.obtenerParametrosDeAnalisis(itemAnalisis.getId());

        DefaultTableModel modelo = (DefaultTableModel) tablaParametros.getModel();
        modelo.setRowCount(0);

        for (ParametroEntidad p : parametrosActuales) {
            RangoEntidad rango = resultadoBO.obtenerRangoSugerido(p, sexo, edad);
            String rangoTexto = (rango != null)
                    ? rango.getRangoInicial() + " - " + rango.getRangoFinal() + " " + p.getUnidadMedida()
                    : "Sin rango definido";

            Object[] fila = {p.getNombre(), "", "", rangoTexto};
            modelo.addRow(fila);
        }
    }
    
    private PruebaLaboratorioEntidad buscarPruebaPorId(int idPrueba) {
        for (PruebaLaboratorioEntidad p : pruebaBO.obtenerTodasPruebas()) {
            if (p.getIdPrueba() == idPrueba) {
                return p;
            }
        }
        return null;
    }

    private int calcularEdad(java.sql.Date fechaNacimiento) {
        return Period.between(
                fechaNacimiento.toLocalDate(),
                LocalDate.now()
        ).getYears();
    }


    private static class ComboItem {

        private final int id;
        private final String nombre;

        public ComboItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
    
    public void actualizarPanel() {
        comboBoxPrueba.removeAllItems();
        for (PruebaLaboratorioEntidad p : pruebaBO.obtenerTodasPruebas()) {
            String etiqueta = "Folio " + p.getIdPrueba() + " - " + p.getCliente().getNombres() + " " + p.getCliente().getApellidoPaterno();
            comboBoxPrueba.addItem(new ComboItem(p.getIdPrueba(), etiqueta));
        }

        comboBoxAnalisis.removeAllItems();
        for (ServicioAnalisisEntidad a : analisisBO.obtenerTodosLosAnalisis()) {
            comboBoxAnalisis.addItem(new ComboItem(a.getIdAnalisis(), a.getNombre()));
        }

        limpiarTabla();
    }
    
    private void guardar(){
        ComboItem itemPrueba = (ComboItem) comboBoxPrueba.getSelectedItem();

        if (itemPrueba == null || parametrosActuales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una prueba y un análisis con parámetros.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tablaParametros.getModel();
        int idPrueba = itemPrueba.getId();
        int guardados = 0;

        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            String valorTexto = modelo.getValueAt(fila, 1).toString().trim();

            if (valorTexto.isEmpty()) {
                continue;
            }

            try {
                double valor = Double.parseDouble(valorTexto);
                String observacion = modelo.getValueAt(fila, 2).toString().trim();
                int idParametro = parametrosActuales.get(fila).getIdParametro();

                boolean exito = resultadoBO.registrarResultado(idPrueba, idParametro, valor, observacion.isEmpty() ? null : observacion);
                if (exito) {
                    guardados++;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El resultado de '" + parametrosActuales.get(fila).getNombre() + "' debe ser numérico.", "Error de formato", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        if (guardados > 0) {
            JOptionPane.showMessageDialog(this, "¡" + guardados + " resultado(s) registrado(s) exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarParametrosEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se capturó ningún resultado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSolicitudes = new javax.swing.JButton();
        btnAnlaisis = new javax.swing.JButton();
        btnResultados = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaParametros = new javax.swing.JTable();
        comboBoxPrueba = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxAnalisis = new javax.swing.JComboBox<>();
        btnReportes = new javax.swing.JButton();
        btnReportes1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(638, 508));
        setMinimumSize(new java.awt.Dimension(638, 508));

        jLabel1.setText("resultados");

        btnSolicitudes.setText("solicitudes");
        btnSolicitudes.setMaximumSize(new java.awt.Dimension(84, 23));
        btnSolicitudes.setMinimumSize(new java.awt.Dimension(84, 23));
        btnSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudesActionPerformed(evt);
            }
        });

        btnAnlaisis.setText("analisis");
        btnAnlaisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnlaisisActionPerformed(evt);
            }
        });

        btnResultados.setText("resultados");
        btnResultados.setEnabled(false);
        btnResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadosActionPerformed(evt);
            }
        });

        btnGuardar.setText("guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tablaParametros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "parametro", "resultado ", "observacion ", "rango sugerido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaParametros);

        comboBoxPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPruebaActionPerformed(evt);
            }
        });

        jLabel2.setText("pruebas:");

        jLabel3.setText("analisis:");

        comboBoxAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAnalisisActionPerformed(evt);
            }
        });

        btnReportes.setText("reportes");
        btnReportes.setMaximumSize(new java.awt.Dimension(84, 23));
        btnReportes.setMinimumSize(new java.awt.Dimension(84, 23));

        btnReportes1.setText("reportes");
        btnReportes1.setMaximumSize(new java.awt.Dimension(84, 23));
        btnReportes1.setMinimumSize(new java.awt.Dimension(84, 23));
        btnReportes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportes1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnlaisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSolicitudes, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(btnReportes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(397, 397, 397)
                                .addComponent(btnGuardar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBoxPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBoxAnalisis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btnSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnlaisis, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReportes1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(comboBoxAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed
        // TODO add your handling code here:
        frame.mostrarPanel("solicitud");
    }//GEN-LAST:event_btnSolicitudesActionPerformed

    private void btnAnlaisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnlaisisActionPerformed
        // TODO add your handling code here:
        frame.mostrarPanel("analisis");
    }//GEN-LAST:event_btnAnlaisisActionPerformed

    private void btnResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadosActionPerformed
        // TODO add your handling code here:
        frame.mostrarPanel("resultados");
    }//GEN-LAST:event_btnResultadosActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboBoxPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPruebaActionPerformed
        // TODO add your handling code here:
        cargarParametrosEnTabla();
    }//GEN-LAST:event_comboBoxPruebaActionPerformed

    private void comboBoxAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAnalisisActionPerformed
        // TODO add your handling code here:
        cargarParametrosEnTabla();
    }//GEN-LAST:event_comboBoxAnalisisActionPerformed

    private void btnReportes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportes1ActionPerformed
        // TODO add your handling code here:
        frame.mostrarPanel("reportes");
    }//GEN-LAST:event_btnReportes1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnlaisis;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReportes1;
    private javax.swing.JButton btnResultados;
    private javax.swing.JButton btnSolicitudes;
    private javax.swing.JComboBox<ComboItem> comboBoxAnalisis;
    private javax.swing.JComboBox<ComboItem> comboBoxPrueba;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaParametros;
    // End of variables declaration//GEN-END:variables
}
