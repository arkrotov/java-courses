package ru.parsentev.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: comment
 * @author parsentev
 * @since 06.05.2015
 */


@Getter
@Setter
public class Role extends Base {
	private String name;

	public Role() {
	}

}
