/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Property.Property;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gurpartap Gill
 */
@WebServlet("/Game.GameServlet")
public class GameServlet extends HttpServlet {
    private ArrayList<Property> properties;
    private Bank bank;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   /* private int gameID;
    private Player activePlayer; 
    private ArrayList<Property> properties;
    //private Bank bank;
    //private Database database;
    private static GameServlet gameInstance;
    public GameServlet() {
    }
    public GameServlet(int gameID, Player player, ArrayList<Property> properties) 
    {
        this.gameID = gameID;
        this.activePlayer = player;
        this.properties = properties;
        //this.bank = bank;
        //this.gameBoard = gameBoard;
        //this.database = database;
    }
    /*let's try to use just one GameInstance since we are having just one Player session.
    Should try avoiding multiple GameServlet objects. I have to check on this one, may we can jsut create one GameServlet
    object through Client Side and then keep using it. The following might not be needed.*/
    /*public static GameServlet getInstance(){
        if(gameInstance == null){
            gameInstance = new GameServlet();
        }
        return gameInstance;
    }*/
    /*for we are doing everything through doget()
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GameServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GameServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        //processRequest(request, response);
        /*sets the response content type to text to return dice value*/
        if(request.getParameter("diceRoll").compareTo("true") == 0){
        response.setContentType("text/plain; charset=UTF-8"); 
        PrintWriter out = response.getWriter();
                Dice.getinstance().rollDice();
                int diceValue = Dice.getinstance().getDiceTotal();
        /*returns the diceValue as a string to the AJAX call made by client*/
        out.write(String.valueOf(diceValue)); 
        }
    }
    
    public boolean buyProperty(Player player, int propertyID)
    {
        for(Property current : this.properties)
        {
            if(current.getPropertyID() == propertyID)
            {
                int propertyCost = current.getPurchasePrice();
                if(bank.getPlayerBankAccount(player).getCurrentBalance() < propertyCost)
                    return false;
                bank.debitAccount(player.getPlayerID(), propertyCost);
                current.setOwnerID(player.getPlayerID());
                return true;
            }
        }
        return false;
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
     //   processRequest(request, response);
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
