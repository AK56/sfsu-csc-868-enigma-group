/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import java.util.*;
/**
 *
 * @author JattMac
 */
public class Jail extends Space{
    
    private int fine;
    private int turnsLeft;
    private Map playersInJail; //maps the number of turns left for a player to get out of Jail by rolling die
    private ArrayList<Player> getOutOfJailCard; // list of player with getOutofJailCard
    
    public Jail(int location){
        super(location);
        fine = 50;
        turnsLeft = 3;
        playersInJail = new HashMap();
        getOutOfJailCard = new ArrayList<Player>();
        
    }
    public int getFine(){
        return fine;
    }
    public void setFine(int fine){
        this.fine = fine;
    }
    public void goToJail(int playerId){
        /*double check if the player is not already in Jail, then send to jail*/
        if(!playersInJail.containsKey(playerId)){
            //initialize with 3 turns for the player to get outOfJail
            playersInJail.put(playerId, turnsLeft);
        }
    }
    public boolean isInJail(int playerId){
        return playersInJail.containsKey(playerId);
    }
    public boolean hasGetOutOfJailCard(Player player){
        //if the player has getOutOFJailCard
        return getOutOfJailCard.contains(player);
    }
    public void addGetOutOfJailCard(Player player){
        /*if player has received a getOutOfJailCard, add the player to the list*/
        getOutOfJailCard.add(player);
        
    }
    
        /*three ways to get out of Jail:
        1. To use getOutOfJailCard
        2. Paying a fine
        3. Roll a die twice in the next three turns*/
    
    public boolean useGetOutOfJailCard(Player playerInJail){
        boolean isOut = false;
        if(getOutOfJailCard.contains(playerInJail)){
            /*if player has getOutOfJailCard, use it and remove player from the jail*/
            playersInJail.remove(playerInJail.getPlayerID());
            return getOutOfJailCard.remove(playerInJail);
            
        }
        else
            return isOut;
    }
    public boolean getOutOfJailByFine(Player playerInJail){
        boolean isOut = false;
        /*if players account balance is sufficient to pay $50 fine, then debit player's account
        and remove player from the jail*/
        /**
        if(bank.getPlayerBankAccount(playerInJail).setCurrentBalance
                (bank.getPlayerBankAccount(playerInJail).getCurrentBalance - 50) >= 0){
            playersInJail.remove(playerInJail);
            isOut = true;
            return isOut;
        }
        else
        ***/
            return isOut;
        
    }
    
    
}
