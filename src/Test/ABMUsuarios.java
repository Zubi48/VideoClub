/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zubi
 */
public class ABMUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form ABMUsuarios
     */
    String user="";
    public ABMUsuarios(String usuario) {
        initComponents();
        combo.removeAllItems();
        combo.addItem("Cajero");
        combo.addItem("Administrador");
        cargar("");
        mostrar(false);
        btnmod.setVisible(false);
        btnagr.setVisible(false);
        btnelim.setVisible(false);
        user = usuario;
    }
    
    DefaultTableModel Model;
    void Agregar(){
    Conection conec = new Conection();  
    conec.conectar();
    
    String Nombre = txtnombre.getText()+" "+txtapellido.getText();
    String Direccion = txtcalle.getText()+" "+txtnumero.getText()+", "+txtcolonia.getText();
    String Telefono = txttelefono.getText();
    String Pass = txtcontraseña.getText();
    float Salario = Integer.parseInt(txtsalario.getText());
    
    if (txtcontraseña.getText().equals(txtcontraseña2.getText())){
 
    if(combo.getSelectedIndex()== 0){ 
        String sql = "INSERT INTO EMPLEADOS (id_emp,nombre,direccion,telefono,salario,pass) "
            + "VALUES (INCR_EMP.nextval,'"+Nombre+"','"+Direccion+"','"+Telefono+"',"+Salario+",'"+Pass+"')";
                
        
            System.out.println(sql);
            ResultSet rs = conec.consultar(sql);
    
            try { 
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Empleado Ingresado"); 
                
                }
    

            } catch (SQLException ex) {
                Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (combo.getSelectedIndex() == 1)
        {
           String sql = "INSERT INTO EMPLEADOS (id_emp,nombre,direccion,telefono,salario,adm_pass) "
            + "VALUES (INCR_EMP.nextval,'"+Nombre+"','"+Direccion+"','"+Telefono+"',"+Salario+",'"+Pass+"')";
        
            System.out.println(sql);
            ResultSet rs = conec.consultar(sql);
    
            try { 
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Empleado Ingresado"); 
                
                }
    

            } catch (SQLException ex) {
                Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            
        }
    }
    else{
        JOptionPane.showMessageDialog(null, "Contraseña no coincide");
    }
    cargar("");
    }
    void cargar(String valor){
        String [] Titulos = {"Nombre","Direccion","Telefono","Salario"};
        String [] Registros = new String [4];
        
        String sql = "SELECT * FROM VISTA_EMPLEADOS WHERE LOWER (NOMBRE) LIKE '%"+valor+"%'";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("NOMBRE");
                Registros [1]=rs.getString("DIRECCION");
                Registros [2]=rs.getString("TELEFONO");
                Registros [3]=rs.getString("SALARIO");            
                Model.addRow(Registros);        
            }
            Tabla_emp.setModel(Model);
            Tabla_emp.setEnabled(false);  
            Tabla_emp.getColumn("Nombre").setPreferredWidth(130);
            Tabla_emp.getColumn("Direccion").setPreferredWidth(230);
            Tabla_emp.getColumn("Telefono").setPreferredWidth(70);
            Tabla_emp.getColumn("Salario").setPreferredWidth(30);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    void limpiar(){
    txtapellido.setText(null);
    txtcalle.setText(null);
    txtcolonia.setText(null);
    txtcontraseña.setText(null);
    txtcontraseña2.setText(null);
    txtnombre.setText(null);
    txtnumero.setText(null);
    txtsalario.setText(null);
    txttelefono.setText(null);
    }
    void eliminar(){
    Conection conec = new Conection();
    conec.conectar();
    
    String Nombre = txtnombre.getText();
    String sql = "DELETE FROM EMPLEADOS WHERE LOWER (NOMBRE) = '"+Nombre+"'";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Empleado Eliminado"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el empleado");
            
        }
        cargar("");
        txtbusqueda.setText(null);
      
    }
    void modificar(){
    Conection conec = new Conection();  
    conec.conectar();
    
    String Nombre = txtnombre.getText()+" "+txtapellido.getText();
    String Direccion = txtcalle.getText()+" "+txtnumero.getText()+", "+txtcolonia.getText();
    String Telefono = txttelefono.getText();
    String Busqueda = txtbusqueda.getText();
    
    float Salario = Integer.parseInt(txtsalario.getText());
    
    String sql = "UPDATE EMPLEADOS  SET NOMBRE = '"+Nombre+"',DIRECCION = '"+Direccion+"', TELEFONO = '"+Telefono+"', SALARIO = "+Salario+" "
            + "WHERE NOMBRE = '"+Busqueda+"'  ";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Empleado Modificada"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Modificación fallida");
            
        }
    }
    void mostrar(boolean i){
        lblapel.setVisible(i);
        lblcal.setVisible(i);
        lblcol.setVisible(i);
        lblcon.setVisible(i);
        lblcon2.setVisible(i);
        lblnom.setVisible(i);
        lblnum.setVisible(i);
        lblsal.setVisible(i);
        lbltel.setVisible(i);
        txtapellido.setVisible(i);
        txtcalle.setVisible(i);
        txtcontraseña.setVisible(i);
        txtcontraseña2.setVisible(i);
        txtnombre.setVisible(i);
        txtnumero.setVisible(i);
        txtsalario.setVisible(i);
        txttelefono.setVisible(i);
        txtcolonia.setVisible(i);
        combo.setVisible(i);
        
                
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblnom = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        lblapel = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        lblcal = new javax.swing.JLabel();
        txtcalle = new javax.swing.JTextField();
        lblcol = new javax.swing.JLabel();
        txtcolonia = new javax.swing.JTextField();
        lblnum = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        lbltel = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        lblsal = new javax.swing.JLabel();
        txtsalario = new javax.swing.JTextField();
        lblcon = new javax.swing.JLabel();
        txtcontraseña = new javax.swing.JPasswordField();
        txtcontraseña2 = new javax.swing.JPasswordField();
        lblcon2 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_emp = new javax.swing.JTable();
        txtbusqueda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnagr = new javax.swing.JButton();
        btnmod = new javax.swing.JButton();
        btnelim = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblnom.setText("Nombre (s)");

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        lblapel.setText("Apellido");

        lblcal.setText("Calle");

        lblcol.setText("Colonia");

        lblnum.setText("Número");

        lbltel.setText("Telefono");

        lblsal.setText("Salario");

        lblcon.setText("Contraseña");

        lblcon2.setText("Reingresar contraseña");

        combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        Tabla_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tabla_emp);

        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        jLabel4.setText("Búsqueda");

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Modificar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnagr.setText("Aceptar");
        btnagr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagrActionPerformed(evt);
            }
        });

        btnmod.setText("Aceptar");
        btnmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodActionPerformed(evt);
            }
        });

        btnelim.setText("Aceptar");
        btnelim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnelimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblcon2)
                                        .addComponent(lblcon)
                                        .addComponent(lblapel)
                                        .addComponent(txtapellido)
                                        .addComponent(lblnom)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtcalle, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbltel))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblsal)
                                                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblcal)
                                            .addGap(125, 125, 125)
                                            .addComponent(lblnum))
                                        .addComponent(txtnombre)
                                        .addComponent(lblcol)
                                        .addComponent(txtcolonia)
                                        .addComponent(txtcontraseña2)
                                        .addComponent(txtcontraseña)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtsalario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnagr)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnelim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnmod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton5)
                            .addComponent(jButton7))
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblnom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lblapel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblnum)
                            .addComponent(lblcal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltel)
                            .addComponent(lblsal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcontraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btnagr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnelim)
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Menu go = new Menu(user);
    go.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
    
    }//GEN-LAST:event_comboActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
    
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
    cargar(txtbusqueda.getText().toLowerCase());
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    btnmod.setVisible(false);
    btnagr.setVisible(false);
    btnelim.setVisible(false);
    mostrar(false);
    mostrar(true);
    btnagr.setVisible(true);
    
    
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnagrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagrActionPerformed
        mostrar(false);
        mostrar(true);
        Agregar();
        limpiar();
    }//GEN-LAST:event_btnagrActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    btnmod.setVisible(false);
    btnagr.setVisible(false);
    btnelim.setVisible(false);
    mostrar(false);
    mostrar(true);
    txtcontraseña.setVisible(false);
    txtcontraseña2.setVisible(false);
    lblcon.setVisible(false);
    lblcon2.setVisible(false);
    btnmod.setVisible(true);    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    btnmod.setVisible(false);
    btnagr.setVisible(false);
    btnelim.setVisible(false);
    mostrar(false);
    btnelim.setVisible(true);
    txtnombre.setVisible(true);
    lblnom.setVisible(true);
       
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodActionPerformed
    modificar();
    limpiar();
    cargar("");
    }//GEN-LAST:event_btnmodActionPerformed

    private void btnelimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnelimActionPerformed
    eliminar();
    limpiar();
    cargar("");
    }//GEN-LAST:event_btnelimActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_emp;
    private javax.swing.JButton btnagr;
    private javax.swing.JButton btnelim;
    private javax.swing.JButton btnmod;
    private javax.swing.JComboBox combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblapel;
    private javax.swing.JLabel lblcal;
    private javax.swing.JLabel lblcol;
    private javax.swing.JLabel lblcon;
    private javax.swing.JLabel lblcon2;
    private javax.swing.JLabel lblnom;
    private javax.swing.JLabel lblnum;
    private javax.swing.JLabel lblsal;
    private javax.swing.JLabel lbltel;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcalle;
    private javax.swing.JTextField txtcolonia;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JPasswordField txtcontraseña2;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtsalario;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
