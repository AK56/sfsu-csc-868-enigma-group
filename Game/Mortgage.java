
/**
 *
 * @author John
 * 
 * Summary: The Mortgage Class keeps track of the mortgage value of a particular property. It is tied directly to
a bank id, a player id, and a property id.
 */
package Game;


public class Mortgage
{
    private int mortgageID;
    private int bankID;
    private int playerID;
    private int propertyID;
    private int mortgageAmount;
    
    public Mortgage(){
    
    }
    
    public Mortgage(int mortgageID, int bankID, int playerID, int propertyID, int mortgageAmount){
        this.mortgageID = mortgageID;
        this.bankID = bankID;
        this.playerID = playerID;
        this.propertyID = propertyID;
        this.mortgageAmount = mortgageAmount;
    }

    public void setMortgageAmount(int mortgageAmount){
        this.mortgageAmount = mortgageAmount;
    }

    public int getMortgageAmount(){
        return mortgageAmount;
    }
    
    public void setBankID(int bankID){
        this.bankID = bankID;
    }
    
    public int getBankID(){
        return bankID;
    }
    
    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    public int getPlayerID(){
        return playerID;
    }

    public void setPropertyID(int propertyID){
        this.propertyID = propertyID;
    }

    public int getPropertyID(){
        return propertyID;
    }
}
