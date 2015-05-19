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


/**
 *
 * @author Robert Moon
 * This class is a Servlet class that gets executed once the User clicks on 'Start new Game' on lobby Page.
 * The request is then handled by this class and records the current User and creates a new Player object
 * for the current session. The User is pretty much retrieved from the database using the login 
 * username/password parameters. Once the User is retrieved, then a new Player is created with the same 
 * player_id as user_id in the database. 
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
            
            user = Database.UserPlayerDatabaseController.getInstance().getUserByLogin(username, passwrd);//user is null!!!
            
            if (user == null)
            {
                System.out.println("user is null");
            }
            ArrayList<Player> playerList = new ArrayList(4);
            
            Player player = new Player();
        
            player = Database.UserPlayerDatabaseController.getInstance().addNewPlayer(user,9,1);//player is null!!!
            
            if (player == null)
            {
                System.out.println("player is null.");
            }
            playerList.add(player);
            
            session.setAttribute("player", player);
            
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
