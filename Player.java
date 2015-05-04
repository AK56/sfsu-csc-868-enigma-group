
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author John
 * 
 * Summary: The Player class contains information about the player’s status in the game. Each player will have
 * player_id and token_id. Each player will be assigned a turn order in the beginning of the game.
 */
public class Player
{
    private int playerID;
    private int userID;
    private int tokenID; // one player has one token id
    private ArrayList<Integer> propertyList; // list of property ids
    private int bankAccountID; // a player has one bank account
    private int networth; // list of property + total money - mortgage
    private String actOfPlayer; // waiting, name of move
    private int numHouses;
    private int numHotels;
    
    public Player(){
        
    }

    // returns player’s net worth
    public int getTotalValue(BankAccount bankAccount){
        networth = bankAccount.getCurrentBalance();
        return networth;
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

    public int getUserID(){
        return userID;
    }

    public void setBankAccountID(int id){
        bankAccountID = id;
    }

    public int getBankAccountID(BankAccount bankAccount){
        return bankAccount.getBankAccountID();
    }

    public ArrayList<Integer> getPropertyList(){
        return propertyList;
    }
    
    public void printPropertyList(){
        
    }

    public void addProperty(Property property){
        propertyList.add(property.getPropertyID());
    }

    public void removeProperty(Property property){
        propertyList.remove(property.getPropertyID());
    }

    public boolean hasProperty(Property property){
        return propertyList.contains(property.getPropertyID());
    }

}
    