package Game;

/*
 * The Space Class keeps track of a space for the game board.  Each space has a
 * location set on the game board.
 */

/**
 *
 * @author Derek Ma
 */
public class Space {
    protected int spaceID;
    
    /* Constructor */
    public Space (int location) {
        this.spaceID = location;
    }    
    
    public Space () {
        this.spaceID = 0;
    }
    
    /* Initialize Method */
    public void initialize(int location) {
        this.setSpaceID(location);
    }
    
    /* Getters */
    public int getSpaceID () {
        return spaceID;
    }
    
    /* Setters */
    public void setSpaceID (int location) {
        this.spaceID = location;
    }
}
