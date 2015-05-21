package Cards;
import Game.*;
import User.Player;
/**
 * This class is for cards that force the player to move to a specific location.
 * Some of these cards have special modifers like changes in the rate owed and
 * if the player gets money for passing go.
 * @author Kenneth Robertson
 */
public class MoveToLocationCard extends Card
{
    private String spaceTypeToMoveTo;
    private boolean hasRentModifier;
    private boolean doNotPassGo;

    /**
     * Default constructor
     */
    public MoveToLocationCard() 
    {
        doNotPassGo = false;
        hasRentModifier = false;
    }

    /**
     * Constructors - 8 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param spaceTypeToMoveTo String that describes the kind of location the player will be sent to
     * @param hasRentModifier   boolean stores whether the player will need to pay a modified rent when he/she lands on space
     * @param doNotPassGo       boolean stores whether the player can collect go money as they move to the new space
     * 
     */
    public MoveToLocationCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, String spaceTypeToMoveTo, boolean hasRentModifier, boolean doNotPassGo) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.spaceTypeToMoveTo = spaceTypeToMoveTo;
        this.hasRentModifier = hasRentModifier;
        this.doNotPassGo = doNotPassGo;
    }

    
    /**
     * Initializer - 8 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param spaceTypeToMoveTo String that describes the kind of location the player will be sent to
     * @param hasRentModifier   boolean stores whether the player will need to pay a modified rent when he/she lands on space
     * @param doNotPassGo       boolean stores whether the player can collect go money as they move to the new space
     * 
     */
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, String spaceTypeToMoveTo, boolean hasRentModifier, boolean doNotPassGo)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setSpaceTypeToMoveTo(spaceTypeToMoveTo);
        this.setHasRentModifier(hasRentModifier);
        this.setDoNotPassGo(doNotPassGo);
        this.setGameServlet(gameServlet);
    }

    //  Note: I need to be able to change the cardDrawers location on the board.
    //    Currently player holds a tokenID and the Token class holds the actual location,
    //    but the only way to get that Token object is throught the GameBoard.
    @Override
    void playCard() throws Exception
    {
        if(spaceTypeToMoveTo == "go") 
        {
            //Set cardDrawer to the Go space
        }
        else if(spaceTypeToMoveTo == "jail") 
        {
            doNotPassGo = true;
            //Set cardDrawer to the In Jail space
        }
        else if(spaceTypeToMoveTo == "boardwalk") 
        {
            //Set cardDrawer to the boardwalk space
        }
        else if(spaceTypeToMoveTo == "st charles place") 
        {
            //Set cardDrawer to the st charles place space
        }
        else if(spaceTypeToMoveTo == "reading railroad") 
        {
            //Set cardDrawer to the reading railroad space
        }
        else if(spaceTypeToMoveTo == "illinois ave") 
        {
            //Set cardDrawer to the illinois ave space
        }
        else if(spaceTypeToMoveTo == "nearest railroad")
        {
            hasRentModifier = true;
            //Get the cardDrawers current location and iterate throug the spaces
            //until the current space is a railroad
        }
        else if(spaceTypeToMoveTo == "nearest utility")
        {
            hasRentModifier = true;
            //Get the cardDrawers current location and iterate throug the spaces
            //until the current space is a utility
        }
        else
            throw new Exception("Exception: no board location \""+spaceTypeToMoveTo+"\"");
    }

    /**
     * @return  returns string holding the kind of location the player will be sent to
     */
    public String getSpaceTypeToMoveTo() {
        return spaceTypeToMoveTo;
    }

    /**
     * @param spaceTypeToMoveTo  stores string holding the kind of location the player will be sent to
     */
    public void setSpaceTypeToMoveTo(String spaceTypeToMoveTo) {
        this.spaceTypeToMoveTo = spaceTypeToMoveTo;
    }

    /**
     * @return  returns boolean telling whether the player will need to pay a modified rent when he/she lands on space
     */
    public boolean isHasRentModifier() {
        return hasRentModifier;
    }

    /**
     * @param hasRentModifier  returns boolean telling whether the player will need to pay a modified rent when he/she lands on space
     */
    public void setHasRentModifier(boolean hasRentModifier) {
        this.hasRentModifier = hasRentModifier;
    }

    /**
     * @return  returns boolean telling whether the player can collect go money as they move to the new space
     */
    public boolean isDoNotPassGo() {
        return doNotPassGo;
    }

    /**
     * @param doNotPassGo  returns boolean telling whether the player can collect go money as they move to the new space
     */
    public void setDoNotPassGo(boolean doNotPassGo) {
        this.doNotPassGo = doNotPassGo;
    }
}
