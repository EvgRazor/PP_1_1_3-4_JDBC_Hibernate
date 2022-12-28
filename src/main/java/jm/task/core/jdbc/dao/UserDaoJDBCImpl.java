package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();
    // не очень  понял, если у нас Util имеет static метод. Мы же могли сразу писать в методах ниже Util.getConnection().....
    // подскажите пожалуйста, для чего вы посоветовали вынести в поля Connection? (или я неправильно понял реализацию совета))

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  { // создание таблицы для User
        try (Statement statement = connection.createStatement(); ResultSet resultSet =  statement.executeQuery("SHOW TABLES LIKE 'User'");) {
            if (!resultSet.next()) {
                statement.execute("CREATE TABLE User ( id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (255), lastname VARCHAR (255), age INT );");
            }
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при проверке на существование  " + sqlException.getMessage());
        }
    }

    public void dropUsersTable() { // Удаление таблицы User(ов)
        try (Statement statement = connection.createStatement();){
            statement.executeUpdate("DROP TABLE User");
            System.out.println("Удалили таблицу");
        } catch (SQLException sqlException){

        }
    }

    public void saveUser(String name, String lastName, byte age) { // добавления юзера в таблицу
        try ( PreparedStatement preparedStatement = connection.prepareStatement("INSERT User(id, name, lastname, age) VALUES (?,?,?,?);")){
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

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User where id = ?")){
             preparedStatement.setLong(1, id);
        } catch (SQLException sqlException){
            System.out.println("Ошибка при удалении : "+sqlException.getMessage());
        }

    }

    public List<User> getAllUsers() { // получение всех юзеров с таблицы
        List <User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM User")){
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
        try (Statement statement = connection.createStatement()){
             statement.executeUpdate("DELETE FROM User");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка при очистки такблицы " + sqlException.getMessage());
        }
    }
}
