/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.UserPlayerDatabaseController;
/**
 *
 * @author Gurpartap Gill
 */
public class Register extends HttpServlet {

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
        PrintWriter out = response.getWriter();
       
        String userName = request.getParameter("username");
        String passwrd =  request.getParameter("password");
        String confirmPasswrd = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        System.out.println(firstName + " " + lastName + " " + userName + " " + passwrd);
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        session.setAttribute("passwrd", passwrd);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
            
        
        String ifRegistered = new String("http://localhost:8080/WebApplication1/registrationSucess.html");
        /* check if the password entries match*/
       // if((passwrd.compareTo(confirmPasswrd) == 0) && (userName != null || passwrd != null || confirmPasswrd != null
       //         || firstName != null || lastName != null)){
        if (Database.UserPlayerDatabaseController.getInstance().registerNewUser(firstName, lastName,userName, passwrd))
        {
            /*after the parameter check is done, add the above values into database and redirect the User
             to RegisteredSuccess.htm*/
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.sendRedirect(ifRegistered);
        }
        else {
        try {
            /* if Regisrtration Unsuccessful */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Registration Attempt Failed<br><a href=\"register.html\">Try Again</a></h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
       }
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
