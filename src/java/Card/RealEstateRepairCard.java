package Card;
import Game.*;
import Property.*;
import User.Player;
import java.util.ArrayList;
/**
 * This class is for the cards that require the player who receives it to pay a variable amount 
 * based on the number of houses and hotels that player owns.
 * 
 * @author Kenneth Robertson
 */

public class RealEstateRepairCard extends Card
{
    private int costPerHouse;
    private int costPerHotel;

    public RealEstateRepairCard() {
    }

    /**
     * Constructors - 7 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param costPerHouse      int stores the amount the card drawer will have to pay per house
     * @param costPerHotel      int stores the amount the card drawer will have to pay per hotel(5 houses on a property)
     * 
     */
    public RealEstateRepairCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int costPerHouse, int costPerHotel) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.costPerHouse = costPerHouse;
        this.costPerHotel = costPerHotel;
    }
    

    /**
     * Initializer - 7 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param costPerHouse      int stores the amount the card drawer will have to pay per house
     * @param costPerHotel      int stores the amount the card drawer will have to pay per hotel(5 houses on a property)
     * 
     */
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int costPerHouse, int costPerHotel)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setCostPerHouse(costPerHouse);
        this.setCostPerHotel(costPerHotel);
        this.setGameServlet(gameServlet);
    }

    //Overriden method
    @Override
    void playCard() throws Exception 
    {
        int houses = 0;
        int hotels = 0;
        int rawHouses = 0;
        //Get Property list from cardDrawer, this currently cant be done as far as I can tell.
        ArrayList<Property> cardDrawersProperties = gameServlet.getProperties(); //Not an actual method
        for(Property currentProperty : cardDrawersProperties)
        {
            if(currentProperty.getOwnerID() == cardDrawer.getPlayerID() && currentProperty.getClass() == RealEstate.class)
            {
                rawHouses = ((RealEstate)currentProperty).getNumberOfHouses();
                
                if(rawHouses == 5) hotels++;
                else houses += rawHouses;
            }
        }
        BankAccount cardDrawersBankAccount = gameServlet.getBank().getPlayerBankAccount(cardDrawer);
        int newBalance = cardDrawersBankAccount.getCashBalance() - (costPerHouse*houses + costPerHotel*hotels);
        
        if(newBalance < 0) {} //Code needed to force player to become solvent
        cardDrawersBankAccount.setCashBalance(newBalance);
    }

    /**
     * @return  int stores the amount the card drawer will have to pay per house
     */
    public int getCostPerHouse() {
        return costPerHouse;
    }

    /**
     * @param costPerHouse  sets the int that holds the amount the card drawer will have to pay per house
     */
    public void setCostPerHouse(int costPerHouse) {
        this.costPerHouse = costPerHouse;
    }

    /**
     * @return  int stores the amount the card drawer will have to pay per hotel
     */
    public int getCostPerHotel() {
        return costPerHotel;
    }

    /**
     * @param costPerHotel  sets the int that holds the amount the card drawer will have to pay per hotel
     */
    public void setCostPerHotel(int costPerHotel) {
        this.costPerHotel = costPerHotel;
    }
}
