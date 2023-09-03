package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS `mydb`.`User` (
                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NOT NULL,
                      `lastName` VARCHAR(45) NOT NULL,
                      `age` TINYINT NOT NULL,
                      PRIMARY KEY (`id`))
                    ENGINE = InnoDB;""";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = "DROP TABLE IF EXISTS user";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = String.format("INSERT INTO user (name, lastName, age) VALUES (\"%s\", \"%s\", %d)", name, lastName, age);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = "DELETE FROM user WHERE (id=@id)";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                list.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            String sql = "DELETE FROM user";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
