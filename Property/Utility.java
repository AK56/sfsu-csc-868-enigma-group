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
    public Utility(int owner, int location, int property, /*int base,*/ String name, int pp) {
        super(owner, location, property, /*base,*/ name, pp); //5 parameters
        this.ownBothUtilities = false;
    }
    public Utility() {
        super();
        this.ownBothUtilities = false;
    }
    
    /* Initialize method - 7 parameters */
    public void initialize (int owner, int location, int property, /*int base,*/ String name, int pp, boolean utility, boolean mortgage) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setPropertyID(property);
        //this.setBaseRent(base);
        this.setName(name);
        this.setPurchasePrice(pp);
        this.setOwnBothUtilities(utility);
        this.setIsMortgaged(mortgage);
    }
    
    /* Getters */
    public boolean getOwnBothUtilities() {
        return ownBothUtilities;
    }

    /* Setters */
    public void setOwnBothUtilities(boolean ownBothUtilities) {
        this.ownBothUtilities = ownBothUtilities;
    }

    @Override
    /* calculateRent
     * If a player owns both utilities, the calculated rent becomes 10 times
     * the roll of the first dice.
     * Else the calculated rent is 4 times the roll of the first die.
     */
    public int calculateRent(/*Player renter*/) {
        Dice dice = new Dice();
        dice.rollDice();
       
        /*
        Possible more code to display the dice roll on the screen
        USE DICE ONE TO DISPLAY
        */
        if(ownBothUtilities) //This is where the check of the renter should be used instead of calculateRentWhenEffectedByCard()
        {
            return 10 * dice.getDieOne();
        }
        return 4 * dice.getDieOne();
    }
    
    /*  Note: This is not ideal,
        it would be better if the utility object could get a boolean
        indicating that the calculateRent method use the 10 * coefficent.
        Since the player class isn't implemented yet I'm not sure how to implement this.
    */
    public int calculateRentWhenEffectedByCard(/*Player renter*/) {
        Dice dice = new Dice();
        dice.rollDice();
        /*
        Possible more code to display the dice roll on the screen
        USE DICE ONE TO DISPLAY
        */
        return 10 * dice.getDiceValue();
    }
/*    
   public static void main(String[] args) {
        Utility utility = new Utility();
        String output;
        utility.initialize(2, 28, 45, 0, "Waterworks", 200, true, true);
        utility.setBaseRent(utility.calculateRent()); //Changes base rent
        output = "Test Case Utility Class - utility.initialize(2, 28, 45, 0, \"Waterworks\", 200, true, true)"
                + "\nOwner: " + utility.getOwnerID()
                + "\nSpace Id: " + utility.getSpaceID()
                + "\nProperty Id: " + utility.getPropertyID()
                + "\nBase Rent: " + utility.getBaseRent()
                + "\nName Of Property: " + utility.getName()
                + "\nPurchase Price: " + utility.getPurchasePrice()
                + "\nOwn both utilities: ";
        output += ((utility.getOwnBothUtilities()) ? "Yes" : "No");
        output += "\nMortgaged: ";
        output += ((utility.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);

        Utility utility2 = new Utility(4, 18, 5, 0, "Electric", 250);
        utility2.setBaseRent(utility2.calculateRent()); //Changes base rent
        output = "\nTest Case Utility Class - utility2 constructor (4, 18, 5, 0, \"Electric\", 250)"
                + "\nOwner: " + utility2.getOwnerID()
                + "\nSpace Id: " + utility2.getSpaceID()
                + "\nProperty Id: " + utility2.getPropertyID()
                + "\nBase Rent: " + utility2.getBaseRent()
                + "\nName Of Property: " + utility2.getName()
                + "\nPurchase Price: " + utility2.getPurchasePrice()
                + "\nOwn both utilities: ";
        output += ((utility2.getOwnBothUtilities()) ? "Yes" : "No");
        output += "\nMortgaged: ";
        output += ((utility2.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);
        
        System.out.println("\n Calculating Rent for utility and utility2");
        int roll1, roll2;
        for(int i = 0; i < 100; i++) {  
            roll1 = utility.calculateRent();
            roll2 = utility2.calculateRent();
            
            output = "Turn " + i + " - Utility Rent 1 and 2: " + roll1 + ", " + roll2;
            System.out.println(output);
        }
    }    
   */
}
