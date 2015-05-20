/*
 *
 */
package Database;

import Game.Bank;
import Game.BankAccount;
import java.sql.*;
import java.util.ArrayList;


/***
 * This class is a wrapper for the SQL queries to the database for the Bank and 
 * BankAccount objects. It also creates objects of these classes and save the new
 * object information to the database when a new Game is started.  These objects 
 * are fundamentally linked, because only the Bank owns and has access to the BankAccounts.
 * 
 * @author Cheryl Nielsen
 */
public class BankDatabaseController
{    
    // The URL specifying the MySQL database to which this program
    // connects using JDBC.      
    private final String url = "jdbc:mysql://localhost:3306/monopoly";  
    private final String username = "root";
    private final String password = "space1987";
    
    // for the singleton design patter to ensure that only one class has access 
    // to the database for data integrity and security
    private static BankDatabaseController instance;    
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
     * @return BankDatabaseController the existing instance, or a new object if no instance currently exists
     */
    public static BankDatabaseController getInstance() 
    {
        if(instance == null) {
         instance = new BankDatabaseController();
        }
        
        return instance;
    }
            
    
    /*****
     * Private constructor for the singleton design pattern.
     */
   private BankDatabaseController() 
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
   * This gets the Bank from the database that corresponds to the unique id key.
   * If successful it returns a new Bank object with the data values belonging 
   * to that Bank, otherwise it returns a NULL Bank object.
   * 
    * @param bankID the id of the bank
    * @return Bank the new object or null
    */
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


      
   /***
   * This gets the BankAccount from the database that corresponds to the unique id key.
   * If successful it returns a new BankAccount object with the data values belonging 
   * to that BankAccount, otherwise it returns a NULL BankAccount object.
   * 
    * @param accountID the id of the account
    * @return BankAccount the new object or null
    */
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
   
   

   /***
   * This gets the entire set of BankAccounts from the database that corresponds
   * to the unique id key of a particular Bank.
   * If not successful it returns a NULL ArrayList.
   * 
    * @param bankID the id of the bank
    * @return ArrayList of BankAccounts
    */
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
   * This updates the database information for the number of houses belonging to 
   * the Bank with the given unique id key. 
   * 
   * @param bankID the unique id key of the Bank in the database
   * @param numberOfHouses the new number of houses owned by that Bank
   * @return boolean if successful returns true, otherwise it returns false
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
   * This updates the database information for cash balance of the given BankAccount.
   * Precondition: The new cash balance to be saved to the database must already have been 
   * updated in the given BankAccount object, and exist in the database.
   * 
   * @param account the BankAccount to update in the database
   * @return boolean if successful returns true, otherwise it returns false
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
   

   /***
    * This creates a new Bank object and saves it to the database.
    * If successful it returns a new Bank object, otherwise it returns a NULL Bank object.
    * 
    * @param playerIDs all the players that need to have new BankAccounts in the new Bank.
    * @return Bank the new object or null
    */
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
   
     
   /***
    * This creates a new BankAccount object and saves it to the database.
    * If successful it returns a new BankAccount object, otherwise it returns a NULL BankAccount object.
    * 
    * @param playerID the Player that has the new BankAccount
    * @param bankID the Bank that owns the new BankAccount
    * @return BankAccount the new object or null
    */
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
   

   
   /***
    * Deletes the Bank with the given unique id key from the database.
    * @param bankID the id of the Bank
    * @return boolean returns true if successful, or false if not successful
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
   
   
   /***
    * Deletes the BankAccount with the given unique id key from the database.
    * @param accountID the id of the bank account
    * @return boolean returns true if successful, or false if not successful
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
