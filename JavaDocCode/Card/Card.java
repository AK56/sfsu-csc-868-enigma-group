package Cards;
import Game.*;
import User.Player;

/**
 * This is the abstract base class for all the other card classes.
 * It contains the information needed by all card objects including:
 * text description, cardID, player who drew the card, and if it is a chance or community chest card
 * 
 * @author Kenneth Robertson
 */
public abstract class Card 
{
    protected int cardID; //Card number
    protected String cardDescription; //Text on Card
    protected Player cardDrawer; //Player who drew the card
    protected String cardStackType; //Chance or Community Chest
    protected GameServlet gameServlet;

    /**
     * Default constructor
     */
    public Card() {
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
    public Card(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet) 
    {
        this.cardID = cardID;
        this.cardDescription = cardDescription;
        this.cardDrawer = cardDrawer;
        this.cardStackType = cardStackType;
        this.gameServlet = gameServlet;
    }

    /**
     * Initializer - 5 parameters
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

    /**
     * This method performs all the operations that the card needs to perform.
     * @throws Exception 
     */
    abstract void playCard() throws Exception;

    /**
     * @return  int holding the card identifier
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * @param cardID  sets the card identifier
     */
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    /**
     * @return  String holding the card description
     */
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * @oaram cardDescription   sets the card description
     */
    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    /**
     * @return  Player holding the object associated with the player who drew the card
     */
    public Player getCardDrawer() {
        return cardDrawer;
    }

    /**
     * @oaram cardDrawer   sets the player who drew the card
     */
    public void setCardDrawer(Player cardDrawer) {
        this.cardDrawer = cardDrawer;
    }

    /**
     * @return  String that describes the stack type the card came from (ie Chance)
     */
    public String getCardStackType() {
        return cardStackType;
    }

    /**
     * @oaram cardStackType   sets the stack type the card came from (ie Chance)
     */
    public void setCardStackType(String cardStackType) {
        this.cardStackType = cardStackType;
    }

    /**
     * @return  GameServlet that holds all necessary game data.
     */
    public GameServlet getGameServlet() {
        return gameServlet;
    }

    /**
     * @oaram gameServlet   sets GameServlet that holds all necessary game data.
     */
    public void setGameServlet(GameServlet gameServlet) {
        this.gameServlet = gameServlet;
    }
}
