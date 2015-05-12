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
    private int numHouses = 32;
    private int numHotels =12;
    private ArrayList<Mortgage> mortgageList;
    
    public Bank(){        
    }
    
    public void getNewMortgage(Property property){
        mortgageList.add(property.propertyID());
    }
            

    public void payOffMortgage(Property property){
        mortgageList.remove(property.propertyID());
    }
    
    public BankAccount getPlayerBankAccount(Player player){
        return bankAccountList.get(player.getPlayerID());
    }

    public ArrayList <Mortgage> getMortgageList(){
        return mortgageList;
    }

    public void sellHouseToBank(){
        numHouses++;      
    }

    public void sellHotelToBank(){     
        numHotels++;
    }

    public void subtractFromAccount(Player player, int amount){
        bankAccountList.get(player.getPlayerID()).subtractFromAccountBalance(amount);
    }

    public void addToAccount(Player player, int amount){
        bankAccountList.get(player.getPlayerID()).addToAccountBalance(amount);  
    } 
    
    public boolean isPlayerBankrupt(Player player){
        return bankAccountList.get(player.getPlayerID()).getAccountBalance() == 0;
    }
            
}
