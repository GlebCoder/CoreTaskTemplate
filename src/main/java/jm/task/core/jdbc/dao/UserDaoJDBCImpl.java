package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users" +
                "(id bigint auto_increment, " +
                " name VARCHAR(50) not null, " +
                " last_name VARCHAR (50) not null, " +
                " age int not NULL, " +
                " PRIMARY KEY (id))";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(SQL);
        } catch(SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(SQL);
        } catch(SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }
    }

    public void saveUser(String name, String lastName, int age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name,last_Name,age)" +
                    "VALUES (?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3,age);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")
        ) {
           statement.setLong(1, id);
           statement.execute();
        } catch(SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("last_Name"), resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }

        return users;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println("SQL exception problem has occurred");
        }
    }
}
