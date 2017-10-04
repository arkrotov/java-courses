package ru.krotov.service;

import ru.krotov.models.Role;
import ru.krotov.models.User;

import java.util.Collection;

public interface RoleService {

    Collection<Role> values();

    int add(final Role user);

    void edit(final Role user);

    void delete(final int id);

    Role get(final int id);

    User findByName(final String name) ;

    int generateId();

    void close();
}
