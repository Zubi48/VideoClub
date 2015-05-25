/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class Rentas extends javax.swing.JFrame {

    /**
     * Creates new form Rentas
     */
    public Rentas(String usuario) {
        initComponents();
        user = usuario;
    }
   String user="";
   DefaultTableModel Model;

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtbusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtdevolver = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        jLabel1.setText("Búsqueda");

        jtxtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtbusquedaActionPerformed(evt);
            }
        });
        jtxtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtbusquedaKeyReleased(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Devolver");

        jButton3.setText("Aceptar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtdevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(txtdevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void cargar(String valor){
        String [] Titulos = {"ID","Pelicula","Socio","Empleado","Fecha de renta","Devolución"};
        String [] Registros = new String [6];      
        String nombre = jtxtbusqueda.getText().toLowerCase();
        String sql = "select rentar.ID_RENT, peliculas.NOMBRE pelicula, socios.NOMBRE socio, "
                + "empleados.NOMBRE empleado,fecha_renta, fecha_dev from rentar join peliculas using (id_peli) "
                + "join socios using (id_socio) join empleados using (id_emp) WHERE LOWER (socios.NOMBRE) ='"+nombre+"'";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             while (rs.next()){
                Registros [0]=rs.getString("id_rent");
                Registros [1]=rs.getString("pelicula");
                Registros [2]=rs.getString("socio");
                Registros [3]=rs.getString("empleado");
                Registros [4]=rs.getString("fecha_renta");
                Registros [5]=rs.getString("fecha_dev");             
                Model.addRow(Registros);            
            }
             tabla1.setModel(Model);
            tabla1.setEnabled(false); 
            tabla1.getColumn("ID").setPreferredWidth(10);
            tabla1.getColumn("Pelicula").setPreferredWidth(120);
            /*
            tabla1.getColumn("Nombre").setPreferredWidth(300);
            tabla1.getColumn("Clasificacion").setPreferredWidth(100);
            tabla1.getColumn("Genero").setPreferredWidth(80);
            tabla1.getColumn("Copias").setPreferredWidth(80);
            tabla1.getColumn("Disponibles").setPreferredWidth(120);
            tabla1.getColumn("Costo").setPreferredWidth(70); */ 
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       cargar("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtbusquedaKeyReleased
       // cargar(txtbusqueda.getText().toLowerCase());
    }//GEN-LAST:event_jtxtbusquedaKeyReleased

    private void jtxtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtbusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtbusquedaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Conection conec = new Conection();
        conec.conectar();
        int devolver = Integer.parseInt(txtdevolver.getText());
        String sql ="DELETE FROM RENTAR WHERE ID_RENT = "+devolver+"";
        ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Pelicula Eliminada"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro la pelicula");
            
        }
        cargar("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu go = new Menu(user);
        go.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtxtbusqueda;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtdevolver;
    // End of variables declaration//GEN-END:variables
}
