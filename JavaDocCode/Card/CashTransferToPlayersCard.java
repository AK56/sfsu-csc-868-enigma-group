package Cards;
import Game.*;
import User.Player;

/**
 * This class is for cards that transfer money to or from all the players in the game.
 * 
 * @author Kenneth Robertson
 */
public class CashTransferToPlayersCard extends Card
{
    private boolean payToPlayers;
    private int amountTransfered;

    /**
     * Default constructor
     */
    public CashTransferToPlayersCard() {
    }

    /**
     * Constructors - 7 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param payToPlayers      boolean the if true means that the card drawer must pay to players instead of getting paid from players
     * @param amountTransfered  int that stores the amount to be transfered from player(s) to player(s)
     * 
     */
    public CashTransferToPlayersCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, boolean payToPlayers, int amountTransfered) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.payToPlayers = payToPlayers;
        this.amountTransfered = amountTransfered;
    }
    
    /**
     * Initializer - 7 parameters
     *
     * @param cardID            int stores the card identifier
     * @param cardDescription	String stores the text of the card as in board game
     * @param cardDrawer	Player stores the player who drew the card
     * @param cardStackType	String stores the stack type (ie Chance or Community Chest)
     * @param gameServlet	GameServlet gives access to all other game data
     * @param payToPlayers      boolean the if true means that the card drawer must pay to players instead of getting paid from players
     * @param amountTransfered  int that stores the amount to be transfered from player(s) to player(s)
     * 
     */
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, boolean payToPlayers, GameServlet gameServlet, int amountTransfered)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setPayToPlayers(payToPlayers);
        this.setAmountTransfered(amountTransfered);
        this.setGameServlet(gameServlet);
    }

    //Overridden method
    @Override
    void playCard() throws Exception 
    {
        if(payToPlayers)
        {
            int totalPaid = 0;
            for(Player currentPlayer : gameServlet.getPlayers())
            {
                if(currentPlayer.getPlayerID() != cardDrawer.getPlayerID())
                {
                    gameServlet.getBank().addToAccount(currentPlayer, amountTransfered);
                    
                    totalPaid += amountTransfered;
                }
            }
            
            BankAccount cardDrawersBankAccount = gameServlet.getBank().getPlayerBankAccount(cardDrawer);
            int newBalance = cardDrawersBankAccount.getCashBalance() - totalPaid;

            if(newBalance < 0) {} //Code needed to force player to become solvent
            else gameServlet.getBank().subtractFromAccount(cardDrawer, totalPaid);
        }
        
        else
        {
            int totalReceived = 0;
            
            for(Player currentPlayer : gameServlet.getPlayers())
            {
                if(currentPlayer.getPlayerID() != cardDrawer.getPlayerID())
                {
                    BankAccount cardDrawersBankAccount = gameServlet.getBank().getPlayerBankAccount(currentPlayer);
                    int newBalance = cardDrawersBankAccount.getCashBalance() - amountTransfered;

                    if(newBalance < 0) {} //Code needed to force player to become solvent
                    else cardDrawersBankAccount.setCashBalance(newBalance);
                    
                    totalReceived += amountTransfered;
                }
            }
            
            gameServlet.getBank().addToAccount(cardDrawer, amountTransfered);
        }
    }
 
    /**
     * @return  boolean that tells if the card drawer must pay to players instead of getting paid from players
     */
    public boolean isPayToPlayers() {
        return payToPlayers;
    }

    /**
     * @param payToPlayers  sets the boolean that tells if the card drawer must pay to players instead of getting paid from players
     */
    public void setPayToPlayers(boolean payToPlayers) {
        this.payToPlayers = payToPlayers;
    }

    /**
     * @return  int that holds the amount to be transfered from player(s) to player(s)
     */
    public int getAmountTransfered() {
        return amountTransfered;
    }

    /**
     * @param amountTransfered  sets the int that holds the amount to be transfered from player(s) to player(s)
     */
    public void setAmountTransfered(int amountTransfered) {
        this.amountTransfered = amountTransfered;
    } 
}
