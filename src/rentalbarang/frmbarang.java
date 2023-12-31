/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rentalbarang;
//Library
import java.awt.Dimension; //Untuk mendapatkan dimensi lebar atau tinggi suatu komponen dalam int atau double.
import java.awt.Toolkit; //Salah satu fungsinya Untuk mendapatkan ukuran jendela.
import java.sql.SQLException; // Untuk menangani kesalahan-kesalahan yang mungkin terjadi saat pengolahan database
import javax.swing.JOptionPane; //Untuk menyediakan jendela dialog.
import java.sql.Connection; //Mengkoneksikan java dengan SQL.
import java.sql.PreparedStatement; // Prepared statement memungkinkan pengguna untuk mengganti nilai parameter dalam string syntaks SQL dengan ?.
import java.sql.Statement; // Statement hanya memungkinkan pengguna untuk mengirim syntaks SQL tanpa bisa mengubah nilai parameter.
import java.sql.ResultSet; // ResultSet memungkinkan Anda untuk mengakses baris-baris data yang dikembalikan oleh query SELECT dan melakukan operasi
                           //seperti membaca nilai-nilai kolom, memperbarui data, atau melakukan iterasi melalui hasil.

import java.awt.event.KeyEvent;  //Key event untuk membuat trigger yang akan terpicu ketika sebuah tombol di keyboard ditekan.
import javax.swing.SpinnerNumberModel; //Untuk mengatur swing control JSpinner untuk memilih nilai numerik dalam rentang tertentu..
import java.util.Date; //Membuat objek Date: Anda dapat membuat objek Date untuk merepresentasikan tanggal dan waktu saat ini.
import java.text.SimpleDateFormat; //mengubah format tanggal dan waktu menjadi bentuk string, atau sebaliknya.
import java.text.ParseException; //Digunakan dalam parsing string menjadi angka atau parsing string menjadi tanggal.
import javax.swing.table.DefaultTableModel; //Digunakan untuk menampilkan dan mengedit data dalam bentuk tabel.


/**
 *
 * @author Kelompok 8
 */
public class frmbarang extends javax.swing.JFrame {
    //Inisiasi variable dan Class
    String NPM;
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;
    /**
     * Creates new form frmbarang
     */
    public frmbarang() {
        initComponents();
        
        //Berfungsi agar window berada di tengah dengan menggunakan library Dimension 
        // dan toolkit agar bisa mendapatkan ukuran layar.
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize(); 
        int x = layar.width / 2 - this.getSize().width / 2; //Mendaatkan Lebar layar.
        int y = layar.height / 2 - this.getSize().height / 2; //Mendapatkan tinggi layar.
        this.setLocation(x,y); //Meletakan lokasi jendela tepat di tengah layar.
        
        //Nilai awal=0, Nilai minimum = 0, Nilai maksimum = 10, Jarak setiap naik =1.
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel( 0, 0, 10, 1); 
        jml.setModel(spinnerNumberModel);
        
        //Memberi Nama pada kolom tabel.
        String[] header = {"Id Rental", "Barang", "Jumlah", "Merk Barang", "Tgl Pinjam", "Tgl Kembali", "Lama Pinjam","Telat", "Kembali", "Denda" };
        model = new DefaultTableModel(header,0);  //Membuat Objek Tabel
        tabelStatus.setModel(model); //Menampilkan nilai dari objek tabel.
         
        
        
    }
    
    
    private void autonumber(){
        //Fungsi untuk membuat ID Rental secara Otomatis.
        try{
            Connection c = koneksi.getkoneksi(); //Menghubungkan Java dengan SQL
            st = c.createStatement();
            String slnpm = this.NPM.substring(8, 11); //Mendapatkan char dari NPM dari indeks 8-11
            int i =1; 
            String Id_ren = (slnpm + i); //Menambahkan angka pada akhir substring NPM yang sudah di slice.
            rs = st.executeQuery("SELECT id_Rental FROM tb_rental"); //Menampilkan nilai dari id_rental dati tabel tb_rental.
            
            while(rs.next()){ //Mengakses seluruh baris dari kolom id_rental.
                String id_ren = rs.getString(1); //Mendapatkan Id Rental
                if(!id_ren.equals(Id_ren)){ //Periksa apakah id rental yang di inputkan sudah ada ?
                }
                else //Jika sudah ada maka tambbah i dengan 1
                { 
                    i ++; 
                    Id_ren = (slnpm + i); 
                }
            }
            idrental.setText(Id_ren); //Mendapatkan Id_rental Baru
        }catch(Exception e){
            System.out.println("e"); //Menerima error saat SQL gagap di olah.
        }
    }
      
    public void tanggalpinjam(){
      //fungsi untuk mendapatkan tanggal terkini
        Date tgl =new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy"); //Format penanggalan
        tglpinjam.setText(dt.format(tgl));//Menampilkan teks tanggal
    } 
    
