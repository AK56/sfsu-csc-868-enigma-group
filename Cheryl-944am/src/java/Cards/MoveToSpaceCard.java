/*
 * This class is for the card that tells the player to move back 3 spaces,
 * it could be used for other kinds of movements based on number of spaces.
 */
package Cards;
import Game.*;
/**
 *
 * @author Kenneth Robertson
 */
public class MoveToSpaceCard extends Card
{
    private int spaceChange;

    public MoveToSpaceCard() {
    }

    public MoveToSpaceCard(String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int spaceChange, int cardID) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.spaceChange = spaceChange;
    }

    public void initialize(String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet,int spaceChange, int cardID) 
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setSpaceChange(spaceChange);
        this.setGameServlet(gameServlet)
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

    public int getSpaceChange() {
        return spaceChange;
    }

    public void setSpaceChange(int spaceChange) {
        this.spaceChange = spaceChange;
    } 
}
