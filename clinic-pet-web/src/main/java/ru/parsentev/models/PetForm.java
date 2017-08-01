package ru.parsentev.models;

public class PetForm {

    private String name;
    private String owner;
    private Boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean isSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex.equals("Male");
    }
}
