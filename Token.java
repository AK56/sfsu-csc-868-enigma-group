/**
 *
 * @author John
 * 
 * Summary: The Token class is a wrapper for the player’s token image to be called. The Token has no actions in
a game. It just is displayed on the game board space that is the location of the player during the
game. The Token does not store that space location. Instead the player stores the id of the token
and the space location of the player on the game board.
 */
public class Token
{
    private int tokenID;
    private String imageFileName;
    private int boardSpace;
    
    public Token(){
        
    }

    public void setId(int id){
        tokenID = id;
    }
    
    public int getId(){
        return tokenID;
    } 
    
    public void setImageFileName(String fileName){
        imageFileName = fileName;
    }

    public String getImageFileName(){
        return imageFileName;
    }

    public void setBoardSpace(int space){
        // maybe call the dice class and keep adding the roll
        boardSpace = space;
        boardSpace = boardSpace % 40;
    }
    
    public int getBoardSpace(){
        return boardSpace;
    }
    
}
