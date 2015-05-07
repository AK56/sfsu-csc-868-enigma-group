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

import java.util.ArrayList;
import java.util.List;

public class Bank
{
    private int bankID;
    private List <BankAccount> bankAccountList;  // list of players' bank accounts
    //private Bankruptcy bankruptcy;
    private ArrayList<Integer> propertyList; // 40 spaces for the game board
    private int numHouses;
    private int numHotels;
    private ArrayList<Mortgage> mortgageList;
    int numSpaces = 40;
    
    public Bank(){
        // initialize property list to 0 for property not taken, 1 for taken
        for(int i=0; i<numSpaces; i++)
        {
            propertyList.add(0);
        }
        
    }
    
    public void getNewMortgage(Player player, Property property){
        player.getPlayerID();
    }
            

    public void payOffMortgage(Player player, Property property){
        
    }

    //public boolean isPlayerBankrupt(Player player)
    
    public BankAccount getPlayerBankAccount(Player player){
        return bankAccountList.get(player.getBankAccountID());
    }

    public ArrayList <Integer> getPropertyList (){
        return propertyList;
    }

    public ArrayList <Mortgage> getMortgageList(){
        return mortgageList;
    }

    public void buyPropertyFromBank(Player player){
        // assign player id on the space player owns
        player.addProperty();
    }

    public void sellHouseToBank(Player player){
        player.
    }

    public void sellHotelToBank(Player player){
        
    }

    public void debitAccount(Player player, int amount){
        
    }

    public void creditAccount(Player player, int amount){
        
    }

    public void addProperty(int id){
        propertyList.add(id);
    }

    public void removeProperty(int id){
        propertyList.remove(id);
    }

    public boolean hasProperty(int id){
        return propertyList.contains(id);
    }
            
}
