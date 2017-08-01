package ru.parsentev.models;

import ru.lessons.lesson_6.Animal;
import ru.lessons.lesson_6.Pet;

public class Dog implements Pet{

    private final Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Boolean isMale() {
        return animal.isMale();
    }

    public int getId() {
        return animal.getId();
    }

    public String getName() {
        return this.animal.getName();
    }

    public String getOwner() {
        return this.animal.getOwner();
    }

    public String getSex() {
        return animal.getSex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return animal.equals(dog.animal);
    }

    @Override
    public int hashCode() {
        return animal.hashCode();
    }
}
