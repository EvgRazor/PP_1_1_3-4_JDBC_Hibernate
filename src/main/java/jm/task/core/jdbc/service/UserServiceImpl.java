package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl createUsersTable = new UserDaoJDBCImpl();
    public void createUsersTable() {
        createUsersTable.createUsersTable();
    }

    public void dropUsersTable() {
        createUsersTable.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        createUsersTable.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        createUsersTable.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return createUsersTable.getAllUsers();
    }

    public void cleanUsersTable() {
        createUsersTable.cleanUsersTable();
    }
}
