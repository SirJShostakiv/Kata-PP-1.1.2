package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() {
        String hostName = "localhost";
        String dbName = "mydb";
        String userName = "root";
        String password = "wGgfwfyg672";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) {
        try {
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            return DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}