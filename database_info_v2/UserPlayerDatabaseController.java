/*
 *
 */
package Data;

import Game.Player;
import User.User;
import java.sql.*;
import java.util.*;


/**
 * This class is a wrapper for the SQL queries to the database for the User and 
 * Player objects.  It also provides the additional logic functionality needed.  
 * For example it ensures that unique login information has been provided
 * before saving new user registration to the database. 
 * 
 * @author Cheryl
 */
public class UserPlayerDatabaseController
{
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static UserPlayerDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static UserPlayerDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new UserPlayerDatabaseController();
        }
        
        return instance;
    }
            
    // private constructor for the singleton design pattern
   private UserPlayerDatabaseController() 
   {          
   }
   
   
   private void getDatabaseConnection()
   {
        // The URL specifying the MySQL database to which this program
        // connects using JDBC.      
        String url = "jdbc:mysql://localhost:3306/monopoly";  
        String username = "root";
        String password = "space1987";

        // Load the driver to allow connection to the database
        try 
        {
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
   
   
   
   /*************** User and Player database functions for the  **************/
   /******* registration and login of Users and Player creation **************/
   

   /*****
   * looks for a match to the login information in the database
   * by testing for a match to the combination of the username and password
   * 
   * if match found returns true, if not returns false
   * ****/
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
       
       return hasMatch;
   }
   
   
   /*****
   // Saves the new user registration information to the database so it can
   // be used in logins later.  The database then assigns the user a user id.
   // 
   // if successful returns true
   // if cannot save user returns false
   * ****/
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
   // gets user from the database that corresponds to that users
   // unique login information as username and password
   // 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   * ****/
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
       
       return user;
   }


   /*****
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
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
                query = "UPDATE user SET user_name=' " + newUsername + 
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
       
       return success;
   }
   
   
   /*****
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public Player addNewPlayer(int userID)
   {
       String query;
       Player player = null;
       
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                         
             // TO BE DONE ------ USE REAL PLAYER VALUES
            query = "INSERT INTO player (user_id, token_id, game_id, space_id, spectator ) "
                 + "VALUES ('" + userID + "', '1', '1', '1', '0') ";
         
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            query = "SELECT * FROM player " +
                        "WHERE user_id = '" + userID + "' ";

             resultSet = statement.executeQuery( query );
             
             // if player was successfully created in the database
             if(resultSet.next())
             {
                player = new Player();
                player.setTokenID(1);
                player.setSpectator(false);
                player.setSpaceID(1);
                player.setPlayerID(1);
                player.setUserID(resultSet.getInt("player_id"));
             }

         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add new player" );
            sqlex.printStackTrace();
         }
       
       return player;
   }
   
   /**
    * 
    * @param userID
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
                        
            query = "SELECT * FROM user " +
                       "WHERE user_id = '" + userID + "' " + 
                        "AND player_id = IS NOT NULL "; 

            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );
            // next varifies a first row of results
            // If there is no result row, then no id match was found
            if(!resultSet.next()) success = false;
            
            if(success)
            {
                query = "DELETE FROM user " + 
                        "WHERE user_id = '" + userID + "' ";                 
                statement.executeUpdate(query);                
            }
            
            resultSet.close();
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to get the sql result" );
           sqlex.printStackTrace();
        }
       
       return success;
   }
   
   
   /**
    * 
    * @param playerID
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
                        
            query = "SELECT * FROM player " +
                       "WHERE player_id = '" + playerID + "' ";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );
            // next varifies a first row of results
            // If there is no result row, then no id match was found
            if(!resultSet.next()) success = false;
            
            if(success)
            {
                query = "DELETE FROM player " + 
                        "WHERE user_id = '" + playerID + "' ";      
                
                statement.executeUpdate(query);  
                
                query = "UPDATE user SET player_id = NULL"; 
                
                statement.executeUpdate(query); 
            }
            
            resultSet.close();
            statement.close();
            success = true;
        }
        catch ( SQLException sqlex ) 
        {
           System.err.println( "Unable to get the sql result" );
           sqlex.printStackTrace();
        }
       
       return success;
   }
   
}
