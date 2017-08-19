package ru.parsentev.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: comment
 * @author parsentev
 * @since 16.05.2015
 */

@Getter
@Setter
public class Message extends Base {
	private User user;
	private String text;

	public Message() {
	}
}
