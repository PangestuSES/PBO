/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rentalbarang;
//Library
import java.awt.Dimension; //Untuk mendapatkan dimensi lebar atau tinggi suatu komponen dalam int atau double.
import java.awt.Toolkit; //salah satu fungsinya Untuk mendapatkan ukuran jendela.
import java.sql.Connection; //Mengkoneksikan java dengan SQL.
import java.sql.PreparedStatement; // Prepared statement memungkinkan pengguna untuk mengganti nilai parameter dalam string syntaks SQL dengan ?.
import java.sql.Statement; // Statement hanya memungkinkan pengguna untuk mengirim syntaks SQL tanpa bisa mengubah nilai parameter.

import java.sql.ResultSet; // ResultSet memungkinkan Anda untuk mengakses baris-baris data yang dikembalikan oleh query SELECT dan melakukan operasi
                           //seperti membaca nilai-nilai kolom, memperbarui data, atau melakukan iterasi melalui hasil.

import java.sql.SQLException; // Untuk menangani kesalahan-kesalahan yang mungkin terjadi saat pengolahan database
import javax.swing.JOptionPane; //Untuk menyediakan jendela dialog.

/**
 *
 * @author Kelompok 8
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    
    public Register() {
        
        initComponents();
        //Berfungsi agar window berada di tengah dengan menggunakan library Dimension 
        // dan toolkit agar bisa mendapatkan ukuran layar.
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize(); 
        int x = layar.width / 2 - this.getSize().width / 2; //Mendaatkan Lebar layar.
        int y = layar.height / 2 - this.getSize().height / 2; //Mendapatkan tinggi layar.
        this.setLocation(x,y); //Meletakan lokasi jendela tepat di tengah layar.
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    //Pembuatan Component-component desain dari template swing java
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        npmtype = new javax.swing.JTextField();
        namatype = new javax.swing.JTextField();
        passtype = new javax.swing.JPasswordField();
        conpasstype = new javax.swing.JPasswordField();
        register = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Register");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NPM");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Password");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Confirm Paswword");

        npmtype.setBackground(new java.awt.Color(255, 255, 255));
        npmtype.setForeground(new java.awt.Color(0, 0, 0));

        namatype.setBackground(new java.awt.Color(255, 255, 255));
        namatype.setForeground(new java.awt.Color(0, 0, 0));

        passtype.setBackground(new java.awt.Color(255, 255, 255));
        passtype.setForeground(new java.awt.Color(0, 0, 0));
        passtype.setText("jPasswordField1");

        conpasstype.setBackground(new java.awt.Color(255, 255, 255));
        conpasstype.setForeground(new java.awt.Color(0, 0, 0));
        conpasstype.setText("jPasswordField2");

        register.setBackground(new java.awt.Color(255, 255, 255));
        register.setForeground(new java.awt.Color(0, 0, 0));
        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(npmtype)
                            .addComponent(namatype)
                            .addComponent(passtype, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(conpasstype, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(register)
                        .addGap(65, 65, 65)
                        .addComponent(jButton1)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(npmtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namatype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(passtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(conpasstype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(register)
                    .addComponent(jButton1))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        // TODO add your handling code here:
        String NPM = npmtype.getText().toString().trim();
        String Nama = namatype.getText().toString().trim();
        String Password = passtype.getText().toString().trim();
        String conPassword = conpasstype.getText().toString().trim();
        
        if (!Password.equals(conPassword)){
            JOptionPane.showMessageDialog(null, "Password not match");
        }else if (Password.equals("") || Nama.equals("") || NPM.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
        }else{
            try{
                Connection c = koneksi.getkoneksi();
                String sql = "INSERT INTO tb_peminjam VALUES (?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, NPM);
                p.setString(2, Nama);
                p.setString(3, Password);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Create Account Successfully");
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                this.dispose();
                Login a = new Login();
                a.kirim(NPM);
                a.setVisible(true);
            }
        }
    }//GEN-LAST:event_registerActionPerformed
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Fungsi Button Login
        this.dispose(); //Menghancurkan jendela Register
        Login a = new Login(); //Membuat jendela Login dari kelas Login
        a.setVisible(true); //Akan M
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField conpasstype;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField namatype;
    private javax.swing.JTextField npmtype;
    private javax.swing.JPasswordField passtype;
    private javax.swing.JButton register;
    // End of variables declaration//GEN-END:variables
}
