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

import java.util.ArrayList;
import java.util.List;
import Game.Player;
import Property.Property;


public class Bank
{
    private int bankID;
    private List <BankAccount> bankAccountList;  // list of players' bank accounts
    // default value for number starter number of houses = 44
    private int numHouses = 44;

    
    public Bank(){        
    }
    
    
    public BankAccount getPlayerBankAccount(Player player){

        for (BankAccount account : bankAccountList){
            if(account.getPlayerID() == player.getPlayerID()){
                return account;
            }
        }
        
        return null;
    }

    
    public void sellHouseToBank(Player player, int housePurchasePrice){
        int amount = housePurchasePrice/2;
        numHouses++;    
        addToAccount(player, amount);
    }


    public void subtractFromAccount(Player player, int amount){
        BankAccount account = getPlayerBankAccount(player);
        
        if(account != null){
            account.subtractFromAccountBalance(amount);
        }
    }

    
    public void addToAccount(Player player, int amount){
        BankAccount account = getPlayerBankAccount(player);
        
        if(account != null){
            account.addToAccountBalance(amount);
        }
    } 
    
    
    public boolean isPlayerBankrupt(Player player, int amountOwed){
        BankAccount account = getPlayerBankAccount(player);
        boolean bankrupt = false;
        
        if(account != null){
            int balance = account.getCashBalance();
            bankrupt = (balance < amountOwed) ? true : false;
        }
        
        return bankrupt;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }
               

}
