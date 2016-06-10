/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Database;
import Model.MessageClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohamed Ramadan
 */
@WebServlet(name = "emailResultServlet", urlPatterns = {"/emailResultServlet"})
public class emailResultServlet extends HttpServlet {
      final String senderEmail = "mramadan2494@gmail.com";
    final String senderPassword = "0503832848";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                Database db=null;
                MessageClass m=new MessageClass();
                m.setUserEmail(request.getParameter("user"));
                m.setContent(request.getParameter("content"));
                m.setSeen("0");
                m.setSubject(request.getParameter("subject"));
                
               Connection con=db.SetConnection();
               Statement stmt=db.getStatment(con);
               m.send(stmt);
               
              ////////////////////////////////////////////////////////////////// 
               String to = m.getUserEmail();
             
               String from ="mramadan2494@gmail.com";
              // String host = "smtp.gmail.com";
               Properties properties = System.getProperties();
               
              
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.starttls.enable", "true");
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");
                
               Session session = Session.getInstance( properties,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		  });
        
        try{
        
        Message messag = new MimeMessage(session);
       messag.setFrom(new InternetAddress(from));
      messag.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
        messag.setSubject(m.getSubject());
        messag.setText(m.getContent());
         Transport.send(messag);
        }
        
        
        catch (MessagingException mex) {
         mex.printStackTrace();
      }
        
       
    }

   public class SMTPAuthenticator extends javax.mail.Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
return new PasswordAuthentication(senderEmail, senderPassword);
}
} // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

