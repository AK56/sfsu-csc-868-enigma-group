/*
 * Questions to be answered:
 * 1) How are the tokens going to be handled.
 * 2) How are we handling the turn logic 
 * 3) How do we intend on implementing time limited games
 * 4) Interfacing with the javascript still needs to be implemented
 */
package Game;
import Property.Property;
import java.util.ArrayList;
import Data.Database;

/**
 *
 * @author Kenneth Robertson
 */
public class Game 
{
    private int gameID;
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    //private Bank bank;
    private GameBoard gameBoard;
    private Database database;
    private Player activePlayer;
    private boolean hasTimeLimit;
    private String startTime;
    private String endTime;
    
    public Game() {
    }

    /**
    public Game(int gameID, ArrayList<Player> players, ArrayList<Property> properties, 
            Bank bank, GameBoard gameBoard, Database database, Player activePlayer, boolean hasTimeLimit)
            
    {
        this.gameID = gameID;
        this.players = players;
        this.properties = properties;
        //this.bank = bank;
        this.gameBoard = gameBoard;
        this.database = database;
        this.activePlayer = activePlayer;
        this.hasTimeLimit = hasTimeLimit;
    }
    
    public void initialize(int gameID, ArrayList<Player> players, ArrayList<Property> properties, 
            Bank bank, GameBoard gameBoard, Database database, int activePlayer, boolean hasTimeLimit)
    {
        setGameID(gameID);
        setPlayers(players);
        setProperties(properties);
        setBank(bank);
        setGameBoard(gameBoard);
        setDatabase(database);
        //setActivePlayer(activePlayer);
        setHasTimeLimit(hasTimeLimit);
    }
    **/
    void startNextTurn()
    {
        //Player rolls Dice
        //Player moves to the space = Players current space + dice value
        //Player performs actions based on space landed on.
        //Player may perform additional actions like morgage/unmorage, buy/sell houses
        //If player rolled doubles he goes again unless its his third double then he goes to jail
        //Next player takes turn    
    }
    
    void initializeGameBoard()
    {
    }
    
    //???
    //void endTurn() {}
    
    //???
    //void setTokens() {}

    void giveStarterMoney()
    {
        for(Player currentPlayer : players)
            ;
            //bank.getPlayerBankAccount(currentPlayer).setCurrentBalance(1500);
    }

    void updateAllPlayerScreens()
    {
        
    }

    void deletePlayerFromGameTurns(Player deletePlayer)
    {
        /*ArrayList<Player> newPlayers = new ArrayList<Players>();
        *how about use Player as an arg, and pass that to remove it from players ArrayList ?
        */
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).equals(deletePlayer))
                players.remove(i);
        }
    }

    
    Player getWhosTurn()
    {
        return activePlayer;
    }
    void setWhosTurn(Player activePlayer)
    {
        this.activePlayer = activePlayer;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    ***/
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public boolean isHasTimeLimit() {
        return hasTimeLimit;
    }

    public void setHasTimeLimit(boolean hasTimeLimit) {
        this.hasTimeLimit = hasTimeLimit;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }
}
