package Card;
import Game.*;
import User.Player;
/**
 * This class is for the card that tells the player to move back 3 spaces,
 * it could be used for other kinds of movements based on number of spaces.
 * 
 * @author Kenneth Robertson
 */
public class MoveToSpaceCard extends Card
{
    private int spaceChange;

    /**
     * Default constructor
     */
    public MoveToSpaceCard() {
    }

    /**
     * Constructors - 6 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param spaceChange       int storing the number of the spaces and directions the card drawer will be moved
     * 
     */
    public MoveToSpaceCard(String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int spaceChange, int cardID) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.spaceChange = spaceChange;
    }

    /**
     * Initializer - 6 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param spaceChange       int storing the number of the spaces and directions the card drawer will be moved
     * 
     */
    public void initialize(String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet,int spaceChange, int cardID) 
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setSpaceChange(spaceChange);
        this.setGameServlet(gameServlet);
    }

    /*  Note: I need to be able to change the cardDrawers location on the board.
        Currently player holds a tokenID and the Token class holds the actual location,
        but the only way to get that Token object is throught the GameBoard.
    */
    @Override
    void playCard() throws Exception 
    {
        //Set cardDrawer to its current space + spaceChange
    }

    /**
     * @return  int holding the number of the spaces and directions the card drawer will be moved
     */
    public int getSpaceChange() {
        return spaceChange;
    }

    /**
     * @param spaceChange  sets the int holding the number of the spaces and directions the card drawer will be moved
     */
    public void setSpaceChange(int spaceChange) {
        this.spaceChange = spaceChange;
    } 
}
