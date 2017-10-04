package ru.krotov.service;

import ru.krotov.dao.RoleDao;
import ru.krotov.dao.hibernate.HibernateRoleDaoImpl;
import ru.krotov.models.Role;
import ru.krotov.models.User;

import java.util.Collection;

public class RoleServiceImpl implements RoleService {

    private static final RoleServiceImpl INSTANCE = new RoleServiceImpl();

    private final RoleDao roleDao = new HibernateRoleDaoImpl();

    public static RoleService getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Role> values() {
        return roleDao.values();
    }

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public void edit(Role role) {
        return roleDao.
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
    public Role get(final int id) {
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
