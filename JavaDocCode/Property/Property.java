package Property;

import Game.Space;

/**
 * The Property Class is an abstract class that inherits from Space This class
 * keeps track of all properties for a property. An abstract method
 * calculateRent is used for child classes to calculate the rent dependent on on
 * the type of property.
 *
 * @author Kenneth Robertson and Derek Ma
 */
public abstract class Property extends Space {

    protected int ownerID;
    protected String name;
    protected int purchasePrice;
    protected boolean isMortgaged;
    protected int mortgageAmount;
    protected int gameID;

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
    public Property(int owner, int location, String name, int price, int game) {
        super(location); // 1 parameter
        this.ownerID = owner;
        this.name = name;
        this.purchasePrice = price;
        this.isMortgaged = false;
        this.mortgageAmount = price / 2;
        this.gameID = game;
    }
    
    /**
     * Default constructor
     */
    public Property() {
        super(); // 1 parameter
        this.ownerID = 0;
        this.name = "";
        this.purchasePrice = 0;
        this.isMortgaged = false;
        this.mortgageAmount = 0;
    }

    /**
     * Initialize method
     *
     * @param owner	int stores the playerID that owns the property
     * @param location	int stores the space on the board where the property is located
     * 
     * @param name	String that holds the name of the property
     * @param price	int stores the banks price for the property
     * @param game	int stores the gameID for when multiple games are running at once
     */
    public void initialize(int owner, int location, String name, int price, boolean mortgage, int gameID) {
        this.ownerID = owner;
        this.isMortgaged = mortgage;
        this.purchasePrice = price;
        this.mortgageAmount = price / 2;
        this.spaceID = location;
        this.gameID = gameID;
        this.name = name;
    }

    /**
     * @return  returns the player ID of the owner of the property
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * @return	returns the property name
     */
    public String getName() {
        return name;
    }

    /**
     * @return	returns banks purchase price
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @return	returns true if the property is mortgaged otherwise returns false
     */
    public boolean getIsMortgaged() {
        return isMortgaged;
    }

    /**
     * @return	returns the amount a player gets when they mortgage a property
     */
    public int getMortgageAmount() {
        return mortgageAmount;
    }

    /**
     * @return  returns the gameID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * @param ownerID  Sets the player ID of the owner of the property
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @param name  Sets the name of the property
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param purchasePrice  Sets the purchasePrice of the property
     */
    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * @param isMortgaged  Makes the property mortgaged or un-mortgaged.
     */
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    /**
     * @param mortgageAmount  Sets the mortgage amount the player gets from the bank
     */
    public void setMortgageAmount(int mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    /**
     * @param gameID  Sets the gameID
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Abstract method that calculates the amount the player who lands on the property has to pay the property owner.
     * 
     * @param numOwned  Variable is used in some of the subclasses
     * @return      returns an int representing the rent owed to the property owner     
     */
    public abstract int calculateRent(int numOwned);

}
