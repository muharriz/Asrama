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
    public static ResultSetMetaData rsm;
    
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
    /*public int GetJumlahTipe(){
        int jumlah = 0;
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select count(*) as jumlah_tipe from gettipekamar");
             while(rs.next()){
                 jumlah = rs.getInt("jumlah_tipe");
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }finally{ 
           return jumlah;
        }
    }*/
    //Fung
    public int GetJumlahKamar(){
        int jumlah = 0;
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select * from getjumlahkamar");
             rs.next();
             jumlah = rs.getInt("ruangan_no");
             
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
            System.out.println("Error Di proses count!");
        }finally{ 
           return jumlah;
        }
    }
    public String[] nomorKamar(int jumlahkamar){
        String[] nomorkamar = new String[jumlahkamar];
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select ruangan_no from ruangan");
             int i = 0;
             while(rs.next()){
                 nomorkamar[i] = rs.getString("ruangan_no");
                 i++;
             }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
            System.out.println("Error Di proses pembuatan list nomor kamar!");
        }finally{
            if(nomorkamar == null)
                System.out.println("null!");
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
    
    public void InsertRuanganBaru(int nomorkamar,String tipekamar){
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
             rs = ExecQuery("Select type_id from type_ruangan where type_nama like '"+tipekamar+"'");
             rs.next();
             int tipekamarid = Integer.parseInt(rs.getString("type_id"));
            //menjalankan query dan dimasukkan ke objek rs
             stmt.executeUpdate("Insert into ruangan "+
                                "values("+nomorkamar+",'"+tipekamarid+"','kosong')");
             JOptionPane.showMessageDialog(null,"Data Berhasil Masuk!","BERHASIL",JOptionPane.INFORMATION_MESSAGE);
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Masuk!","ERROR",JOptionPane.ERROR_MESSAGE);
        }finally{ 
           
        }
    
    }
}
