/*
 * 
 */
package Game;

import java.util.Random;
/**
 * This class takes the responsibility to roll the dice and return the total dice value of the dice rolled.
 * 
 * @author Kenneth Robertson
 */
public class Dice 
{
    private Random dieGenerator;
    private int dieOne;
    private int dieTwo;
    private static Dice instance; // use this instance to get diceValue everytime instead of creating new object every time
    private String dieOneImagePath;
    private String dieTwoImagePath;

    /**
     * Default Constructor
     */
    public Dice() 
    {
        dieGenerator = new Random();
        this.dieOne = dieGenerator.nextInt((6 - 1) + 1) + 1;//Max-Min+(1+Min)
        this.dieTwo = dieGenerator.nextInt((6 - 1) + 1) + 1;//Max-Min+(1+Min)
        
        //This is just one way to handle the filepath
        this.dieOneImagePath = null;
        this.dieTwoImagePath = null;
    }
 
    /**
     * Initialize the dice object
     * @param dieOneImage sets dieOneImagePath for die
     * @param dieTwoImage sets dieTwoImagePath for die
     */
    public void initialize(String dieOneImage, String dieTwoImage) {
        this.setDieOne(0);
        this.setDieTwo(0);
        this.setDieOneImagePath(dieOneImage);
        this.setDieTwoImagePath(dieTwoImage);
            
    }
    /**
     * SingleTon Pattern for dice.
     * @return returns the Dice instance if exist, create one if doesn't exist 
     */
    public static Dice getinstance(){
        if(instance == null){
            instance = new Dice();
        }
        return instance;
    }
    
    /*** 
     * returns value of dieOne
     * @return returns value of dieOne
     **/
    public int getDieOne() {
        return dieOne;
    }
    
    /*** 
     * returns value of dietwo
     * @return returns value of dietwo
     **/
    public int getDieTwo() {
        return dieTwo;
    }
    
    /*** 
     * returns image path for dieOne
     * @return returns image path for dieOne
     **/
    public String getDieOneImagePath() {
        return dieOneImagePath;
    }
    
    /*** 
     * returns image path for dieTwo
     * @return returns image path for dieTwo
     **/
    public String getDieTwoImagePath() {
        return dieTwoImagePath;
    }

    /*** 
     * sets dieOne value
     * @param dieOne sets dieOne value
     **/
    public void setDieOne(int dieOne) {
        this.dieOne = dieOne;
    }
    
    /***
     * sets dieTwo value
     * @param dieTwo sets dieTwo value
     **/
    public void setDieTwo(int dieTwo) {
        this.dieTwo = dieTwo;
    }
    
    /**
     * sets the filename for the dieOne image file
     * @param dieOneImagePath 
     */
    public void setDieOneImagePath(String dieOneImagePath) {
        this.dieOneImagePath = dieOneImagePath;
    }
    
    /**
     * sets the filename for the dieTwo image file
     * @param dieTwoImagePath 
     */
    public void setDieTwoImagePath(String dieTwoImagePath) {
        this.dieTwoImagePath = dieTwoImagePath;
    }
    
    /***
     * Assigns the die new values from 1 - 6.
     * Sets the image path for said die.
     * 
     */
    public void rollDice()
    {
        this.setDieOne(dieGenerator.nextInt((6 - 1) + 1) + 1);//Max-Min+(1+Min)
        this.setDieTwo(dieGenerator.nextInt((6 - 1) + 1) + 1);//Max-Min+(1+Min)
        
        //The dieImagePaths needs to be reconfigured here.
        this.setDieOneImagePath("");
        this.setDieTwoImagePath("");
        
    }
    
    /*** 
     * returns the total dice value after the dice rolled
     * @return returns the total dice value after the dice rolled
     **/
    public int getDiceTotal()
    {
        return dieOne + dieTwo;
    }
}
