/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Database;
import Model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohamed Ramadan
 */
@WebServlet(name = "SearchStudentResultServlet", urlPatterns = {"/SearchStudentResultServlet"})
public class SearchStudentResultServlet extends HttpServlet {

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
        Connection con=db.SetConnection();
        
        Statement stmt=db.getStatment(con);
        String name,author,year,category;
        Book b=new Book();
        
        name=request.getParameter("name");
        b.setName(name);
        author=request.getParameter("author");
        b.setAuthorName(author);
        year=request.getParameter("year");
        b.setPublisherYear(year);
        category=request.getParameter("category");
        b.setCategoryName(category);
        
        
        
             int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
//        BookDAO dao = new BookDAO();
//        List<Book> list = dao.viewAllBooks((page - 1) * recordsPerPage,recordsPerPage);
         List<Book> list = b.viewSpecificBooks((page - 1) * recordsPerPage,recordsPerPage ,stmt,b);  
           int noOfRecords = b.getNoOfRecords();
           int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        
       request.setAttribute("bookList", list);
        RequestDispatcher view=null;    
       HttpSession session=request.getSession();
          
      String type=(String) session.getAttribute("userType");
        if(type.equals("0"))    
         view = request.getRequestDispatcher("studentResultPage.jsp");
       else
           view = request.getRequestDispatcher("adminResultPage.jsp");
        
        view.forward(request, response);
    }
       
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
