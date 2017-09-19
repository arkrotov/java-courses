package ru.parsentev.service;

import ru.parsentev.dao.hibernate.HibernatePetDaoImpl;
import ru.parsentev.models.Pet;

import java.util.Collection;

public class PetServiceImpl implements PetService{

    private static final PetService INSTANCE = new PetServiceImpl();
    private final PetService petService = new HibernatePetDaoImpl(); //TODO: Говнокод
    public static PetService getInstance() {
        return INSTANCE;
    }

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
