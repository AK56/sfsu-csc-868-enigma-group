/*
 * This is a special card that the player who draws it gets to keep,
 * and use to get out of jail for free.
 */
package Cards;
import Game.*;
import User.Player;
/**
 *
 * @author Kenneth Robertson
 */
public class GetOutOfJailFreeCard extends Card
{
    
    public GetOutOfJailFreeCard() {
    }

    public GetOutOfJailFreeCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        
    }
    
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setGameServlet(gameServlet);
    }

    /*  Note: Need a way to store this card in the player class for later use.
    */
    @Override
    void playCard() throws Exception {
        /*still checking on where to include the method to add getOutOfJailCard to Player
        and to perform other operations related Jail*/
    }
    
}
