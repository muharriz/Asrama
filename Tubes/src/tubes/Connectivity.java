/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Muharriz
 */
public class Connectivity {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    
    public Connectivity(){
        con = null;
        stmt = null;
        rs = null;
    }
    
    public ResultSet ExecQuery(String Query){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }
        
        return rs;
    }
    
    public ArrayList GetNamaTipe(){
        ArrayList<String> listNama = new ArrayList<String>();
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select * from gettipekamar");
             while(rs.next()){
                 listNama.add(rs.getString("type_nama"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{
            if(listNama == null)
                System.out.println("null!");
            return listNama;
        }
    }
    public ArrayList getIDFasilitas(){
        ArrayList<String> fasilitas_id = new ArrayList<String>();
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select fasilitas_id from fasilitas");
             while(rs.next()){
                 fasilitas_id.add(rs.getString("fasilitas_id"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{
            if(fasilitas_id == null)
                System.out.println("null!");
            return fasilitas_id;
        }
    }
    
    public ArrayList namaFasilitas(){
       ArrayList<String> namafasilitas = new ArrayList<String>();
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select fasilitas_nama from fasilitas");
             while(rs.next()){
                 namafasilitas.add(rs.getString("fasilitas_nama"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{
            if(namafasilitas == null)
                System.out.println("null!");
            return namafasilitas;
        }
    }
    
    public ArrayList nomorKamar(){
        ArrayList<String> nomorkamar = new ArrayList<String>();
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select ruangan_no from ruangan order by ruangan_no");
             while(rs.next()){
                 nomorkamar.add(rs.getString("ruangan_no"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
            System.out.println("Error Di proses pembuatan list nomor kamar!");
        }finally{
            return nomorkamar;
        }
        
    }
    
    public int Login(String username,String password){
        int state = 0;
         try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select user_level from user where user.user_username = '"+ username +
                            "' and user.user_password = '"+ password +"'");
             rs.next();
             String level = rs.getString("user_level");
             if(level.equals("admin"))
                 state = 2;
             else if(level.equals("penghuni"))
                 state = 1;
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
        return state;
    }
    
    public void TambahRuanganBaru(int nomorkamar,String tipekamar){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
             rs = ExecQuery("Select type_id from type_ruangan where type_nama like '"+tipekamar+"'");
             rs.next();
             int tipekamarid = Integer.parseInt(rs.getString("type_id"));
            //mengeksekusi query
             stmt.executeUpdate("Insert into ruangan "+
                                "values("+nomorkamar+",'"+tipekamarid+"','kosong')");
             JOptionPane.showMessageDialog(null,"Data Berhasil Masuk!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Masuk!","ERROR",JOptionPane.ERROR_MESSAGE);
        }finally{ 
           
        }
    
    }
    
    public void TambahFasilitasRuangan(int nomorruangan,String namafasilitas,int kuantitas){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
             rs = ExecQuery("Select fasilitas_id from fasilitas where fasilitas_nama like '"+namafasilitas+"'");
             rs.next();
             int fasilitasid = Integer.parseInt(rs.getString("fasilitas_id"));
            //mengeksekusi query
             stmt.executeUpdate("insert into fasilitas_ruangan "
                     + "         (kamar_no,fasilitas_id,fr_kuantitas,fr_kondisi) "
                     + "         values("+nomorruangan+","+fasilitasid +","+kuantitas+",'bagus');");
             JOptionPane.showMessageDialog(null,"Data Berhasil Masuk!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Masuk!","ERROR",JOptionPane.ERROR_MESSAGE);
        }finally{ 
           
        }
    }
    
    public void TambahFasilitas(String namafasilitas,int harga){
    try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             stmt.executeUpdate("Insert into fasilitas "+
                                "(fasilitas_nama,fasilitas_harga) "+
                                "values('"+namafasilitas+"',"+harga+");");
             JOptionPane.showMessageDialog(null,"Data Berhasil Masuk!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    
    public void hapusRecord(int id,String namakolom,String namatable){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             stmt.executeUpdate("delete from "+namatable+" where "+namakolom+" = '"+id+"';");
             JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    public ResultSet ambilDataFasilitas(int id){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select fasilitas_nama,fasilitas_harga from fasilitas where fasilitas_id = '"+id+"';");
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return rs;
        }
    }
    public void updateDataFasilitas(int id,String namafasilitas,int hargafasilitas){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             stmt.executeUpdate("update fasilitas set fasilitas_nama = '"+namafasilitas+"',fasilitas_harga = '"+hargafasilitas+"' where fasilitas_id = '"+id+"'");
             JOptionPane.showMessageDialog(null,"Data Berhasil Diupdate!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    public ResultSet ambilDataRuangan(int id){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select ruangan.ruangan_no,type_ruangan.type_nama,ruangan.ruangan_status "
                     + "     from ruangan inner join type_ruangan "
                     + "     on ruangan.type_id = type_ruangan.type_id "
                     + "     where ruangan.ruangan_no = '"+id+"';");
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return rs;
        }
    }
    public void updateDataRuangan(int id,int nomorkamar,String tipekamar,String statuskamar){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select type_id from type_ruangan where type_nama = '"+tipekamar+"'");
             rs.next();
             int tipeid = Integer.parseInt(rs.getString("type_id"));
             stmt.executeUpdate("update ruangan "
                             + " set ruangan_no = '"+nomorkamar+"',type_id = '"+tipeid+"',ruangan_status = '"+statuskamar+"' "
                             + " where  ruangan_no = '"+id+"'");
             JOptionPane.showMessageDialog(null,"Data Berhasil Diupdate!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    public ArrayList ambilKodeFasilitasRuangan(String nomorruangan){
        ArrayList<String> hasil = new ArrayList<String>();
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select fasilitas_ruangan_id from fasilitas_ruangan where kamar_no = '"+nomorruangan+"'");
             while(rs.next()){
                 hasil.add(rs.getString("fasilitas_ruangan_id"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return hasil;
        }
    }
    
    public ResultSet ambilDataFasilitasRuangan(int id){
         try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select fasilitas_ruangan.kamar_no,fasilitas.fasilitas_nama,fasilitas_ruangan.fr_kuantitas,fasilitas_ruangan.fr_kondisi,fasilitas_ruangan.fasilitas_ruangan_id "
                     + "     from fasilitas_ruangan inner join fasilitas "
                     + "     on fasilitas_ruangan.fasilitas_id = fasilitas.fasilitas_id "
                     + "     where fasilitas_ruangan.fasilitas_ruangan_id = '"+id+"';");
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return rs;
        }
    }
    
    public void updateDataFasilitasRuangan(int id,String nomorkamar,String namafasilitas,String kuantitas,String kondisi){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select fasilitas_id from fasilitas where fasilitas_nama = '"+ namafasilitas +"'");
             rs.next();
             int fasilitasid = Integer.parseInt(rs.getString("fasilitas_id"));
             stmt.executeUpdate("update fasilitas_ruangan "
                             + " set kamar_no = '"+ nomorkamar +"',fasilitas_id = '"+ fasilitasid +"',fr_kuantitas = '"+ kuantitas +"',fr_kondisi = '"+ kondisi +"' "
                             + " where fasilitas_ruangan_id = '"+ id +"'");
             JOptionPane.showMessageDialog(null,"Data Berhasil Diupdate!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    public void tambahDataPenghuni(String namadepan,String namabelakang,String NIK,String tgllahir,String nomorkamar,String username,String password){
    try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             stmt.executeUpdate("insert into "
                     + "         user(user_username,user_password,user_level)"
                     + "         values('"+ username +"','"+ password +"','penghuni')");
             rs = ExecQuery("select user_id from user where user_username = '"+ username +"' and user_password = '"+ password +"'");
             rs.next();
             int id = Integer.parseInt(rs.getString("user_id"));
             stmt.executeUpdate("insert into penghuni"
                     + "         values('"+ id +"','"+ namadepan +"','"+ namabelakang +"',STR_TO_DATE('"+ tgllahir +"','%d-%m-%Y'),'"+ nomorkamar +"','"+ NIK +"','tinggal')");
             JOptionPane.showMessageDialog(null,"Data Berhasil Masuk!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
    public ArrayList ambilIdPenghuni(){
        ArrayList<String> listId = new ArrayList<String>();
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select penghuni_id from penghuni");
             while(rs.next()){
              listId.add(rs.getString("penghuni_id"));
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return listId;
        }
    }
    public ResultSet ambilDataPenghuni(int id){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             rs = ExecQuery("select * from"
                     + "     penghuni "
                     + "     where penghuni_id = '"+id+"';");
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return rs;
        }
    }
    public void updatePenghuni(int id,String namadepan,String namabelakang,String nik,String tgllahir,String status,String ruanganno){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //mengeksekusi query
             stmt.executeUpdate("update penghuni "
                             + " set penghuni_nama_depan = '"+ namadepan +"',penghuni_nama_belakang = '"+ namabelakang +"',penghuni_NIK = '"+ nik +"',penghuni_tgl_lahir = STR_TO_DATE('"+ tgllahir +"','%Y-%m-%d'),ruangan_no = '"+ ruanganno +"',penghuni_status = '"+ status +"' "
                             + " where penghuni_id = '"+ id +"'");
             JOptionPane.showMessageDialog(null,"Data Berhasil Diupdate!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           
        }
    }
}
