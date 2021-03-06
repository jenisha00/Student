/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

/**
 *
 * @author Jenisha Munikar
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {
    
    public Connection con;                                             
    // The database connection object.
    public Statement stat;  
    // the database statement object, used to execute SQL commands.
    public ResultSet rs;
    
    public void setConnection(Connection connection){
        this.con = connection;
    }
    
    public Connection getConection(){
        return con;
    }

    public Database (String username, String password ) { 
    // the constructor for the database manager.
        
        if (con == null){
            String url = "jdbc:mysql://localhost:3306/" + "student"+
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";      
            // our database--username is your O'Reilly login username and is passed in.      
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");                           
            // get the driver for this database.
            System.out.println("Driver is set; ready to go!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return;                                                            
            // cannot even find the driver--return to caller since cannot do anything.
        }

        try { // Establish the database connection, create a statement for execution of SQL commands.
            con = (Connection) DriverManager.getConnection (url, username, password );  
            // username and password are passed into this Constructor.
            stat  = (Statement) con.createStatement();                            
            // statement used to do things in the database (e.g., create the PhoneBook table).
        }catch (SQLException exception ) {
            System.out.println ("\n*** SQLException caught ***\n");
            while (exception != null) 
            {                                   // grab the exception caught to tell us the problem.
                System.out.println ("SQLState:   " + exception.getSQLState()  );
                System.out.println ("Message:    " + exception.getMessage()   );
                System.out.println ("Error code: " + exception.getErrorCode() );
                exception = exception.getNextException ();
                System.out.println ( "" );
            }
        }
        }
        
       
         // perhaps there is an exception that was not SQL related.
         // shows a trace of the exception error--like we see in the console.

    }
}

