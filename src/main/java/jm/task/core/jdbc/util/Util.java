package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/my_db_test";
    private final String USERNAME = "root1";
    private final String PASSWORD = "root1";
    private Connection connection; // подключаемся к БД
    public Util () {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при подключении к БД : " + sqlException.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
