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
 * @author Zubi
 */
public class ABMPeliculas extends javax.swing.JFrame {

    /**
     * Creates new form ABMPeliculas
     */
    String user="";
    public ABMPeliculas(String usuario) {
        initComponents();
        this.setTitle("Altas, bajas y modificaciones");
        ComboBox();        
        cargar("");
        mostrar(false);
        btnagre.setVisible(false);
        btnelim.setVisible(false);
        btnmodi.setVisible(false);
        user = usuario;
    }
    DefaultTableModel Model;
    void cargar(String valor){
        String [] Titulos = {"Nombre","Copias","Genero","Clasificación"};
        String [] Registros = new String [4];
        
        String sql = "SELECT * FROM VISTA_PELICULAS WHERE LOWER (NOMBRE) LIKE '%"+valor+"%'";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                Registros [0]=rs.getString("NOMBRE");
                Registros [1]=rs.getString("COPIAS_PEL");
                Registros [2]=rs.getString("GENERO");
                Registros [3]=rs.getString("CLASIFICACION");
                
                Model.addRow(Registros);         
            }
            Tabla_pel.setModel(Model);
            Tabla_pel.setEnabled(false);
            Tabla_pel.getColumn("Nombre").setPreferredWidth(200);
            Tabla_pel.getColumn("Copias").setPreferredWidth(60);
            Tabla_pel.getColumn("Genero").setPreferredWidth(100);
            Tabla_pel.getColumn("Clasificación").setPreferredWidth(100);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    void ComboBox(){
        Clasificacion.removeAllItems();
        Clasificacion.addItem("AA");
        Clasificacion.addItem("A");
        Clasificacion.addItem("B");
        Clasificacion.addItem("B15");
        Clasificacion.addItem("C");
        Clasificacion.addItem("D");
        
        Genero.removeAllItems();
        Genero.addItem("Accion");
        Genero.addItem("Animadas");
        Genero.addItem("Ficcion");
        Genero.addItem("Drama");
        Genero.addItem("Fantasia");
        Genero.addItem("Infantil");
        Genero.addItem("Romance");
        Genero.addItem("Terror");
        Genero.addItem("Suspenso");
        Genero.addItem("Comedia");
        Genero.addItem("Adultos");
    }
    void Agregar(){
        Conection conec = new Conection();
        conec.conectar();
       
        String Nombre = txtnombre.getText();
        int cantidad = Integer.parseInt(txtcopias.getText());
        int clas = Clasificacion.getSelectedIndex()+1;
        int gen = Genero.getSelectedIndex()+1;
        int precio = Integer.parseInt(txtprecio.getText());

        String sql = "INSERT INTO PELICULAS (ID_PELI,NOMBRE, ID_CLAS, ID_GEN,COPIAS_PEL, COPIAS_DISP, PRECIO) VALUES ( incremento.nextval ,'"+Nombre+"', "+clas+","+gen+","+cantidad+","+cantidad+","+precio+")";

        ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Pelicula ingresada"); //si existe y entra al if, muestro en un panel la 2da columna del primer row leido, en este caso el nombre de usuario por eso el 2 dentro del parentesis
                
            }
    

        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtcopias.setText(null);
        txtnombre.setText(null);
    }
    void Eliminar(){
    Conection conec = new Conection();
    conec.conectar();
    
    String Nombre = txtnombre.getText();
    String sql = "DELETE FROM PELICULAS WHERE LOWER (NOMBRE) = '"+Nombre+"'";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Pelicula Eliminada"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro la pelicula");
            
        }
        cargar("");
        txtcopias.setText(null);
        txtnombre.setText(null);
        txtbusqueda.setText(null);
    }
    void modificar(){
    Conection conec = new Conection();
    conec.conectar();
  
    String Nombre = txtnombre.getText();
    String busqueda = txtbusqueda.getText();
    int cantidad = Integer.parseInt(txtcopias.getText());
    int clas = Clasificacion.getSelectedIndex()+1;
    int gen = Genero.getSelectedIndex()+1;
    
    String sql = "UPDATE PELICULAS SET NOMBRE = '"+Nombre+"',ID_CLAS = "+clas+", ID_GEN = "+gen+", COPIAS_PEL = "+cantidad+" WHERE NOMBRE = '"+busqueda+"'  ";
    
    ResultSet rs = conec.consultar(sql);
        
        try { 
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Pelicula Modificada"); 
                
            }    
    

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Modificación fallida");
            
        }
        cargar("");
        txtcopias.setText(null);
        txtnombre.setText(null);
    }
    void mostrar(boolean i){ 
        lblclas.setVisible(i);
        lblcop.setVisible(i);
        lblgen.setVisible(i);
        lblnom.setVisible(i);
        txtcopias.setVisible(i);
        txtnombre.setVisible(i);
        Clasificacion.setVisible(i);
        Genero.setVisible(i);
        jlblprecio.setVisible(i);
        txtprecio.setVisible(i);
        
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblnom = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        lblclas = new javax.swing.JLabel();
        Clasificacion = new javax.swing.JComboBox();
        lblgen = new javax.swing.JLabel();
        Genero = new javax.swing.JComboBox();
        lblcop = new javax.swing.JLabel();
        txtcopias = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_pel = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtbusqueda = new javax.swing.JTextField();
        btnagre = new javax.swing.JButton();
        btnelim = new javax.swing.JButton();
        btnmodi = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jlblprecio = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblnom.setText("Nombre");

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

        lblclas.setText("Clasificación");

        Clasificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Clasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClasificacionActionPerformed(evt);
            }
        });

        lblgen.setText("Genero");

        Genero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblcop.setText("Número de Copias");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Tabla_pel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tabla_pel);

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Búsqueda");

        txtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusquedaActionPerformed(evt);
            }
        });
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        btnagre.setText("Aceptar");
        btnagre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagreActionPerformed(evt);
            }
        });

        btnelim.setText("Aceptar");
        btnelim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnelimActionPerformed(evt);
            }
        });

        btnmodi.setText("Aceptar");
        btnmodi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodiActionPerformed(evt);
            }
        });

        jButton4.setText("Regresar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jlblprecio.setText("Precio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblclas)
                                    .addComponent(Clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblgen)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Genero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblnom)
                                    .addComponent(btnagre)
                                    .addComponent(btnelim)
                                    .addComponent(btnmodi)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblcop)
                                            .addComponent(txtcopias, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblprecio)
                                            .addComponent(txtprecio))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(17, 17, 17)
                        .addComponent(lblnom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcop)
                            .addComponent(jlblprecio))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcopias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblclas)
                            .addComponent(lblgen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnagre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnelim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        btnagre.setVisible(false);
        btnelim.setVisible(false);
        btnmodi.setVisible(false);
        mostrar(false);
        mostrar(true);
        btnagre.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClasificacionActionPerformed
        
    }//GEN-LAST:event_ClasificacionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrar(false);
        btnagre.setVisible(false);
        btnelim.setVisible(false);
        btnmodi.setVisible(false);
        lblnom.setVisible(true);
        txtnombre.setVisible(true);
        btnelim.setVisible(true);
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
    
    }//GEN-LAST:event_txtnombreKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mostrar(false);
        btnagre.setVisible(false);
        btnelim.setVisible(false);
        btnmodi.setVisible(false);
        mostrar(true);
        btnmodi.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
    cargar(txtbusqueda.getText().toLowerCase());
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void btnelimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnelimActionPerformed
    Eliminar();
    lblnom.setVisible(false);
    txtnombre.setVisible(false);
    btnelim.setVisible(false);
    cargar("");
    btnelim.setVisible(false);
    }//GEN-LAST:event_btnelimActionPerformed

    private void btnagreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagreActionPerformed
    Agregar();
    mostrar(false);
    cargar("");
    btnagre.setVisible(false);
    }//GEN-LAST:event_btnagreActionPerformed

    private void btnmodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodiActionPerformed
    modificar();
    cargar("");
    btnmodi.setVisible(false);
    }//GEN-LAST:event_btnmodiActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    Menu go = new Menu(user);
    go.setVisible(true);
    this.dispose();              
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusquedaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Clasificacion;
    private javax.swing.JComboBox Genero;
    private javax.swing.JTable Tabla_pel;
    private javax.swing.JButton btnagre;
    private javax.swing.JButton btnelim;
    private javax.swing.JButton btnmodi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblprecio;
    private javax.swing.JLabel lblclas;
    private javax.swing.JLabel lblcop;
    private javax.swing.JLabel lblgen;
    private javax.swing.JLabel lblnom;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcopias;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
