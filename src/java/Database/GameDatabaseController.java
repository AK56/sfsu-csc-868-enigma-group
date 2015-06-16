/*
 *
 */
package Database;

import java.sql.*;
import java.util.ArrayList;

/***
 * This class is a wrapper for the SQL queries to the database for a new game. 
 * It saves the new id to the database so all database items needed by the game can be associated 
 * with a particular game instance in the relational database when a new game is started.
 * This enables the information for more than one game to be in the various database 
 * tables simultaneously.
 * 
 * @author Cheryl Nielsen
 */
public class GameDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static GameDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    

    /***
     * Static function for the singleton design pattern, this is 
     * used by other classes instead of calling the constructor.
     * 
     * @return GameDatabaseController the existing instance, or a new object if no instance currently exists
     */
    public static GameDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new GameDatabaseController();
        }
        
        return instance;
    }
            
    
    /*****
     * Private constructor for the singleton design pattern.
     */
   private GameDatabaseController() 
   {                 
   }
   
   
    /***
    * This loads the database driver for MySQL and opens a connection to the database.
    */
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

   
   
   /***
    * This saves a new Game to the database.
    * If successful it returns the database unique id key of the new Game.
    * If not successful it returns -1, which is an invalid id number.
    * 
    * @return int the new Game id 
    */
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
