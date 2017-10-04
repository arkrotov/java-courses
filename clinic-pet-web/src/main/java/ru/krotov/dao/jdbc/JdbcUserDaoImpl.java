package ru.krotov.dao.jdbc;

import ru.krotov.configs.Settings;
import ru.krotov.dao.UserDao;
import ru.krotov.models.Role;
import ru.krotov.models.User;
import ru.krotov.service.RoleServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcUserDaoImpl implements UserDao {

    private final Connection connection;

    public JdbcUserDaoImpl() {
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

        String sqlQuery = "SELECT * FROM user";

        final List<User> users = new ArrayList<>();

        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQuery)) {

            while (rs.next()) {

                users.add(User.builder()
                        .id(rs.getInt("uid"))
                        .login(rs.getString("login"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .sex(rs.getString("sex"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .role(RoleServiceImpl.getInstance().get(rs.getInt("role_id")))
                        .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    @Override
    public int add(User user) {

        String sqlQuery = "INSERT INTO user (login, first_name, last_name, sex, phone, email, role_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(
                sqlQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getRole().getId());
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

        String sqlQuery = "UPDATE user SET login = ?, first_name = ?, last_name = ?, sex = ?, " +
                "phone = ?, email = ?, role_id = ?" +
                "WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getRole().getId());
            statement.setInt(8, user.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sqlQuery = "DELETE FROM user WHERE uid = ?";

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

        String sqlQuery = "SELECT * FROM user WHERE uid = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return User.builder()
                            .id(rs.getInt("uid"))
                            .login(rs.getString("login"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .sex(rs.getString("sex"))
                            .phone(rs.getString("phone"))
                            .email(rs.getString("email"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User with id = '%s' does not exists", id));
    }

    @Override
    public User findByLogin(String login) {

        String sqlQuery = "SELECT * FROM user WHERE login = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setString(1, login);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User.builder()
                            .id(rs.getInt("uid"))
                            .login(rs.getString("login"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .sex(rs.getString("sex"))
                            .phone(rs.getString("phone"))
                            .email(rs.getString("email"))
                            .build();
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User with login = '%s' does not exists", login));
    }

    @Override
    public Collection<User> getByRoles(Role role) {
        return null;
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
