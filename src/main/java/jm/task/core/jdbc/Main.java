package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Test_name1", "Test_lastname1", (byte) 20);
        userService.saveUser("Test_name2", "Test_lastname2", (byte) 30);
        userService.saveUser("Test_name3", "Test_lastname3", (byte) 40);
        userService.saveUser("Test_name4", "Test_lastname4", (byte) 50);
        userService.saveUser("Test_name5", "Test_lastname5", (byte) 60);
        userService.saveUser("Test_name6", "Test_lastname6", (byte) 70);
        userService.saveUser("Test_name7", "Test_lastname7", (byte) 80);
        userService.saveUser("Test_name8", "Test_lastname8", (byte) 90);

        System.out.println(userService.getAllUsers());

        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();







    }
}
