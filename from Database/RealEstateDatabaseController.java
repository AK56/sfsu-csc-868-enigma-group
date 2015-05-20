/*
 *
 */
package Database;

import Property.Property;
import Property.RealEstate;
import java.sql.*;
import java.util.ArrayList;


/***
 * This class is a wrapper for the SQL queries to the database for the RealEstate 
 * objects. It also creates objects of these classes and save the new
 * object information to the database when a new Game is started.
 * 
 * @author Cheryl Nielsen
 */
public class RealEstateDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static RealEstateDatabaseController instance;    
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
     * @return RealEstateDatabaseController the existing instance, or a new object if no instance currently exists
     */
    public static RealEstateDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new RealEstateDatabaseController();
        }
        
        return instance;
    }
            
    
    /*****
     * Private constructor for the singleton design pattern.
     */
   private RealEstateDatabaseController() 
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
   * This gets the RealEstate from the database that corresponds to the unique id key combination.
   * If successful it returns a new RealEstate object with the data values belonging 
   * to that RealEstate, otherwise it returns a NULL RealEstate object.
   * 
    * @param gameID the particular game this RealEstate belongs to 
    * @param spaceID the space on the game board where this RealEstate is located
    * @return RealEstate 
    */
   public RealEstate getRealEstateByID(int gameID, int spaceID)
   {       
       String query1, query2;
       RealEstate realestate = null;
       ArrayList<Integer> rents = new ArrayList<Integer>();
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query1 = "SELECT * FROM realestate_constants " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query1 );   
            
             if(resultSet.next())
             {                
                String name = resultSet.getString("name");
                int price = resultSet.getInt("purchase_price");                
                String color = resultSet.getString("color_group");
                int costOfAHouse = resultSet.getInt("cost_of_a_house");
                int numForMonopoly = resultSet.getInt("number_for_monopoly");
                
                query2 = "SELECT * FROM realestate_game_data " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 
            
                statement = connection.createStatement();
                resultSet = statement.executeQuery( query2 );             
                
                if(resultSet.next())
                {
                    realestate = new RealEstate();
                    
                    int owner = resultSet.getInt("player_owner_id"); 
                    boolean mortgaged = (resultSet.getInt("has_mortgage") == 1);
                    int numberOfHouses = resultSet.getInt("number_houses");
                    
                    rents = getRents(spaceID);
                    
                    realestate.initialize(owner, spaceID, name, price, costOfAHouse, mortgaged, 
                            color, numberOfHouses, numForMonopoly, rents, gameID);
                }
             }
             
             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get realestate from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return realestate;
       }
   }
  
   
   
   /***
    * This creates an ArrayList of rents for a given RealEstate unique id key spaceID from the database.
    * If not successful it returns a NULL ArrayList.
    * 
    * @param spaceID the space location on the game board
    * @return ArrayList of Integer rents
    */  
   public ArrayList<Integer> getRents(int spaceID)
   {
       ArrayList<Integer> rents = new ArrayList<Integer>();
       String query;
       
       try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM realestate_constants " +
                    "WHERE space_id = '" + spaceID + "' ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             if(resultSet.next())
             {    
                 rents.add(resultSet.getInt("rent_0_houses"));
                 rents.add(resultSet.getInt("rent_1_houses"));
                 rents.add(resultSet.getInt("rent_2_houses"));
                 rents.add(resultSet.getInt("rent_3_houses"));
                 rents.add(resultSet.getInt("rent_4_houses"));
                 rents.add(resultSet.getInt("rent_5_houses"));
             }
            
            resultSet.close();
            statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add 4 new realestates" );
            sqlex.printStackTrace();
         }
        finally
        {
            return rents;
        }
       
   }
   
 
   
   /***** 
   * This updates the database information for the given RealEstate.
   * Precondition: The RealEstate object to be saved to the database must already 
   * have been updated as needed, and exist in the database.
   * 
   * @param realestate the RealEstate to update in the database
   * @return boolean if successful returns true, otherwise it returns false
   * ****/
   public boolean updateRealEstate(RealEstate realestate)
   {      
       String query;
       boolean success = true;
       boolean hasMortgage = realestate.getIsMortgaged();
       int spaceID = realestate.getSpaceID();
       int owner = realestate.getOwnerID();
       int numHouses = realestate.getNumberOfHouses();
       int gameID = realestate.getGameID();
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }
            
            query = "UPDATE realestate_game_data " +
                    "SET has_mortgage = '" + hasMortgage + "', player_owner_id = '" + owner + ", " + "', number_houses = '" + numHouses + ", " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 

            statement.executeUpdate(query);
            resultSet.close();
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to update realestate" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
   
   
   /***
    * This creates a new set of RealEstate objects associated with the unique id key of a particular
    * game, saves them to the database, and returns them in an ArrayList.
    * If not successful it returns a NULL ArrayList.
    * 
    * @param gameID the game id
    * @return ArrayList of RealEstate Properties
    */
   public ArrayList<Property> addAllRealEstatesToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        String color;
        int numForMonopoly;
        int costOfAHouse;
        ArrayList<Property> realestateList = new ArrayList<Property>();
        ArrayList<Integer> rents = new ArrayList<Integer>();
                
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM realestate_constants";
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                space = resultSet.getInt("space_id");
                name = resultSet.getString("name");
                price = resultSet.getInt("purchase_price");
                color = resultSet.getString("color_group");
                numForMonopoly = resultSet.getInt("number_for_monopoly");
                costOfAHouse = resultSet.getInt("cost_of_a_house");
                        
                query = "INSERT INTO realestate_game_data (space_id, game_id, name, number_houses, has_mortgage) "
                        + "VALUES ( '" + space + "', '" + gameID + "', '" + name + "', '0', '0' ) ";
                
                statement.executeUpdate(query);                
                RealEstate realestate = new RealEstate();
                rents = getRents(space);
                
                realestate.initialize(owner, space, name, price, costOfAHouse, false, 
                            color, 0, numForMonopoly, rents, gameID);
                
                realestateList.add(realestate);     
                
             }   
             
             resultSet.close();
            statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add 4 new realestates" );
            sqlex.printStackTrace();
         }
        finally
        {
            return realestateList;
        }
       
   }
   

   
    /***
    * This is used to determine the player with the given unique id key owns the required number
    * of the given RealEstate color set to qualify as a monopoly of that color.
    * This is needed to calculate the rent for a RealEstate.  
    * 
    * @param ownerID the player's id
    * @param numberForMonopoly the number of real estate properties needed in that color to be a monopoly
    * @param color the color set being checked for a monopoly with that player owner
    * @return boolean are both of the Utility owned by that player
    */
   public boolean doesPlayerHaveMonopoly(int ownerID, int numberForMonopoly, String color)
   {
       String query;
       boolean monopoly = false;
       int numOwned = 0;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM realestate_game_data, realestate_constants " +
                    "WHERE realestate_game_data.player_owner_id = '" + ownerID + "' " +
                    "AND realestate_constants.color_group = '" + color + "' ";
                         
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             while(resultSet.next())
             {  
                numOwned++;
             }

            resultSet.close();
            statement.close();   
            
            monopoly = (numOwned == numberForMonopoly);
            
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get realestate from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return monopoly;
       }
   }
   
   
   
   /***
    * Deletes all of the RealEstate from the database that belong to the given game unique key id
    * @param gameID the game id in the database
    * @return boolean returns true if successful, or false if not successful
    */
   public boolean deleteAllGameRealEstates(int gameID)
   {      
       String query;
       boolean success = false;
       
       try 
        {
            if(connection == null)
            {   getDatabaseConnection();
            }            
			
            query = "DELETE FROM realestate_game_data " + 
                    "WHERE game_id = '" + gameID + "' ";  
            
            statement.executeUpdate(query);            
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to delete the realestates" );
           sqlex.printStackTrace();
        }
       finally
       {
           return success;
       }
   }
   
  
   
}
