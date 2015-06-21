package User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Player class contains information about the player’s status in the game. Each player will have
 * player_id and token_id. Each player will be assigned a turn order in the beginning of the game.
 * 
 * @author John Santos, edited by Cheryl
 */
public class Player
{
    private int playerID;
    private int userID;
    // the player's piece on the game board
    private int tokenID; 
    // the player's space on the game board
    private int spaceID;
    // called by the game to see if a player is still able to play due to bankruptcy or whatever
    // player is still in the game but only as an observer
    private boolean spectator;
    //private boolean isInJail =false;
    
    /**
     * default empty constructor needed for JSP/Servlets and Database Classes
     */
    public Player(){
        
    }
    

    /**
     * initializer needed by the Database Class
     * 
     * @param playerID
     * @param userID
     * @param tokenID
     * @param spaceID 
     */
    public void initializePlayer(int playerID, int userID, int tokenID, int spaceID) 
    {
        this.playerID = playerID;
        this.userID = userID;
        this.tokenID = tokenID;
        this.spaceID = spaceID;
        this.spectator = false;
    }
    
   
    /**
     * initializer without a player id
     * 
     * @param userID
     * @param tokenID
     * @param spaceID 
     */
    public void initializePlayer(int userID, int tokenID, int spaceID) 
    {
        this.userID = userID;
        this.tokenID = tokenID;
        this.spaceID = spaceID;
        this.spectator = false;
    }
    
    
    /**
     * 
     * Called by the game to see if a player is still able to play due to bankruptcy 
     * or whatever player is still in the game but only an observer.
     * 
     * @return boolean true if player is no longer taking turns in the game, otherwise false
     */
    public boolean getSpectator() {
        return spectator;
    }

    /**
     * Sets the value that determines if a Player is still actively participating in
     * the game (false) or only an observer of the game (true).
     * 
     * @param spectator true if player is no longer taking turns in the game, otherwise false
     */
    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    /**
     * Returns the value of the Space ID
     * @return int the id for the location of the Player on the game board
     */
    public int getSpaceID() {
        return spaceID;
    }

    /**
     * Sets the value of the Space ID
     * @param spaceID the id for the location of the Player on the game board
     */
    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }
    
    /**
     * Sets the id of the token image (game pice) assigned to the Player
     * This is the key for the token image file in the database.
     * 
     * @param id the id for the token
     */
    public void setTokenID(int id){
        tokenID = id;
    }

    /**
     * Gets the id of the token image (game pice) assigned to the Player.
     * This is the key for the token image file in the database.
     * 
     * @return int the token id
     */
    public int getTokenID(){
        return tokenID;
    }
    
    /**
     * Set the Player id number.
     * This should only be set by the Database Controller when it creates a new Player.
     * 
     * @param id the Player id in the database and in the game
     */
    public void setPlayerID(int id){
        playerID = id;
    }

    /**
     * Get the Player id number
     * @return int the Player id in the database and in the game
     */
    public int getPlayerID(){
        return playerID;
    }

    /**
     * Get the database id for the registered User who is the Player.
     * 
     * @return int the User id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Set the database id for the registered User who is the Player.
     * This should only be set by the Database Controller when it creates a new Player.
     * 
     * @param userID the User id
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

}
    
