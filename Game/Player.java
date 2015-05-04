package Game;

import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author John
 * 
 * Summary: The Player class contains information about the player’s status in the game. Each player will have
 * player_id and token_id. Each player will be assigned a turn order in the beginning of the game.
 * 
 * Edited by Kenneth Robertson
 */
public class Player
{
    private int playerID;
    private int userID;
    private int tokenID; // one player has one token id
    private int bankAccountID; // a player has one bank account
    private String actOfPlayer; // waiting, name of move
    private int numHouses;
    private ArrayList<Integer> propertyList;
    private Game game;
    
    public Player(){
        
    }

    public Player(int playerID, int userID, int tokenID, int bankAccountID, String actOfPlayer, Game game, ArrayList<Integer> propertyList) 
    {
        this.playerID = playerID;
        this.userID = userID;
        this.tokenID = tokenID;
        this.bankAccountID = bankAccountID;
        this.actOfPlayer = actOfPlayer;
        this.game = game;
        this.propertyList = propertyList;
    }
    
    public void initialize(int playerID, int userID, int tokenID, int bankAccountID, String actOfPlayer, Game game, ArrayList<Integer> propertyList)
    {
        this.setPlayerID(playerID);
        this.setUserID(userID);
        this.setTokenID(tokenID);
        this.setBankAccountID(bankAccountID);
        this.setActOfPlayer(actOfPlayer);
        this.setGame(game);
        this.setPropertyList(propertyList);
    }
    
    public int getNetWorth()
    {
        game.getBank().getPlayerBankAccount(this).getNetWorth();
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public String getActOfPlayer() {
        return actOfPlayer;
    }

    public void setActOfPlayer(String actOfPlayer) {
        this.actOfPlayer = actOfPlayer;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Integer> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(ArrayList<Integer> propertyList) {
        this.propertyList = propertyList;
    }
}
    