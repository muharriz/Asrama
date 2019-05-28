/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.sql.*;
import java.util.ArrayList;

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
    public int GetColumnNumber(ResultSet rs){
        int column = 0;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
            stmt = con.createStatement();
            rsm = rs.getMetaData();
            column = rsm.getColumnCount();
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }
        
        return column;
    }
    public ArrayList GetColumnName(ResultSet rs,int column){
        ArrayList columnName = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
            stmt = con.createStatement();
            rsm = rs.getMetaData();
            for(int i = 1; i <= column;i++){
                columnName.add(rsm.getColumnName(i));
            }
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            System.out.println("SQL State : "+e.getSQLState());
            System.out.println("Error Code : "+e.getErrorCode());
        }
        
        return columnName;
    }
    
    public String[] GetNamaTipe(int jumlah){
        String[] listNama = new String[jumlah];
        
        try{
            //menciptakan koneksi dan membuat statement ke database
             con = DriverManager.getConnection("jdbc:mysql://localhost/asrama?"+"user=root&password=");
             stmt = con.createStatement();
            //menjalankan query dan dimasukkan ke objek rs
             rs = ExecQuery("select * from gettipekamar");
             int i = 0;
             while(rs.next()){
                 listNama[i] = rs.getString("type_nama");
                 i++;
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
    public int GetJumlahTipe(){
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
}
