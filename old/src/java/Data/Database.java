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
public class Database
{
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static Database instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static Database getInstance() 
    {
        return instance;
    }
            
    // private constructor for the singleton design pattern
   private Database() 
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
   
   
   /*************** User and Player database functions for ******************/
   /*************** registration, login, game start, game end **************/
   

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
   
   
   // Saves the new user registration information to the database so it can
   // be used in logins later.  The database then assigns the user a user id.
   // 
   // if successful returns true
   // if cannot save user returns false
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


   public boolean updateUserLogin(String username, String password)
   {
       // if a match was found the user cannot be saved because they must have
       // a unique username and password combination
       if(doesUserLoginExist(username, password)) return false;
       
       boolean success = false;
       return false;
   }
   
   

}
