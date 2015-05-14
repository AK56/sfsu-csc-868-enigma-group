/*
 * The Railroad class extends Property, inheriting all parameters from it.
 * This class specifically calculates the rent for railroad properties.
 */
package Property;

/**
 *
 * @author Kenneth Robertson and Derek Ma
 */
public class Railroad extends Property {

    private static int baseRent = 25;
    
    /* Constructors - 5 parameters */
    public Railroad(int owner, int location, String name, int price) {
        super(owner, location, name, price); // 5 parameters
    }

    public Railroad() {
        super(); // 7 parameters
    }

    /* Initialize method - 8 parameters */
    public void initialize(int owner, int location, int base, String name, int price, boolean mortgaged) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setBaseRent(base);
        this.setName(name);
        this.setPurchasePrice(price);
        this.setIsMortgaged(mortgaged);
        this.setMortgageAmount(price/2);
    }

    /* Getters */

    public static int getBaseRent() {
        return baseRent;
    }
    

    /* Setters */

    public static void setBaseRent(int baseRent) {
        Railroad.baseRent = baseRent;
    }
    

    @Override
    /* calculateRent
     * For every railroad a particular player owns, the base rent doubles
     */
    public int calculateRent(int numOwned) {        
        int rent = baseRent;
        /* Presumably the Game class will set the concurrent number of railroads
         owned by a player.
         */
        if (isMortgaged) return 0;
        
        for (int i = 1; i < numOwned; i++) {
            rent *= 2;
        }
        return rent;
    }
 
    
}
