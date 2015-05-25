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
 * @author Zubi
 */

public class Menu extends javax.swing.JFrame {

    String user ="";
    double sumatoria1=0.00;
    
    public Menu(String usuario) {
        initComponents();
        jscroll.addHierarchyListener(null);
        this.setTitle("Menu");
        //ComboBox();
        mostrar(false);
        cargar(""); 
        CargarSocio("");
        btndev.setVisible(false);
        btnrenta.setVisible(false);  
        user = usuario;
        lblempleado.setText(user);
        String [] Titulos = {"Pelicula","Cliente","Costo"};
        Model2 = new DefaultTableModel(null, Titulos);
        tabla3.setModel(Model2);
        lbltotal.setVisible(false);
        lblcuenta.setVisible(false);
        lblrecibe.setVisible(false);
        jtxtrecibe.setVisible(false);
        jbutonpagar.setVisible(false);      
        jbutoncobrar.setVisible(false);
    }
    
    DefaultTableModel Model;
    DefaultTableModel Model2;
    
    void rentar (){
        Conection conec = new Conection();  
        conec.conectar();
        int costo=0;
        String nombre_pel = null , nombre_socio = null; 
        int pelicula =Integer.parseInt(txtpelicula.getText());
        int socio = Integer.parseInt(txtsocio.getText());
        String sql = "SELECT PRECIO FROM PELICULAS WHERE ID_PELI = "+pelicula+"";
        ResultSet rs = conec.consultar(sql);
        
        String sql2 = "SELECT NOMBRE FROM PELICULAS WHERE ID_PELI = "+pelicula+"";
        ResultSet rs2 = conec.consultar(sql2);
        
        String sql3 = "SELECT NOMBRE FROM SOCIOS WHERE ID_SOCIO ="+socio+"";
        ResultSet rs3 = conec.consultar(sql3);
        
        try {
            if(rs.next()){
                costo = Integer.parseInt(rs.getString("PRECIO"));               
            }
            
            if(rs2.next()){
                nombre_pel = rs2.getString("NOMBRE");
            }
            if(rs3.next()){
                nombre_socio = rs3.getString("NOMBRE");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        String costo2 = Integer.toString(costo);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = datedev.getDate();
        String Fechadev =(String) format.format(date2);
        fechas[peli]=format.format(datedev.getDate());
        Model2.addRow(new Object []{nombre_pel,nombre_socio,costo2,Fechadev});
        peli++;
        
 }
    int peli =0;
    String [] fechas = new String[99];
    
    void mostrar(boolean i){
        lblpel.setVisible(i);
        lblsocio.setVisible(i);
        txtpelicula.setVisible(i);
        txtsocio.setVisible(i);
        datedev.setVisible(i);
        fechadev.setVisible(i);
    }
    void cargar(String valor){
        String [] Titulos = {"Clave","Nombre","Clasificación","Genero","Copias","Disponibles","Costo"};
        String [] Registros = new String [7];      
        String sql = "SELECT * "
                    +"FROM RENTAS_PELI "
                    +"WHERE LOWER (NOMBRE) LIKE '%"+valor+"%'";      
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("ID_PELI");
                Registros [1]=rs.getString("NOMBRE");
                Registros [2]=rs.getString("GENERO");
                Registros [3]=rs.getString("CLASIFICACION");
                Registros [4]=rs.getString("COPIAS_PEL");
                Registros [5]=rs.getString("COPIAS_DISP");
                Registros [6]=rs.getString("PRECIO");
                Model.addRow(Registros);            
            }
            tabla.setModel(Model);
            tabla.setEnabled(false);   
            tabla.getColumn("Clave").setPreferredWidth(70);
            tabla.getColumn("Nombre").setPreferredWidth(300);
            tabla.getColumn("Clasificación").setPreferredWidth(100);
            tabla.getColumn("Genero").setPreferredWidth(80);
            tabla.getColumn("Copias").setPreferredWidth(80);
            tabla.getColumn("Disponibles").setPreferredWidth(120);
            tabla.getColumn("Costo").setPreferredWidth(70);  
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    void CargarSocio(String val){
        String [] Titulos = {"Clave","Nombre"};
        String [] Registros = new String [2];
        
        String sql = "SELECT "
                    +"ID_SOCIO,NOMBRE "
                    +"FROM SOCIOS WHERE LOWER (NOMBRE) LIKE '%"+val+"%'";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("ID_SOCIO");
                Registros [1]=rs.getString("NOMBRE");
                Model.addRow(Registros);        
            }
            tablasocio.setModel(Model);
            tablasocio.setEnabled(false);   
            tablasocio.getColumn("Clave").setPreferredWidth(50);
            tablasocio.getColumn("Nombre").setPreferredWidth(250);
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void cargarRentas(){
        int id = Integer.parseInt(txtsocio.getText());
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        String fechaini = formateador.format(ahora);
        
        String nombre="";
        Conection conec = new Conection();
        conec.conectar();
        String sql = "SELECT NOMBRE "
                    +"FROM SOCIOS "
                    +"WHERE ID_SOCIO = "+id+"";
        
        ResultSet rs = conec.consultar(sql);
            try { 
                if(rs.next()){   
                    nombre = rs.getString("NOMBRE");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        String [] Titulos = {"Pelicula","Cliente","Costo"};
        String [] Registros = new String [3];      
        String sql2 = "SELECT * "
                    + "FROM COMPRA "
                    + "WHERE CLIENTE = '"+nombre+"' AND FECHA_RENTA ='"+fechaini+"' ";    
        
        
        Model = new DefaultTableModel(null, Titulos);
        
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs2 = st.executeQuery(sql2);
            
            while (rs2.next()){
                Registros [0]=rs2.getString("PELICULA");
                Registros [1]=rs2.getString("CLIENTE");
                Registros [2]=rs2.getString("COSTO");          
                Model.addRow(Registros);            
            }
            tabla3.setModel(Model);
            tabla3.setEnabled(false);   
            tabla3.getColumn("Pelicula").setPreferredWidth(300);
            tabla3.getColumn("Cliente").setPreferredWidth(300);
            tabla3.getColumn("Costo").setPreferredWidth(70);      
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
            
        }       
    }
    void Cuenta(){   
        int totalRow= tabla3.getRowCount();
        System.out.println(totalRow);
        totalRow-=1;
       
        for(int i=0;i<=(totalRow);i++)
        {
             double sumatoria= Double.parseDouble(String.valueOf(tabla3.getValueAt(i, 2)));

             sumatoria1+=sumatoria;
        }
        lblcuenta.setText(Double.toString(sumatoria1));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtbusqueda = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtpelicula = new javax.swing.JTextField();
        lblpel = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        txtsocio = new javax.swing.JTextField();
        lblprecio = new javax.swing.JLabel();
        btnrenta = new javax.swing.JButton();
        btndev = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablasocio = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        buscsoc = new javax.swing.JTextField();
        lblsocio = new javax.swing.JLabel();
        fechadev = new javax.swing.JLabel();
        datedev = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla3 = new javax.swing.JTable();
        lblempleado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jscroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jbutoncobrar = new javax.swing.JButton();
        lblcuenta = new javax.swing.JLabel();
        lblrecibe = new javax.swing.JLabel();
        jtxtrecibe = new javax.swing.JTextField();
        jbutonpagar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Sistema de rentas");

        jLabel3.setText("Búsqueda");

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

        jButton2.setText("Rentar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtpelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpeliculaActionPerformed(evt);
            }
        });

        lblpel.setText("Clave de la pelicula");

        lbltotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltotal.setText("Total:");

        lblprecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnrenta.setText("Aceptar");
        btnrenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrentaActionPerformed(evt);
            }
        });

        btndev.setText("Aceptar");

        jButton1.setText("Género");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Clasificación");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tablasocio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablasocio);

        jLabel2.setText("Búsqueda");

        buscsoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscsocKeyReleased(evt);
            }
        });

        lblsocio.setText("Clave del socio");

        fechadev.setText("Fecha de devolucion");

        datedev.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                datedevFocusLost(evt);
            }
        });

        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tabla3);

        lblempleado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblempleado.setText("jLabel6");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cajero:");

        jscroll.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jscrollComponentRemoved(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jscroll.setViewportView(tabla);

        jbutoncobrar.setText("Cobrar");
        jbutoncobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutoncobrarActionPerformed(evt);
            }
        });

        lblcuenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblcuenta.setText("$$");

        lblrecibe.setText("Recibe");

        jbutonpagar.setText("Aceptar");
        jbutonpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutonpagarActionPerformed(evt);
            }
        });

        jMenu1.setText("Inicio");

        jMenuItem2.setText("ABC Peliculas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("ABC Usuarios");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("ABC Socios");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Rentas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem1.setText("Salir");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                            .addComponent(jscroll))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscsoc))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lbltotal)
                                    .addGap(11, 11, 11)
                                    .addComponent(lblcuenta))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(lblrecibe)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtxtrecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jbutonpagar))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblprecio)
                                        .addGap(9, 9, 9))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fechadev)
                                            .addComponent(datedev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblpel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtpelicula))
                                                        .addGap(18, 18, 18))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblsocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(26, 26, 26))
                                                    .addComponent(txtsocio))))
                                        .addComponent(btndev)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jbutoncobrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnrenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblempleado)
                        .addGap(290, 290, 290)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lblempleado)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)
                        .addComponent(buscsoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblpel)
                            .addComponent(lblsocio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fechadev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datedev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltotal)
                            .addComponent(lblcuenta))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblrecibe)
                            .addComponent(jtxtrecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbutonpagar)
                            .addComponent(jbutoncobrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnrenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblprecio)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
       
    }//GEN-LAST:event_formComponentAdded

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    ABMPeliculas go = new ABMPeliculas(user);
    go.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    ABMUsuarios go = new ABMUsuarios(user);
    go.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusquedaActionPerformed
  // TODO add your handling code here:
    }//GEN-LAST:event_txtbusquedaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    ABCLientes go = new ABCLientes(user);
    go.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnrentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrentaActionPerformed
        rentar();
        //cargarRentas();
        //Cuenta();    
        txtpelicula.setText("");
        txtsocio.setText("");
        datedev.setDateFormatString("dd/MM/yyyy");
        jbutoncobrar.setVisible(true);
       
    }//GEN-LAST:event_btnrentaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrar(false);
        mostrar(true);
        btndev.setVisible(false);
        btnrenta.setVisible(true);    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String [] Titulos = {"Clave","Nombre","Clasificacion","Genero","Copias","Disponibles","Costo"};
        String [] Registros = new String [7];
        
        String sql = "SELECT * FROM RENTAS_PELI ORDER BY CLASIFICACION ASC";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("ID_PELI");
                Registros [1]=rs.getString("NOMBRE");
                Registros [2]=rs.getString("GENERO");
                Registros [3]=rs.getString("CLASIFICACION");
                Registros [4]=rs.getString("COPIAS_PEL");
                Registros [5]=rs.getString("COPIAS_DISP");
                Registros [6]=rs.getString("PRECIO");
                
                Model.addRow(Registros);       
                
            }
            tabla.setModel(Model);
            tabla.setEnabled(false);
            tabla.getColumn("Clave").setPreferredWidth(70);
            tabla.getColumn("Nombre").setPreferredWidth(300);
            tabla.getColumn("Clasificacion").setPreferredWidth(100);
            tabla.getColumn("Genero").setPreferredWidth(80);
            tabla.getColumn("Copias").setPreferredWidth(80);
            tabla.getColumn("Disponibles").setPreferredWidth(120);
            tabla.getColumn("Costo").setPreferredWidth(70);  
            
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String [] Titulos = {"Clave","Nombre","Clasificacion","Genero","Copias","Disponibles","Costo"};
        String [] Registros = new String [7];
        
        String sql = "SELECT * FROM RENTAS_PELI ORDER BY GENERO ASC";
        
        Model = new DefaultTableModel(null, Titulos);
        Conection conec = new Conection();
        conec.conectar();
        try {
            Statement st = conec.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                Registros [0]=rs.getString("ID_PELI");
                Registros [1]=rs.getString("NOMBRE");
                Registros [2]=rs.getString("GENERO");
                Registros [3]=rs.getString("CLASIFICACION");
                Registros [4]=rs.getString("COPIAS_PEL");
                Registros [5]=rs.getString("COPIAS_DISP");
                Registros [6]=rs.getString("PRECIO");
                
                Model.addRow(Registros);
            }
            tabla.setModel(Model);
            tabla.setEnabled(false);
            tabla.getColumn("Clave").setPreferredWidth(70);
            tabla.getColumn("Nombre").setPreferredWidth(300);
            tabla.getColumn("Clasificacion").setPreferredWidth(100);
            tabla.getColumn("Genero").setPreferredWidth(80);
            tabla.getColumn("Copias").setPreferredWidth(80);
            tabla.getColumn("Disponibles").setPreferredWidth(120);
            tabla.getColumn("Costo").setPreferredWidth(70);  
            
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ABMPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        cargar(txtbusqueda.getText().toLowerCase());
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void buscsocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscsocKeyReleased
        CargarSocio(buscsoc.getText().toLowerCase());
    }//GEN-LAST:event_buscsocKeyReleased

    private void txtpeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpeliculaActionPerformed
        txtpelicula.getText();
    }//GEN-LAST:event_txtpeliculaActionPerformed

    private void jscrollComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jscrollComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jscrollComponentRemoved

    private void jbutoncobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutoncobrarActionPerformed
        lblcuenta.setVisible(true);
        lbltotal.setVisible(true);
        jtxtrecibe.setVisible(true);
        jbutonpagar.setVisible(true);
        lblrecibe.setVisible(true);
        btnrenta.setVisible(false);
        Cuenta();
        jbutoncobrar.setVisible(false);
        lblpel.setVisible(false);
        lblsocio.setVisible(false);
        fechadev.setVisible(false);
        txtpelicula.setVisible(false);
        txtsocio.setVisible(false);
        datedev.setVisible(false);
        
    }//GEN-LAST:event_jbutoncobrarActionPerformed

    private void jbutonpagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutonpagarActionPerformed
       Conection conec = new Conection();  
       conec.conectar();
        double cambio = Double.parseDouble(jtxtrecibe.getText());
        double result = cambio-sumatoria1;
        JOptionPane.showMessageDialog(null, "Su cambio es: $"+result);
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        String fechaini = formateador.format(ahora);
        
      
        for (int i = 0; i < tabla3.getRowCount(); i++) {
            String sql ="INSERT INTO RENTAR (id_rent,fecha_renta,fecha_dev,costo,id_peli,id_socio,id_emp) VALUES "
                    + "(INCREM_RENTAR.nextval,'"+fechaini+"','"+fechas[i]+"',"+tabla3.getValueAt(i, 2)+",(SELECT ID_PELI FROM PELICULAS WHERE NOMBRE='"+tabla3.getValueAt(i, 0)+"'),(SELECT ID_SOCIO FROM SOCIOS WHERE NOMBRE='"+tabla3.getValueAt(i, 1)+"'),(SELECT ID_EMP FROM EMPLEADOS WHERE NOMBRE = '"+lblempleado.getText()+"'))";
            conec.consultar(sql);           
            
        }
        JOptionPane.showMessageDialog(null, "Renta Realizada!");
        peli=0;
        Menu obj = new Menu(user);
        obj.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jbutonpagarActionPerformed

    private void datedevFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_datedevFocusLost
               // TODO add your handling code here:
    }//GEN-LAST:event_datedevFocusLost

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Rentas go = new Rentas(user);
        go.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndev;
    private javax.swing.JButton btnrenta;
    private javax.swing.JTextField buscsoc;
    private com.toedter.calendar.JDateChooser datedev;
    private javax.swing.JLabel fechadev;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbutoncobrar;
    private javax.swing.JButton jbutonpagar;
    private javax.swing.JScrollPane jscroll;
    private javax.swing.JTextField jtxtrecibe;
    private javax.swing.JLabel lblcuenta;
    private javax.swing.JLabel lblempleado;
    private javax.swing.JLabel lblpel;
    private javax.swing.JLabel lblprecio;
    private javax.swing.JLabel lblrecibe;
    private javax.swing.JLabel lblsocio;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla3;
    private javax.swing.JTable tablasocio;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtpelicula;
    private javax.swing.JTextField txtsocio;
    // End of variables declaration//GEN-END:variables
}
