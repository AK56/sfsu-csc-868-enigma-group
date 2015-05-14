/*
 *
 */
package Data;

import Property.Property;
import Property.Utility;
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
    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static UtilityDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new UtilityDatabaseController();
        }
        
        return instance;
    }
            
    
    // private constructor for the singleton design pattern
   private UtilityDatabaseController() 
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
   public Utility getUtilityByID(int gameID, int spaceID)
   {       
       String query;
       Utility utility = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM utility_constants " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );   
            
             if(resultSet.next())
             {  
                String name = resultSet.getString("name");
                int price = resultSet.getInt("purchase_price");
                int mortgage = resultSet.getInt("mortgage_amount");
                int owner = resultSet.getInt("player_owner_id");
                boolean hasMortgage = (resultSet.getInt("has_mortgage") == 1);
                        
                utility = new Utility();
                utility.setSpaceID(spaceID);
                utility.setOwnerID(owner); 
                utility.setMortgageAmount(mortgage);
                utility.setIsMortgaged(hasMortgage);
                utility.setPurchasePrice(price);
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
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
   public boolean updateUtility(Utility utility, int gameID)
   {      
       String query;
       boolean success = true;
       boolean hasMortgage = utility.getIsMortgaged();
       int spaceID = utility.getSpaceID();
       int owner = utility.getOwnerID();
       
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
   
   
   
   /*****
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public ArrayList<Property> addBothUtilitiesToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        int mortgage;
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
                mortgage = resultSet.getInt("mortgage_amount");
                
                query = "INSERT INTO utility_game_data (space_id, game_id, name, has_mortgage) "
                        + "VALUES ( '" + space + "', '" + gameID + "', '" + name + "', 'false' ) ";
                
                statement.executeUpdate(query);
                
                Utility utility = new Utility();
                utility.setSpaceID(space);
                utility.setOwnerID(owner); 
                utility.setMortgageAmount(mortgage);
                utility.setIsMortgaged(false);
                utility.setPurchasePrice(price);       
                
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
   

   public boolean doesPlayerHaveMonopoly(int playerID)
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
                    "WHERE player_owner_id = '" + playerID + "' "; 
            
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
   
   
   
   /**
    * 
    * @param spaceID
    * @return true if successful, else false
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
