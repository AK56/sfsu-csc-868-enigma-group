/*
 * The Railroad class extends Property, inheriting all parameters from it.
 * This class specifically calculates the rent for railroad properties.
 */
package Property;

/**
 *
 * @author Derek Ma
 */
public class Railroad extends Property {

    private int numOwned; // Number of Railroads Properties owned by a player

    /* Constructors - 8 parameters */
    public Railroad(int owner, int location, int property, int base, String name, int pp) {
        super(owner, location, property, base, name, pp); // 7 parameters
        this.numOwned = 0;
    }

    public Railroad() {
        super(); // 7 parameters
        this.numOwned = 0;
    }

    /* Initialize method - 8 parameters */
    public void initialize(int owner, int location, int property, int base, String name, int pp, int numberOwned, boolean mortgaged) {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setPropertyID(property);
        this.setBaseRent(base);
        this.setName(name);
        this.setPurchasePrice(pp);
        this.setNumOwned(numberOwned);
        this.setIsMortgaged(mortgaged);
    }

    /* Getters */
    public int getNumOwned() {
        return numOwned;
    }

    /* Setters */
    public void setNumOwned(int numOwned) {
        this.numOwned = numOwned;
    }

    @Override
    /* calculateRent
     * For every railroad a particular player owns, the base rent doubles
     */
    public int calculateRent(/*Player Owner*/) {
        int rent = baseRent;
        /* Presumably the Game class will set the concurrent number of railroads
         owned by a player.
         */
        for (int i = 1; i < numOwned; i++) {
            rent *= 2;
        }
        return rent;
    }
    
/*
    public static void main(String[] args) {
        Railroad rail = new Railroad();
        String output;
        rail.initialize(1, 12, 45, 25, "Choochoo Railroad", 100, 3, true);
        output = "Test Case Railroad Class - rail.initialize(1, 12, 45, 25, \"Choochoo Railroad\", 100, 3, true)"
                + "\nOwner: " + rail.getOwnerID()
                + "\nSpace Id: " + rail.getSpaceID()
                + "\nProperty Id: " + rail.getPropertyID()
                + "\nBase Rent: " + rail.getBaseRent()
                + "\nName Of Property: " + rail.getName()
                + "\nPurchase Price: " + rail.getPurchasePrice()
                + "\nNumber Of Railroads Owned: " + rail.getNumOwned()
                + "\nMortgaged: ";
        output += ((rail.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);

        Railroad rail2 = new Railroad(2, 35, 123, 45, "Bunchaos Railroad", 200);
        output = "\nTest Case Railroad Class - rail2 constructor (2, 35, 123, 45, \"Bunchaos Railroad\", 200)"
                + "\nOwner: " + rail2.getOwnerID()
                + "\nSpace Id: " + rail2.getSpaceID()
                + "\nProperty Id: " + rail2.getPropertyID()
                + "\nBase Rent: " + rail2.getBaseRent()
                + "\nName Of Property: " + rail2.getName()
                + "\nPurchase Price: " + rail2.getPurchasePrice()
                + "\nNumber Of Railroads Owned: " + rail2.getNumOwned()
                + "\nMortgaged: ";
        output += ((rail2.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);

        System.out.println("\n Calculating Rent for rail");
        for (int i = 1; i < 5; i++) {
            rail.setNumOwned(i);
            output = "Output for num of railroads - " + rail.getNumOwned()
                    + " : " + rail.calculateRent();
            System.out.println(output);
        }
        System.out.println("\n Calculating Rent for rail2");
        for (int i = 1; i < 5; i++) {
            rail2.setNumOwned(i);
            output = "Output for num of railroads - " + rail2.getNumOwned()
                    + " : " + rail2.calculateRent();
            System.out.println(output);
        }
    }
*/
}
