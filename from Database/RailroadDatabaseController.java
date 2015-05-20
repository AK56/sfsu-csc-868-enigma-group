/*
 *
 */
package Database;

import Property.Property;
import Property.Railroad;
import java.sql.*;
import java.util.ArrayList;


/***
 * This class is a wrapper for the SQL queries to the database for the Railroad
 * objects. It also creates objects of these classes and save the new
 * object information to the database when a new Game is started.
 * 
 * @author Cheryl Nielsen
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
    

    /***
     * Static function for the singleton design pattern, this is 
     * used by other classes instead of calling the constructor.
     * 
     * @return RailroadDatabaseController the existing instance, or a new object if no instance currently exists
     */
    public static RailroadDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new RailroadDatabaseController();
        }
        
        return instance;
    }
            
    
    /*****
     * Private constructor for the singleton design pattern.
     */
   private RailroadDatabaseController() 
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
   * This gets the Railroad from the database that corresponds to the unique id key combination.
   * If successful it returns a new Railroad object with the data values belonging 
   * to that Railroad, otherwise it returns a NULL Railroad object.
   * 
    * @param gameID the particular game this Railroad belongs to 
    * @param spaceID the space on the game board where this Railroad is located
    * @return Railroad 
    */
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
   * This updates the database information for the given Railroad.
   * Precondition: The Railroad object to be saved to the database must already 
   * have been updated as needed, and exist in the database.
   * 
   * @param railroad the Railroad to update in the database
   * @return boolean if successful returns true, otherwise it returns false
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
   
   
   
   /***
    * This creates a new set of Railroad objects associated with the unique id key of a particular
    * game, saves them to the database, and returns them in an ArrayList.
    * If not successful it returns a NULL ArrayList.
    * 
    * @param gameID the game id
    * @return ArrayList of Railroad Properties
    */
   public ArrayList<Property> addAllRailroadsToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        ArrayList<Property> railroadList = new ArrayList<Property>();
                
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
   

   /***
    * This is used to determine how many Railroads are owned by the player with the given unique id key.
    * This is needed to calculate the rent for a Railroad.
    * 
    * @param ownerID the player's id
    * @return int the number of Railroads owned by that player
    */
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
   
   
   
   /***
    * Deletes the set of all Railroads from the database that belong to the given game unique key id
    * @param gameID the bank id
    * @return boolean returns true if successful, or false if not successful
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
