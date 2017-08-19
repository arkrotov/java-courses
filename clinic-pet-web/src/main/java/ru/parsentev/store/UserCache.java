package ru.parsentev.store;

import ru.parsentev.models.User;
import ru.parsentev.store.impl.hibernate.HibernateUserStorageImpl;

import java.util.Collection;

/**
 * TODO: comment
 * @author parsentev
 * @since 18.04.2015
 */
public class UserCache implements UserStorage {

	private static final UserCache INSTANCE = new UserCache();

	private final UserStorage userStorage = new HibernateUserStorageImpl();

	public static UserCache getInstance() {
		return INSTANCE;
	}

	@Override
	public Collection<User> values() {
		return userStorage.values();
	}

	@Override
	public int add(final User user) {
		return this.userStorage.add(user);
	}

	@Override
	public void edit(final User user) {
		this.userStorage.edit(user);
	}

	@Override
	public void delete(final int id) {
		this.userStorage.delete(id);
	}

	@Override
	public User get(final int id) {
		return this.userStorage.get(id);
	}

	@Override
	public User findByLogin(final String login) {
		return this.userStorage.findByLogin(login);
	}

	@Override
	public int generateId() {
		return this.userStorage.generateId();
	}

	@Override
	public void close() {
		this.userStorage.close();
	}
}
