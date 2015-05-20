/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import User.Player;
import Property.*;
import Database.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gurpartap Gill
 * This is the main Servlet class that handles Player actions and requests. This class interacts directly 
 * and mostly with Game page class and perform many actions like RollDice, BuyProperty etc.
 */
@WebServlet("/Game.GameServlet")
public class GameServlet extends HttpServlet {


    private int gameID;
    private Bank bank;
    private ArrayList<Player> players; 
    private ArrayList<Property> properties;
    boolean isInJail = false;
    int playerId = 0;
    BankDatabaseController bankData = null;
    UserPlayerDatabaseController playerData= null;
    private Player activePlayer;
    
    /**
     * Default Constructor
     */
    public GameServlet() {
    }
    /**
     * Constructor - 3 parameters
     * @param gameId gameId of the Game
     * @param bank   sets the bank for the Game
     * @param activePlayer sets the player to tale first turn on Game start 
     */
    public GameServlet(int gameId, Bank bank, Player activePlayer)
                         
    {
        this.gameID = gameId;
        this.bank = bank;
        this.activePlayer = activePlayer;
    }    
   
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
        try {
            
            out.println("<!DOCTYPE html>");
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
        String param = request.getParameter("param");
        
            if(param.compareTo("rollDice") == 0){
                response.setContentType("text/plain; charset=UTF-8");  // application/json MIME type for JSON
                PrintWriter out = response.getWriter();
                        Dice.getinstance().rollDice();
                        int diceValue = Dice.getinstance().getDiceTotal();
                /*returns the diceValue as a string to the AJAX call made by client*/
                out.write(String.valueOf(diceValue)); 
                }
        /*else if(request.getParameter("subtractBalance").compareTo("50") == 0){
            int taxAmount = Integer.parseInt(request.getParameter("subtractBalance"));
            
        }*
        //else if(request.getParameter("inJail").compareTo("true") == 0){
            else if(param.compareTo("inJail") == 0){
                //activePlayer.get(0).setIsInJail(true);
                isInJail = true;
            }*/
            else if(param.compareTo("buyProperty") == 0){
                /*takes space id from Game*/
                //int spaceId = Integer.parseInt(request.getParameter("spaceId"));
                response.setContentType("text/plain; charset=UTF-8");  // application/json MIME type for JSON
                PrintWriter out = response.getWriter();
                out.write("True");
            }
            else if(param.compareTo("payTax") == 0){
                /*takes the taxAmount from client */
                int taxAmount = Integer.parseInt(request.getParameter("taxAmount"));
                bank.subtractFromAccount(activePlayer, taxAmount);
                /*update Database*/
                bankData.updateCashBalance(bank.getPlayerBankAccount(activePlayer));
            }
            
        
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
    /*** @return returns the gameId of current Game in session **/
    public int getGameID() {
        return gameID;
    }
    /*** @param gameID sets gameId of current Game in session **/
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    /*** @return returns the bank for the Game **/
    public Bank getBank() {
        return bank;
    }
    /*** @param bank sets the bank for the current **/
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    /*** @return checks if the currentPlayer is in Jail or Not **/
    public boolean isIsInJail() {
        return isInJail;
    }
    /*** @param isInJail sets the currentPlayer inJail status **/
    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }
    /*** @return returns the playerId of current Player**/
    public int getPlayerId() {
        return playerId;
    }
    /*** @param playerId sets the current playerId**/
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    /*** @return returns the activePlayer or player in turn**/
    public Player getActivePlayer() {
        return activePlayer;
    }
    /*** @param activePlayer sets the activePlayer**/
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
    /*** @return returns the list of players**/
    public ArrayList<Player> getPlayers() {
        return players;
    }
    /*** @param players sets the list of players**/
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    /*** @return  returns list of properties**/
    public ArrayList<Property> getProperties() {
        return properties;
    }
    /*** @param properties sets the list of properties**/
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    
    
}
