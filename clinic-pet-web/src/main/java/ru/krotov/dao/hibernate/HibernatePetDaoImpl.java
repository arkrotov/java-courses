package ru.krotov.dao.hibernate;

import ru.krotov.models.Pet;
import ru.krotov.service.*;

import java.util.Collection;

/**
 * Created by me on 19.08.17.
 */
public class HibernatePetDaoImpl implements PetService {


    @Override
    public Collection<Pet> values() {
        return null;
    }

    @Override
    public int add(Pet user) {
        return 0;
    }

    @Override
    public void edit(Pet user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Pet get(int id) {
        return null;
    }

    @Override
    public Pet findByName(String login) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }
}
