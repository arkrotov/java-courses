package ru.parsentev.store;

import ru.lessons.lesson_6.Pet;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO: comment
 * @author parsentev
 * @since 18.04.2015
 */
public class PetCache {
    
    private static final PetCache INSTANCE = new PetCache();

    private final ConcurrentHashMap<Integer, Pet> pets = new ConcurrentHashMap<Integer, Pet>();
    private String owner;

    public static PetCache getInstance() {
        return INSTANCE;
    }

    public Collection<Pet> values() {

        if (this.owner == null || this.owner.isEmpty()) {
            return this.pets.values();
        }

        final ConcurrentHashMap<Integer, Pet> pets = new ConcurrentHashMap<Integer, Pet>();

        for (Map.Entry<Integer, Pet> entry : this.pets.entrySet()) {
            Pet pet = entry.getValue();

            if (pet.getOwner().equals(owner)) {
                pets.put(pet.getId(), pet);
            }
        }

        return pets.values();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void clear () {
        this.pets.clear();
    }
}
