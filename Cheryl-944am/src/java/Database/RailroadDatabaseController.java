/*
 *
 */
package Database;

import Property.Property;
import Property.Railroad;
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
public class RailroadDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static RailroadDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static RailroadDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new RailroadDatabaseController();
        }
        
        return instance;
    }
            
    
    // private constructor for the singleton design pattern
   private RailroadDatabaseController() 
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
   
   
   /******* Property database functions for Railroads, Utilities, and Realestate *************/
   

   /*****
   // gets user from the database that corresponds to that user's unique id 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   * ****/
   public Railroad getRailroadByID(int gameID, int spaceID)
   {       
       String query1, query2;
       Railroad railroad = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query1 = "SELECT * FROM railroad_constants " +
                    "WHERE space_id = '" + spaceID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query1 );   
            
             if(resultSet.next())
             {  
                railroad = new Railroad();
                railroad.setSpaceID(spaceID);
                String name = resultSet.getString("name");
                int price = resultSet.getInt("purchase_price");
                
                query2 = "SELECT * FROM railroad_game_data " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' ";
             
                statement = connection.createStatement();
                resultSet = statement.executeQuery( query2 );   

                if(resultSet.next())
                {  
                   int owner = resultSet.getInt("player_owner_id"); 
                   boolean mortgaged = (resultSet.getInt("has_mortgage") == 1);
                   railroad.initialize(owner, spaceID, name, price, mortgaged, gameID);
                }             
             }
             
             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get railroad from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return railroad;
       }
   }
  
   
   
   /*****
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
   public boolean updateRailroad(Railroad railroad)
   {      
       String query;
       boolean success = true;
       boolean hasMortgage = railroad.getIsMortgaged();
       int spaceID = railroad.getSpaceID();
       int owner = railroad.getOwnerID();
       int gameID = railroad.getGameID();
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }
            
            query = "UPDATE railroad_game_data " +
                    "SET has_mortgage = '" + hasMortgage + "', player_owner_id = '" + owner + ", " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 

            statement.executeUpdate(query);
            resultSet.close();
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to update railroad" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
   
   
   /*****
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public ArrayList<Railroad> addAllRailroadsToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        ArrayList<Railroad> railroadList = new ArrayList<Railroad>();
                
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM railroad_constants";
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                space = resultSet.getInt("space_id");
                name = resultSet.getString("name");
                price = resultSet.getInt("purchase_price");
                
                query = "INSERT INTO railroad_game_data (space_id, game_id, name, has_mortgage) "
                        + "VALUES ( '" + space + "', '" + gameID + "', '" + name + "', '0' ) ";
                
                statement.executeUpdate(query);                
                Railroad railroad = new Railroad();
                railroad.initialize(owner, space, name, price, false, gameID);                
                railroadList.add(railroad);                        
             }   
             
             resultSet.close();
            statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add 4 new railroads" );
            sqlex.printStackTrace();
         }
        finally
        {
            return railroadList;
        }
       
   }
   

   public int numberRailroadsOwned(int ownerID)
   {
       String query;
       int numOwned = 0;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM railroad_game_data " +
                    "WHERE player_owner_id = '" + ownerID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                numOwned++;
             }

             resultSet.close();
            statement.close();
            
            
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get railroad from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return numOwned;
       }
   }
   
   
   
   /**
    * 
    * @param spaceID
    * @return true if successful, else false
    */
   public boolean deleteAllGameRailroads(int gameID)
   {      
       String query;
       boolean success = false;
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }            
			
            query = "DELETE FROM railroad_game_data " + 
                    "WHERE game_id = '" + gameID + "' ";  
            
            statement.executeUpdate(query);            
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to delete the railroads" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
  
   
}
