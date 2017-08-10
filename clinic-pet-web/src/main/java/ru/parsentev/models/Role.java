package ru.parsentev.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO: comment
 * @author parsentev
 * @since 06.05.2015
 */


public class Role extends Base {
	private String name;

	public Role() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
