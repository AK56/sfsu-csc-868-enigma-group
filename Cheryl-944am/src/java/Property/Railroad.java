/*
 * The Railroad class extends Property, inheriting all parameters from it.
 * This class specifically calculates the rent for railroad properties.
 */
package Property;

import Database.RailroadDatabaseController;

/**
 *
 * @author Kenneth Robertson and Derek Ma
 */
public class Railroad extends Property {

    private static final int baseRent = 25;
    public static RailroadDatabaseController database = Database.RailroadDatabaseController.getInstance();
    
    /* Constructors - 5 parameters */
    public Railroad(int owner, int location, String name, int price, int game) {
        super(owner, location, name, price, game); // 5 parameters
    }

    public Railroad() {
        super(); // 7 parameters
    }

    /* Initialize method - 7 parameters */
    public void initialize(int owner, int location, String name, int price, boolean mortgaged, int game) {
        super.initialize(owner, location, name, price, mortgaged, game);
    }

    /* Getters */

    public static int getBaseRent() {
        return baseRent;
    }
   
    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        database.updateRailroad(this);
    }
    
    @Override
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
        database.updateRailroad(this);
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
