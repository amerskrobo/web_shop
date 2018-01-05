
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect_db {
     private  Connection conn=null;
     private  String databaseURL = "jdbc:mysql://localhost/web_prodavnica";
     private  String user = "root";
     private  String password = "******";
    public  Connection getConnection() {
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(databaseURL, user, password);
            if(conn!=null){
                System.out.println("Connected to database");
            }
        }catch(ClassNotFoundException ex){
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        }catch(SQLException ex){
            System.out.println("An error occured. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        return conn;
    }
    
}