    public void jumlahchar(KeyEvent a){
      //fungsi agar ID Rental tidak lebih dari 4
        if(idrental.getText().length()==4){
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan 4 Karakter",//JOption memberikan dialog Peringatan bila lebih dari 4
                    "Peringatan",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void kirim(String NPM){ 
      //Mendapatkan nilai NPM dari kelas Login
         this.NPM = NPM;
         tanggalpinjam(); 
         autonumber();
         tampil();
    }
    
    public void tampil(){
      //Fungsi untuk menampilkan tabel
        hitungtelat();
        koneksi classKoneksi = new koneksi();//Mengkoneksikan dengan SQL
        try{
            con = classKoneksi.getkoneksi();
            st = con.createStatement(); //Menyiapkan preparedStatement
            rs = st.executeQuery("SELECT * FROM tb_rental WHERE NPM='" + this.NPM + "'"); //Mengeksekusi syntaks SQL
            
            int no = 1;
            while(rs.next()){ //Mengaskses seluruh baris di database dimana NPM nilainya sama dengan yang di Inputkan
              //Mendapatkan semua nilai Coloumn
                String[] row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                                rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
                model.addRow(row);//Membentuk tabel
                no++;
            }
        }catch(SQLException ex){
            System.out.print(ex.getMessage());//Menerima error bila SQL gagal diolah.
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

        tampilkan = new javax.swing.JMenuBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idrental = new javax.swing.JTextField();
        cmb_barang = new javax.swing.JComboBox<>();
        tglkembali = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        merkbarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jml = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tglpinjam = new javax.swing.JTextField();
        lmpinjam = new javax.swing.JTextField();
        aturulang = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelStatus = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(102, 255, 102));
        jLabel1.setText("Id. Rental");

        idrental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idrentalActionPerformed(evt);
            }
        });
        idrental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idrentalKeyTyped(evt);
            }
        });

        cmb_barang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Microphone", "Speaker", "Kabel", "Spidol" }));
        cmb_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_barangActionPerformed(evt);
            }
        });

        tglkembali.setBackground(new java.awt.Color(255, 255, 255));
        tglkembali.setForeground(new java.awt.Color(255, 255, 255));
        tglkembali.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tglkembaliAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                tglkembaliAncestorRemoved(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("DAFTAR PEMINJAMAN");

        jLabel4.setText("Tanggal Pinjam");

        jLabel2.setText("Lama Pinjam");

        jLabel5.setText("Merk Barang");

        jLabel6.setText("Barang");

        jml.setBounds(0, 0, 10, 1);

        jLabel7.setText("Jumlah");

        submit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/down.png"))); // NOI18N
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal Kembali");

        tglpinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglpinjamActionPerformed(evt);
            }
        });

        aturulang.setText("Isi Ulang");
        aturulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aturulangActionPerformed(evt);
            }
        });

        jLabel9.setText("hari");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PNG/tool.png"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Note* : Bersedia untuk bertanggung jawab bila barang yang dipinjam mengalami kerusakan / hilang");

        tabelStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabelStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Titile 10"
            }
        ));
        tabelStatus.setGridColor(new java.awt.Color(0, 0, 0));
        tabelStatus.setShowGrid(true);
        jScrollPane1.setViewportView(tabelStatus);

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("PINJAM BARANG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 173, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(aturulang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jml)
                            .addComponent(cmb_barang, javax.swing.GroupLayout.Alignment.LEADING, 0, 154, Short.MAX_VALUE)
                            .addComponent(idrental, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(merkbarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lmpinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tglpinjam)
                            .addComponent(tglkembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idrental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(merkbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tglpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lmpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jLabel9)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(aturulang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tglpinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglpinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglpinjamActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        Connection c = koneksi.getkoneksi(); //Mengkoneksikan dengan SQL
      
        //Mendapatkan semua Input user lalu di convert menjadi nilai string.
        String id_rental = idrental.getText().toString().trim();
        String varName = (String)cmb_barang.getSelectedItem();
        String barang = cmb_barang.getSelectedItem().toString().trim();
        String jumlah = jml.getValue().toString().trim();
        String merk = merkbarang.getText().toString().trim();
        String kembali = "Belum";
        
        //Mendapatkan nilai tanggal
        Date tgl =new Date();
        Date tgl_kmbl = tglkembali.getDate();
        String lm_pinjam = lmpinjam.getText().toString().trim();
        
        //convert date menjadi time
        java.sql.Date tgl_pinjam = new java.sql.Date(tgl.getTime());
        java.sql.Date tgl_kembali = new java.sql.Date(tgl_kmbl.getTime());
        
        //memeriksa apakah ada nilai yang kosong.
        if (id_rental.equals("") || barang.equals("") || jumlah.equals("") || merk.equals("") ||
                tgl_pinjam.equals("") ||  tgl_kembali.equals("") ||  lm_pinjam.equals("")){
            JOptionPane.showMessageDialog(null, "Form tdk boleh ada yang kosong");//Bila kosong Joption menampilkan dialog
        }else{
            try{
                PreparedStatement st = c.prepareStatement("SELECT NPM FROM tb_peminjam");//Menyiapkan SQL untuk mengakses database
                ResultSet rs = st.executeQuery();
                rs.next();//akses database berdasarkan NPM
                
                String sql = "INSERT INTO tb_rental VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Memasukan value dari variable yg telah dibuat kedalam SQL
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id_rental);
                p.setString(2, barang);
                p.setString(3, jumlah);
                p.setString(4, merk);
                p.setDate(5, tgl_pinjam);
                p.setDate(6, tgl_kembali);
                p.setString(7, lm_pinjam);
                p.setString(8, "0");
                p.setString(9, kembali);
                p.setString(10, "0");
                p.setString(11, this.NPM);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Create Pinjam Successfully");
            }catch(SQLException e){
                System.out.println("pinjam Error");
                System.out.println(e);
            }
            finally{
                this.dispose();
                frmbarang a = new frmbarang();
                a.kirim(this.NPM);
                a.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_submitActionPerformed
    
    public String hitungselisih(){
            Date tgl2 =new Date();// mendapatkan tanggal saat ini
            Date date1 = tglkembali.getDate(); //mendapatkan tanggal kembali
            long selisihMilidetik = date1.getTime() - tgl2.getTime(); //mengconvert tanggal menjadi milidetik
            long selisihHari = (selisihMilidetik / (24 * 60 * 60 * 1000)) + 1;//convert  milidetik menjadi hari
            String selisih = Long.toString(selisihHari);//mengubah tipe long menjadi String
        return selisih;   
    }
    
    public void hitungtelat(){
      //Fungsi untuk menghitung telat
        Date tgl2 =new Date();
        String telats = "";//Inisiasi Nilai telat
        
            try{            
                Connection c = koneksi.getkoneksi();//mendapatkan koneksi SQL
                st = c.createStatement();
                rs = st.executeQuery("SELECT tgl_kembali, kembali FROM tb_rental WHERE NPM='" + this.NPM + "'");//mendapatkan tanggal kembali berdasarkan NPM

                int i =1;
                while(rs.next()){//Mengakses database
                    String slnpm = this.NPM.substring(8, 11); //mendapatkan id rental
                    String kembali = rs.getString(2);//mendapatkan status kembali
                  
                    if (kembali.equals("Belum")){//Bila status Belum maka mulai hitung apakah telat?
                        String Id_ren = (slnpm + i);
                        Date date1 = rs.getDate(1);
                        long selisihMilidetik = tgl2.getTime()- date1.getTime();
                        long selisihHari = (selisihMilidetik / (24 * 60 * 60 * 1000)+1) ;
                        telats = Long.toString(selisihHari);
                        System.out.println(telats); //mendapatkan nilai telat


                        if(telat >= 0){ // bila nilai telat tidak minus maka mulai menghitung denda
                            PreparedStatement tm = c.prepareStatement("UPDATE tb_rental SET telat='" + telat +"' WHERE id_rental ='" + Id_ren + "'");
                            tm.executeUpdate(); //mengupdate nilai telat di SQl
                            int denda = 0 + telat*5000;
                            PreparedStatement km = c.prepareStatement("UPDATE tb_rental SET denda='" + denda +"' WHERE id_rental ='" + Id_ren + "'");
                            km.executeUpdate(); //mengupdate nilai denda di SQl
                            i++;
                        }
                        else{
                            i++;
                        }   
                    }else {
                        i++;
                    }
             
                }
                
            }
            catch(SQLException e){
                System.out.println(e); //menerima error bila SQL gagal di olah
            }   
    }
    
    private void idrentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idrentalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idrentalActionPerformed

    private void aturulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aturulangActionPerformed
        // Mereset semua isi formulir
        merkbarang.setText("");
        tglkembali.setDate(null);
        lmpinjam.setText("");
        jml.setValue(0);
    }//GEN-LAST:event_aturulangActionPerformed

    private void idrentalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idrentalKeyTyped
        // memeriksa jumlah char di ID-rental
        jumlahchar(evt);
    }//GEN-LAST:event_idrentalKeyTyped

    private void tglkembaliAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tglkembaliAncestorAdded
   
    }//GEN-LAST:event_tglkembaliAncestorAdded

    private void tglkembaliAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tglkembaliAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tglkembaliAncestorRemoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        lmpinjam.setText(hitungselisih());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_barangActionPerformed

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
            java.util.logging.Logger.getLogger(frmbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmbarang().setVisible(true);//menampilkan frame barang
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aturulang;
    private javax.swing.JComboBox<String> cmb_barang;
    private javax.swing.JTextField idrental;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jml;
    private javax.swing.JTextField lmpinjam;
    private javax.swing.JTextField merkbarang;
    private javax.swing.JButton submit;
    private javax.swing.JTable tabelStatus;
    private javax.swing.JMenuBar tampilkan;
    private com.toedter.calendar.JDateChooser tglkembali;
    private javax.swing.JTextField tglpinjam;
    // End of variables declaration//GEN-END:variables
}
