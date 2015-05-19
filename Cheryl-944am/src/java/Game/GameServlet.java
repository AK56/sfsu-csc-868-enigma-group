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
 */
@WebServlet("/Game.GameServlet")
public class GameServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int gameID;
    private Bank bank;
    private ArrayList<Player> players; 
    private ArrayList<Property> properties;
    boolean isInJail = false;
    int playerId = 0;
    BankDatabaseController bankData = null;
    UserPlayerDatabaseController playerData= null;
    private Player activePlayer;
    //private Bank bank;
    //private Database database;
    //private static GameServlet gameInstance;
    public GameServlet() {
    }
    public GameServlet(int gameId, Bank bank, Player activePlayer)
                         
    {
        this.gameID = gameId;
        this.bank = bank;
        this.activePlayer = activePlayer;
        //this.bankData = bankData;
        
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
        String param = request.getParameter("param");
        //if(request.getParameter("checkIfInJail").compareTo("false") == 0){
            /*if(param.compareTo("isInJail") == 0){
                response.setContentType("text/plain; charset=UTF-8"); 
                PrintWriter out = response.getWriter();
                   /*returns if the Player is InJail or Not*/
                /*out.write(String.valueOf(isInJail));
            //out.write(String.valueOf(activePlayer.get(0).isInJail()));
            }*/
        /*sets the response content type to text to return dice value*/
        //else if(request.getParameter("diceRoll").compareTo("true") == 0){
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

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isIsInJail() {
        return isInJail;
    }

    public void setIsInJail(boolean isInJail) {
        this.isInJail = isInJail;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    
    
}
