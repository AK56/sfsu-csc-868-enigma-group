package Game;

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

    private int bankAccountID; // maybe given by the db?
    private int bankID;
    private int playerID;
    private int cashBalance;
    //private int networth;

    // empty constructor
    public BankAccount(){
        
    }
    
    public BankAccount(int bankAccountID, int bankID, int playerID){
        this.bankAccountID = bankAccountID;
        this.bankID = bankID;
        this.playerID = playerID;
    }

    public void initializer(int bankAccountID, int bankID, int playerID){
        this.bankAccountID = bankAccountID;
        this.bankID = bankID;
        this.playerID = playerID;
    }
    
    public int getBankAccountID(){
        return bankAccountID;
    }

    public int getBankID(){
        return bankID;
    }

    public int getPlayerID(){
        return playerID;
    }
    
    public void addToAccountBalance(int income){
        cashBalance += income;
    }
    
    public void subtractFromAccountBalance(int amount){
        cashBalance -= amount;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    
    
}