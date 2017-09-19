package ru.parsentev.dao.memory;

import ru.parsentev.dao.UserDao;
import ru.parsentev.models.Pet;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;
import ru.parsentev.service.UserService;
import ru.parsentev.service.UserServiceImpl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryUserDaoImpl implements UserDao {

	private final AtomicInteger ids = new AtomicInteger();

	private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();


	@Override
	public Collection<User> values() {
		return this.users.values();
	}

	@Override
	public int add(final User user) {
		this.users.put(user.getId(), user);
		return user.getId();
	}

	@Override
	public void edit(final User user) {
		this.users.replace(user.getId(), user);
	}

	@Override
	public void delete(final int id) {
		this.users.remove(id);
	}

	@Override
	public User get(final int id) {
		return this.users.get(id);
	}

	@Override
	public User findByLogin(final String login) {
		for (final User user : users.values()) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		throw new IllegalStateException(String.format("Login %s not found", login));
	}

	@Override
	public Collection<User> getByRoles(Role role) {
		ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

		for (Map.Entry<Integer, User> entry : this.users.entrySet()) {
			User user = entry.getValue();

			if (user.getRole().equals(role)) {
				users.put(user.getId(), user);
			}
		}

		return users.values();
	}

	@Override
	public int generateId() {
		return this.ids.incrementAndGet();
	}

	@Override
	public void close() {
	}
}
