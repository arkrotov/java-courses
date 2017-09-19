package ru.parsentev.service;

import ru.parsentev.models.User;

import java.util.Collection;

public interface UserService {

	Collection<User> values();

	int add(final User user);

	void edit(final User user);

	void delete(final int id);

	User get(final int id);

	User findByLogin(final String login) ;

	int generateId();

	void close();
}
