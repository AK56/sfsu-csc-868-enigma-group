/*
 *
 */
package Database;

import java.sql.*;
import java.util.ArrayList;


/**
 * This class is a wrapper for the SQL queries to the database for the User and 
 * Player objects.  It also provides the additional logic functionality needed.  
 * For example it ensures that unique login information has been provided
 * before saving new user registration to the database. 
 * 
 * @author Cheryl
 */
public class GameDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "punjabi23";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static GameDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static GameDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new GameDatabaseController();
        }
        
        return instance;
    }
            
    
    // private constructor for the singleton design pattern
   private GameDatabaseController() 
   {                 
   }
   
   
   private void getDatabaseConnection()
   {        
        try 
        {
           // Load the driver to allow connection to the database
           Class.forName( "com.mysql.jdbc.Driver" );
           connection = DriverManager.getConnection( url, username, password);
        } 
        catch ( ClassNotFoundException cnfex ) 
        {
           System.err.println( "Failed to load the database driver." );
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to connect to the database" );
        }
        
   }
   
   
   /************* Game database functions *****************/
   
   
   /*****
   * just a stub for now
   * puts a new game id into the database and returns the game id
   * ****/
   public int addNewGame()
   {
       String query;
       int gameID = -1;
       
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                         
             // TO BE DONE ------ USE REAL BANK VALUES
            query = "INSERT INTO game (game_bord_image_file) "
                 + "VALUES ('classic_game_board_sm.png') ";
         
            statement = connection.createStatement();
            statement.executeUpdate(query);            
            query = "SELECT * FROM game";
            resultSet = statement.executeQuery( query );    
            resultSet.last();
             
             // if player was successfully created in the database
             if(resultSet.next())
             {                
                gameID = resultSet.getInt("game_id");                
             }

         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add new player" );
            sqlex.printStackTrace();
         }
        finally
        {
            return gameID;
        }
       
   }
   
 

   
}
