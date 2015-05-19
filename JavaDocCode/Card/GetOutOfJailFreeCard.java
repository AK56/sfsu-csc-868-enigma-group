package Cards;
import Game.*;
import User.Player;
/**
 * This is a special card that the player who draws it gets to keep,
 * and use to get out of jail for free.
 * 
 * @author Kenneth Robertson
 */
public class GetOutOfJailFreeCard extends Card
{
    /**
     * Default constructor
     */
    public GetOutOfJailFreeCard() {
    }

    /**
     * Constructors - 5 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * 
     */
    public GetOutOfJailFreeCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        
    }
    
    /**
     * initializer - 5 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * 
     */
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setGameServlet(gameServlet);
    }

    //Override method should be exposed by super class in javadoc
    //Note: Need a way to store this card in the player class for later use.
    @Override
    void playCard() throws Exception {
        /*still checking on where to include the method to add getOutOfJailCard to Player
        and to perform other operations related Jail*/
    }
    
}
