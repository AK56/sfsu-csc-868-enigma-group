package Property;

import java.util.ArrayList;
import java.util.Arrays;
import Database.RealEstateDatabaseController;

/**
 * The RealEstate class extends Property, inheriting all parameters from it.
 * This class specifically keeps track of colored properties that is used
 * for holding the number of houses on a particular colored property and 
 * for calculating the rent for these types of properties.
 * 
 * @author Kenneth Robertson and Derek Ma
 */
public class RealEstate extends Property {

    private String color;
    private int numberOfHouses;
    private int costOfAHouse;
    private int numberForMonopoly; 
    private ArrayList<Integer> rents;
    
    public static RealEstateDatabaseController database = Database.RealEstateDatabaseController.getInstance();

    /**
     * Constructors - 11 parameters
     *
     * @param owner             int stores the playerID that owns the property
     * @param location          int stores the space on the board where the property is located
     * @param name              String that holds the name of the property
     * @param price             int stores the banks price for the property
     * @param costOfAHouse      int stores the cost to a player to buy a house on the property
     * @param hasMortgage       boolean stores the mortgage status of the real estate
     * @param color             String that stores the color of the real estate
     * @param numberOfHouses    int stores the number of houses on the property
     * @param numberForMonopoly int stores the number of the property color needed to have a real estate monopoly
     * @param rents             ArrayList of Integer stores the rent list for different numbers of houses
     * @param gameID            int stores the gameID for when multiple games are running at once
     * 
     */
    public RealEstate(int owner, int location, String name, int price, int costOfAHouse, boolean hasMortgage,
            String color, int numberOfHouses, int numberForMonopoly, ArrayList<Integer> rents, int gameID) 
    {
        super(owner, location, name, price, gameID); // 7 parameters
        this.color = color;
        this.numberOfHouses = numberOfHouses;
        this.numberForMonopoly = numberForMonopoly;
        this.costOfAHouse = costOfAHouse;
        this.rents = rents;
        this.isMortgaged = hasMortgage;
    }
    
    /**
     * Default constructor
     */
    public RealEstate() {
        super(); // 7 parameters
        this.numberOfHouses = 0;
        this.rents = new ArrayList<Integer>();
    }

    /**
     * Initialize method - 11 parameters
     *
     * @param owner             int stores the playerID that owns the property
     * @param location          int stores the space on the board where the property is located
     * @param name              String that holds the name of the property
     * @param price             int stores the banks price for the property
     * @param costOfAHouse      int stores the cost to a player to buy a house on the property
     * @param hasMortgage       boolean stores the mortgage status of the real estate
     * @param color             String that stores the color of the real estate
     * @param numberOfHouses    int stores the number of houses on the property
     * @param numberForMonopoly int stores the number of the property color needed to have a real estate monopoly
     * @param rents             ArrayList of Integer stores the rent list for different numbers of houses
     * @param gameID              int stores the gameID for when multiple games are running at once
     * 
     */
    public void initialize(int owner, int location, String name, int price, int costOfAHouse, boolean hasMortgage,
            String color, int numberOfHouses, int numberForMonopoly, ArrayList<Integer> rents, int gameID) 
    {
        super.initialize(owner, location, name, price, hasMortgage, gameID);
        this.setColor(color);
        this.setNumberOfHouses(numberOfHouses);
        this.setNumberForMonopoly(numberForMonopoly);
        this.setRents(rents);
    }

    
    
    /**
     * @return  returns color of the real estate property
     */
    public String getColor() {
        return color;
    }
    
    /**
     * @return  returns number of houses on the property
     */
    public int getNumberOfHouses() {
        return numberOfHouses;
    }
    
    /**
     * @return  returns number of the property color needed to have a real estate monopoly
     */
    public int getNumberForMonopoly() {
        return numberForMonopoly;
    }
    
    /**
     * @return  returns rent list for different numbers of houses
     */
    public ArrayList<Integer> getRents() {
        return rents;
    }

    /**
     * @return  returns the cost to a player to buy a house on the property
     */
    public int getCostOfAHouse() {
        return costOfAHouse;
    }
    
    //These are override methods that have javadoc in the parent class
    @Override
    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
        database.updateRealEstate(this);
    }
    
    //These are override methods that have javadoc in the parent class
    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        database.updateRealEstate(this);
    }
    
    /**
     * @param color  sets color of the real estate property
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * @param numberOfHouses    sets number of houses on the property
     */
    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
        database.updateRealEstate(this);
    }
    
    /**
     * @param numberOfColorNeededForMonopoly    sets number of the property color needed to have a real estate monopoly
     */
    public void setNumberForMonopoly(int numberOfColorNeededForMonopoly) {
        this.numberForMonopoly = numberOfColorNeededForMonopoly;
    }
    
    /**
     * @param rents    sets rent list for different numbers of houses
     */
    public void setRents(ArrayList<Integer> rents) {
        this.rents = rents;
    }

    /**
     * @param costOfAHouse  sets the cost to a player to buy a house on the property
     */
    public void setCostOfAHouse(int costOfAHouse) {
        this.costOfAHouse = costOfAHouse;
    }
    
        

   
    // TO BE RE DONE AS A CASE STATMENT ***************************
    //*******************************************************************
    
    
    
    //These are override methods that have javadoc in the parent class
    @Override
    /* calculateRent
     * If the player has a colored set monopoly for the color
     * of this set, the rent will be doubled.  With each additional
     * house on the property, the total rent will be the following:
     * 
     * rent for monopolys is either the rent value based on houses on property
     * or two times the normal rent without a monopoly.
     * Game class determines if a colored monopoly exists 
     */
    public int calculateRent(int numOwned) {
        
        int rent = rents.get(0);
        
        if (isMortgaged) return 0;
                
        if (numOwned == numberForMonopoly) {            
            if(numberOfHouses == 0)
                return 2*rents.get(0);
            return rents.get(numberOfHouses);
        }
        else {            
            rent = rents.get(numberOfHouses);
        }
        
        return rent;
    }
}

