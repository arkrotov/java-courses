package ru.parsentev.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO: comment
 * @author parsentev
 * @since 16.05.2015
 */


public class Message extends Base {
	private User user;
	private String text;

	public Message() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
