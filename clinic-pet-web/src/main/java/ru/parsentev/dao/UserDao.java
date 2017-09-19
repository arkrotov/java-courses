package ru.parsentev.dao;

import ru.parsentev.models.Role;
import ru.parsentev.models.User;

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
