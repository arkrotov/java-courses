package ru.lessons.lesson_6;

/**
 * TODO: comment
 * @author parsentev
 * @since 07.04.2015
 */
public class Animal implements Pet {

	private final int id;
	private final String name;
	private final String owner;
	private final boolean sex;


	public Animal(int id, String name, String owner, boolean sex) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public String getOwner() {
		return owner;
	}

	public Boolean isMale() {
		return sex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Animal animal = (Animal) o;

		if (id != animal.id) return false;
		if (!name.equals(animal.name)) return false;
		return owner.equals(animal.owner);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + owner.hashCode();
		return result;
	}
}
