package ru.krotov.dao;

import ru.krotov.models.Pet;
import ru.krotov.models.User;

import java.util.Collection;

public interface PetDao {

    Collection<Pet> values();

    int add(final Pet user);

    void edit(final Pet user);

    void delete(final int id);

    Pet get(final int id);

    Pet findByName(final String login);

    Collection<Pet> getByOwner(final User owner);

    int generateId();

    void close();

}
