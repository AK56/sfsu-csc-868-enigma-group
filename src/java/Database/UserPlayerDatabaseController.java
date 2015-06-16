/*
 *
 */
package Database;

import User.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * This class is a wrapper for the SQL queries to the database for the User and 
 * Player objects.  It also provides the additional logic functionality needed.  
 * For example it ensures that unique login information has been provided
 * before saving new user registration to the database. It also creates objects of these 
 * classes and save the new object information to the database.  A new Player is 
 * created for a User when a new game is started.
 * 
 * @author Cheryl Nielsen
 */
public class UserPlayerDatabaseController
{
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static UserPlayerDatabaseController instance;    
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
    public static UserPlayerDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new UserPlayerDatabaseController();
        }
        
        return instance;
    }
         
    
    /*****
     * Private constructor for the singleton design pattern.
     */
   private UserPlayerDatabaseController() 
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
   
   


   /*****
   * Looks for a match to the login information in the database
   * by testing for a match to the combination of the username and password
   * 
   * @param username login 
   * @param password login
   * @return boolean if match found returns true, if not returns false
   *****/
   public boolean doesUserLoginExist(String username, String password)
   {       
       boolean hasMatch = false;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
            
            String query = "SELECT * FROM user " +
                       "WHERE user.user_name = '" + username + "' " +
                       "AND user.password = '" + password + "' ";

            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );

            // next varifies a first row of results
            // If there is a result rows, then a login match has been found
            // and hasMatch becomes true.
            hasMatch = resultSet.next();  
            
            resultSet.close();
            statement.close();
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "error in database login test" );
           sqlex.printStackTrace();
        }
        finally 
        {
          return hasMatch;
        }

   }
   
   
   /*****
   * Saves the new user registration information to the database so it can
   * be used in logins later.  The database then assigns the user a user id.
   * If the combination of login username and password are already in the database
   * the registration will not succeed, the value of false is returned, and 
   * the new user is not saved to the database.
   * 
   * if successful returns true
   * if cannot save user returns false
   * 
   * @param firstname real name
   * @param lastname real name
   * @param username for login
   * @param password for login
   * @return boolean
   * 
   *****/
   public boolean registerNewUser(String firstname, String lastname, String username, String password)
   {	
       // if a match was found the user cannot be saved because they must have
       // a unique username and password combination
       if(doesUserLoginExist(username, password)) return false;
       
       boolean success = false;
       
       // no match so save the new user registration information
      try 
      {
        if(connection == null)
        {
            getDatabaseConnection();
        }
                    
         String query = "INSERT INTO user (first_name, last_name, user_name, password) "
                 + "VALUES ('" + firstname + "', '" + lastname + "', '" 
                 + username + "', '" + password + "') ";
         
         statement = connection.createStatement();
         statement.executeUpdate(query);
         statement.close();         
         success = true;
         
      }
      catch ( SQLException sqlex ) 
      {
         System.err.println( "Unable to save new user" );
         sqlex.printStackTrace();
      }
      finally 
      {
          return success;
      }
      
   }
   
   
   /*****
   * Gets the user from the database that corresponds to that users
   * unique login information as username and password.
   * 
   * If successful returns a new User object with the data values belonging 
   * to the user, else returns a NULL User object.
   * 
   * @param username login 
   * @param password login 
   * @return User the new User object
   * 
   *****/
   public User getUserByLogin(String username, String password)
   {       
       String query;
       User user = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM user " +
                        "WHERE user_name = '" + username + "' " +
                        "AND password = '" + password + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 user = new User();
                 user.setFirstName(resultSet.getString("first_name"));
                 user.setLastName(resultSet.getString("last_name"));
                 user.setPassword(resultSet.getString("password"));
                 user.setUsername(resultSet.getString("user_name"));
                 user.setUserID(resultSet.getInt("user_id"));
             }

             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get user from database" );
            sqlex.printStackTrace();
         }
        finally 
        {
          return user;
        }

   }


   /*****
   * Gets the User from the database that corresponds to that User's unique id key.
   * If successful returns a new User object with the data values belonging 
   * to the user, else returns a NULL User object.
   * 
   * @param id the user id
   * @return User
   *****/
   public User getUserByID(int id)
   {       
       String query;
       User user = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM user " +
                        "WHERE user_id = '" + id + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 user = new User();
                 user.setFirstName(resultSet.getString("first_name"));
                 user.setLastName(resultSet.getString("last_name"));
                 user.setPassword(resultSet.getString("password"));
                 user.setUsername(resultSet.getString("user_name"));
                 user.setUserID(resultSet.getInt("user_id"));
             }

             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get user from database" );
            sqlex.printStackTrace();
         }
        finally 
        {
          return user;
        }
        
   }



   /*****
   * Saves the User's new login information to the database so it can
   * be used in logins later.  
   * If the new User login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * @param userID the user id in the database
   * @param newUsername new login
   * @param newPassword new login
   * @return boolean if successful returns true, else returns false
   *****/
   public boolean updateUserLogin(int userID, String newUsername, String newPassword)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "SELECT * FROM user " +
                       "WHERE user_id = '" + userID + "' "; 

            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );
            // next varifies a first row of results
            // If there is no result row, then no id match was found
            if(!resultSet.next()) success = false;
            // verify that login info will be unique
            if(doesUserLoginExist(newUsername, newPassword)) success = false;
            
            if(success)
            {
                query = "UPDATE user SET user_name = '" + newUsername + 
                        "', password = '" + newPassword + "' " +
                        "WHERE user_id = '" + userID + "' "; 
                
                statement.executeUpdate(query);                
            }
            
            resultSet.close();
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to update user login" );
           sqlex.printStackTrace();
        }
        finally 
        {
          return success;
        }
    
   }
   
   
   /*****
   * Saves the new player information to the database so it be used in a game.
   * The database then assigns the new player an id, token, space 1, and active status.
   * 
   * If successful returns a new Player object and gives the User the, else returns a NULL Player object.
   * 
   * @param user the user id 
   * @param token_id the token id 
   * @param game_id the game id in the database
   * @return Player
   * 
   *****/
   public Player addNewPlayer(User user, int token_id, int game_id)
   {
       String query;
       Player player = null;
       
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                         
            query = "INSERT INTO player (user_id, token_id, game_id ) "
                 + "VALUES ('" + user.getUserID() + "', '" + token_id + "', '" + game_id + "') ";
         
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            query = "SELECT * FROM player " +
                        "WHERE user_id = '" + user.getUserID() + "' ";

             resultSet = statement.executeQuery( query );
             
             // if player was successfully created in the database
             if(resultSet.next())
             {
                 int id = resultSet.getInt("player_id");
                player = new Player();
                player.setTokenID(token_id);
                player.setSpectator(false);
                player.setSpaceID(1);
                player.setPlayerID(id);
                player.setUserID(user.getUserID());                
                
                query = "UPDATE user SET player_id = '" + id + "' " +
                        "WHERE user_id = '" + user.getUserID() + "' ";
                
                statement.executeUpdate(query);
                user.setPlayerID(player.getPlayerID());
             }

         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add new player" );
            sqlex.printStackTrace();
         }
        finally 
        {
          //return player;
        }
        return player;
   }  
   
   
   
    /*****
    * Gets the User from the database that corresponds to that user's unique id key.
    * If successful returns a new User object with the data values belonging 
    * to the user, else returns a NULL User object.
    * 
    * @param id the player id in the database
    * @return Player
    ****/
   public Player getPlayerByID(int id)
   {       
       String query;
       Player player = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM player " +
                        "WHERE player_id = '" + id + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 player = new Player();
                 player.setPlayerID(resultSet.getInt("player_id"));
                 player.setUserID(resultSet.getInt("user_id"));
                 player.setTokenID(resultSet.getInt("token_id"));
                 player.setSpaceID(resultSet.getInt("space_id") + 1);
                 int i = resultSet.getInt("spectator");
                 boolean spectator = (i == 1);
                 player.setSpectator(spectator);
             }

             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get user from database" );
            sqlex.printStackTrace();
         }
        finally 
        {
          return player;
        }
       
   }
   
  
   /*****
   * Gets the image file name from the database that corresponds to that token's unique id key
   * If successful returns name of the image file on the web server. 
   * 
   * @param token_id the token id in the database
   * @return String file name
   ****/
   public String getTokenFileName(int token_id)
   {       
       String query;
       String fileName = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT image_file_name FROM token " +
                        "WHERE token_id = '" + token_id + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 fileName = resultSet.getString("image_file_name");
             }

             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get user from database" );
            sqlex.printStackTrace();
         }
        finally 
        {
          return fileName;
        }
   }
   
   
   /**
    * Deletes the Player from the database that belong to the given game unique key id
    * @param playerID the id of the player in the database
    * @return true if successful, else false
    */
   public boolean deletePlayer(int playerID)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }  

            query = "UPDATE user SET player_id = NULL " +
                    "WHERE player_id = '" + playerID + "' "; 
            
            statement.executeUpdate(query); 
            
            query = "DELETE FROM player " + 
                    "WHERE player_id = '" + playerID + "' ";     

            statement.executeUpdate(query);
            
            resultSet.close();
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
   
   
      
   /**
    * Deletes the User from the database that belong to the given game unique key id
    * @param userID the id of the user in the database
    * @return true if successful, else false
    */
   public boolean deleteUser(int userID)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "UPDATE user SET player_id = NULL"; 
            statement.executeUpdate(query);
            
            query = "DELETE FROM player " + 
                    "WHERE user_id = '" + userID + "' ";                 
            statement.executeUpdate(query);
            
            query = "DELETE FROM user " + 
                    "WHERE user_id = '" + userID + "' ";                 
            statement.executeUpdate(query);                
            
            resultSet.close();
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
   
     
   /*****
   * Gets a list of Players from the database that corresponds to the game unique id key
   * If successful returns the Player list, else returns a NULL ArrayList object.
   * 
   * @param gameID the id of the game in the database
   * @return ArrayList of Players
   *****/
   public ArrayList<Player> getPlayerListByGameID(int gameID)
   {       
       String query;
       ArrayList<Player> accountList = new ArrayList<Player>();
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM player " +
                        "WHERE game_id = '" + gameID + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             while(resultSet.next())
             {
                 Player player = new Player();
                 player.setPlayerID(resultSet.getInt("player_id"));
                 player.setUserID(resultSet.getInt("user_id"));
                 player.setTokenID(resultSet.getInt("token_id"));
                 player.setSpaceID(resultSet.getInt("space_id") + 1);
                 int i = resultSet.getInt("spectator");
                 boolean spectator = (i == 1);
                 player.setSpectator(spectator);
             }

             resultSet.close();
             statement.close();
         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to get user from database" );
            sqlex.printStackTrace();
         }
       finally
       {
           return accountList;
       }
   }
   
}
