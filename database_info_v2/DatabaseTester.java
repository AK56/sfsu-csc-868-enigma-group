/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 *
 * @author Cheryl
 */
public class DatabaseTester extends JFrame
{
    private static final long serialVersionUID = -9131750957418024148L;
    private Connection connection;
    private JTable table;
    JPanel container;
    Container content;
    private Statement statement;
    private ResultSet resultSet;
    
    
   public DatabaseTester() 
   {   
      // The URL specifying the database to which 
      // this program connects using JDBC to connect to a
      // Microsoft ODBC database.
      
      String url = "jdbc:mysql://localhost:3306/monopoly";  
      String username = "root";
      String password = "space1987";
      
      // Load the driver to allow connection to the database
      try {
         Class.forName( "com.mysql.jdbc.Driver" );
         connection = DriverManager.getConnection( url, username, password);
      } 
      catch ( ClassNotFoundException cnfex ) {
         System.err.println( "Failed to load the database driver." );
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to connect to the database" );
	}
      
      // setup the JFrame GUI
      container = new JPanel();
      // setup scroll for outer window so all tables can be seen together
      JScrollPane scrPane = new JScrollPane(container);
      getContentPane().add(scrPane);
      container.setBackground(Color.white);
      setTitle( "Database SQL Results" );  
      setSize( 1000, 550 );
      
   }
   
   
   // Runs the query SELECT * FROM customer,
   // which should return all the contents of the customer table.
   private void getUserTable()
   {
        String titleForResultTable = "User";
	   
      try {
         String query = "SELECT * FROM user";  
         
         statement = connection.createStatement();
         resultSet = statement.executeQuery( query );
         displayResultSet( resultSet, titleForResultTable );
         resultSet.close();
         statement.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to get the data" );
      }
   }
   
   
   // Runs the query SELECT * FROM car,
   // which should return all the contents of the car table.
   private void getPlayerTable()
   {
	   String titleForResultTable = "Player";
            
      try {
         String query = "SELECT * FROM player";  

         statement = connection.createStatement();
         resultSet = statement.executeQuery( query );
         displayResultSet( resultSet, titleForResultTable );
         resultSet.close();
         statement.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to get the data" );
      }
   }
   

   
   // Runs one sample query for information.
   // This should return the names and addresses of all the customers
   // who purchased a green car.
   private void getSQL_Result()
   {
	   String titleForResultTable = "SQL Query: get the realestate with color green.";
            
      try {   

    	  String query = "SELECT * FROM realestate_constants, railroad_game_data" +
                        "WHERE realestate_constants.color_group = 'Green' " +
                        "AND realestate.player_owner_id = '1'";
    	  
         statement = connection.createStatement();
         resultSet = statement.executeQuery( query );
         displayResultSet( resultSet, titleForResultTable );
         resultSet.close();
         statement.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to get the sql result" );
         sqlex.printStackTrace();
      }
      
   }
   

   // Displays the results of the above queries as tables on the screen GUI.
   private void displayResultSet( ResultSet rs, String titleForResultTable )
      throws SQLException
   {
      // position to first record
      boolean moreRecords = rs.next();  

      // If there are no records, display a message
      if ( ! moreRecords ) {
         JOptionPane.showMessageDialog( this, 
            "ResultSet contained no records" );
         setTitle( "No records to display" );
         return;
      }

      Vector columnHeads = new Vector();
      Vector rows = new Vector();

      try {
         // get column heads
         ResultSetMetaData rsmd = rs.getMetaData();
      
         for ( int i = 1; i <= rsmd.getColumnCount(); ++i ) 
            columnHeads.addElement( rsmd.getColumnName( i ) );

         // get row data
         do {
            rows.addElement( getNextRow( rs, rsmd ) ); 
         } while ( rs.next() );

         // display table with ResultSet contents
         table = new JTable( rows, columnHeads );
         JScrollPane scroller = new JScrollPane( table );
         scroller.setBorder (BorderFactory.createTitledBorder (titleForResultTable));
         container.add(scroller);

      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to get the data" );
      }
   }

   
   // Helper function to get the data for each row and data type
   // of the current query result.
   private Vector getNextRow( ResultSet rs, 
                              ResultSetMetaData rsmd )
       throws SQLException
   {
      Vector currentRow = new Vector();
      
      for ( int i = 1; i <= rsmd.getColumnCount(); ++i )
         switch( rsmd.getColumnType( i ) ) {
            case Types.VARCHAR:
                  currentRow.addElement( rs.getString( i ) );
               break;
            case Types.INTEGER:
                  currentRow.addElement( 
                     new Long( rs.getLong( i ) ) );
               break;
            case Types.DATE:
                currentRow.addElement( rs.getDate( i ) );
             break;
            default: 
               System.out.println( "Type was: " + 
                  rsmd.getColumnTypeName( i ) );
         }
      
      return currentRow;
   }

   
   // Clean up before exiting by closing all connections to the database.
   public void shutDown()
   {
      try {
    	 resultSet.close();
    	 statement.close();
         connection.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to disconnect from the database" );
      }
   }

   
   // starting point for the application
   public static void main( String args[] ) 
   {
      final DatabaseTester app = new DatabaseTester(); 
      // sets up the GUI for viewing
      app.setVisible(true);
      app.validate();            
      app.getUserTable(); 
      // refresh the GUI each time for the new content
      app.validate();
      app.getPlayerTable();
      app.validate();
      //app.getRailroadConstantsTable();
      //app.validate();
      app.getSQL_Result();
      app.validate();
      
      // GUI listener for the exit button on the application window.
      app.addWindowListener( 
         new WindowAdapter() {
            public void windowClosing( WindowEvent e ) 
            {  
               app.shutDown();
               System.exit( 0 );
            }
         }
      );
   }

}
