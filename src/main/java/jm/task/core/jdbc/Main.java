package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable(); //Создание таблицы

        userDaoJDBC.saveUser("Вася", "Васенька", (byte)25); // добавления
        userDaoJDBC.saveUser("Петя", "Петров", (byte)45); // добавления
        userDaoJDBC.saveUser("Стас", "Максимов", (byte)19); // добавления
        userDaoJDBC.saveUser("Максим", "Максимыч", (byte)38); // добавления
        userDaoJDBC.saveUser("Стас", "Максимов", (byte)19); // добавления
        userDaoJDBC.saveUser("Максим", "Максимыч", (byte)38); // добавления

        System.out.println(userDaoJDBC.getAllUsers()); // вывод юзеров

        //userDaoJDBC.removeUserById(1); // удаление по ID

        userDaoJDBC.cleanUsersTable(); //  очистка таблицы User(ов)

       userDaoJDBC.dropUsersTable(); // удаление таблицы



    }
}
