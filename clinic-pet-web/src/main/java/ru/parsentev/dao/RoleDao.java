package ru.parsentev.dao;

import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import java.util.Collection;


public interface RoleDao {

    Collection<Role> values();

    int add(final Role user);

    void edit(final Role user);

    void delete(final int id);

    Role get(final int id);

    Role findByName(final String name) ;

    int generateId();

    void close();
}
