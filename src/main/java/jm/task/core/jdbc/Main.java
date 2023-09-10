package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Петя", "Петров", (byte) 17);
        System.out.println("Пользователь с именем Петя добавлен в базу данных");
        userService.saveUser("Анна", "Каренина", (byte) 33);
        System.out.println("Пользователь с именем Анна добавлен в базу данных");
        userService.saveUser("Рик", "Санчез", (byte) 50);
        System.out.println("Пользователь с именем Рик добавлен в базу данных");
        userService.saveUser("Эрик", "Картман", (byte) 9);
        System.out.println("Пользователь с именем Эрик добавлен в базу данных");

        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
