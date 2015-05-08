/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import User.User;
import java.sql.*;
import java.util.*;


/**
 *
 * @author Cheryl
 */
public class UserDatabaseController
{
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static UserDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static UserDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new UserDatabaseController();
        }
        return instance;
    }
            
    // private constructor for the singleton design pattern
   private UserDatabaseController() 
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
   
   
   /*************** User database functions for the  **************/
   /*************** registration and login of users  **************/
   

   /*****
   * looks for a match to the login information in the database
   * by testing for a match to the combination of the username and password
   * 
   * if match found returns true, if not returns false
   * ****/
   public boolean doesUserLoginExist(String username, String password)
   {       
       String query;
       boolean hasMatch = false;
       
       try 
        {
            query = "SELECT * FROM user" +
                       "WHERE user_name = '" + username + "'" +
                       "AND password = '" + password + "'";

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
           System.err.println( "Unable to get the sql result" );
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
         String query = "INSERT INTO user (first_name, last_name, user_name, password)"
                 + "VALUES ('" + firstname + "', '" + lastname + "', '" 
                 + username + "', '" + password + "')";
         
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
             query = "SELECT * FROM user" +
                        "WHERE user_name = '" + username + "'" +
                        "AND password = '" + password + "'";

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
            System.err.println( "Unable to get the sql result" );
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
            query = "SELECT * FROM user" +
                       "WHERE user_id = '" + userID + "'"; 

            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );
            // next varifies a first row of results
            // If there is no result row, then no id match was found
            if(!resultSet.next()) success = false;
            // verify that login info will be unique
            if(doesUserLoginExist(newUsername, newPassword)) success = false;
            
            if(success)
            {
                query = "UPDATE user SET user_name='" + newUsername + 
                        "', password=' " + newPassword + "' " +
                        "WHERE user_id = '" + userID + "'"; 
                
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
    * @param userID
    * @return true if successful, else false
    */
   public boolean deleteUser(int userID)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            query = "SELECT * FROM user" +
                       "WHERE user_id = '" + userID + "'"; 

            statement = connection.createStatement();
            resultSet = statement.executeQuery( query );
            // next varifies a first row of results
            // If there is no result row, then no id match was found
            if(!resultSet.next()) success = false;
            
            if(success)
            {
                query = "DELETE FROM user " + 
                        "WHERE user_id = '" + userID + "'";                 
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
