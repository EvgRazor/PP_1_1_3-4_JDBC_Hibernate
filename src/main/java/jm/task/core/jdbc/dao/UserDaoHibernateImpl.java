package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User ( id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (255), lastname VARCHAR (255), age INT );").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана ");
        } catch (Exception exception) {
            System.out.println("Таблица уже имеется ");
        } finally {
            Util.getSessionFactory().close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        } catch (Exception exception) {
            System.out.println("Ошибка при удалении таблицы " + exception.getMessage());
        } finally {
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("В таблицу был добавлен " + name);
        } catch (Exception exception) {
            System.out.println("Ошибка при добавлении " + exception.getMessage());
            session.getTransaction().rollback();
        } finally {
            Util.getSessionFactory().close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        try{
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Строка с ID " + id +" была удалена");
        } catch (Exception exception) {
            System.out.println("Ошибка при удалении " + exception.getMessage());
            session.getTransaction().rollback();
        } finally {
            Util.getSessionFactory().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List <User> userList = null;
            try {
                session.beginTransaction();
                userList = session.createCriteria(User.class).list();
                session.getTransaction().commit();
            } catch (Exception exception) {
                System.out.println("Ошибка при выводе всех юзеров " + exception.getMessage());
                session.getTransaction().rollback();
            } finally {
                Util.getSessionFactory().close();
            }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищены");
        } catch (Exception exception) {
            System.out.println("Ошибка при очистки таблицы " + exception.getMessage());
            session.getTransaction().rollback();
        } finally {
            Util.getSessionFactory().close();
        }
    }

}
