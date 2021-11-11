package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.getSessionFactory2();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        String SQL = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                " name VARCHAR(50) not null, " +
                " lastName VARCHAR (50) not null, " +
                " age int not NULL)";
        Query query = session.createSQLQuery(SQL).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory2().openSession()) {
            Transaction transaction = session.beginTransaction();
            String SQL = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(SQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            System.out.println("Problem with sessionFactory has occurred");
        }
    }

    @Override
    public void saveUser(String name, String lastName, int age) {

        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> getAllUsers = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            getAllUsers = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return getAllUsers;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User");
            transaction.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
