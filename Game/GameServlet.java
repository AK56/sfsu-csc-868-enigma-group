/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Property.*;
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
    private int gameID;
    private ArrayList<Property> properties;
    private ArrayList<Player> players; //If we had multiple players
    private Bank bank;
    private ArrayList<Space> spaces;
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
    
    public String movePlayer(Player player, int newSpace)
    {
        String returnString = null;
        if(player.getSpaceID() > newSpace || newSpace == 0)
            bank.addToAccount(player, 200);
        
        Space nextSpace = this.spaces.get(newSpace);
        
        
        if(nextSpace instanceof Property && ((Property)nextSpace).getOwnerID() == bank.getBankID()) 
        {
            returnString = "Unowned Property "+((Property)nextSpace).getPropertyID();
        }
        else if (nextSpace.getSpaceID() == 30) //Goto jail
        {
            //Add in jail code
            newSpace = 10; //Goto jail not newSpace
        }
        else if (nextSpace.getClass() == ChanceSpace.class) 
        {
            int cardID = -1;
            //Perform card action
            
            returnString = "Card Chance "+cardID;
        } 
        else if (nextSpace.getClass() == CommunityChestSpace.class) 
        {
            int cardID = -1;
            //Perform card Action
            returnString = "Card CommunityChest "+cardID;
        }  
        else if (nextSpace.getClass() == RealEstate.class) 
        {
            int rent = ((RealEstate)nextSpace).calculateRent();
            bank.subtractFromAccount(player, rent);
            bank.addToAccount(players.get(((RealEstate)nextSpace).getOwnerID()), rent);  
        } 
        else if (nextSpace.getClass() == Utility.class) 
        {
            int rent = ((Utility)nextSpace).calculateRent();
            bank.subtractFromAccount(player, rent);
            bank.addToAccount(players.get(((Utility)nextSpace).getOwnerID()), rent);
        } 
        else if (nextSpace.getClass() == Railroad.class) 
        {
            int rent = ((Railroad)nextSpace).calculateRent();
            bank.subtractFromAccount(player, rent);
            bank.addToAccount(players.get(((Railroad)nextSpace).getOwnerID()), rent);
        } 
        else if (nextSpace.getSpaceID() == 4) //Income Tax
        {
            bank.subtractFromAccount(player, 200);
        }
        else if (nextSpace.getSpaceID() == 38) //Pay Luxury Tax
        {
            bank.subtractFromAccount(player, 100);
        }
        
        player.setSpaceID(newSpace);
        
        return returnString;
    }
    
    public boolean buyProperty(Player player, int propertyID)
    {
        for(Property current : this.properties)
        {
            if(current.getPropertyID() == propertyID)
            {
                int propertyCost = current.getPurchasePrice();
                if(bank.getPlayerBankAccount(player).getAccountBalance() < propertyCost)
                    return false;
                bank.subtractFromAccount(player, propertyCost);
                current.setOwnerID(player.getPlayerID());
                return true;
            }
        }
        return false;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }
}
