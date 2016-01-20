/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author JAMEO
 */
public class ViewProducts extends javax.swing.JFrame {

    /**
     * Creates new form ViewProducts
     */
    MainController controller;
    String selectedProduct;
    DefaultTableModel model;

    public ViewProducts() {
        initComponents();
    }

    public ViewProducts(MainController aThis) {
        this();
        controller = aThis;
        Utility.centerFrame(this);
        Utility.setFormIcon(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setTitle("View Products");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Search by");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select -", "Category", "Supplier", "Name" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select", " " }));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("View Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Sign out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Name", "Category", "Company"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        controller.backToHome(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        //Show the search type in the label
        String st = "Select Product ";
        st += String.valueOf(jComboBox1.getSelectedItem());
        String str = String.valueOf(jComboBox1.getSelectedItem());
        jLabel2.setText(str);
        //Load the search types elements to the combobox2
        ResultSet rs = null;
        switch (str) {
            case "Category": {
                jComboBox2.removeAllItems();
                jLabel2.setText(st);
                rs = controller.loadCategories();
                try {
                    while (rs.next()) {
                        jComboBox2.addItem(rs.getString("cat_desc"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ViewProducts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "Supplier": {
                jComboBox2.removeAllItems();
                jLabel2.setText(st);
                rs = controller.loadSuppliers();
                try {
                    while (rs.next()) {
                        jComboBox2.addItem(rs.getString("full_name"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ViewProducts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "Name": {
                jComboBox2.removeAllItems();
                jLabel2.setText(st);
                rs = controller.loadProductNames();
                try {
                    while (rs.next()) {
                        jComboBox2.addItem(rs.getString("prod_name"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ViewProducts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            default: {
                jComboBox2.removeAllItems();
                jComboBox2.addItem("-Select-");

            }
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {

            model = (DefaultTableModel) jTable2.getModel();
            model.getDataVector().removeAllElements();
            //Load Products to table
        jTable2.setModel(DbUtils.resultSetToTableModel(controller.loadProducts()));
       
            /*
            ResultSet rs = null;
            rs = controller.loadProducts();
            model.addRow(new Object[]{rs.getInt("ID"), rs.getString("Product_Name"), rs.getString("Category"), rs.getString("Company"), rs.getLong("price"), rs.getLong("offer")});
            //<editor-fold defaultstate="collapsed" desc=" resize table ">
            final TableColumnModel columnModel = jTable2.getColumnModel();
            for (int column = 0; column < jTable2.getColumnCount(); column++) {
                int width = 50; // Min width
                for (int row = 0; row < jTable2.getRowCount(); row++) {
                    TableCellRenderer renderer = jTable2.getCellRenderer(row, column);
                    Component comp = jTable2.prepareRenderer(renderer, row, column);
                    width = Math.max(comp.getPreferredSize().width, width);
                }
                columnModel.getColumn(column).setPreferredWidth(width);
            }
                 */
            //</editor-fold>
        } catch (Exception ex) {
            Logger.getLogger(ViewProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowActivated

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (selectedProduct != null) {
            controller.setProd_id(Long.parseLong(selectedProduct));
            controller.showProdDetails();
        } else {
            JOptionPane.showMessageDialog(null, "No Product selected !!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int row = jTable2.getSelectedRow();
        selectedProduct = (jTable2.getValueAt(row, 0).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        controller.showCProfile();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String str = jComboBox1.getSelectedItem().toString();
            System.out.println(str);
            ResultSet rs = null;
            switch (str) {
                case "Category":
                    rs = controller.loadCategoryProducts(jComboBox2.getSelectedItem().toString());
                    model = (DefaultTableModel) jTable2.getModel();
                    model.getDataVector().removeAllElements();
                    if (rs.next()) {
                        // The columns you need to set in the table
                        model.addRow(new Object[]{rs.getInt("ID"), rs.getString("Product_Name"), rs.getString("Supplier_Name"),rs.getString("Category"), rs.getString("Company"), rs.getLong("price"), rs.getLong("offer")});
                        //<editor-fold defaultstate="collapsed" desc=" resize table ">
                        final TableColumnModel columnModel = jTable2.getColumnModel();
                        for (int column = 0; column < jTable2.getColumnCount(); column++) {
                            int width = 50; // Min width
                            for (int row = 0; row < jTable2.getRowCount(); row++) {
                                TableCellRenderer renderer = jTable2.getCellRenderer(row, column);
                                Component comp = jTable2.prepareRenderer(renderer, row, column);
                                width = Math.max(comp.getPreferredSize().width, width);
                            }
                            columnModel.getColumn(column).setPreferredWidth(width);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sorry,This product is not available now");
                    }
                    break;
                case "Supplier":
                    rs = controller.loadSupplierProducts(jComboBox2.getSelectedItem().toString());
                    model = (DefaultTableModel) jTable2.getModel();
                    model.getDataVector().removeAllElements();
                    if (rs.next()) {
                        System.out.println(rs.getInt(1));
                        // The columns you need to set in the table 
                        model.addRow(new Object[]{rs.getInt("ID"), rs.getString("Product_Name"), rs.getString("Supplier_Name"),rs.getString("Category"), rs.getString("Company"), rs.getLong("price"), rs.getLong("offer")});
                        //<editor-fold defaultstate="collapsed" desc=" resize table ">
                        final TableColumnModel columnModel = jTable2.getColumnModel();
                        for (int column = 0; column < jTable2.getColumnCount(); column++) {
                            int width = 50; // Min width
                            for (int row = 0; row < jTable2.getRowCount(); row++) {
                                TableCellRenderer renderer = jTable2.getCellRenderer(row, column);
                                Component comp = jTable2.prepareRenderer(renderer, row, column);
                                width = Math.max(comp.getPreferredSize().width, width);
                            }
                            columnModel.getColumn(column).setPreferredWidth(width);
                        }
                    //</editor-fold>

                        //
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sorry,This product is not available now");
                    }
                    break;

                case "Name":
                    rs = controller.loadNameProducts(jComboBox2.getSelectedItem().toString());
                    model = (DefaultTableModel) jTable2.getModel();
                    model.getDataVector().removeAllElements();
                    if (rs.next()) {
                        System.out.println(rs.getInt(1));
                        // The columns you need to set in the table
                        model.addRow(new Object[]{rs.getInt("ID"), rs.getString("Product_Name"), rs.getString("Supplier_Name"),rs.getString("Category"), rs.getString("Company"), rs.getLong("price"), rs.getLong("offer")});
                        //<editor-fold defaultstate="collapsed" desc=" resize table ">
                        final TableColumnModel columnModel = jTable2.getColumnModel();
                        for (int column = 0; column < jTable2.getColumnCount(); column++) {
                            int width = 50; // Min width
                            for (int row = 0; row < jTable2.getRowCount(); row++) {
                                TableCellRenderer renderer = jTable2.getCellRenderer(row, column);
                                Component comp = jTable2.prepareRenderer(renderer, row, column);
                                width = Math.max(comp.getPreferredSize().width, width);
                            }
                            columnModel.getColumn(column).setPreferredWidth(width);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sorry,This product is not available now");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(rootPane, "No search type selected ");
                    jTable2.setModel(DbUtils.resultSetToTableModel(controller.loadProducts()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
