/*
 * This class is for cards that transfer money to or from all the players in the game.
 */
package Cards;

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

    public CashTransferToPlayersCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, boolean payToPlayers, int amountTransfered) {
        super(cardID, cardDescription, cardDrawer, cardStackType);
        this.payToPlayers = payToPlayers;
        this.amountTransfered = amountTransfered;
    }
    
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, boolean payToPlayers, int amountTransfered)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setPayToPlayers(payToPlayers);
        this.setAmountTransfered(amountTransfered);
    }

    /*  Note: Need a way to get a list of all the players currently active.
    */
    @Override
    void playCard() throws Exception 
    {
        if(payToPlayers)
        {
            int totalPaid = 0;
            for(Player currentPlayer : Game.getPlayers())
            {
                if(currentPlayer.getPlayerId() != cardDrawer.getPlayerId())
                {
                    Bank.getPlayerBankAccount(currentPlayer).setCurrentBalance(
                            Bank.getPlayerBankAccount(currentPlayer).currentPlayerBankAccount.getCurrentBalance() + amountTransfered);
                    
                    totalPaid += amountTransfered;
                }
            }
            
            BankAccount cardDrawersBankAccount = Bank.getPlayerBankAccount(cardDrawer);
            int newBalance = cardDrawersBankAccount.getCurrentBalance() + totalPaid;

            if(newBalance < 0) {} //Code needed to force player to become solvent
            cardDrawersBankAccount.setCurrentBalance(newBalance);
        }
        
        else
        {
            int totalReceived = 0;
            
            for(Player currentPlayer : Game.getPlayers())
            {
                if(currentPlayer.getPlayerId() != cardDrawer.getPlayerId())
                {
                    BankAccount cardDrawersBankAccount = Bank.getPlayerBankAccount(currentPlayer);
                    int newBalance = cardDrawersBankAccount.getCurrentBalance() - amountTransfered;

                    if(newBalance < 0) {} //Code needed to force player to become solvent
                    cardDrawersBankAccount.setCurrentBalance(newBalance);
                    
                    totalReceived += amountTransfered;
                }
            }
            
            Bank.getPlayerBankAccount(cardDrawer).setCurrentBalance(
                            Bank.getPlayerBankAccount(cardDrawer).currentPlayerBankAccount.getCurrentBalance() + amountTransfered);
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
