package ru.krotov.dao.jdbc;

import ru.krotov.configs.Settings;
import ru.krotov.dao.RoleDao;
import ru.krotov.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcRoleDaoImpl implements RoleDao {

    private Connection connection;

    public JdbcRoleDaoImpl() {
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
    public Collection<Role> values() {

        String sqlQuery = "SELECT * FROM role";

        final List<Role> roles = new ArrayList<>();

        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQuery)) {

            while (rs.next()) {
                roles.add(Role.builder()
                        .id(rs.getInt("uid"))
                        .name(rs.getString("name"))
                        .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public int add(Role role) {
        String sqlQuery = "INSERT INTO role (name) " +
                "VALUES (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(
                sqlQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new role");
    }

    @Override
    public void edit(Role role) {

        String sqlQuery = "UPDATE role SET name = ?" +
                "WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {

            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sqlQuery = "DELETE FROM role WHERE uid = ?";

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
    public Role get(int id) {

        String sqlQuery = "SELECT * FROM role WHERE uid = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return Role.builder()
                            .id(rs.getInt("uid"))
                            .name(rs.getString("name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Role with id = '%s' does not exists", id));

    }

    @Override
    public Role findByName(String name) {

        String sqlQuery = "SELECT * FROM role WHERE name = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return Role.builder()
                            .id(rs.getInt("uid"))
                            .name(rs.getString("name"))
                            .build();
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Role with name = '%s' does not exists", name));
    }

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
