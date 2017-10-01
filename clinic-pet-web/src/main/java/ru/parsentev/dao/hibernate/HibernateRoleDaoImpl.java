package ru.parsentev.dao.hibernate;

import ru.parsentev.dao.RoleDao;
import ru.parsentev.models.Role;

import java.util.Collection;

/**
 * Created by me on 19.08.17.
 */
public class HibernateRoleDaoImpl implements RoleDao{
    @Override
    public Collection<Role> values() {
        return null;
    }

    @Override
    public int add(Role user) {
        return 0;
    }

    @Override
    public void edit(Role user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Role get(int id) {
        return null;
    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }
}
