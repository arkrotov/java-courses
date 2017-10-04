package ru.krotov.dao;

import ru.krotov.models.Role;
import ru.krotov.models.User;

import java.util.Collection;

public interface UserDao {

    Collection<User> values();

    int add(User user);

    void edit(User user);

    void delete(int id);

    User get(int id);

    User findByLogin(String login) ;

    Collection<User> getByRoles(Role role);

    int generateId();

    void close();

}
