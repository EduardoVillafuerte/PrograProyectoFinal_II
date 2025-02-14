/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package capainterfaz;
import capanegocio.Articulo;
import capanegocio.Hotel;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author zapat
 */
public class JFrameCliente extends javax.swing.JFrame {
    private Hotel hotel;    
    private DefaultTableModel modeloTabla; 
    private DefaultTableModel modelTablaFactura;
    private DefaultTableModel modelTablaReservas;
    private double compra_Total;
    private String cedula;
    private List<Articulo> articulosagregados;    
    private List<Articulo> articulos;
    private List<Integer> dias;
    private String[] meses;
    private int mesInicio;
    
    /**
     * Creates new form JFrameCliente
     */
    public JFrameCliente(Hotel hotel, String cedula) {
        this.hotel = hotel;
        this.cedula = cedula;
        this.compra_Total = 0;
        this.meses = new String[12];
        this.articulosagregados = new ArrayList<Articulo>();
        this.dias = new ArrayList<Integer>();
        initComponents();
        obtenerDatos();
    }

    private JFrameCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
    private void llenaDias(){
        jTable4.setModel(hotel.generarModeloTabla("102"));
        jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel column = jTable4.getColumnModel();
        column.getColumn(0).setPreferredWidth(75);
        column.getColumn(1).setPreferredWidth(665);
    }
    
