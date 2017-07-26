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


	public Animal(int id, String name, String owner) {
		this.id = id;
		this.name = name;
		this.owner = owner;
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


}
