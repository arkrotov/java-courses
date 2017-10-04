package ru.krotov.dao.memory;

import ru.krotov.dao.PetDao;
import ru.krotov.models.Pet;
import ru.krotov.models.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class MemoryPetDaoImpl implements PetDao {

    private final AtomicInteger ids = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Pet> pets = new ConcurrentHashMap<Integer, Pet>();

    @Override
    public Collection<Pet> values() {
            return this.pets.values();
    }

    @Override
    public int generateId() {
        return this.ids.incrementAndGet();
    }

    @Override
    public int add(final Pet pet) {
        this.pets.put(pet.getId(), pet);
        return pet.getId();
    }

    @Override
    public void edit(final Pet pet) {
        this.pets.replace(pet.getId(), pet);
    }

    @Override
    public void delete(final int id) {
        this.pets.remove(id);
    }

    @Override
    public Pet get(final int id) {
        return this.pets.get(id);
    }

    @Override
    public Pet findByName(String login) {
        for (final Pet pet : pets.values()) {
            if (pet.getName().equals(login)) {
                return pet;
            }
        }
        throw new IllegalStateException(String.format("Login %s not found", login));
    }

    @Override
    public Collection<Pet> getByOwner(User owner) {

        ConcurrentHashMap<Integer, Pet> pets = new ConcurrentHashMap<Integer, Pet>();

        for (Map.Entry<Integer, Pet> entry : this.pets.entrySet()) {
            Pet pet = entry.getValue();

            if (pet.getOwner().equals(owner)) {
                pets.put(pet.getId(), pet);
            }
        }

        return pets.values();
    }

    public void clear () {
        this.pets.clear();
    }

    @Deprecated
    @Override
    public void close() {

    }

}
