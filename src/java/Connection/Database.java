package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed Ramadan 20120308
 */
public class Database {
    
    public static Connection SetConnection(){
         String URL = "jdbc:mysql://localhost:3306/librarydb";
        String UserName="root";
        String password="2494";
        Connection con=null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection(URL,UserName,password);
           // System.out.println("fff");
        }
        catch(Exception ex){
        
        System.out.println(ex.toString());
        return null;
        }
    
    return con;
    }
    
    public static Statement getStatment(Connection con){
     Statement stmt=null;
     
        try {
            stmt=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    return stmt;
    
    }
    
    
    
    
    
    
}
