/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ABCLientes extends javax.swing.JFrame {

    String user="";
    public ABCLientes(String usuario) {
        initComponents();
        cargar("");
        mostrar(false);
        btnagreg.setVisible(false);
        btnelim.setVisible(false);
        btnmod.setVisible(false);
        user=usuario;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        lblnom = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        lblapell = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        lblcall = new javax.swing.JLabel();
        txtcalle = new javax.swing.JTextField();
        lblcol = new javax.swing.JLabel();
        txtcolonia = new javax.swing.JTextField();
        lbltel = new javax.swing.JLabel();
        txtelefono = new javax.swing.JTextField();
        calendario = new com.toedter.calendar.JDateChooser();
        lblfech = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_socios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        lblnum = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtbusqueda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnagreg = new javax.swing.JButton();
        btnmod = new javax.swing.JButton();
        btnelim = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        lblapell.setText("Apellido");

        lblcall.setText("Calle");

        lblcol.setText("Colonia");

        lbltel.setText("Teléfono");

        calendario.setDateFormatString("dd/MM/yyyy");

        lblfech.setText("Fecha de Registro");

        tabla_socios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_socios);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblnum.setText("Número");

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        jLabel8.setText("Búsqueda");

        btnagreg.setText("Aceptar");
        btnagreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregActionPerformed(evt);
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
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(calendario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtelefono, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtapellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblcall)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblnum)
                                        .addGap(44, 44, 44))
                                    .addComponent(txtcolonia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtcalle, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblfech, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblcol)
                                    .addComponent(lblapell))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnagreg)
                                    .addComponent(lblnom)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3))
                                    .addComponent(btnmod)
                                    .addComponent(btnelim))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblapell)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcall)
                            .addComponent(lblnum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblcol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblfech)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnagreg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnelim)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(39, 39, 39))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Menu go = new Menu(user);
    go.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrar(false);
        btnagreg.setVisible(false);
        btnelim.setVisible(false);
        btnmod.setVisible(false);
        mostrar(true);
        btnagreg.setVisible(true);
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mostrar(false);
        btnagreg.setVisible(false);
        btnelim.setVisible(false);
        btnmod.setVisible(false);
        txtnombre.setVisible(true);
        lblnom.setVisible(true);
        btnelim.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
    
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
    cargar(txtbusqueda.getText().toLowerCase());
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrar(false);
        btnagreg.setVisible(false);
        btnelim.setVisible(false);
        btnmod.setVisible(false);
        mostrar(true);
        btnmod.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnagregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregActionPerformed
        agregar();
        limpiar();
        cargar("");
    }//GEN-LAST:event_btnagregActionPerformed

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
    DefaultTableModel Model;
 void cargar(String valor){
     String [] Titulos = {"Nombre","Telefono","Dirección","Inicio"};
        String [] Registros = new String [5];
        
        String sql = "SELECT * FROM SOCIOS WHERE LOWER (NOMBRE) LIKE '%"+valor+"%'";
       
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("NOMBRE");
                Registros [1]=rs.getString("TELEFONO");
                Registros [2]=rs.getString("DIRECCION");
                Registros [3]=rs.getString("FECHA_INICIO");
      
                Model.addRow(Registros);
         
            }
            tabla_socios.setModel(Model);
            tabla_socios.setEnabled(false);
            tabla_socios.getColumn("Nombre").setPreferredWidth(60);
            tabla_socios.getColumn("Telefono").setPreferredWidth(50);
            tabla_socios.getColumn("Dirección").setPreferredWidth(100);
            tabla_socios.getColumn("Inicio").setPreferredWidth(30);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 void agregar(){
    Conection conec = new Conection();  
    conec.conectar();
    
    String Nombre = txtnombre.getText()+" "+txtapellido.getText();
    String Direccion = txtcalle.getText()+" "+txtnumero.getText()+", "+txtcolonia.getText();
    String Telefono = txtelefono.getText();
   
    
    SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
    Date date = calendario.getDate();
    String Fecha=(String) forma.format(date);
    System.out.println(Fecha);

        String sql = "INSERT INTO SOCIOS (id_socio,nombre,telefono,direccion,fecha_inicio)"
            + "VALUES (INC_SOCIOS.nextval,'"+Nombre+"','"+Telefono+"','"+Direccion+"','"+Fecha+"')";
       
            System.out.println(sql);
            ResultSet rs = conec.consultar(sql);
    
            try { 
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Cliente Ingresado"); 
                
                }
    

            } catch (SQLException ex) {
                Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    cargar("");
 }
 void limpiar(){
     
    txtapellido.setText(null);
    txtcalle.setText(null);
    txtcolonia.setText(null);
    txtnombre.setText(null);
    txtnumero.setText(null);

    
 }
 void eliminar(){
    Conection conec = new Conection();
    conec.conectar();
    
    String Nombre = txtnombre.getText();
    String sql = "DELETE FROM SOCIOS WHERE LOWER (NOMBRE) = '"+Nombre+"'";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Socio Eliminado"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el Cliente");
            
        }
        cargar("");
        
      
    }
 void mostrar(boolean i){
     lblapell.setVisible(i);
     lblcall.setVisible(i);
     lblcol.setVisible(i);
     lblfech.setVisible(i);
     lblnom.setVisible(i);
     lblnum.setVisible(i);
     lbltel.setVisible(i);
     txtapellido.setVisible(i);
     txtcalle.setVisible(i);
     txtcolonia.setVisible(i);
     txtelefono.setVisible(i);
     txtnombre.setVisible(i);
     txtnumero.setVisible(i);
     calendario.setVisible(i);
     
 }
 void modificar(){
    Conection conec = new Conection();  
    conec.conectar();
    
    String Nombre = txtnombre.getText()+" "+txtapellido.getText();
    String Direccion = txtcalle.getText()+" "+txtnumero.getText()+", "+txtcolonia.getText();
    String Telefono = txtelefono.getText();
    String Busqueda = txtbusqueda.getText();
    
    SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
    Date date = calendario.getDate();
    String Fecha=(String) forma.format(date);
    System.out.println(Fecha);
    
    String sql = "UPDATE SOCIOS  SET NOMBRE = '"+Nombre+"',DIRECCION = '"+Direccion+"', TELEFONO = '"+Telefono+"', FECHA_INICIO = "+Fecha+" "
            + "WHERE NOMBRE = '"+Busqueda+"' ";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Empleado Modificada");       
            }   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Modificación fallida");           
        }
    
 }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagreg;
    private javax.swing.JButton btnelim;
    private javax.swing.JButton btnmod;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblapell;
    private javax.swing.JLabel lblcall;
    private javax.swing.JLabel lblcol;
    private javax.swing.JLabel lblfech;
    private javax.swing.JLabel lblnom;
    private javax.swing.JLabel lblnum;
    private javax.swing.JLabel lbltel;
    private javax.swing.JTable tabla_socios;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcalle;
    private javax.swing.JTextField txtcolonia;
    private javax.swing.JTextField txtelefono;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnumero;
    // End of variables declaration//GEN-END:variables
}
