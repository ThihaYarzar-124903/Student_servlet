package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection con=null;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3307/studentsdb","root","root");
            System.out.println("Connection....");
            
        }catch(ClassNotFoundException e) {
            System.out.println("Driver class not found...");
        }catch(SQLException e) {
            System.out.println("Database not found...");
        }
        return con;
    }
}
