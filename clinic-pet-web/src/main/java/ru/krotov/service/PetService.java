package ru.krotov.service;

import ru.krotov.models.Pet;

import java.util.Collection;

public interface PetService {

    Collection<Pet> values();

    int add(final Pet user);

    void edit(final Pet user);

    void delete(final int id);

    Pet get(final int id);

    Pet findByName(final String login);

    int generateId();

    void close();

}
