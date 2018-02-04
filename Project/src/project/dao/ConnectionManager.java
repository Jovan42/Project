package project.dao;

import project.res.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jovan on 30-Jan-18.
 */
public class ConnectionManager {
    private static Connection connection;

    public static void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Strings.DATABASE + "?useSSL=false",
                    Strings.DB_USER_NAME, Strings.DB_PASSWORD);
            System.out.println("otbvorena konekcija");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }
}
