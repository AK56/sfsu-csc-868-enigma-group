/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Property;

import Database.UtilityDatabaseController;



/**
 *
 * @author Kenneth Robertson and NeatoTest
 */
public class Utility extends Property{
    
    public static UtilityDatabaseController database = Database.UtilityDatabaseController.getInstance();
    
    /* Constructors - 5 parameters */
    public Utility(int owner, int location, String name, int price, int game) {
        super(owner, location, name, price, game); //5 parameters
    }
    
    
    public Utility() {
        super();
    }
    
    /* Initialize method - 7 parameters */
    public void initialize (int owner, int location, String name, int price, boolean mortgage, int game) {
        super.initialize(owner, location, name, price, mortgage, game);
    }
    
    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        database.updateUtility(this);
    }
    
    @Override
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
        database.updateUtility(this);
    }
    
    // call the other calculateRent instead of this one
    @Override
    public int calculateRent(int numOwned) {    
        if (isMortgaged) return 0;
        else return -1;
    }
    
    /* calculateRent
     * If a player owns both utilities, the calculated rent becomes 10 times
     * the roll of the first dice.
     * Else the calculated rent is 4 times the roll of the first die.
     */
    public int calculateRent(int numOwned, int diceRoll) {
        
        if (isMortgaged) return 0;
        
        if(numOwned == 2) //This is where the check of the renter should be used instead of calculateRentWhenEffectedByCard()
        {
            return 10 * diceRoll;
        }
        return 4 * diceRoll;
    }
    
   
}