    private void obtenerDatos(){
        llenaDias();
        hotel.obtenerClientes();
        jCBoxHabitacion.setModel(hotel.getHabitaciones());
        
        modelTablaReservas = new DefaultTableModel(
        new Object[]{"Cedula","Cliente","Habitacion", "Fecha Inicio", "Fecha Fin", "Fecha Reserva","Numero de dias"}, 0);
        jTable3.setModel(hotel.getReservas(modelTablaReservas,cedula));
        
        modelTablaFactura = new DefaultTableModel(
        new Object[]{"Fecha", "NumFactura", "Total"}, 0);
        jTable2.setModel(hotel.getFacturas(cedula,modelTablaFactura));  
        
        modeloTabla = new DefaultTableModel(
        new Object[]{"Nombre", "Cantidad", "Precio"}, 0);
        jTable1.setModel(modeloTabla);  

        meses[0]="Enero";
        meses[1]= "Febrero";
        meses[2]= "Marzo";
        meses[3]= "Abril";
        meses[4]= "Mayo";
        meses[5]= "Junio";
        meses[6]= "Julio";
        meses[7]= "Agosto";
        meses[8]= "Septiembre";
        meses[9]= "Octubre";
        meses[10]= "Noviembre";
        meses[11]="Diciembre";
        
        DefaultComboBoxModel<String> mesCombo = new DefaultComboBoxModel<>();
        for (int i = 0; i < 12; i++) {
            mesCombo.addElement(meses[i]);
        }
        jCBoxMes.setModel(mesCombo);
        
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modeloComboBox1 = new DefaultComboBoxModel<>();

        modeloComboBox.addElement(" ");
        try {         
            articulos = hotel.getInventario("ProductoUsuarios");
            for(Articulo articulo: articulos){
                String producto = articulo.getNombre();
                int longitud = producto.length();
                String precio = String.format("%.2f", articulo.getPrecio());
                producto = String.format("%-"+(50-longitud)+"s",producto);
                modeloComboBox.addElement(producto + "   ~       $"+precio);
            }  
        } catch (Exception e) {
            System.out.println("No se pudo obtener el inventario: " + e.getMessage());
        }        
        modeloComboBox1.addElement(" ");
        try {         
            for(Articulo articulo: articulos){
                modeloComboBox1.addElement(articulo.getNombre());
            }  

        } catch (Exception e) {
            System.out.println("No se pudo obtener el inventario: " + e.getMessage());
        }
        jComboBox2.setModel(modeloComboBox);
        //jComboBox5.setModel(modeloComboBox1);
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelReserva = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBoxMes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCBoxDiaEntrada = new javax.swing.JComboBox<>();
        jCBoxDiaSalida = new javax.swing.JComboBox<>();
        jCBoxHabitacion = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLDisponibilidad = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        PanelVerFacturas = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        PanelCancelarReserva = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        PanelCompra = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLTotal = new javax.swing.JLabel();
        btnConfPay = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnReservar = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        btnVerFacturas = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();


        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int filaSeleccionada = jTable2.getSelectedRow();

                    if (filaSeleccionada != -1) {
                        String rutaArchivo = "C:\\Programacion UDLA\\Programacion II\\PrograProyectoFinal_II\\src\\capanegocio\\facturas\\"+jTable2.getValueAt(filaSeleccionada, 1).toString()+".txt";

                        try {
                            File archivo = new File(rutaArchivo);
                            if (archivo.exists() && archivo.isFile()) {
                                Desktop.getDesktop().open(archivo);
                                jTable2.clearSelection();
                            } else {
                                System.err.println("El archivo no existe o no es válido: " + rutaArchivo);
                            }
                        } catch (Exception ex) {
                            System.err.println("Error al intentar abrir el archivo: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(350, 150));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capainterfaz/Img10.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, -10, 290, 220));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 430, 60));

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Elija el Mes");

        jCBoxMes.setBorder(null);
        jCBoxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxMesActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel4.setText("Día de Ingreso");

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel5.setText("Día de Salida");

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Habitación");

        jCBoxDiaEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jCBoxDiaEntrada.setBorder(null);
        jCBoxDiaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxDiaEntradaActionPerformed(evt);
            }
        });

        jCBoxDiaSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jCBoxDiaSalida.setBorder(null);

        jCBoxHabitacion.setBorder(null);
        jCBoxHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxHabitacionActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 0, 153));
        jButton1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Confirmar Reserva");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLDisponibilidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("ENTRADA");

        jLabel24.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("SALIDA");

        jLabel25.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Elija el Mes");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout PanelReservaLayout = new javax.swing.GroupLayout(PanelReserva);
        PanelReserva.setLayout(PanelReservaLayout);
        PanelReservaLayout.setHorizontalGroup(
            PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReservaLayout.createSequentialGroup()
                .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReservaLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(PanelReservaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCBoxDiaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(159, 159, 159)
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReservaLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(37, 37, 37))
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelReservaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCBoxDiaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBoxHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelReservaLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PanelReservaLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelReservaLayout.setVerticalGroup(
            PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReservaLayout.createSequentialGroup()
                .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReservaLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReservaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReservaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelReservaLayout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelReservaLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCBoxDiaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PanelReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PanelReservaLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jCBoxDiaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelReservaLayout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jCBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelReservaLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel6)
                                .addGap(13, 13, 13)
                                .addComponent(jCBoxHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Reserva", PanelReserva);

        jLabel18.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Buscar Factura");

        jLabel19.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel19.setText("Fecha de emisión");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Ver Historial de Facturas");

        jButton4.setBackground(new java.awt.Color(102, 0, 102));
        jButton4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Ver Historial");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        javax.swing.GroupLayout PanelVerFacturasLayout = new javax.swing.GroupLayout(PanelVerFacturas);
        PanelVerFacturas.setLayout(PanelVerFacturasLayout);
        PanelVerFacturasLayout.setHorizontalGroup(
            PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                .addGroup(PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21)))
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        PanelVerFacturasLayout.setVerticalGroup(
            PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                .addGroup(PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addGroup(PanelVerFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelVerFacturasLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ver Facturas", PanelVerFacturas);

        jLabel22.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 0, 0));
        jLabel22.setText("POLÍTICAS DE CANCELACIÓN");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(" Política de Cancelación\n\nCancelación con 15 días o más de antelación:\nSi el cliente cancela la reserva al menos 15 días antes de la fecha de entrada, \nse procederá a realizar la cancelación sin inconvenientes.\n\nCancelación con menos de 15 días de antelación:\nEn caso de que la cancelación se realice con menos de 15 días de antelación, \nno será posible llevar a cabo la cancelación de la reserva.");
        jScrollPane3.setViewportView(jTextArea1);

        jCheckBox1.setText("He leído y acepto la política de cancelación.");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel15.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Seleccione cuál de todas sus reservas desea cancelar:");

        jButton6.setBackground(new java.awt.Color(204, 0, 51));
        jButton6.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Cancelar Reserva");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCancelarReservaLayout = new javax.swing.GroupLayout(PanelCancelarReserva);
        PanelCancelarReserva.setLayout(PanelCancelarReservaLayout);
        PanelCancelarReservaLayout.setHorizontalGroup(
            PanelCancelarReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                .addGroup(PanelCancelarReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel15)))
                .addGap(0, 114, Short.MAX_VALUE))
            .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                .addGroup(PanelCancelarReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jButton6))
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jCheckBox1))
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel22))
                    .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCancelarReservaLayout.setVerticalGroup(
            PanelCancelarReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCancelarReservaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Cancelar Reserva", PanelCancelarReserva);

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Comprar Producto");

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel11.setText("Nombre Producto");

        jLabel12.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel12.setText("Cantidad");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capainterfaz/Img17.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton2.setText("Añadir al Carrito");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel17.setFont(new java.awt.Font("Lucida Bright", 1, 15)); // NOI18N
        jLabel17.setText("Total : ");

        jLTotal.setFont(new java.awt.Font("Lucida Bright", 0, 16)); // NOI18N

        btnConfPay.setBackground(new java.awt.Color(102, 0, 102));
        btnConfPay.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        btnConfPay.setForeground(new java.awt.Color(255, 255, 255));
        btnConfPay.setText("Confirmar y Pagar");
        btnConfPay.setBorder(null);
        btnConfPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfPayActionPerformed(evt);
            }
        });

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));

        javax.swing.GroupLayout PanelCompraLayout = new javax.swing.GroupLayout(PanelCompra);
        PanelCompra.setLayout(PanelCompraLayout);
        PanelCompraLayout.setHorizontalGroup(
            PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCompraLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCompraLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap())
                    .addGroup(PanelCompraLayout.createSequentialGroup()
                        .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCompraLayout.createSequentialGroup()
                                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelCompraLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addGroup(PanelCompraLayout.createSequentialGroup()
                                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelCompraLayout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelCompraLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(btnConfPay, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)))
                        .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCompraLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCompraLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jLTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))))))
        );
        PanelCompraLayout.setVerticalGroup(
            PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCompraLayout.createSequentialGroup()
                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCompraLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)))
                .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCompraLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelCompraLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnConfPay, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))))
        );

        jTabbedPane1.addTab("Comprar", PanelCompra);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 930, 510));

        jPanel3.setBackground(new java.awt.Color(166, 204, 204));

        btnReservar.setBackground(new java.awt.Color(204, 204, 255));
        btnReservar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservar.setText("Reservar");
        btnReservar.setBorder(null);
        btnReservar.setEnabled(false);
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        btnComprar.setBackground(new java.awt.Color(204, 204, 255));
        btnComprar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnComprar.setText("Comprar");
        btnComprar.setBorder(null);
        btnComprar.setEnabled(false);

        btnVerFacturas.setBackground(new java.awt.Color(204, 204, 255));
        btnVerFacturas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnVerFacturas.setText("Ver Facturas");
        btnVerFacturas.setBorder(null);
        btnVerFacturas.setEnabled(false);
        btnVerFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerFacturasActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Cerrar Sesion");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 200, 240));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capainterfaz/fondo4.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 1200, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservarActionPerformed

    private void jCBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxMesActionPerformed
        if (jCBoxMes.getSelectedItem() == null) {
            return; 
        }
        String mes = jCBoxMes.getSelectedItem().toString();
        mesInicio = hotel.mesInt(mes);

        dias = hotel.getDias();
        jCBoxDiaEntrada.removeAllItems();
        DefaultComboBoxModel<String> diaCombo = new DefaultComboBoxModel<>();
        for (int i = 1; i <= dias.get(mesInicio); i++) {
            String iAsString = String.valueOf(i);
            diaCombo.addElement(iAsString);
        }
        
        DefaultComboBoxModel<String> mesCombo = new DefaultComboBoxModel<>();
        for (int i = mesInicio; i <= 11; i++) {
            mesCombo.addElement(meses[i]);
        }
        jComboBox1.setModel(mesCombo);
        jCBoxDiaEntrada.setModel(diaCombo);

    }//GEN-LAST:event_jCBoxMesActionPerformed
    
    public void llenarFacturas(){
        modelTablaFactura.setRowCount(0);
        modelTablaFactura = hotel.getFacturas(cedula, modelTablaFactura);
        
        if(modelTablaFactura.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "El cliente no tiene facturas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        jTable2.setModel(modelTablaFactura); 
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        llenarFacturas();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnVerFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerFacturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerFacturasActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!jCheckBox1.isSelected()){
            JOptionPane.showMessageDialog(this, "Marque el checkbox primero de terminos y condiciones", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            String cedula, habitacion, inicio, fin = "";
            try {
                int num = jTable3.getSelectedRow();
                cedula = modelTablaReservas.getValueAt(num, 0).toString();
                habitacion = modelTablaReservas.getValueAt(num, 2).toString();
                inicio = modelTablaReservas.getValueAt(num, 3).toString();
                fin = modelTablaReservas.getValueAt(num, 4).toString();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Seleccione la reserva a cancelar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {

                String[] partes = inicio.split(" ");
                String mesNombre = partes[0];
                int mesNumero = hotel.mesInt(mesNombre)+1;

                if (mesNumero == -1) {
                    System.out.println("Mes no reconocido.");
                    return;
                }

                String fechaFormateada = partes[1] + "-" + mesNumero + "-" + LocalDate.now().getYear();

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate fechaInicio = LocalDate.parse(fechaFormateada, formato);

                LocalDate hoy = LocalDate.now();

                long diasDiferencia = ChronoUnit.DAYS.between(hoy, fechaInicio);
                if (diasDiferencia < 15) {
                    JOptionPane.showMessageDialog(this, "La fecha de inicio está a menos de 15 días. No se permite la cancelación de la reserva.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    hotel.cancelarReserva(cedula, habitacion, inicio,fin);
                    jTable3.setModel(hotel.getReservas(modelTablaReservas,cedula));
                    llenaDias();
                    JOptionPane.showMessageDialog(this, "La fecha de inicio es válida para la cancelación.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (DateTimeParseException e) {
                System.out.println("Error al parsear la fecha: " + e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String producto = (String) jComboBox2.getSelectedItem();
        String cantidadStr = (String) jComboBox6.getSelectedItem();
        int cantidad = 0;
        double subtotal = 0;
        String productoComparar = "";
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if(cantidad == 0){
                JOptionPane.showMessageDialog(this,"La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            if(!producto.equals(" ")){
                for(Articulo articulo: articulos){
                    int longitud = articulo.getNombre().length();
                    if(producto.substring(0,longitud).equals(articulo.getNombre())){
                        if(cantidad <= articulo.getCantidadDisponible()){
                            articulo.setCantidadDisponible(articulo.getCantidadDisponible() - cantidad);
                            subtotal = cantidad * articulo.getPrecio();
                            this.compra_Total += subtotal;
                            String total = String.format("%.2f", this.compra_Total);
                            modeloTabla.addRow(new Object[]{producto, cantidad, subtotal});
                            articulosagregados.add(new Articulo(articulo.getNombre(),cantidad ,(float) articulo.getPrecio()));
                            jLTotal.setText(total);
                        } 
                        else{
                            JOptionPane.showMessageDialog(this, "No existen suficientes productos en stock");
                        }
                    } 
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Ingrese un producto primero" , "Error", JOptionPane.ERROR_MESSAGE);
            }
  
            jComboBox2.setSelectedIndex(0);
            jComboBox6.setSelectedIndex(0);
        }
        catch (Exception e){
            System.out.println("No se pudo recuoerar: "+ e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnConfPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfPayActionPerformed
        if (!this.articulosagregados.isEmpty()) {
            hotel.guardarFactura(cedula, this.articulosagregados, (float) this.compra_Total);
            hotel.guardarCambiosInventario(this.articulos,"ProductoUsuarios");
            for (int i = modeloTabla.getRowCount() - 1; i >= 0; i--) {
                modeloTabla.removeRow(i);
            }   
            articulosagregados.clear();
            this.compra_Total = 0;
            jLTotal.setText("0,00");
            llenarFacturas();
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningún producto en la lista", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfPayActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        dias = hotel.getDias();
        jCBoxDiaSalida.removeAllItems();
        DefaultComboBoxModel<String> diaCombo = new DefaultComboBoxModel<>();
        for (int i = 1; i <= dias.get(mesInicio); i++) {   
            String iAsString = String.valueOf(i);
            diaCombo.addElement(iAsString);
        }
        jCBoxDiaSalida.setModel(diaCombo);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCBoxDiaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxDiaEntradaActionPerformed
        if (jComboBox1.getSelectedItem() == null || jCBoxDiaEntrada.getSelectedItem() == null) {
            return;
        }

        String mes = jComboBox1.getSelectedItem().toString();
        mesInicio = hotel.mesInt(mes); 
        dias = hotel.getDias();
        jCBoxDiaSalida.removeAllItems();
        int diaSeleccionado = Integer.parseInt(jCBoxDiaEntrada.getSelectedItem().toString()) + 1;
        DefaultComboBoxModel<String> diaCombo = new DefaultComboBoxModel<>();

        if (diaSeleccionado > dias.get(mesInicio)) {
            diaSeleccionado = 1;
            mesInicio++;

            if (mesInicio > 12) {
                mesInicio = 1;
            }
        }

        for (int i = diaSeleccionado; i <= dias.get(mesInicio); i++) {
            diaCombo.addElement(String.valueOf(i));
        }

        jCBoxDiaSalida.setModel(diaCombo);

    }//GEN-LAST:event_jCBoxDiaEntradaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modelTablaFactura.setRowCount(0);
        String mes = jComboBox3.getSelectedItem().toString();
        String dia = jComboBox4.getSelectedItem().toString();
        String mesStr = String.format("%02d", hotel.mesInt(mes) + 1);
        modelTablaFactura = hotel.getFacturasPorFecha(cedula, mesStr, dia, modelTablaFactura);

        if (modelTablaFactura.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No existe ninguna factura para esa fecha", "Error", JOptionPane.ERROR_MESSAGE);
        }
        jTable2.setModel(modelTablaFactura); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String mesInicio = jCBoxMes.getSelectedItem().toString();
        int diaInicio = Integer.parseInt(jCBoxDiaEntrada.getSelectedItem().toString());
        String habitacion = jCBoxHabitacion.getSelectedItem().toString();
        String mesFin = jComboBox1.getSelectedItem().toString();
        int diaFin = Integer.parseInt(jCBoxDiaSalida.getSelectedItem().toString());
        
        int mes = hotel.mesInt(mesInicio)+1;
        LocalDate hoy = LocalDate.now();
        int mesActual = hoy.getMonthValue();
        int diaActual = hoy.getDayOfMonth();
        
        if(mes < mesActual || (mes == mesActual && diaInicio < diaActual)){
            JOptionPane.showMessageDialog(null, "No puedes seleccionar una fecha anterior a hoy.", "Fecha Inválida", JOptionPane.WARNING_MESSAGE);

        }else{

            if(diaInicio == diaFin && mesFin.equals(mesInicio)) {
                JOptionPane.showMessageDialog(this, "No se puede tener el reservar y salir el mismo dia", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                hotel.modificarDisponibilidad(habitacion, mesInicio, diaInicio, mesFin, diaFin,true,cedula);
                llenaDias();
                jCBoxHabitacion.setSelectedIndex(0);
                jTable3.setModel(hotel.getReservas(modelTablaReservas,cedula));
            }  
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBoxHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxHabitacionActionPerformed
        jTable4.setModel(hotel.generarModeloTabla(jCBoxHabitacion.getSelectedItem().toString()));
        jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel column = jTable4.getColumnModel();
        column.getColumn(0).setPreferredWidth(75);
        column.getColumn(1).setPreferredWidth(665);
    }//GEN-LAST:event_jCBoxHabitacionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new Inicio().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCancelarReserva;
    private javax.swing.JPanel PanelCompra;
    private javax.swing.JPanel PanelReserva;
    private javax.swing.JPanel PanelVerFacturas;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnConfPay;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnVerFacturas;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jCBoxDiaEntrada;
    private javax.swing.JComboBox<String> jCBoxDiaSalida;
    private javax.swing.JComboBox<String> jCBoxHabitacion;
    private javax.swing.JComboBox<String> jCBoxMes;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLDisponibilidad;
    private javax.swing.JLabel jLTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
