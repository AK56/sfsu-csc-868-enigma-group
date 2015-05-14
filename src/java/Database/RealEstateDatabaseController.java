/*
 *
 */
package Data;

import Property.Property;
import Property.RealEstate;
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
public class RealEstateDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "punjabi23";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static RealEstateDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static RealEstateDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new RealEstateDatabaseController();
        }
        
        return instance;
    }
            
    
    // private constructor for the singleton design pattern
   private RealEstateDatabaseController() 
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
   
   
   /******* Property database functions for RealEstates, Utilities, and Realestate *************/
   

   /*****
   // gets user from the database that corresponds to that user's unique id 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   * ****/
   public RealEstate getRealEstateByID(int gameID, int spaceID)
   {       
       String query1, query2;
       RealEstate realestate = null;
       
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
                realestate = new RealEstate();
                realestate.setSpaceID(spaceID);                
                realestate.setName(resultSet.getString("name"));
                realestate.setMortgageAmount(resultSet.getInt("mortgage_amount"));
                realestate.setPurchasePrice(resultSet.getInt("purchase_price"));                
                realestate.setColor(resultSet.getString("color_group"));
                realestate.setCostOfAHouse(resultSet.getInt("cost_of_a_house"));
                realestate.setNumberForMonopoly(resultSet.getInt("number_for_monopoly"));
                
                query2 = "SELECT * FROM realestate_game_data " +
                    "WHERE space_id = '" + spaceID + "', " + 
                    "AND game_id = '" + gameID + "' "; 
            
                statement = connection.createStatement();
                resultSet = statement.executeQuery( query2 ); 
            
                if(resultSet.next())
                {
                    realestate.setOwnerID(resultSet.getInt("player_owner_id")); 
                    realestate.setIsMortgaged(resultSet.getInt("has_mortgage") == 1);
                    realestate.setNumberOfHouses(resultSet.getInt("number_houses"));
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
  
   
   
   /*****
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
   public boolean updateRealEstate(RealEstate realestate, int gameID)
   {      
       String query;
       boolean success = true;
       boolean hasMortgage = realestate.getIsMortgaged();
       int spaceID = realestate.getSpaceID();
       int owner = realestate.getOwnerID();
       int numHouses = realestate.getNumberOfHouses();
       
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
   
   
   
   /*****
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public ArrayList<RealEstate> addAllRealEstatesToGame(int gameID)
   {
       String query;
        int space;
        // flag for not owned by a player, but owned by the bank
        int owner = -1;
        String name;
        int price;
        int mortgage;
        ArrayList<RealEstate> realestateList = new ArrayList<RealEstate>();
                
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
                mortgage = resultSet.getInt("mortgage_amount");
                
                query = "INSERT INTO realestate_game_data (space_id, game_id, name, number_houses, has_mortgage) "
                        + "VALUES ( '" + space + "', '" + gameID + "', '" + name + "', '0', '0' ) ";
                
                statement.executeUpdate(query);
                
                RealEstate realestate = new RealEstate();
                realestate.setSpaceID(space);
                realestate.setOwnerID(owner); 
                realestate.setName(name);
                realestate.setMortgageAmount(mortgage);
                realestate.setIsMortgaged(false);
                realestate.setPurchasePrice(price);       
                
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
   

   public boolean doesPlayerHaveMonopoly(int ownerID, int numberForMonopoly)
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
                        
            query = "SELECT * FROM realestate_game_data " +
                    "WHERE player_owner_id = '" + ownerID + "' "; 
            
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
   
   
   
   /**
    * 
    * @param spaceID
    * @return true if successful, else false
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
