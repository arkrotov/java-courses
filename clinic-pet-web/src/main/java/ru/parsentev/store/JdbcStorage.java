package ru.parsentev.store;


import ru.parsentev.models.User;
import ru.parsentev.service.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 29.04.2015
 */
public class JdbcStorage implements Storage {
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<User> values() {

        String sqlQuery = "SELECT * FROM client";

        final List<User> users = new ArrayList<>();

        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQuery)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("uid"),
                        rs.getString("login"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("sex"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public int add(User user) {

        String sqlQuery = "INSERT INTO client (login, first_name, last_name, sex, phone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(
                sqlQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public void edit(User user) {

        String sqlQuery = "UPDATE client SET login = ?, first_name = ?, last_name = ?, sex = ?, phone = ?, email = ?" +
                "WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sqlQuery = "DELETE FROM client WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(
                sqlQuery, Statement.RETURN_GENERATED_KEYS)
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(int id) {

        String sqlQuery = "SELECT * FROM client WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new User(
                            rs.getInt("uid"),
                            rs.getString("login"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("sex"),
                            rs.getString("phone"),
                            rs.getString("login")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User with id = '%s' does not exists", id));
    }

    @Override
    public User findByLogin(String login) {
        try (final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM client WHERE login = (?)")) {
            statement.setString(1, login);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new User(
                            rs.getInt("uid"),
                            rs.getString("login"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("sex"),
                            rs.getString("phone"),
                            rs.getString("login")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User with login = '%s' does not exists", login));
    }

    @Deprecated
    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
