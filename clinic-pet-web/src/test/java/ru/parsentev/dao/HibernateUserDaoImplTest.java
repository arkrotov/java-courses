package ru.parsentev.dao;

import org.junit.Ignore;
import org.junit.Test;
import ru.parsentev.models.Message;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;
import ru.parsentev.dao.hibernate.HibernateUserDaoImpl;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.05.2015
 */
public class HibernateUserDaoImplTest {

    @Ignore
    @Test
    public void testCRUD() throws Exception {
        final HibernateUserDaoImpl storage = new HibernateUserDaoImpl();
  /*      final int id = storage.add(new User(
                "hibernate",
                "name",
                "secondname",
                "male",
                "+7(999)123-12-12",
                "test@test.com", null, null));
        final User user = storage.get(id);

        assertEquals(id, user.getId());
        assertEquals(id, storage.findByLogin("hibernate").getId());

        user.setSex("nosex");
        System.out.println(user);
        storage.edit(user);

        System.out.println(storage.get(id));
        System.out.println(user.equals(storage.get(id)));
        assertEquals(user, storage.get(id));

        storage.delete(id);
        assertNull(storage.get(id));
        storage.close();*/
    }

    @Test
    public void testCreateUser() throws Exception {

        final HibernateUserDaoImpl storage = new HibernateUserDaoImpl();

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setLogin("ipetrov");
        user.setFirstName("Ivan");
        user.setLastName("Petrov");
        user.setSex("Male");
        user.setEmail("ipetrov@company.com");
        user.setPhone("8(999)999-99-99");
        user.setRole(role);

        final int id = storage.add(user);
        user = storage.get(id);

        Message message = new Message();
        message.setUser(user);
        message.setText("test");
        HashSet<Message> messages = new HashSet<>();
        messages.add(message);
        user.setMessages(messages);

        storage.edit(user);
        assertEquals(1, storage.get(id).getMessages().size());
        storage.delete(id);
        storage.close();

    }
}