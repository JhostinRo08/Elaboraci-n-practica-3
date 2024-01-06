/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.TDA.Listas.DynamicList;
import controlador.TDA.Listas.Exceptions.EmptyException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Dao.DaoPasajeros;
import modelos.Pasajeros;
import modelos.VentaBoleto;
import vista.modelos.modeloBoleto;

/**
 *
 * @author Jhostin Roja
 */
public class RegistroPasajero extends javax.swing.JFrame {
    DynamicList<Pasajeros> ListadePasajeros = new DynamicList<>();
    modeloBoleto modelodeTabla = new modeloBoleto();
    DaoPasajeros PasajerosControl = new DaoPasajeros();
   
    

    /**
     * Creates new form RegistroPasajero
     */
    public RegistroPasajero() throws EmptyException {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarTabla();
    }
    
    private void cargarTabla(){
       modelodeTabla.setListadePasajeros(PasajerosControl.getPasajerosList());
       tblVentas.setModel(modelodeTabla);
       tblVentas.updateUI();
       cbxLlegada.setSelectedIndex(-1);
       cbxSalida.setSelectedIndex(-1);
       cbxHoradeEmbarque.setSelectedIndex(-1);
       tblVentas.setModel(modelodeTabla);
       tblVentas.updateUI();
    }
    private Boolean Validar() {
        return (!txtDNI.getText().trim().isEmpty() && !txtNombres.getText().trim().isEmpty() && !txtApellidos.getText().trim().isEmpty() && !txtBoletoscomprados.getText().trim().isEmpty() & !txtFechadeSalida.getText().trim().isEmpty() && !txtPrecioTotal.getText().trim().isEmpty());
    }
    private void Limpiar() throws EmptyException {
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDNI.setText("");
        cbxSalida.setSelectedIndex(-1);
        cbxLlegada.setSelectedIndex(-1);
        cbxHoradeEmbarque.setSelectedIndex(-1);
        txtPrecioTotal.setText("");
        txtFechadeSalida.setText("");
        txtBoletoscomprados.setText("");
        PasajerosControl.setPasajeros(null);
        cargarTabla();
    }
    
