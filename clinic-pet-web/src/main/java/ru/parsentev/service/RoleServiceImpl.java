package ru.parsentev.service;

import ru.parsentev.dao.hibernate.HibernateUserDaoImpl;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.util.Collection;

public class RoleServiceImpl implements RoleService {

    private static final RoleServiceImpl INSTANCE = new RoleServiceImpl();

    private final UserService userService = new HibernateUserDaoImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<User> values() {
        return userService.values();
    }

    @Override
    public int add(Role user) {
        return 0;
    }

    @Override
    public void edit(Role user) {

    }

    @Override
    public int add(final User user) {
        return this.userService.add(user);
    }

    @Override
    public void edit(final User user) {
        this.userService.edit(user);
    }

    @Override
    public void delete(final int id) {
        this.userService.delete(id);
    }

    @Override
    public User get(final int id) {
        return this.userService.get(id);
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User findByLogin(final String login) {
        return this.userService.findByLogin(login);
    }

    @Override
    public int generateId() {
        return this.userService.generateId();
    }

    @Override
    public void close() {
        this.userService.close();
    }
}
