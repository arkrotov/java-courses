package ru.parsentev.dao.jdbc;


import ru.parsentev.configs.Settings;
import ru.parsentev.dao.PetDao;
import ru.parsentev.models.Pet;
import ru.parsentev.models.User;
import ru.parsentev.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcPetDaoImpl implements PetDao {

    private Connection connection;

    public JdbcPetDaoImpl() {
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
    public Collection<Pet> values() {

        String sqlQuery = "SELECT * FROM pet";

        final List<Pet> pets = new ArrayList<>();

        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(sqlQuery)) {

            while (rs.next()) {
                pets.add(Pet.builder()
                        .id(rs.getInt("uid"))
                        .name(rs.getString("name"))
                        .sex(rs.getString("sex"))
                        .owner(UserServiceImpl.getInstance().get(rs.getInt("user_id")))
                        .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public int add(Pet pet) {
        String sqlQuery = "INSERT INTO pet (name, sex, user_id) " +
                "VALUES (?, ?, ?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(
                sqlQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSex());
            statement.setInt(3, pet.getOwner().getId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new pet");
    }

    @Override
    public void edit(Pet pet) {

        String sqlQuery = "UPDATE pet SET name = ?, sex = ?, user_id = ?" +
                "WHERE uid = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSex());
            statement.setInt(3, pet.getOwner().getId());
            statement.setInt(4, pet.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sqlQuery = "DELETE FROM pet WHERE uid = ?";

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
    public Pet get(int id) {

        String sqlQuery = "SELECT * FROM pet WHERE uid = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return Pet.builder()
                            .id(rs.getInt("uid"))
                            .name(rs.getString("name"))
                            .sex(rs.getString("sex"))
                            .owner(UserServiceImpl.getInstance().get(rs.getInt("user_id")))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("pet with id = '%s' does not exists", id));

    }

    @Override
    public Pet findByName(String name) {

        String sqlQuery = "SELECT * FROM pet WHERE name = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return Pet.builder()
                            .id(rs.getInt("uid"))
                            .name(rs.getString("name"))
                            .sex(rs.getString("sex"))
                            .owner(UserServiceImpl.getInstance().get(rs.getInt("user_id")))
                            .build();
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("pet with name = '%s' does not exists", name));
    }

    @Override
    public Collection<Pet> getByOwner(User owner) {

        String sqlQuery = "SELECT * FROM pet WHERE user_id = (?)";

        try (final PreparedStatement statement = this.connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, owner.getId());
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Pet.builder()
                            .id(rs.getInt("uid"))
                            .name(rs.getString("name"))
                            .sex(rs.getString("sex"))
                            .owner(UserServiceImpl.getInstance().get(rs.getInt("user_id")))
                            .build();
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Pet with owner = '%s' does not exists", owner.toString()));
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
