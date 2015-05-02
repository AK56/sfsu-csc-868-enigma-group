/*
 * This is the base class for all the other card classes.
 * It contains the information needed by all card objects including:
 * text description, cardID, player who drew the card, and if it is a chance or community chest card
 */
package Cards;

/**
 *
 * @author Kenneth Robertson
 */
public abstract class Card 
{
    protected int cardID; //Card number
    protected String cardDescription; //Text on Card
    protected Player cardDrawer; //Player who drew the card
    protected String cardStackType; //Chance or Community Chest

    public Card() {
    }

    public Card(int cardID, String cardDescription, Player cardDrawer, String cardStackType) 
    {
        this.cardID = cardID;
        this.cardDescription = cardDescription;
        this.cardDrawer = cardDrawer;
        this.cardStackType = cardStackType;
    }

    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType) 
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
    }

    abstract void playCard() throws Exception;

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public Player getCardDrawer() {
        return cardDrawer;
    }

    public void setCardDrawer(Player cardDrawer) {
        this.cardDrawer = cardDrawer;
    }

    public String getCardStackType() {
        return cardStackType;
    }

    public void setCardStackType(String cardStackType) {
        this.cardStackType = cardStackType;
    }
    
    
}
