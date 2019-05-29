/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;
import java.sql.*;

/**
 *
 * @author Muharriz
 */
public class Tubes {

    /**
     * @param args the command line arguments
     */
    public static Connection con;
    public static Statement stm;
    public static void main(String[] args) {
        // TODO code application logic here
       try {
           //Set up a connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
            //Create java statement
            stm = con.createStatement();
            String query = "select * from emp";
            ResultSet result = stm.executeQuery(query);
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }   
}
