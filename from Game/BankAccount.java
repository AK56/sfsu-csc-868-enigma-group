package Game;

import Database.BankDatabaseController;
import java.util.ArrayList;


/**
 *
 * @author John
 * 
 * Summary: The BankAccount class keeps track of the account balance of a particular player. Each instance of
this class stores the id of the particular bank account, the bank id associated with the account, the
balance, and the player associated with the balance.
 */
public class BankAccount
{
    private static final int startingBalance = 1500;
    private int bankAccountID; // maybe given by the db?
    private int bankID;
    private int playerID;
    private int cashBalance;
    //private int networth;
    
    public static BankDatabaseController database = Database.BankDatabaseController.getInstance();

    /**
     * Default Constructor
     */
    public BankAccount(){
        
    }
    /**
     * Constructor - 3 parameters
     * @param bankAccountID id for the bankAccount
     * @param bankID        Bank id the bankAccount will be associated with
     * @param playerID      Player id of the player 
     */
    public BankAccount(int bankAccountID, int bankID, int playerID){
        this.bankAccountID = bankAccountID;
        this.bankID = bankID;
        this.playerID = playerID;
        this.cashBalance = startingBalance;
    }

    public void initializer(int bankAccountID, int bankID, int playerID, int balance){
        this.bankAccountID = bankAccountID;
        this.bankID = bankID;
        this.playerID = playerID;
        this.cashBalance = balance;
    }
    /***@return int returns the bankAccount id **/
    public int getBankAccountID(){
        return bankAccountID;
    }
    /*** @return int returns the bankId **/
    public int getBankID(){
        return bankID;
    }
    /*** @return int returns the playerId **/
    public int getPlayerID(){
        return playerID;
    }
    /*** @param income credits Player's account balance **/
    public void addToAccountBalance(int income){
        cashBalance += income;
        database.updateCashBalance(this);
    }
    /*** @param amount debits Player's account balance **/
    public void subtractFromAccountBalance(int amount){
        cashBalance -= amount;
        database.updateCashBalance(this);
    }
    /*** @return int returns cash in Player's account **/
    public int getCashBalance() {
        return cashBalance;
    }
    /*** @param cashBalance updates cashBalance of player **/
    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
        database.updateCashBalance(this);
    }
    /*** @param bankAccountID sets the bankAccount id **/
    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }
    /*** @param bankID sets bank id **/
    public void setBankID(int bankID) {
        this.bankID = bankID;
    }
    /*** @param playerID sets player id for the bank account**/
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public static int getStartingBalance() {
        return startingBalance;
    }

    
    
}