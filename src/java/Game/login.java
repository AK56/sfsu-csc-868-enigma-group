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
import Database.UserPlayerDatabaseController;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Gurpartap Gill
 * The login class is a Java Servlet that checks if the username and password being entered
 * specifies a valid entry in the database for a particular User.
 */
public class login extends HttpServlet {
    
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
        String passwrd = request.getParameter("password");
        
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        session.setAttribute("passwrd", passwrd);
        
        String lobbyPage = new String("lobby.jsp");
        String regPage = new String("noSuccess.html");
      
            /*direct the User to Lobby page, if the user is Registered*/
        if (UserPlayerDatabaseController.getInstance().doesUserLoginExist(userName, passwrd)){
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", lobbyPage);
            }
        /* else prompt the User to Registration page to Register*/
        else {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", regPage);
        }
        
    }
    
     
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
