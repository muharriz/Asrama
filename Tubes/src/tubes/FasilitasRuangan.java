/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tubes;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muharriz
 */
public class FasilitasRuangan extends javax.swing.JFrame {
    //inisialisasi variable
    private Connectivity con;
    private int state;
    private String tipe;
    private String Status;
    private String nomorKamar;
    
    private ResultSet result;
     
    public FasilitasRuangan(int state) {
        initComponents();
        if(state != 2){
           jComboBox1.setVisible(false);
           jButton2.setVisible(false);
           jButton3.setVisible(false);
           jButton4.setVisible(false);
        }
        //membuat kelas koneksi
        con = new Connectivity();
        this.state = state;
        //Membuat combo box nomor ruangan
        ArrayList<String> nomorKamar;
        nomorKamar = con.nomorKamar();
        String[] kodefasilitas = {"Kode Fasilitas"};
        comboTipe.setModel(new javax.swing.DefaultComboBoxModel(nomorKamar.toArray()));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(kodefasilitas));
        //set kolom tabel untuk tidak terlihat
        jTable1.setVisible(false);
    }
    private void table(String nomorKamar){
        DefaultTableModel tb = new DefaultTableModel();
        tb.addColumn("Kode Fasilitas Ruangan");
        tb.addColumn("Nomor Kamar");
        tb.addColumn("Nama Fasilitas");
        tb.addColumn("Kuantitas");
        tb.addColumn("Kondisi");
        jTable1.setModel(tb);
        try{
             con = new Connectivity();
             result = con.ExecQuery("select "
                     + "             fasilitas_ruangan.fasilitas_ruangan_id,fasilitas_ruangan.kamar_no,fasilitas.fasilitas_nama,fasilitas_ruangan.fr_kuantitas,fasilitas_ruangan.fr_kondisi "
                     + "             from fasilitas inner join fasilitas_ruangan inner join ruangan inner join type_ruangan "
                     + "             on "
                     + "             fasilitas_ruangan.fasilitas_id = fasilitas.fasilitas_id and fasilitas_ruangan.kamar_no = ruangan.ruangan_no and ruangan.type_id = type_ruangan.type_id"
                     + "             where fasilitas_ruangan.kamar_no = ' "+ nomorKamar +"'"
                             + "     order by fasilitas_ruangan.kamar_no");
             while(result.next()){
                    tb.addRow(new Object[]{
                    result.getString("fasilitas_ruangan_id"),
                    result.getString("kamar_no"),
                    result.getString("fasilitas_nama"),
                    result.getString("fr_kuantitas"),
                    result.getString("fr_kondisi")
                    });
                }
        }catch(Exception ex){
             
                
        }
    }
    
    public void loadcomboboxfasilitaskode(){
    ArrayList<String> kodefasilitasruangan = new ArrayList<String>();
    kodefasilitasruangan = con.ambilKodeFasilitasRuangan(nomorKamar);
    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(kodefasilitasruangan.toArray()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        buttonKembali = new javax.swing.JButton();
        comboTipe = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fasilitas Ruangan");

        buttonKembali.setText("Kembali");
        buttonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKembaliActionPerformed(evt);
            }
        });

        comboTipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipeActionPerformed(evt);
            }
        });

        jButton1.setText("Cek Fasilitas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Fasilitas Ruangan", "Nomor Kamar", "Nama Fasilitas", "Kuantitas", "Kondisi"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Tambah Fasilitas Ruangan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonKembali)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(64, 64, 64)
                .addComponent(buttonKembali)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKembaliActionPerformed
        // Tombol Kembali
        this.dispose();
        new UI(state).setVisible(true);
    }//GEN-LAST:event_buttonKembaliActionPerformed

    private void comboTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipeActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_comboTipeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Tombol cek Fasilitas
        String nomorKamar;

        nomorKamar = comboTipe.getSelectedItem().toString();
        this.nomorKamar = nomorKamar;
        
        jTable1.setVisible(true);
        loadcomboboxfasilitaskode();
        table(nomorKamar);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Tombol Tambah Fasilitas Ruangan
        new TambahFasilitasRuangan(state).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Tombol Hapus
        int pilihan = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin hapus?", "Hapus", JOptionPane.YES_NO_OPTION);
        if(pilihan == 0){
            int kodefasilitasruangan = Integer.parseInt(jComboBox1.getSelectedItem().toString());
            con.hapusRecord(kodefasilitasruangan,"fasilitas_ruangan_id", "fasilitas_ruangan");
            
            table(nomorKamar);
            loadcomboboxfasilitaskode();
            
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Tombol Edit
        int id = Integer.parseInt(jComboBox1.getSelectedItem().toString());
        new editFasilitasRuangan(state,id).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FasilitasRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FasilitasRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FasilitasRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FasilitasRuangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FasilitasRuangan(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonKembali;
    private javax.swing.JComboBox<String> comboTipe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
