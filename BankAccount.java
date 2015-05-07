
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
    private int currentBalance;
    private ArrayList<Integer> propertyList; // list of property ids

    // empty constructor
    public BankAccount(){
        
    }
    
    public BankAccount(int bankAccountID, int bankID, int playerID){
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

    public int getCurrentBalance(){
        return currentBalance;
    }

    public void setCurrentBalance(Player player, Property property){
        propertyList = player.getPropertyList();
        for (Integer element : propertyList) {
            currentBalance += property.getPurchasePrice(element);
        }
        
    }
}
