/*
 *
 */
package Database;

import Property.Property;
import Property.Utility;
import java.sql.*;
import java.util.ArrayList;


/***
 * This class is a wrapper for the SQL queries to the database for the Utility
 * objects. It also creates objects of these classes and save the new
 * object information to the database when a new Game is started.
 * 
 * @author Cheryl Nielsen
 */
public class UtilityDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static UtilityDatabaseController instance;    
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
     * @return UtilityDatabaseController the existing instance, or a new object if no instance currently exists
     */
    public static UtilityDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new UtilityDatabaseController();
        }
        
        return instance;
    }
            

    /*****
     * Private constructor for the singleton design pattern.
     */
   private UtilityDatabaseController() 
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
   * This gets the Utility from the database that corresponds to the unique id key combination.
   * If successful it returns a new Utility object with the data values belonging 
   * to that Utility, otherwise it returns a NULL Utility object.
   * 
    * @param gameID the particular game this Utility belongs to 
    * @param spaceID the space on the game board where this Utility is located
    * @return Utility 
    */
   public Utility getUtilityByID(int gameID, int spaceID)
   {       
       String query1, query2;
       Utility utility = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query1 = "SELECT * FROM utility_constants " +
                    "WHERE space_id = '" + spaceID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query1 );   
            
             if(resultSet.next())
             {  
                utility = new Utility();
                int price = resultSet.getInt("purchase_price");
                String name = resultSet.getString("name");
                
                query2 = "SELECT * FROM utility_game_data " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' ";
             
                statement = connection.createStatement();
                resultSet = statement.executeQuery( query2 );   

                if(resultSet.next())
                {  
                   boolean mortgage = (resultSet.getInt("has_mortgage") == 1);
                   utility.initialize(resultSet.getInt("player_owner_id"), spaceID, name, price, mortgage, gameID);                   
                }
                
             }
             
             resultSet.close();
            statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get utility from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return utility;
       }
   }
  
   
   
   /***** 
   * This updates the database information for the given Utility.
   * Precondition: The Utility object to be saved to the database must already 
   * have been updated as needed, and exist in the database.
   * 
   * @param utility the Utility to update in the database
   * @return boolean if successful returns true, otherwise it returns false
   * ****/
   public boolean updateUtility(Utility utility)
   {      
       String query;
       boolean success = true;
       boolean hasMortgage = utility.getIsMortgaged();
       int spaceID = utility.getSpaceID();
       int owner = utility.getOwnerID();
       int gameID = utility.getGameID();
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }
            
            query = "UPDATE utility_game_data " +
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
           System.err.println( "Unable to update utility" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
   
   
   /***
    * This creates a new set of Utility objects associated with the unique id key of a particular
    * game, saves them to the database, and returns them in an ArrayList.
    * If not successful it returns a NULL ArrayList.
    * 
    * @param gameID the id of the game in the database
    * @return ArrayList of Utility Properties
    */
   public ArrayList<Property> addBothUtilitiesToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        
        ArrayList<Property> utilityList = new ArrayList<Property>();
                
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM utility_constants";
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                space = resultSet.getInt("space_id");
                name = resultSet.getString("name");
                price = resultSet.getInt("purchase_price");
                
                query = "INSERT INTO utility_game_data (space_id, game_id, name, has_mortgage) "
                        + "VALUES ( '" + space + "', '" + gameID + "', '" + name + "', '0' ) ";
                
                statement.executeUpdate(query);                
                Utility utility = new Utility();
                utility.initialize(owner, space, name, price, false, gameID);                
                utilityList.add(utility);                        
             }   
             
             resultSet.close();
            statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add 2 new utilities" );
            sqlex.printStackTrace();
         }
        finally
        {
            return utilityList;
        }
       
   }
   

   
    /***
    * This is used to determine how many Utility are owned by the player with the given unique id key.
    * This is needed to calculate the rent for a Utility.  There are only 2 Utility on the game board.
    * 
    * @param ownerID the player's id
    * @return boolean are both of the Utility owned by that player
    */
   public boolean doesPlayerHaveMonopoly(int ownerID)
   {
       String query;
       boolean monopoly = false;
       int numOwned = 0;
       int numNeededForMonopoly = 2;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM utility_game_data " +
                    "WHERE player_owner_id = '" + ownerID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                numOwned++;
             }

             resultSet.close();
            statement.close();
            
            monopoly = (numOwned == numNeededForMonopoly);
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get utility from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return monopoly;
       }
   }
   
   
   
   /***
    * Deletes both of the Utility from the database that belong to the given game unique key id.
    * @param gameID the id of the game in the database
    * @return boolean returns true if successful, or false if not successful
    */
   public boolean deleteBothGameUtilites(int gameID)
   {      
       String query;
       boolean success = false;
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }            
			
            query = "DELETE FROM utility_game_data " + 
                    "WHERE game_id = '" + gameID + "' ";  
            
            statement.executeUpdate(query);            
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to get the sql result" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
  
   
}
