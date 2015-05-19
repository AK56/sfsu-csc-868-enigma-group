/*
 * This class is for cards that transfer money to or from all the players in the game.
 */
package Cards;
import Game.*;
import User.Player;

/**
 *
 * @author Kenneth Robertson
 */

public class CashTransferToPlayersCard extends Card
{
    private boolean payToPlayers;
    private int amountTransfered;

    public CashTransferToPlayersCard() {
    }

    public CashTransferToPlayersCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, GameServlet gameServlet, boolean payToPlayers, int amountTransfered) {
        super(cardID, cardDescription, cardDrawer, cardStackType, gameServlet);
        this.payToPlayers = payToPlayers;
        this.amountTransfered = amountTransfered;
    }
    
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

    //  Note: Need a way to get a list of all the players currently active.

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
 
    
    public boolean isPayToPlayers() {
        return payToPlayers;
    }

    public void setPayToPlayers(boolean payToPlayers) {
        this.payToPlayers = payToPlayers;
    }

    public int getAmountTransfered() {
        return amountTransfered;
    }

    public void setAmountTransfered(int amountTransfered) {
        this.amountTransfered = amountTransfered;
    } 
}
