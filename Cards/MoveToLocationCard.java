/*
 * This class is for cards that force the player to move to a specific location.
 * Some of these cards have special modifers like changes in the rate owed and
 * if the player gets money for passing go.
 */
package Cards;

/**
 *
 * @author Kenneth Robertson
 */
public class MoveToLocationCard extends Card
{
    private String spaceTypeToMoveTo;
    private boolean hasRentModifier;
    private boolean doNotPassGo;

    public MoveToLocationCard() 
    {
        doNotPassGo = false;
        hasRentModifier = false;
    }

    public MoveToLocationCard(int cardID, String cardDescription, Player cardDrawer, String cardStackType, String spaceTypeToMoveTo, boolean hasRentModifier, boolean doNotPassGo) {
        super(cardID, cardDescription, cardDrawer, cardStackType);
        this.spaceTypeToMoveTo = spaceTypeToMoveTo;
        this.hasRentModifier = hasRentModifier;
        this.doNotPassGo = doNotPassGo;
    }

    
    
    public void initialize(int cardID, String cardDescription, Player cardDrawer, String cardStackType, String spaceTypeToMoveTo, boolean hasRentModifier, boolean doNotPassGo)
    {
        this.setCardID(cardID);
        this.setCardDescription(cardDescription);
        this.setCardDrawer(cardDrawer);
        this.setCardStackType(cardStackType);
        this.setSpaceTypeToMoveTo(spaceTypeToMoveTo);
        this.setHasRentModifier(hasRentModifier);
        this.setDoNotPassGo(doNotPassGo);
    }

    /*  Note: I need to be able to change the cardDrawers location on the board.
        Currently player holds a tokenID and the Token class holds the actual location,
        but the only way to get that Token object is throught the GameBoard.
    */
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

    public String getSpaceTypeToMoveTo() {
        return spaceTypeToMoveTo;
    }

    public void setSpaceTypeToMoveTo(String spaceTypeToMoveTo) {
        this.spaceTypeToMoveTo = spaceTypeToMoveTo;
    }

    public boolean isHasRentModifier() {
        return hasRentModifier;
    }

    public void setHasRentModifier(boolean hasRentModifier) {
        this.hasRentModifier = hasRentModifier;
    }

    public boolean isDoNotPassGo() {
        return doNotPassGo;
    }

    public void setDoNotPassGo(boolean doNotPassGo) {
        this.doNotPassGo = doNotPassGo;
    }
}
