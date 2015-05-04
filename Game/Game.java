/*
 * Questions to be answered:
 * 1) How are the tokens going to be handled.
 * 2) How are we handling the turn logic 
 * 3) How do we intend on implementing time limited games
 * 4) Interfacing with the javascript still needs to be implemented
 */
package Game;

import java.util.ArrayList;

/**
 *
 * @author Kenneth Robertson
 */
public class Game 
{
    private int gameID;
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    private Bank bank;
    private GameBoard gameBoard;
    private Database database;
    private int activePlayer;
    private boolean hasTimeLimit;
    private String startTime;
    private String endTime;

    public Game() {
    }

    public Game(int gameID, ArrayList<Player> players, ArrayList<Property> properties, Bank bank, GameBoard gameBoard, Database database, int activePlayer, boolean hasTimeLimit) 
    {
        this.gameID = gameID;
        this.players = players;
        this.properties = properties;
        this.bank = bank;
        this.gameBoard = gameBoard;
        this.database = database;
        this.activePlayer = activePlayer;
        this.hasTimeLimit = hasTimeLimit;
    }

    
    
    public void initialize(int gameID, ArrayList<Player> players, ArrayList<Property> properties, Bank bank, GameBoard gameBoard, Database database, int activePlayer, boolean hasTimeLimit)
    {
        setGameID(gameID);
        setPlayers(players);
        setProperties(properties);
        setBank(bank);
        setGameBoard(gameBoard);
        setDatabase(database);
        setActivePlayer(activePlayer);
        setHasTimeLimit(hasTimeLimit);
    }
    
    void startNextTurn()
    {
    }
    
    //???
    //void endTurn() {}
    
    //???
    //void setTokens() {}

    void giveStarterMoney()
    {
        for(Player currentPlayer : players)
            bank.getPlayerBankAccount(currentPlayer).setCurrentBalance(1500);
    }

    void updateAllPlayerScreens()
    {
        
    }

    void deletePlayerFromGameTurns(int playerID)
    {
        ArrayList<Player> newPlayers = new ArrayList<Players>();
        
        for(int i = 0; i < players.size(); i++)
        {
            if(i != playerID)
                newPlayers.add(players.get(i));
        }
    }

    
    Player getWhosTurn()
    {
        return players.get(activePlayer);
    }
    void setWhosTurn(int activePlayer)
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

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

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
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