    private void Guardar() throws EmptyException, ParseException {

        if (txtDNI.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar el DNI", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar los nombres", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar los apellidos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxSalida.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar la Salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxLlegada.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar La LLegada", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtBoletoscomprados.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar los boletos comprados ", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechadeSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar la fecha de salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxHoradeEmbarque.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar la hora de embarque ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Integer IdPasajeros = ListadePasajeros.getLength()+ 1;
            String salida = cbxSalida.getSelectedItem().toString();
            String llegada = cbxLlegada.getSelectedItem().toString();
            Integer Boletoscomprados = Integer.valueOf(txtBoletoscomprados.getText());
            String fechadeSalida = txtFechadeSalida.getText();
            String horadeEmbarque = cbxHoradeEmbarque.getSelectedItem().toString();
            Float precioTotal = Float.parseFloat(txtPrecioTotal.getText());

            VentaBoleto DatosBoleto = new VentaBoleto(IdPasajeros, salida, Boletoscomprados, llegada, fechadeSalida,horadeEmbarque, precioTotal);
          
            PasajerosControl.getPasajero().setIdPasajeros(IdPasajeros);
            PasajerosControl.getPasajero().setDNI(txtDNI.getText());
            PasajerosControl.getPasajero().setNombres(txtNombres.getText());
            PasajerosControl.getPasajero().setApellidos(txtApellidos.getText());
            PasajerosControl.getPasajero().setDatosboleto(DatosBoleto);

            if (PasajerosControl.persist()) {
                JOptionPane.showMessageDialog(null, "Boleto comprado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();

                PasajerosControl.setPasajeros(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo hacer la comprar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        Limpiar();
    }
    
    private void ordenar(){
        String criterio = cbxBusqueda.getSelectedItem().toString();
        Integer tipo = 0;
        if (btnOrden.isSelected()) {
            tipo = 1;
        } 
        try {
            if (cbxMetodo.getSelectedItem() == "ShellSort") {
                modelodeTabla.setListadePasajeros(PasajerosControl.metodoShellSortOrden(PasajerosControl.all(), tipo, criterio));
            } else {
                modelodeTabla.setListadePasajeros(PasajerosControl.metodoOrdenarQuicksort(PasajerosControl.all(), tipo, criterio));
            }
            tblVentas.setModel(modelodeTabla);
            tblVentas.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CargarVista() {
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de los presentados");
        } else {
            try {
                PasajerosControl.setPasajeros(modelodeTabla.getListadePasajeros().getInfo(fila));
                txtNombres.setText(PasajerosControl.getPasajero().getNombres());
                txtApellidos.setText(PasajerosControl.getPasajero().getApellidos());
                txtDNI.setText(PasajerosControl.getPasajero().getDNI());
                txtBoletoscomprados.setText(PasajerosControl.getPasajero().getDatosboleto().getBoletosComprados().toString());
                txtPrecioTotal.setText(PasajerosControl.getPasajero().getDatosboleto().getPrecioTotal().toString());
                txtFechadeSalida.setText(PasajerosControl.getPasajero().getDatosboleto().getFechadeSalida());
                cbxSalida.setSelectedItem(PasajerosControl.getPasajero().getDatosboleto().getSalida());
                cbxLlegada.setSelectedItem(PasajerosControl.getPasajero().getDatosboleto().getLlegada());
                cbxHoradeEmbarque.setSelectedItem(PasajerosControl.getPasajero().getDatosboleto().getHoradeEmbarque().toString());
                
            } catch (EmptyException | IndexOutOfBoundsException e) {

            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxLlegada = new javax.swing.JComboBox<>();
        cbxSalida = new javax.swing.JComboBox<>();
        txtFechadeSalida = new javax.swing.JTextField();
        txtBoletoscomprados = new javax.swing.JTextField();
        txtPrecioTotal = new javax.swing.JTextField();
        cbxHoradeEmbarque = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnComprarBoleto = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        JLabel9 = new javax.swing.JLabel();
        cbxBusqueda = new javax.swing.JComboBox<>();
        btnOrden = new javax.swing.JCheckBox();
        btnOrdenar = new javax.swing.JButton();
        btnTotaldeVentas = new javax.swing.JButton();
        btnSeleccinar = new javax.swing.JButton();
        btnModificarDatos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxMetodo = new javax.swing.JComboBox<>();
        btnOrden1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Boleteria");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Registro de Ventas de Boletos");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Datos del Usuario");

        jLabel15.setText("DNI");

        jLabel16.setText("Apellidos:");

        txtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtApellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Salida:");

        jLabel4.setText("Llegada:");

        jLabel5.setText("Boletos comprados:");

        jLabel6.setText("Fecha de Salida:");

        jLabel7.setText("Hora de Embarque:");

        jLabel10.setText("Precio Total:");

        cbxLlegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora-Chinchipe" }));

        cbxSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora-Chinchipe" }));
        cbxSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSalidaActionPerformed(evt);
            }
        });

        txtFechadeSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtBoletoscomprados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPrecioTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbxHoradeEmbarque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Información Boleto");

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        btnComprarBoleto.setText("Comprar Boleto");
        btnComprarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarBoletoActionPerformed(evt);
            }
        });

        jLabel18.setText("Nombres:");

        JLabel9.setText("Criterio:");

        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres", "Apellidos", "DNI", "IdPasajeros" }));
        cbxBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBusquedaActionPerformed(evt);
            }
        });

        btnOrden.setText("Descendente");
        btnOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenActionPerformed(evt);
            }
        });

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        btnTotaldeVentas.setText("Total de Ventas");
        btnTotaldeVentas.setToolTipText("");
        btnTotaldeVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotaldeVentasActionPerformed(evt);
            }
        });

        btnSeleccinar.setText("Seleccionar");
        btnSeleccinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccinarActionPerformed(evt);
            }
        });

        btnModificarDatos.setText("Modificar Datos");
        btnModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDatosActionPerformed(evt);
            }
        });

        jLabel1.setText("Metodo");

        cbxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ShellSort", "QuickSort" }));

        btnOrden1.setText("Ascendente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btnTotaldeVentas)
                .addGap(150, 150, 150)
                .addComponent(btnSeleccinar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificarDatos)
                .addGap(180, 180, 180))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(12, 12, 12))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxHoradeEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFechadeSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnComprarBoleto))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtBoletoscomprados, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(cbxLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbxSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(JLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(cbxBusqueda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(386, 386, 386)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cbxSalida)
                                    .addComponent(JLabel9)
                                    .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(cbxMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbxLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txtBoletoscomprados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnOrden1)
                                        .addGap(15, 15, 15)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFechadeSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addComponent(btnOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnComprarBoleto))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxHoradeEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTotaldeVentas)
                    .addComponent(btnSeleccinar)
                    .addComponent(btnModificarDatos))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSalidaActionPerformed
    }//GEN-LAST:event_cbxSalidaActionPerformed

    private void btnTotaldeVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotaldeVentasActionPerformed
        // TODO add your handling code here:
        double sumaPrecioFinal = modelodeTabla.SumarTotalGanancias(9);
        //
        JOptionPane.showMessageDialog(null, "EL TOTAL VENDIDO ES: "+sumaPrecioFinal+" DOLARES");

    }//GEN-LAST:event_btnTotaldeVentasActionPerformed

    private void btnModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDatosActionPerformed
        // TODO add your handling code here:
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } else {
            try {
                Integer idPersona = ListadePasajeros.getLength()+ 1;
                String salida = cbxSalida.getSelectedItem().toString();
                String llegada = cbxLlegada.getSelectedItem().toString();
                Integer Boletoscomprados = Integer.parseInt(txtBoletoscomprados.getText());
                String fechadeSalida = txtFechadeSalida.getText();
                String horadeEmbarque = cbxHoradeEmbarque.getSelectedItem().toString();
                Float precioTotal = Float.parseFloat(txtPrecioTotal.getText());

               VentaBoleto BoletoPasajero = new VentaBoleto(idPersona, salida, Boletoscomprados, llegada, fechadeSalida,horadeEmbarque, precioTotal);

                String NumeroDNI = txtDNI.getText();
                String NombrePasajeros = txtNombres.getText();
                String ApellidoPasajeros = txtApellidos.getText();

                Pasajeros PasajeroCambiar = new Pasajeros(idPersona, NumeroDNI, NombrePasajeros, ApellidoPasajeros, BoletoPasajero);

                PasajerosControl.merge(PasajeroCambiar, fila);

                cargarTabla();

                Limpiar();
            } catch (EmptyException | NumberFormatException e) {

            }
        }
    }//GEN-LAST:event_btnModificarDatosActionPerformed

    private void btnSeleccinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccinarActionPerformed
        // TODO add your handling code here:
        CargarVista();
    }//GEN-LAST:event_btnSeleccinarActionPerformed

    private void btnComprarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarBoletoActionPerformed
        if (txtDNI.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta dni", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta apellido", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (cbxSalida.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar la salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxLlegada.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar la llegada", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtBoletoscomprados.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta los boletos comprados", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechadeSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta la fecha de salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxHoradeEmbarque.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta la hora de embarque", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                Integer idPersona = ListadePasajeros.getLength()+ 1;
                String salida = cbxLlegada.getSelectedItem().toString();
                String llegada = cbxSalida.getSelectedItem().toString();
                Integer Boletoscomprados = Integer.parseInt(txtBoletoscomprados.getText());
                String fechadeSalida = txtFechadeSalida.getText();
                String horadeEmbarque = cbxHoradeEmbarque.getSelectedItem().toString();
                Float precioTotal = Float.parseFloat(txtPrecioTotal.getText());

               VentaBoleto BoletoPasajero = new VentaBoleto(idPersona, salida, Boletoscomprados, llegada, fechadeSalida,horadeEmbarque, precioTotal);

                String Dni = txtDNI.getText();
                String nombresPasajeros = txtNombres.getText();
                String apellidosPasajeros = txtApellidos.getText();
                
                
                Pasajeros PasajeroGuardados = new Pasajeros(idPersona,Dni,nombresPasajeros,apellidosPasajeros,BoletoPasajero);
                ListadePasajeros.add(PasajeroGuardados);
                Guardar();

            } catch (ParseException | EmptyException ex) {
            }
        }
    }//GEN-LAST:event_btnComprarBoletoActionPerformed

    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdenActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        ordenar();
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void cbxBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBusquedaActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               try {
                new RegistroPasajero().setVisible(true);
            } catch (EmptyException ex) {
                Logger.getLogger(RegistroPasajero.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel9;
    private javax.swing.JButton btnComprarBoleto;
    private javax.swing.JButton btnModificarDatos;
    private javax.swing.JCheckBox btnOrden;
    private javax.swing.JCheckBox btnOrden1;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSeleccinar;
    private javax.swing.JButton btnTotaldeVentas;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbxHoradeEmbarque;
    private javax.swing.JComboBox<String> cbxLlegada;
    private javax.swing.JComboBox<String> cbxMetodo;
    private javax.swing.JComboBox<String> cbxSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBoletoscomprados;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtFechadeSalida;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPrecioTotal;
    // End of variables declaration//GEN-END:variables
 }
