/*
 * The Property Class is an abstract class that inherits from Space
 * This class keeps track of all properties for a property.  An abstract method
 * calculateRent is used for child classes to calculate the rent dependent on
 * on the type of property.
 */

package Property;
import Game.Space;

/**
 *
 * @author Kenneth Robertson and Derek Ma
 */
public abstract class Property extends Space {

    protected int ownerID;
    protected String name;
    protected int purchasePrice;
    protected boolean isMortgaged;
    protected int mortgageAmount;

    /* Constructors - 6 parameters */
    public Property(int owner, int location, String name, int price) {
        super(location); // 1 parameter
        this.ownerID = owner;
        this.name = name;
        this.purchasePrice = price;
        this.isMortgaged = false;
        this.mortgageAmount = price/2;
    }

    public Property() {
        super(); // 1 parameter
        this.ownerID = 0;
        this.name = "";
        this.purchasePrice = 0;
        this.isMortgaged = false;
        this.mortgageAmount = 0;
    }

    /* Initialize method */
    public void initialize(int owner, int location, String name, int price, boolean mortgage) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setName(name);
        this.setPurchasePrice(price);
        this.setIsMortgaged(mortgage);
        this.setMortgageAmount(price/2);
    }

    /* Getters */
    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public boolean getIsMortgaged() {
        return isMortgaged;
    }

    public int getMortgageAmount() {
        return mortgageAmount;
    }
    
    

    /* Setters */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    public void setMortgageAmount(int mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    
    /* Abstract class used to calculate rent dependent on the type of property  */
    public abstract int calculateRent(int numOwned);


}
