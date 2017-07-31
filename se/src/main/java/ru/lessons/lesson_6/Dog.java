package ru.lessons.lesson_6;

/**
 * TODO: comment
 * @author parsentev
 * @since 16.04.2015
 */
public class Dog implements Pet {

	private final Animal animal;

	public Dog(Animal animal) {
		this.animal = animal;
	}

	@Override
	public Boolean isMale() {
		return  animal.isMale();
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


}
