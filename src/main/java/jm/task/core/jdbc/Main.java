package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {



        UserService userService = new UserServiceImpl();

        userService.createUsersTable(); //Создание таблицы

        userService.saveUser("Вася", "Васенька", (byte)25);
        userService.saveUser("Петя", "Петров", (byte)45);
        userService.saveUser("Стас", "Максимов", (byte)19);
        userService.saveUser("Максим", "Максимыч", (byte)38);
        userService.saveUser("Стас", "Максимов", (byte)19);
        userService.saveUser("Максим", "Максимыч", (byte)38);

        System.out.println(userService.getAllUsers()); // вывод юзеров
        
        userService.cleanUsersTable(); //  очистка таблицы User(ов)

        userService.dropUsersTable(); // удаление таблицы

        Util.getClosedConnection();




    }
}
