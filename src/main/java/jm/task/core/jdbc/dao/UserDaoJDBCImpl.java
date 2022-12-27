package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util = new Util(); // подключение к БД
    private User user = new User(); // класс дял работы с юзерами
    private String nameTable = "User"; // имя таблицы
    private String tableYN = "SHOW TABLES LIKE '"+nameTable+"'"; // запрос на проверку существования таблицы
    private String createUsersTableStr = "CREATE TABLE " +nameTable+ " ( id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (255), lastname VARCHAR (255), age INT );"; // создание таблицы для User
    private String dropUsersTableStr = "DROP TABLE " + nameTable; // // Удаление таблицы User(ов)
    private String saveUserStr = "INSERT "+ nameTable +"(id, name, lastname, age) VALUES (?,?,?,?);"; // добавления юзера в таблицу
    private String removeUserByIdStr = "DELETE FROM "+nameTable+" where id = "; // удаления юзера по id
    private String getAllUsersStr = "SELECT * FROM "+nameTable; //  получение всех юзеров с таблицы
    private String cleanUsersTableStr = "DELETE FROM "+nameTable; //  очистить таблицу


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  { // создание таблицы для User
        try (Statement statement = util.getConnection().createStatement(); ResultSet resultSet =  statement.executeQuery(tableYN);) {
            if (!resultSet.next()) {
                statement.execute(createUsersTableStr);
            }
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при проверке на существование  " + sqlException.getMessage());
        }
    }

    public void dropUsersTable() { // Удаление таблицы User(ов)
        try (Statement statement = util.getConnection().createStatement();){
            statement.executeUpdate(dropUsersTableStr);
            System.out.println("Удалили таблицу");
        } catch (SQLException sqlException){

        }
    }

    public void saveUser(String name, String lastName, byte age) { // добавления юзера в таблицу
        try ( PreparedStatement preparedStatement = util.getConnection().prepareStatement(saveUserStr)){
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, lastName);
                preparedStatement.setByte(4, age);
                preparedStatement.execute();
            System.out.println("User с именем – "+ name+ " добавлен в базу данных  ");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка в добалении юзера : " + sqlException.getMessage());
        }
    }

    public void removeUserById(long id) { // удаления юзера по id
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(removeUserByIdStr+id);
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при удалении юзер : "+sqlException.getMessage());
        }
    }

    public List<User> getAllUsers() { // получение всех юзеров с таблицы
        List <User> userList = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(getAllUsersStr)){
                while (resultSet.next()) {
                    userList.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"))
                    );
                }
        } catch (SQLException sqlException) {
            System.out.println("Ошикба добавления юзер в List");
        }
        return  userList;
    }


    public void cleanUsersTable() { // очистить таблицу
        try (Statement statement = util.getConnection().createStatement()){
             statement.executeUpdate(cleanUsersTableStr);
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при очистки такблицы " + sqlException.getMessage());
        }
    }
}
