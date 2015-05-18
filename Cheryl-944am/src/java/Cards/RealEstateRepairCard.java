/*
 * This class is for the cards that require the player who receives it to pay a variable amount 
 * based on the number of houses and hotels that player owns.
 * 
 */
package Cards;
import Game.*;
import Property.Property;
/**
 *
 * @author Kenneth Robertson
 */
/**
public class RealEstateRepairCard extends Card
{
    private int costPerHouse;
    private int costPerHotel;

    public RealEstateRepairCard() {
    }

    public RealEstateRepairCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, int costPerHouse, int costPerHotel) {
        super(cardID, cardDescription, cardDrawer, cardStackType);
        this.costPerHouse = costPerHouse;
        this.costPerHotel = costPerHotel;
    }

    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, int costPerHouse, int costPerHotel)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setCostPerHouse(costPerHouse);
        this.setCostPerHotel(costPerHotel);
    }

    /*  Note One: Again I need access to the bankAccount of the player who received the card,
        and it is unclear how this is to be accomplished.    
    
        Note Two: It's unclear from the documentation how we plan on forcing players with a negative balance to sell assets
    
        Note Three: There is currently no way to gain access to the property objects themselves, only the properties IDs 
    */
/**
    @Override
    void playCard() throws Exception 
    {
        int houses = 0;
        int hotels = 0;
        int rawHouses = 0;
        //Get Property list from cardDrawer, this currently cant be done as far as I can tell.
        ArrayList<Property> cardDrawersProperties = cardDrawer.getPropertyList(); //Not an actual method
        for(Property currentProperty : cardDrawersProperties)
        {
            if(currentProperty.getClass() == RealEstate.class)
            {
                rawHouses = ((RealEstate)currentProperty).getNumberOfHouses();
                
                if(rawHouses == 5) hotels++;
                else houses += rawHouses;
            }
        }
        BankAccount cardDrawersBankAccount = Bank.getPlayerBankAccount(cardDrawer);
        int newBalance = cardDrawersBankAccount.getCurrentBalance() - (costPerHouse*houses + costPerHotel*hotels);
        
        if(newBalance < 0) {} //Code needed to force player to become solvent
        cardDrawersBankAccount.setCurrentBalance(newBalance);
    }

    public int getCostPerHouse() {
        return costPerHouse;
    }

    public void setCostPerHouse(int costPerHouse) {
        this.costPerHouse = costPerHouse;
    }

    public int getCostPerHotel() {
        return costPerHotel;
    }

    public void setCostPerHotel(int costPerHotel) {
        this.costPerHotel = costPerHotel;
    }
}
**/