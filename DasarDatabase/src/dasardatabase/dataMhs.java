/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dasardatabase;

import java.awt.HeadlessException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alamsyah Firdaus
 */
public final class dataMhs extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    
    private void getData(){
        tableModel.getDataVector().removeAllElements();
        try {
            Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
            try (java.sql.Statement stm = conn.createStatement(); java.sql.ResultSet sql = stm.executeQuery("SELECT nim, nama_lengkap, email, no_handphone, nama_program_studi, fakultas_name FROM mahasiswa INNER JOIN program_studi ON mahasiswa.program_studi_id = program_studi.program_studi_id INNER JOIN fakultas ON program_studi.fakultas_id = fakultas.fakultas_id")) {
                
                while (sql.next()) {
                    Object[] obj = new Object[6];
                    obj[0] = sql.getString("nim");
                    obj[1] = sql.getString("nama_lengkap");
                    obj[2] = sql.getString("email");
                    obj[3] = sql.getString("no_handphone");
                    obj[4] = sql.getString("nama_program_studi");
                    obj[5] = sql.getString("fakultas_name");

                    
                    tableModel.addRow(obj);
                }
                
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
    }
    
    

    /**
     * Creates new form dataMhs
     * @throws java.sql.SQLException
     */
    public dataMhs() throws SQLException {
        initComponents();
        getProdi();
        batal();
        passwordMD5();
        tableModel=new DefaultTableModel();
        tableMhs.setModel(tableModel);
        
        tableModel.addColumn("NIM");
        tableModel.addColumn("Nama Lengkap");
        tableModel.addColumn("Email");
        tableModel.addColumn("No Handphone");
        tableModel.addColumn("Program Studi");
        tableModel.addColumn("Fakultas");
        getData();
        
        tableMhs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try{
                    tfNim.setText(tableMhs.getValueAt(tableMhs.getSelectedRow(), 0).toString());
                    tfNim.setEnabled(false);
                    tfNama.setText(tableMhs.getValueAt(tableMhs.getSelectedRow(), 1).toString());
                    tfEmail.setText(tableMhs.getValueAt(tableMhs.getSelectedRow(), 2).toString());
                    tfNoHp.setText(tableMhs.getValueAt(tableMhs.getSelectedRow(), 3).toString());
                    cbProdi.setSelectedItem(tableMhs.getValueAt(tableMhs.getSelectedRow(), 4).toString());
                }catch (Exception e){
                    
                }
                

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void getProdi() {
        try {
            Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
            try (Statement stm = conn.createStatement() ) {
                String sql = "SELECT * FROM program_studi";
                try (ResultSet res = stm.executeQuery(sql)) {
                    cbProdi.addItem("Pilih Program Studi");
                    while (res.next()) {

                          String program_studi_id=res.getString("nama_program_studi");
                          cbProdi.addItem(program_studi_id);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

public void batal() {
    tfNim.setText(null);
    tfNim.setEnabled(true);
    tfNama.setText(null);
    tfEmail.setText(null);    
    tfPassword.setText(null);
    tfNoHp.setText(null);
    cbProdi.setSelectedIndex(0);

}

private String passwordMD5(){
    String password,md5;
    password = tfPassword.getText();
    String Pass=null;
    try {
        MessageDigest digest=MessageDigest.getInstance("MD5");
        digest.update(password.getBytes(),0,password.length());
        Pass=new BigInteger(1,digest.digest()).toString(16);
        md5=Pass.toUpperCase();
    }
    catch(NoSuchAlgorithmException e) {
    }
        return Pass;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        tfCari = new javax.swing.JTextField();
        lblNim = new javax.swing.JLabel();
        tfNim = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblNama = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfNoHp = new javax.swing.JTextField();
        cbProdi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMhs = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        lblCari = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfPassword = new javax.swing.JTextField();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("DATA MAHASISWA");

        tfCari.setBackground(java.awt.Color.white);
        tfCari.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N

        lblNim.setText("NIM");

        tfNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNimActionPerformed(evt);
            }
        });

        btnCari.setBackground(java.awt.Color.white);
        btnCari.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        lblNama.setText("Nama Lengkap");

        lblEmail.setText("Email");

        lblNoHp.setText("No. Handphone");

        cbProdi.setBackground(java.awt.Color.white);
        cbProdi.setForeground(java.awt.Color.white);

        jLabel2.setText("Program Studi");

        tableMhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableMhs.setCellSelectionEnabled(true);
        tableMhs.setGridColor(java.awt.Color.lightGray);
        tableMhs.setSelectionBackground(java.awt.Color.white);
        tableMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMhsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMhs);

        btnSimpan.setBackground(java.awt.Color.white);
        btnSimpan.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCari.setText("Pencarian :");

        btnBatal.setBackground(java.awt.Color.white);
        btnBatal.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnEdit.setBackground(java.awt.Color.white);
        btnEdit.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 2, 10)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("NIM TIDAK BISA DIEDIT ");

        btnHapus.setBackground(java.awt.Color.white);
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKeluar.setBackground(java.awt.Color.white);
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        jLabel3.setText("Password");

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
                                .addComponent(jLabel2)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal))
                                    .addComponent(cbProdi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNim)
                                    .addComponent(lblNama)
                                    .addComponent(lblEmail)
                                    .addComponent(lblNoHp)
                                    .addComponent(jLabel3))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel4)
                                    .addComponent(tfNim, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(tfNama, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfNoHp))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCari)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnCari)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnKeluar))
                                            .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnHapus)))))))
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNim)
                    .addComponent(tfNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCari))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCari)
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNoHp)
                        .addComponent(tfNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbProdi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (!tfNim.getText().toString().isEmpty()) {
            try {
                String sql = "INSERT INTO mahasiswa (nim, nama_lengkap, email, password, no_handphone, program_studi_id) VALUES ('" + tfNim.getText() + "','" + tfNama.getText() + "','" + tfEmail.getText() + "','" + tfPassword.getText() + "','" + tfNoHp.getText() + "','" + cbProdi.getSelectedIndex() + "')";
                java.sql.Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            getData();
            batal();
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (!tfNim.getText().toString().isEmpty()) {
            try {
                String sql = "UPDATE mahasiswa SET nim = '" + tfNim.getText() + "', nama_lengkap = '" + tfNama.getText() + "', email = '" + tfEmail.getText() + "', password = '" + tfPassword.getText() + "', no_handphone= '" + tfNoHp.getText() + "', program_studi_id = '" + cbProdi.getSelectedIndex() + "'WHERE nim = '" + tfNim.getText() + "'";
                java.sql.Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIEDIT");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "DATA GAGAL DIEDIT" + e.getMessage());
            }
            getData();
            batal();
        }


    }//GEN-LAST:event_btnEditActionPerformed

    private void tableMhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMhsMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tableMhsMouseClicked

    private void tfNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNimActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfNimActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
       if (!tfNim.getText().toString().isEmpty()) {
           int delete = JOptionPane.showConfirmDialog(null,"YAKIN INGIN MENGAPUS DATA?", "KONFIRMASI",JOptionPane.YES_NO_OPTION);
           if (delete==0) {
             try {
                 String sql ="DELETE FROM mahasiswa WHERE nim='"+tfNim.getText()+"'";
                 java.sql.Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
                 java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                 pst.execute();
                 JOptionPane.showMessageDialog(this, "DATA BERHASIL DIHAPUS");
//                 System.exit(0);
             } catch (HeadlessException | SQLException e) {
                 JOptionPane.showMessageDialog(this, e.getMessage());
             }
             getData();
             batal();
            }
       }

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
            tableModel=new DefaultTableModel();
            tableMhs.setModel(tableModel);

            tableModel.addColumn("NIM");
            tableModel.addColumn("Nama Lengkap");
            tableModel.addColumn("Email");
            tableModel.addColumn("No Handphone");
            tableModel.addColumn("Program Studi");
            tableModel.addColumn("Fakultas");
            try {
                String sql = "SELECT nim, nama_lengkap, email, no_handphone, nama_program_studi, fakultas_name FROM mahasiswa INNER JOIN program_studi ON mahasiswa.program_studi_id=program_studi.program_studi_id INNER JOIN fakultas ON program_studi.fakultas_id=fakultas.fakultas_id WHERE nim LIKE '%" + tfCari.getText() + "%'" + "OR nama_lengkap LIKE '%" + tfCari.getText() + "%'" + "OR email LIKE '%" + tfCari.getText() + "%'" + "OR no_handphone LIKE '%" + tfCari.getText() + "%'" + "OR nama_program_studi LIKE '%" + tfCari.getText() + "%'" + "OR fakultas_name LIKE '%" + tfCari.getText() + "%'";
                java.sql.Connection conn = (Connection) dasardatabase.koneksi.koneksiDB();
                java.sql.Statement state = conn.createStatement();
                java.sql.ResultSet res = state.executeQuery(sql);
                while (res.next()) {
                   tableModel.addRow(new Object[]{
                        res.getString("nim"),
                        res.getString("nama_lengkap"),                        
                        res.getString("email"),
                        res.getString("no_handphone"),
                        res.getString("nama_program_studi"),
                        res.getString("fakultas_name"),
                    });
                    tableMhs.setModel(tableModel);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "DATA TIDAK DITEMUKAN");
    }//GEN-LAST:event_btnCariActionPerformed
}
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
            java.util.logging.Logger.getLogger(dataMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataMhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new dataMhs().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(dataMhs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbProdi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JTable tableMhs;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNim;
    private javax.swing.JTextField tfNoHp;
    private javax.swing.JTextField tfPassword;
    // End of variables declaration//GEN-END:variables

}
