package Property;

import Database.UtilityDatabaseController;



/**
 * The utility class inherits from the Property class and is used to store
 * properties like the Electric Company which have rents based on dice rolls.
 * 
 * @author Kenneth Robertson and NeatoTest
 */
public class Utility extends Property{
    
    public static UtilityDatabaseController database = Database.UtilityDatabaseController.getInstance();
    
    /**
     * Constructors - 5 parameters
     *
     * @param owner	int stores the playerID that owns the property
     * @param location	int stores the space on the board where the property is located
     * 
     * @param name	String that holds the name of the property
     * @param price	int stores the banks price for the property
     * @param game	int stores the gameID for when multiple games are running at once
     * 
     */
    public Utility(int owner, int location, String name, int price, int game) {
        super(owner, location, name, price, game); //5 parameters
    }
    
    /**
     * Default constructor
     */
    public Utility() {
        super();
    }
    
    /**
     * Initialize method - 6 parameters
     *
     * @param owner	int stores the playerID that owns the property
     * @param location	int stores the space on the board where the property is located
     * @param name	String that holds the name of the property
     * @param price	int stores the banks price for the property
     * @param mortgaged boolean stores whether the railroad is mortgaged
     * @param game	int stores the gameID for when multiple games are running at once
     * 
     */
    public void initialize (int owner, int location, String name, int price, boolean mortgage, int game) {
        super.initialize(owner, location, name, price, mortgage, game);
    }
    
    //These are override methods that have javadoc in the parent class
    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        database.updateUtility(this);
    }
    
    //These are override methods that have javadoc in the parent class
    @Override
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
        database.updateUtility(this);
    }
    
    //These are override methods that have javadoc in the parent class
    // call the other calculateRent instead of this one
    @Override
    public int calculateRent(int numOwned) {    
        if (isMortgaged) return 0;
        else return -1;
    }
    

    /**
     *  If a player owns both utilities, the calculated rent becomes 10 times
     *  the roll of the first dice.  Else the calculated rent is 4 times 
     *  the roll of the first die.
     * 
     * @param numOwned  Variable not used here, should be removed.
     * @param diceRoll  Variable gives the value of the dice roll.
     * @return      returns an int representing the rent owed to the property owner     
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
