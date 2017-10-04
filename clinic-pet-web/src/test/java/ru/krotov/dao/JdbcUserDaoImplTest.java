package ru.krotov.dao;

import org.junit.Test;
import ru.krotov.models.User;
import ru.krotov.dao.jdbc.JdbcUserDaoImpl;

import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author krotov
 * @since 29.04.2015
 */
public class JdbcUserDaoImplTest {

    private static int id;

    @Test
    public void testAddAndGet() throws Exception {

        final JdbcUserDaoImpl storage = new JdbcUserDaoImpl();

      /*  id = storage.add(new User(
                "login",
                "name",
                "secondname",
                "male",
                "+7(999)123-12-12",
                "test@test.com", null, null));

        final User user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();*/
    }

    @Test
    public void testEdit() throws Exception {

        final JdbcUserDaoImpl storage = new JdbcUserDaoImpl();

        User userOne = storage.get(id);
        User userTwo = storage.get(id);

        userTwo.setSex("female");

        storage.edit(userTwo);

        userTwo = storage.get(id);

        assertFalse(userOne.equals(userTwo));
        storage.close();
    }

    @Test
    public void testDelete() throws Exception {

        final JdbcUserDaoImpl storage = new JdbcUserDaoImpl();
        storage.delete(id);

        try {
            final User user = storage.get(id);
            assert false;
        } catch (Exception e) {
            assert true;
        }

        storage.close();
    }
}