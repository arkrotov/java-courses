package ru.parsentev.models;

import ru.lessons.lesson_6.Animal;
import ru.lessons.lesson_6.Pet;

public class Dog implements Pet{

    private final Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
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

}
