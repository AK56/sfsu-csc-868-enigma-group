/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import User.User;
import User.Player;
import Database.UserPlayerDatabaseController;
import Game.Bank;
import Game.GameServlet;

/**
 * This holds the actions for starting a new game on the web site, setting up the
 * new game players for the users, and the communication of that information 
 * between the server and the client.
 * 
 * @author Robert Moon
 */
@WebServlet(urlPatterns = {"/StartGame"})
public class StartGame extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("userName");
            String passwrd = (String) session.getAttribute("passwrd");
            //UserPlayerDatabaseController db = Database.UserPlayerDatabaseController.getInstance();
            User user = new User();
            //System.out.println(username);
            //System.out.println(passwrd);

            user = Database.UserPlayerDatabaseController.getInstance().getUserByLogin(username, passwrd);//user is null!!!
            
            if (user == null)
            {
                System.out.println("user is null");
            }
            ArrayList<Player> playerList = new ArrayList(4);
            
            Player player = new Player();
            //System.out.println(user.getFirstName());
            //int temp = user.getUserID;
            
            player = Database.UserPlayerDatabaseController.getInstance().addNewPlayer(user,9,1);//player is null!!!
            
            if (player == null)
            {
                System.out.println("player is null.");
            }
            playerList.add(player);
            
            session.setAttribute("player", player);
            //session.setAttribute("PlayerList", playerList);
            
            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StartGame</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StartGame at " + request.getContextPath() + "</h1>");
            out.println("<a href='gamePage_1.jsp'>Press to continue to game page</a>");
            out.println("</body>");
            out.println("</html>");
            *///bank, player
              String redirect = new String("gamePage.jsp");
            Bank bank = new Bank();
            GameServlet gameservlet = new GameServlet(1, bank, player);
            
             response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", redirect);
            
        
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
