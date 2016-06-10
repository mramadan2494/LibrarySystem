/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mohamed Ramadan
 */
public class User {
    
    private static String id , fname , lname ,password , type , email ; 

    public User(String id, String fname, String lname, 
            String password, String type, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    public User() {
        id="";
        fname="";
        lname="";
        email="";
        password="";
        type="";
                
    }

  
  

    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }
    
    public static boolean validateUser(Statement stmt){
        
 
 String selectUser="Select* from user where    userEmail='"
                           +email+"' and userPass='"+password+"'";
         
       try {
          
           ResultSet RS=stmt.executeQuery(selectUser);
        
           if(!RS.next())
               return false;
         
          // email=RS.getString(1);
           
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
    
    return true;
    }
    
    
    public  String getUserType(Statement stmt , String em) throws SQLException{
        
        
        String userType="Select* from user where    userEmail='"
                           +email+"'";
         ResultSet RS=stmt.executeQuery(userType);
          if(!RS.next())
                return "notExist";
      
          System.out.println(RS.getString(3));
          return RS.getString(3);
 
    }
    
    public  boolean existEmail(Statement stmt , String em){
        
        
        String selectUser="Select* from user where    userEmail='"
                           +em+"'";
        
      try {
      
           ResultSet RS=stmt.executeQuery(selectUser);
        
           if(!RS.next())
               return false;
         
          // email=RS.getString(1);
           
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
    
    return true;
        
 
    }
    
    public int addUser(Statement stmt,User u){
    String insert;
    insert="insert into librarydb.user (fname , lname , userType , "
            + " userPass , userEmail ) "
            + "values( '"+u.getFname()+"', '"+u.getLname()
            + "' , '"+u.getType() +"' , '"+u.getPassword()
            + "' , '"+u.getEmail()+"' );";
            
        try {
                return stmt.executeUpdate(insert);
            } catch (SQLException ex) {
               System.out.println(ex.toString());
            }
  
    
    return 0;
    
    }
    
    public ArrayList getUsers(Statement stmt) throws SQLException{
     ArrayList<String> arr=new ArrayList<>();
        String booksNames="Select userEmail from user ";
         ResultSet RS=null;
         RS=stmt.executeQuery(booksNames);
          while(RS.next()){
          if(!arr.contains(RS.getString(1)))   
          arr.add(RS.getString(1));
              
          }
         
    return arr;
    
    
    }
    
      public ArrayList getStudents(Statement stmt) throws SQLException{
     ArrayList<String> arr=new ArrayList<>();
        String booksNames="Select userEmail from user where userType='0' ";
         ResultSet RS=null;
         RS=stmt.executeQuery(booksNames);
          while(RS.next()){
          if(!arr.contains(RS.getString(1)))   
          arr.add(RS.getString(1));
              
          }
         
    return arr;
    
    
    }
      
    
    
    
}
