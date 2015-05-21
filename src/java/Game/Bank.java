
package Game;

import Database.BankDatabaseController;
import User.Player;

import java.util.ArrayList;


/**
 * The bank owns all the properties, so whenever a player wants to buy or mortgage a property, he
 * needs to call the bank class. The bank gives starter money to all the players in the beginning of the
 * game. It stores all the bank accounts of all the players. It also owns the property cards that will be
 * given to the player who wishes to buy a property.
 * 
 * @author John Santos
 */
public class Bank
{
    private int bankID;
    private ArrayList <BankAccount> bankAccountList;  // list of players' bank accounts
    // default value for number starter number of houses = 44
    private final static int starterNumberHouses = 44;
    private int numHouses;
    
    public static BankDatabaseController database = Database.BankDatabaseController.getInstance();

    /**
     * Default Constructor
     */
    public Bank(){   
        bankAccountList = new ArrayList<BankAccount>();        
    }
    
    /**
     * Initialize Bank
     * @param id    sets the bank id of the Bank for Game
     * @param numHouses sets the number of houses by Bank
     * @param accountList sets the list of Bank accounts
     */
    public void initialize(int id, int numHouses, ArrayList<BankAccount> accountList){
        this.bankAccountList = accountList;
        this.numHouses = numHouses;
        this.bankID = id;
    }
    
    /*** 
     * gets the BankAccount of the player 
     * @param player player in the Game 
     * @return BankAccount of the player 
     **/
    public BankAccount getPlayerBankAccount(Player player){
        /*iterate over bankAccount list*/
        for (BankAccount account : bankAccountList){
            if(account.getPlayerID() == player.getPlayerID()){
                return account;
            }
        }
        
        return null;
    }
    
    
    /**
     * The Player sell a house to the Bank and gets half the house value deposited
     * into the Player's BankAccount. The database is then updated.
     * 
     * @param player  player who sell the house to bank
     * @param housePurchasePrice  original purchase price of the house
     */
    public void sellHouseToBank(Player player, int housePurchasePrice){
        int amount = housePurchasePrice/2;
        numHouses++;    
        addToAccount(player, amount);
        database.updateBankNumberHouses(bankID, numHouses);
    }

    /**
     * The Player sell a house to the Bank and gets half the house value deposited
     * into the Player's BankAccount. The database is then updated.
     * 
     * @param player    player who buys the house from bank
     * @param housePurchasePrice purchase price of the house
     */
    public void buyHouseFromBank(Player player, int housePurchasePrice){
        numHouses--;    
        subtractFromAccount(player, housePurchasePrice);
        database.updateBankNumberHouses(bankID, numHouses);
    }

    /**
     * Removes money from the Player's bank account.
     * 
     * @param player whose balance to be subtracted
     * @param amount amount to be subtracted
     */
    public void subtractFromAccount(Player player, int amount){
        BankAccount account = getPlayerBankAccount(player);
        
        if(account != null){
            account.subtractFromAccountBalance(amount);
        }
    }

    /**
     * Adds money to the Player's bank account
     * @param player whose balance to be added
     * @param amount amount to add
     */
    public void addToAccount(Player player, int amount){
        BankAccount account = getPlayerBankAccount(player);
        
        if(account != null){
            account.addToAccountBalance(amount);
        }
    } 
    
    /**
     * Tests if the Player owes more money then they have in their bank account.
     * If they do not have enough cash they are bankrupt.
     * 
     * @param player    for whom bankruptcy check is done
     * @param amountOwed the amount the player owe to other players or Bank
     * @return returns if the player is bankrupt or not
     */
    public boolean isPlayerBankrupt(Player player, int amountOwed){
        BankAccount account = getPlayerBankAccount(player);
        boolean bankrupt = false;
        
        if(account != null){
            int balance = account.getCashBalance();
            bankrupt = (balance < amountOwed);
        }
        
        return bankrupt;
    }
    
    /*** 
     * returns bankId of the Bank
     * @return returns bankId of the Bank
     **/
    public int getBankID() {
        return bankID;
    }
    
    /*** 
     * sets the bankId
     * 
     * @param bankID sets the bankId
     **/
    public void setBankID(int bankID) {
        this.bankID = bankID;
    }
    
    /*** 
     * returns list of all BankAccounts that the bank has
     * @return returns list of BankAccounts 
     **/
    public ArrayList<BankAccount> getBankAccountList() {
        return bankAccountList;
    }
    
    /*** 
     * Sets the list of bank accounts for a bank at the start of a game
     * @param bankAccountList bankAccount list
     **/
    public void setBankAccountList(ArrayList<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
    
    /*** 
     * returns the number of houses owned by Bank
     * @return returns the number of houses owned by Bank
     **/
    public int getNumHouses() {
        return numHouses;
    }
    
    /*** 
     * sets the number of houses owned by bank
     * @param numHouses  sets the number of houses owned by bank
     **/
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
        database.updateBankNumberHouses(bankID, numHouses);
    }
    
    /**
     * the default number of houses that a bank should own when a new game is started
     * @return int the default number of houses that a bank should own when a new game is started
     */
    public static int getStarterNumberHouses() {
        return starterNumberHouses;
    }
               

}
