/**
 *
 * @author John
 * 
 * Summary: The bank owns all the properties, so whenever a player wants to buy or mortgage a property, he
needs to call the bank class. The bank gives starter money to all the players in the beginning of the
game. It stores all the bank accounts of all the players. It also owns the property cards that will be
given to the player who wishes to buy a property.
 * 
 */
package Game;

import Property.*;
import java.util.ArrayList;
import java.util.List;

public class Bank
{
    private int bankID;
    private List <BankAccount> bankAccountList;  // list of players' bank accounts
    //private Bankruptcy bankruptcy;
    private int numHouses;
    private int numHotels;
    private ArrayList<Mortgage> mortgageList;
    int numSpaces = 40;
    
    public Bank(){
        
    }
    
    public void getNewMortgage(Player player, Property property){
        player.getPlayerID();
    }
            

    public void payOffMortgage(Player player, Property property){
        
    }

    //public boolean isPlayerBankrupt(Player player)
    
    public BankAccount getPlayerBankAccount(Player player){
        return bankAccountList.get(player.getPlayerID());
    }

    public ArrayList <Mortgage> getMortgageList(){
        return mortgageList;
    }

    public void sellHouseToBank(Player player){
        
    }

    public void sellHotelToBank(Player player){
        
    }
    
    public void debitAccount(int playerID, int amount){
        
    }

    public void creditAccount(int playerID, int amount){
        
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }
    
    
            
}
