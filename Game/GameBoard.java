/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import java.util.*;
import Card.*;
import Property.*;
import Data.*;
/**
 *
 * @author Gurpartap Gill
 */
public class GameBoard {
    private int gameBoardId;
    private String image;
    private ArrayList<Space> spaces;
    private ArrayList<Token> tokens;
    private Database database;
    private Dice dice;
    //private Jail jail;
    Game game;

    public GameBoard() {

    }

    public GameBoard(int gameBoardId, ArrayList<Space> spaces, ArrayList<Token> tokens, Database database, Dice dice,
            Game game) {
        this.gameBoardId = gameBoardId;
        this.spaces = spaces;
        this.tokens = tokens;
        this.database = database;
        this.dice = dice;
        this.game = game;

    }

    public void rollDice() {
        dice.rollDice();
    }

    public int getDie1() {
        return dice.getDieOne();
    }

    public int getDie2() {
        return dice.getDieTwo();
    }

    public int diceValue() {
        return dice.getDiceTotal();
    }

    public void setPlayerBoardSpace() {
        // the new space id will be the current space id + total dice value
        int newSpaceId = game.getActivePlayer().getBoardSpace().getSpaceID() + dice.getDiceTotal();
        //requires spaceId handler here, for if newSpaceId > 32, then we set it to 0, as we have 32 spaces.
    /*now lets find the space with that newSpaceId. Once space found, set it for activePlayer*/
        for (Space newSpace : spaces) {
            if (newSpace.getSpaceID() == newSpaceId) {
                game.getActivePlayer().setBoardSpace(newSpace);
            }
        }

    }

    public boolean isPropertyOwned(Property property) {
        if (property.getOwnerID() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void passGo() {

    }

    public String actionOnCurrentSpace(Player player) {
        /*first check what the currentSpace of Player is, then perform other checks and actions*/
        Space currentSpace = this.spaces.get(player.getSpaceID());
        if (currentSpace.getClass() == RealEstate.class) {
            return "Property RealEstate";
        } else if (currentSpace.getClass() == Utility.class) {
            return "Property Utility";
        } else if (currentSpace.getClass() == Railroad.class) {
            return "Property Railroad";
        } else if (currentSpace.getClass() == ChanceSpace.class) {
            return "Card Chance";
        } else if (currentSpace.getClass() == CommunityChestSpace.class) {
            return "Card CommunityChest";
        }
    
        return "Undefined";
    }

    public void showHousesHotels() {
        /*shows houses of current or activePlayer*/
    }

    public void updateDatabase() {
        /*updates the database accordingly*/
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }
    
    
        
}
