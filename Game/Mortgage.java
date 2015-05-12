
/**
 *
 * @author John
 * 
 * Summary: The Mortgage Class keeps track of the mortgage value of a particular property. It is tied directly to
a bank id, a player id, and a property id.
 */



public class Mortgage
{
    private int mortgageID;
    private int bankID;
    private int playerID;
    private int propertyID;
    private int mortgageAmount;
    
    public Mortgage(){
    
    }

    public void setMortgageAmount(Property property){
        // how much is the mortgage
        property.getPropertyID();
    }

    public int getMortgageAmount(){
        return mortgageAmount;
    }
}
