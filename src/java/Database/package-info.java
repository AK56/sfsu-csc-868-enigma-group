/***
 * The Database Controller classes are a set of Facades for database access.  
 * Except for the GameDatabaseController, they are used to instantiate the new objects 
 * needed by a new Monopoly game based on the contents of the database.  To prevent 
 * database corruption they also use the Singleton design pattern.   The use of the 
 * Singleton pattern is why they do not all inherit from a common database class.
 * 
 * These 6 classes encapsulates all of the database interactions from the rest of the 
* application so that no other classes will contain SQL or database connections.    
* For brand new entries to the database the ids will be auto generated by the database.  
* The new object being requested is returned by the Database Controller class when 
* the new object is first created and saved to the database.  Special functions 
* specifically for the handling of login search and retrieval of users are also 
* included for ease of use in the UserPlayerDatabaseController.
* 
 */
package Database;