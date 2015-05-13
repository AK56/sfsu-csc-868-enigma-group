/*
 * The Dice class keeps track of dice rolls
 */
package Game;

import java.util.Random;

/**
 *
 * @author Kenneth Robertson
 */
public class Dice 
{
    private Random dieGenerator;
    private int dieOne;
    private int dieTwo;
    
    /*These image paths are speculative,
    the actual structure of the class will need to be changed 
    to represent the file system and naming conventions we choose.
    */
    private String dieOneImagePath;
    private String dieTwoImagePath;

    /* Constructors - 5 parameters */
    public Dice() 
    {
        dieGenerator = new Random();
        this.dieOne = dieGenerator.nextInt((6 - 1) + 1) + 1;//Max-Min+(1+Min)
        this.dieTwo = dieGenerator.nextInt((6 - 1) + 1) + 1;//Max-Min+(1+Min)
        
        //This is just one way to handle the filepath
        this.dieOneImagePath = null;
        this.dieTwoImagePath = null;
    }
 
    /* Initialize method - 4 parameters */
    public void initialize(String dieOneImage, String dieTwoImage) {
        this.setDieOne(0);
        this.setDieTwo(0);
        this.setDieOneImagePath(dieOneImage);
        this.setDieTwoImagePath(dieTwoImage);
            
    }

    /* Getters */
    public int getDieOne() {
        return dieOne;
    }
    public int getDieTwo() {
        return dieTwo;
    }
    public String getDieOneImagePath() {
        return dieOneImagePath;
    }
    public String getDieTwoImagePath() {
        return dieTwoImagePath;
    }

    /* Setters */
    public void setDieOne(int dieOne) {
        this.dieOne = dieOne;
    }
    public void setDieTwo(int dieTwo) {
        this.dieTwo = dieTwo;
    }
    public void setDieOneImagePath(String dieOneImagePath) {
        this.dieOneImagePath = dieOneImagePath;
    }
    public void setDieTwoImagePath(String dieTwoImagePath) {
        this.dieTwoImagePath = dieTwoImagePath;
    }
    
    /* rollDice()
     * Assigns the die new values from 1 - 6.
     * Sets the image path for said die.
     */
    public void rollDice()
    {
        this.setDieOne(dieGenerator.nextInt((6 - 1) + 1) + 1);//Max-Min+(1+Min)
        this.setDieTwo(dieGenerator.nextInt((6 - 1) + 1) + 1);//Max-Min+(1+Min)
        
        //The dieImagePaths needs to be reconfigured here.
        this.setDieOneImagePath("");
        this.setDieTwoImagePath("");
        
    }
    
    /* getDiceTotal()
     * Returns the sum of the current dice roll.
     */
    public int getDiceTotal()
    {
        return dieOne + dieTwo;
    }
}
