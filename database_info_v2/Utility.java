/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Property;

import Game.Dice;

/**
 *
 * @author Kenneth Robertson and NeatoTest
 */
public class Utility extends Property{
    private boolean ownBothUtilities;
    
    /* Constructors - 5 parameters */
    public Utility(int owner, int location, String name, int price) {
        super(owner, location, name, price); //5 parameters
        this.ownBothUtilities = false;
    }
    
    
    public Utility() {
        super();
        this.ownBothUtilities = false;
    }
    
    /* Initialize method - 7 parameters */
    public void initialize (int owner, int location, String name, int price, boolean utility, boolean mortgage) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setName(name);
        this.setPurchasePrice(price);
        this.setOwnBothUtilities(utility);
        this.setIsMortgaged(mortgage);
        this.setMortgageAmount(price/2);
    }
    
    /* Getters */
    public boolean getOwnBothUtilities() {
        return ownBothUtilities;
    }

    /* Setters */
    public void setOwnBothUtilities(boolean ownBothUtilities) {
        this.ownBothUtilities = ownBothUtilities;
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
