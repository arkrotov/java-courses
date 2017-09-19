package ru.lessons.lesson_6;

public class Dog implements Pet {

    private final Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
    }

    public int getId() {
        return 0;
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


}
