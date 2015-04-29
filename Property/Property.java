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
 * @author Derek Ma
 */
public abstract class Property extends Space {

    protected int ownerID;
    protected int propertyID;
    protected int baseRent;
    protected String name;
    protected int purchasePrice;
    protected boolean isMortgaged;

    /* Constructors - 7 parameters */
    public Property(int owner, int location, int property, int base, String name, int pp) {
        super(location); // 1 parameter
        this.ownerID = owner;
        this.propertyID = property;
        this.baseRent = base;
        this.name = name;
        this.purchasePrice = pp;
        this.isMortgaged = false;
    }

    public Property() {
        super(); // 1 parameter
        this.ownerID = 0;
        this.propertyID = 0;
        this.baseRent = 0;
        this.name = "";
        this.purchasePrice = 0;
        this.isMortgaged = false;
    }

    /* Initialize method */
    public void initialize(int owner, int location, int property, int base, String name, int pp, boolean mortgage) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setPropertyID(property);
        this.setBaseRent(base);
        this.setName(name);
        this.setPurchasePrice(pp);
        this.setIsMortgaged(mortgage);
    }

    /* Getters */
    public int getOwnerID() {
        return ownerID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public int getBaseRent() {
        return baseRent;
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

    /* Setters */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
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

    /* Abstract class used to calculate rent dependent on the type of property  */
    abstract int calculateRent(/*Player Owner*/);

}
