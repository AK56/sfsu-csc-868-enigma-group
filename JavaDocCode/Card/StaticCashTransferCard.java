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

    /**
     * Default constructor
     */
    public StaticCashTransferCard() {
    }

    /**
     * Constructors - 6 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param amountTransfered  int storing the amount the bank will pay/receive from the card drawer
     * 
     */
    public StaticCashTransferCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int amountTransfered) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.amountTransfered = amountTransfered;
    }
    
    /**
     * Initializer - 6 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param amountTransfered  int storing the amount the bank will pay/receive from the card drawer
     * 
     */
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, int amountTransfered)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setAmountTransfered(amountTransfered);
        this.setGameServlet(gameServlet);
    }    
    

    //Overriden method
    @Override
    void playCard() throws Exception 
    {
        BankAccount cardDrawersBankAccount = gameServlet.getBank().getPlayerBankAccount(cardDrawer);
        int newBalance = cardDrawersBankAccount.getCashBalance()+ amountTransfered;
        
        if(newBalance < 0) {} //Code needed to force player to become solvent
        else cardDrawersBankAccount.setCashBalance(newBalance);
    }

    /**
     * @return  returns the amount the bank will pay/receive from the card drawer
     */
    public int getAmountTransfered() {
        return amountTransfered;
    }

    /**
     * @param amountTransfered  sets the amount the bank will pay/receive from the card drawer
     */
    public void setAmountTransfered(int amountTransfered) {
        this.amountTransfered = amountTransfered;
    } 
}
