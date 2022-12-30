package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root1";
    private static Connection connection; // подключаемся к БД_MySql
    private static Configuration configuration; // подключаем наш ресурс файл для Hibernate + обозначаем нашу сущность.
    private static SessionFactory sessionFactory; // sessionFactory для работы с Hibernate
    private static Session session; // Сессия для Hibernate
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

    public static Configuration getConfiguration() {
        return configuration = new Configuration().addAnnotatedClass(User.class);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory = getConfiguration().buildSessionFactory();
    }

    public static Session getSession() {
        return session = getSessionFactory().getCurrentSession();
    }


}
