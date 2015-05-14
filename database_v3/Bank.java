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

import Database.BankDatabaseController;
import User.Player;

import java.util.ArrayList;



public class Bank
{
    private int bankID;
    private ArrayList <BankAccount> bankAccountList;  // list of players' bank accounts
    // default value for number starter number of houses = 44
    private final static int starterNumberHouses = 44;
    private int numHouses;
    private BankDatabaseController database = Database.BankDatabaseController.getInstance();

    
    public Bank(){   
        bankAccountList = new ArrayList<BankAccount>();        
    }
    
    public void initialize(int id, int numHouses, ArrayList<BankAccount> accountList){
        this.bankAccountList = accountList;
        this.numHouses = numHouses;
        this.bankID = id;
    }
    
    public BankAccount getPlayerBankAccount(Player player){
        /*iterate over bankAccount list*/
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
        database.updateBankNumberHouses(bankID, numHouses);
    }

    
    public void buyHouseFromBank(Player player, int housePurchasePrice){
        numHouses--;    
        subtractFromAccount(player, housePurchasePrice);
        database.updateBankNumberHouses(bankID, numHouses);
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
            bankrupt = (balance < amountOwed);
        }
        
        return bankrupt;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public ArrayList<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(ArrayList<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
        database.updateBankNumberHouses(bankID, numHouses);
    }

    public static int getStarterNumberHouses() {
        return starterNumberHouses;
    }
               

}
