package DbAccessObj;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @Class MyConnection: This Connects the Imperial Mills Recipe App up with the database.
 * @author Sean
 */
class MyConnection {
    public Connection connection = null;
    /**
     * @Method: getConnection
     * @Detail: This tries to connect to the MySQL imrdb database that is running in
     * the background.
     * @return The connection that it created
     */
    public Connection getConnection(){
        System.out.println("------Mysql Connection Test------");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Where is MySQL Driver?");
            ex.printStackTrace();
            //return;
        }
        System.out.println("Mysql Driver Registered!");
        //Connection connection = null;
        connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://192.168.101.222:3306/imrdb","imperialmills","knickt25b");
        } catch (SQLException ex) {
            System.out.println("Connection Failed! Check output console");
            ex.printStackTrace();
            //return;
        }
        if (connection != null) {
            System.out.println("You made it, take control you database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
}