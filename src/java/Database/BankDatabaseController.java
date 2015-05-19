/*
 *
 */
package Database;

import Game.Bank;
import Game.BankAccount;
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
public class BankDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "punjabi23";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static BankDatabaseController instance;    
    // for connecting to the database
    private Connection connection;
    // for passing sql querries to the database
    private Statement statement;
    // the resulting data produced by a sql querry
    private ResultSet resultSet;    
    
    /*********** constructor stuff *******************/
    
    // static for the singleton design pattern
    // used by other classes instead of calling the constructor
    public static BankDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new BankDatabaseController();
        }
        
        return instance;
    }
            
    
    // private constructor for the singleton design pattern
   private BankDatabaseController() 
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
   
   
   /************* Bank and BankAccount database functions *****************/
   

   /*****
   // gets user from the database that corresponds to that user's unique id 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   * ****/
   public Bank getBankByID(int bankID)
   {       
       String query;
       Bank bank = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM bank " +
                        "WHERE bank_id = '" + bankID + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 bank = new Bank();
                 bank.initialize(resultSet.getInt("bank_id"), resultSet.getInt("number_houses"), getAccountListByBankID(bankID));              
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
           return bank;
       }
   }


      
    /*****
   // gets user from the database that corresponds to that user's unique id 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   * ****/
   public BankAccount getAccountByID(int accountID)
   {       
       String query;
       BankAccount account = null;
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM bankaccount " +
                        "WHERE bankaccount_id = '" + accountID + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             if(resultSet.next())
             {
                 account = new BankAccount();
                 account.initializer(resultSet.getInt("bankaccount_id"), resultSet.getInt("bank_id"), 
                         resultSet.getInt("player_id"), resultSet.getInt("cash_balance"));
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
           return account;
       }
   }
   
   
   /*****
   // gets user from the database that corresponds to that user's unique id 
   // If successful returns a new User object with the data values belonging 
   // to the user, else returns a NULL User object.
   *****/
   public ArrayList<BankAccount> getAccountListByBankID(int bankID)
   {       
       String query;
       ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
       
        try 
         {
            if(connection == null)
            {
                getDatabaseConnection();
            }
             
             query = "SELECT * FROM bankaccount " +
                        "WHERE bankaccount_id = '" + bankID + "' ";

             statement = connection.createStatement();
             resultSet = statement.executeQuery( query );

             // next varifies a first row of results
             // If there is no result row, then no such user exists
             while(resultSet.next())
             {
                 BankAccount account = new BankAccount();
                 account.initializer(resultSet.getInt("bankaccount_id"), resultSet.getInt("bank_id"), 
                         resultSet.getInt("player_id"), resultSet.getInt("cash_balance"));
                 accountList.add(account);
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
   
   
   /*****
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
   public boolean updateBankNumberHouses(int bankID, int numberOfHouses)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
            
            query = "UPDATE bank SET number_houses = '" + numberOfHouses + "' " +
                    "WHERE bank_id = '" + bankID + "' "; 

            statement.executeUpdate(query);
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
   * Saves the user's new login information to the database so it can
   * be used in logins later.  
   * If the new user login information is not unique or the user id
   * is not in the database, then the save will fail and return false.
   * 
   * if successful returns true
   * ****/
   public boolean updateCashBalance(BankAccount account)
   {      
       String query;
       boolean success = true;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }                        
            
            query = "UPDATE bankaccount SET cash_balance = '" + account.getCashBalance() + "' " +
                    "WHERE bankaccount_id = '" + account.getBankAccountID() + "' "; 

            statement.executeUpdate(query);                
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
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public Bank addNewBank(int[] playerIDs)
   {
       String query;
       Bank bank = null;
       ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
       
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                         
             // TO BE DONE ------ USE REAL BANK VALUES
            query = "INSERT INTO bank (number_houses) "
                 + "VALUES ('" + Bank.getStarterNumberHouses() +"') ";
         
            statement = connection.createStatement();
            statement.executeUpdate(query);            
            query = "SELECT * FROM bank";
            resultSet = statement.executeQuery( query );    
            resultSet.last();
             
             // if player was successfully created in the database
             if(resultSet.next())
             {                
                bank = new Bank();
                int bankID = resultSet.getInt("bank_id");
                
                for (int playerID : playerIDs)
                {
                    BankAccount account = addNewBankAccount(bankID, playerID);
                    accountList.add(account);
                }
                
                bank.initialize(bankID, Bank.getStarterNumberHouses(), accountList);
             }

         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add new player" );
            sqlex.printStackTrace();
         }
        finally
        {
            return bank;
        }
       
   }
   
     
   /*****
   // Saves the new player information to the database so it be used in a game.
   // The database then assigns the new player an id, token, space 1, and active status.
   // 
   // If successful returns a new Player object with the data values belonging 
   // to the user, else returns a NULL Player object.
   * ****/
   public BankAccount addNewBankAccount(int bankID, int playerID)
   {
       String query;
       BankAccount account = null;
       
        try 
         {             
            if(connection == null)
            {
                getDatabaseConnection();
            }
                         
             // TO BE DONE ------ USE REAL PLAYER VALUES
            query = "INSERT INTO bankaccount ( bank_id, player_id, cash_balance ) "
                 + "VALUES ('" + bankID + "', '" + playerID + "', '" + BankAccount.getStartingBalance() + "') ";
         
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            query = "SELECT * FROM bankaccount " +
                        "WHERE bankaccount_id = '" + bankID + "' ";

            statement = connection.createStatement();
            statement.executeUpdate(query);            
            query = "SELECT * FROM bankaccount";
            resultSet = statement.executeQuery( query );    
            resultSet.last();
             
             // if player was successfully created in the database
             if(resultSet.next())
             {
                account = new BankAccount();
                int id = resultSet.getInt("bankaccount_id");
                account.initializer(id, bankID, playerID, BankAccount.getStartingBalance());
             }

         }
         catch ( SQLException sqlex ) 
         {
            System.err.println( "Unable to add new player" );
            sqlex.printStackTrace();
         }
         finally
        {
           return account;
        }
       
   }
   
   

   
   /**
    * 
    * @param bankID
    * @return true if successful, else false
    */
   public boolean deleteBank(int bankID)
   {      
       String query;
       boolean success = false;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "DELETE FROM bankaccount " + 
                    "WHERE bank_id = '" + bankID + "' ";  
            
            statement.executeUpdate(query); 
            
            query = "DELETE FROM bank " + 
                    "WHERE bank_id = '" + bankID + "' ";  
            
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
   
   
   /**
    * 
    * @param accountID
    * @return true if successful, else false
    */
   public boolean deleteBankAccount(int accountID)
   {      
       String query;
       boolean success = false;
       
       try 
        {
            if(connection == null)
            {
                getDatabaseConnection();
            }
                        
            query = "DELETE FROM bankaccount " + 
                    "WHERE bankaccount_id = '" + accountID + "' ";      

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
