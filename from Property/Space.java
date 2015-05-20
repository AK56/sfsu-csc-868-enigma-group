package Property;

/**
 * The Space Class keeps track of a space for the game board.  Each space has a
 * location set on the game board.
 *
 * @author Derek Ma
 */
public class Space {
    protected int spaceID;
    
    /**
     * Constructors - 1 parameters
     *
     * @param location	int stores the location of the space on the board
     * 
     */
    public Space (int location) {
        this.spaceID = location;
    }    
    
    /**
     * Default constructor
     */
    public Space () {
        this.spaceID = 0;
    }
    
    /**
     * Initialize Method - 1 parameters
     *
     * @param location	int stores the location of the space on the board
     * 
     */
    public void initialize(int location) {
        this.setSpaceID(location);
    }
    
    /**
     * @return  the space on the board that this tile exists in
     */
    public int getSpaceID () {
        return spaceID;
    }
    
    /**
     * @param location  sets the space on the board that this tile exists in
     */
    public void setSpaceID (int location) {
        this.spaceID = location;
    }
}
