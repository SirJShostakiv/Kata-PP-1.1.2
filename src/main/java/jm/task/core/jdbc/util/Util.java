package jm.task.core.jdbc.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;

public class Util {
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "wGgfwfyg672";

    public static Connection getMySQLConnection()
            throws SQLException {

        return getMySQLConnection(HOST_NAME, DB_NAME, USERNAME, PASSWORD);
    }
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(connectionURL, userName, password);
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(AvailableSettings.URL, "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME);
                settings.put(AvailableSettings.USER, USERNAME);
                settings.put(AvailableSettings.PASS, PASSWORD);
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(AvailableSettings.SHOW_SQL, true);

                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(AvailableSettings.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}