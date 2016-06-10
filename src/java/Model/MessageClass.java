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
public class MessageClass {
    String subject , userEmail ,content , seen;

    public MessageClass() {
    seen="0";
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getSubject() {
        return subject;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getContent() {
        return content;
    }

    public String getSeen() {
        return seen;
    }
    
    public int send(Statement stmt){
    
        
        String insert;
    insert="insert into librarydb.message ( userEmail , subject , content , seen ) "
            + "values('"+userEmail+"', '"+subject
            + "' , '"+content +"' , '"+seen
            + "' ) ;" ;
            
        try {
                return stmt.executeUpdate(insert);
            } catch (SQLException ex) {
               System.out.println(ex.toString());
            }
  
    
    return 0;
    
    
    } 
    
    public ArrayList getUserMessages(Statement stmt , String email){
            ArrayList <String> messages=new ArrayList<>();
            System.out.println(email);
            String selectMessages="Select subject , content from librarydb.message"
                    + " where    userEmail= "+"'"+ email+" ' ";
        
      try {
      
           ResultSet RS=stmt.executeQuery(selectMessages);
        
           while(RS.next())
               messages.add("Subject : "+RS.getString(1)+" Content "+RS.getString(2));
         
          // email=RS.getString(1);
           
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
    
    return messages;
 
    }
    
}
