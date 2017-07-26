package ru.parsentev.store;

import ru.lessons.lesson_6.Pet;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO: comment
 * @author parsentev
 * @since 18.04.2015
 */
public class PetCache {
    
    private static final UserCache INSTANCE = new UserCache();

    private final ConcurrentHashMap<Integer, Pet> pets = new ConcurrentHashMap<Integer, Pet>();

    public static UserCache getInstance() {
        return INSTANCE;
    }

    public Collection<Pet> values() {
        return this.pets.values();
    }

    public void add(final Pet pet) {
        this.pets.put(pet.getId(), pet);
    }

    public void edit(final Pet pet) {
        this.pets.replace(pet.getId(), pet);
    }

    public void delete(final int id) {
        this.pets.remove(id);
    }

    public Pet get(final int id) {
        return this.pets.get(id);
    }
}
