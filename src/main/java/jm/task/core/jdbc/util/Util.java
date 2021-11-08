package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "1234";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch(ClassNotFoundException e) {
            System.out.println("There is a problem with JDBC DRIVER");
        }
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
