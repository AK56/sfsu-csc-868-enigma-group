package Property;

import Database.RailroadDatabaseController;

/**
 * The Railroad class extends Property, inheriting all parameters from it.
 * This class specifically calculates the rent for railroad properties.
 * 
 * @author Kenneth Robertson and Derek Ma
 */
public class Railroad extends Property {

    private static final int baseRent = 25;
    public static RailroadDatabaseController database = Database.RailroadDatabaseController.getInstance();
    

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
    public Railroad(int owner, int location, String name, int price, int game) {
        super(owner, location, name, price, game); // 5 parameters
    }

    /**
     * Default constructor
     */
    public Railroad() {
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
    public void initialize(int owner, int location, String name, int price, boolean mortgaged, int game) {
        super.initialize(owner, location, name, price, mortgaged, game);
    }

    /**
     * @return  returns int represent the baseRent if no other railroads are owned by a player.
     */
    public static int getBaseRent() {
        return baseRent;
    }
   
    //These are override methods that have javadoc in the parent class
    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        database.updateRailroad(this);
    }
    
    //These are override methods that have javadoc in the parent class
    @Override
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
        database.updateRailroad(this);
    }
    
    //These are override methods that have javadoc in the parent class
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
