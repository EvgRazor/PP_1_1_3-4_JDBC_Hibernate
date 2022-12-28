package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root1";
    private static Connection connection; // подключаемся к БД

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при подключении к БД : " + sqlException.getMessage());
        }
        return connection;
    }

    public static void getClosedConnection () throws SQLException {
         connection.close();
    }

}
