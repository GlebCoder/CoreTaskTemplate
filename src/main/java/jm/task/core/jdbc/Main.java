package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        //userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Gleb", "Berliand", 81);
        System.out.println("User with a name "
                        + userService.getAllUsers().get(0).getName()
                        + " has been created");
        userService.saveUser("Nikita", "Andriyanov", 17);
        System.out.println("User with a name "
                + userService.getAllUsers().get(1).getName()
                + " has been created");
        userService.saveUser("Oksana", "Andriyanova", 43);
        System.out.println("User with a name "
                + userService.getAllUsers().get(2).getName()
                + " has been created");
        userService.saveUser("Iliya", "Berliand", 6);
        System.out.println("User with a name "
                + userService.getAllUsers().get(3).getName()
                + " has been created");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

        }
    }
