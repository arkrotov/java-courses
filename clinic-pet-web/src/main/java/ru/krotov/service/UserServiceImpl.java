package ru.krotov.service;

import ru.krotov.dao.UserDao;
import ru.krotov.models.User;
import ru.krotov.dao.hibernate.HibernateUserDaoImpl;

import java.util.Collection;


public class UserServiceImpl implements UserService {

	private static final UserService INSTANCE = new UserServiceImpl();
	public static UserService getInstance() {
		return INSTANCE;
	}

	private final UserDao userDao = new HibernateUserDaoImpl();

	@Override
	public Collection<User> values() {
		return userDao.values();
	}

	@Override
	public int add(final User user) {
		return this.userDao.add(user);
	}

	@Override
	public void edit(final User user) {
		this.userDao.edit(user);
	}

	@Override
	public void delete(final int id) {
		this.userDao.delete(id);
	}

	@Override
	public User get(final int id) {
		return this.userDao.get(id);
	}

	@Override
	public User findByLogin(final String login) {
		return this.userDao.findByLogin(login);
	}

	@Override
	public int generateId() {
		return this.userDao.generateId();
	}

	@Override
	public void close() {
		this.userDao.close();
	}
}
