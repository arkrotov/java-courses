package ru.parsentev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.04.2015
 */

@Data
@AllArgsConstructor
public class User {
	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private String sex;
	private String phone;
	private String email;

}
