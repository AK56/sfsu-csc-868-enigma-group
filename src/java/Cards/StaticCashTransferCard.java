/*
 * This class is for cards that transfer sums of money that are known at the start of the game.
 */
package Cards;
import Game.*;
import User.Player;
/**
 *
 * @author Kenneth Robertson
 */

public class StaticCashTransferCard extends Card
{
    private int amountTransfered;

    public StaticCashTransferCard() {
    }

    public StaticCashTransferCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int amountTransfered) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.amountTransfered = amountTransfered;
    }
    
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int amountTransfered)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setAmountTransfered(amountTransfered);
        this.setGameServlet(gameServlet);
    }    

    /*  Note One: I need access to the bankAccount of the player who received the card,
        and it is unclear how this is to be accomplished.    
    
        Note Two: It's unclear from the documentation how we plan on forcing players with a negative balance to sell assets
    */

    @Override
    void playCard() throws Exception 
    {
        BankAccount cardDrawersBankAccount = gameServlet.getBank().getPlayerBankAccount(cardDrawer);
        int newBalance = cardDrawersBankAccount.getCashBalance()+ amountTransfered;
        
        if(newBalance < 0) {} //Code needed to force player to become solvent
        else cardDrawersBankAccount.setCashBalance(newBalance);
    }

    public int getAmountTransfered() {
        return amountTransfered;
    }

    public void setAmountTransfered(int amountTransfered) {
        this.amountTransfered = amountTransfered;
    } 
}
