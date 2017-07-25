package ru.lessons.lesson_6;

/**
 * TODO: comment
 * @author parsentev
 * @since 07.04.2015
 */
public class Animal implements Pet {

	private final String name;
	private final String owner;

	public Animal(final String name, String owner) {
		this.name = name;
		this.owner = owner;
	}

	public String getName() {
		return this.name;
	}

	public String getOwner() {
		return owner;
	}
}
