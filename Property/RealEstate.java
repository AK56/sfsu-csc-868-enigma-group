/*
 * The RealEstate class extends Property, inheriting all parameters from it.
 * This class specifically keeps track of colored properties that is used
 * for holding the number of houses on a particular colored property and 
 * for calculating the rent for these types of properties.
 */
package Property;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kenneth Robertson and Derek Ma
 */
public class RealEstate extends Property {

    private String color;
    private int numberOfHouses;
    private boolean hasColorSetMonopoly;
    private int numberOfColorNeededForMonopoly; //This might not be the best place to store this variable
    private ArrayList<Integer> rents;

    /* Constructors - 11 parameters */
    public RealEstate(int owner, int location, int property, /*int base,*/ String name, int pp, String color, int numberOfHouses, int neededForMonopoly, ArrayList<Integer> rents) 
    {
        super(owner, location, property, /*base,*/ name, pp); // 7 parameters
        this.color = color;
        this.numberOfHouses = numberOfHouses;
        this.hasColorSetMonopoly = false;
        this.numberOfColorNeededForMonopoly = neededForMonopoly;
        this.rents = rents;
    }
    public RealEstate() {
        super(); // 7 parameters
        this.color = "";
        this.numberOfHouses = 0;
        this.hasColorSetMonopoly = false;
        this.rents = new ArrayList<Integer>();
    }

    /* Initialize method */
    public void initialize(int owner, int location, int property, /*int base,*/ String name, int pp, String color, int numberOfHouses, boolean colorSet, boolean mortgage, int needForMonopoly, ArrayList<Integer> rents) 
    {
        this.setOwnerID(owner);
        this.setSpaceID(location);
        this.setPropertyID(property);
        //this.setBaseRent(base);
        this.setName(name);
        this.setPurchasePrice(pp);
        this.setColor(color);
        this.setNumberOfHouses(numberOfHouses);
        this.setHasColorSetMonopoly(colorSet);
        this.setIsMortgaged(mortgage);
        this.setNumberOfColorNeededForMonopoly(neededForMonopoly);
        this.setRents(rents);
    }

    /* Getters */
    public String getColor() {
        return color;
    }
    public int getNumberOfHouses() {
        return numberOfHouses;
    }
    public boolean getHasColorSetMonopoly() {
        return hasColorSetMonopoly;
    }
    public int getNumberOfColorNeededForMonopoly() {
        return numberOfColorNeededForMonopoly;
    }
    public ArrayList<Integer> getRents() {
        return rents;
    }
    
    

    /* Setters */
    public void setColor(String color) {
        this.color = color;
    }
    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
    }
    public void setHasColorSetMonopoly(boolean hasColorSetMonopoly) {
        this.hasColorSetMonopoly = hasColorSetMonopoly;
    }
    public void setNumberOfColorNeededForMonopoly(int numberOfColorNeededForMonopoly) {
        this.numberOfColorNeededForMonopoly = numberOfColorNeededForMonopoly;
    }
    public void setRents(ArrayList<Integer> rents) {
        this.rents = rents;
    }
    

   

    @Override
    /* calculateRent
     * If the player has a colored set monopoly for the color
     * of this set, the rent will be doubled.  With each additional
     * house on the property, the total rent will be the following:
     * 
     * rent for monopolys is either the rent value based on houses on property
     * or two times the normal rent without a monopoly.
     * 
     */
    public int calculateRent(/*Player Owner*/) {
        /* Game class determines if a colored monopoly exists */
        if (hasColorSetMonopoly) {
            if(this.numberOfHouses == 0)
                return 2*rents[0];
            return rents[this.numberOfHouses];
        }
        return rents[0];
    }
   /* 
    public static void main(String[] args) {
        RealEstate property = new RealEstate();
        String output;
        property.initialize(3, 1, 12, 48, "Pook Place", 250, "blue", 4, true, true);
        output = "Test Case RealEstate Class - property.initialize(3, 1, 12, 125, \"Pook Place\", 16, 250, \"blue\", 4, true, true)"
                + "\nPlayer: " + property.getOwnerID()
                + "\nSpace Id: " + property.getSpaceID()
                + "\nProperty Id: " + property.getPropertyID()
                + "\nBase Rent: " + property.getBaseRent()
                + "\nName Of Property: " + property.getName()
                + "\nPurchase Price: " + property.getPurchasePrice()
                + "\nColor of Property: " + property.getColor()
                + "\nNumber of Houses: " + property.getNumberOfHouses()
                + "\nMonopoly on Color: ";
        output += ((property.getHasColorSetMonopoly()) ? "Yes" : "No");
        output += "\nMortgaged: ";
        output += ((property.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);

        RealEstate property2 = new RealEstate(4, 3, 57, 211, "Chump Ave", 432, "green", 3);
        output = "\nTest Case RealEstate Class - property2 constructor (3, 57, 211, \"Chump Ave\", 15, 432, \"green\", 3)"
                + "\nPlayer: " + property2.getOwnerID()
                + "\nSpace Id: " + property2.getSpaceID()
                + "\nProperty Id: " + property2.getPropertyID()
                + "\nBase Rent: " + property2.getBaseRent()
                + "\nName Of Property: " + property2.getName()
                + "\nPurchase Price: " + property2.getPurchasePrice()
                + "\nColor of Property: " + property2.getColor()
                + "\nNumber of Houses: " + property2.getNumberOfHouses()
                + "\nMonopoly on Color: ";
        output += ((property2.getHasColorSetMonopoly()) ? "Yes" : "No");
        output += "\nMortgaged: ";
        output += ((property2.getIsMortgaged()) ? "Yes" : "No");
        System.out.println(output);
        
        System.out.println("\n Calculating Rent for property (HasColorMonopoly = true)");
        for (int i = 0; i < 6; i++) {
            property.setNumberOfHouses(i);
            output = "Output for num of houses - " + property.getNumberOfHouses()
                    + " : " + property.calculateRent();
            System.out.println(output);
        }
        
        property.setHasColorSetMonopoly(false);
        System.out.println("\n Calculating Rent for property (HasColorMonopoly = false)");
        for (int i = 0; i < 6; i++) {
            property.setNumberOfHouses(i);
            output = "Output for num of houses - " + property.getNumberOfHouses()
                    + " : " + property.calculateRent();
            System.out.println(output);
        }
    }
   */
}
