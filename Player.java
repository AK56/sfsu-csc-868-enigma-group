
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author John, edited by Cheryl
 * 
 * Summary: The Player class contains information about the player’s status in the game. Each player will have
 * player_id and token_id. Each player will be assigned a turn order in the beginning of the game.
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
    // player is still in the game but only an observer
    private boolean activePlayer;
    
    
    // default empty constructor needed for JSP/Servlets and Database Classes
    public Player(){
        
    }
    
    // initializer needed by the Database Class
    public void initializePlayer(int playerID, int userID, int tokenID, int spaceID) 
    {
        this.playerID = playerID;
        this.userID = userID;
        this.tokenID = tokenID;
        this.spaceID = spaceID;
        this.activePlayer = false;
    }
    
    // called by the game to see if a player is still able to play due to bankruptcy or whatever
    // player is still in the game but only an observer
    public boolean getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(boolean activePlayer) {
        this.activePlayer = activePlayer;
    }
        
    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }
    
    public void setTokenID(int id){
        tokenID = id;
    }

    public int getTokenID(){
        return tokenID;
    }

    public void setPlayerID(int id){
        playerID = id;
    }

    public int getPlayerID(){
        return playerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
}
    