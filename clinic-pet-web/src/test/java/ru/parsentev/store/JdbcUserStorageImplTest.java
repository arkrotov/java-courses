package ru.parsentev.store;

import org.junit.Test;
import ru.parsentev.models.User;
import ru.parsentev.store.impl.jdbc.JdbcUserStorageImpl;

import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 29.04.2015
 */
public class JdbcUserStorageImplTest {

    private static int id;

    @Test
    public void testAddAndGet() throws Exception {

        final JdbcUserStorageImpl storage = new JdbcUserStorageImpl();

        id = storage.add(new User(
                "login",
                "name",
                "secondname",
                "male",
                "+7(999)123-12-12",
                "test@test.com", null, null));

        final User user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }

    @Test
    public void testEdit() throws Exception {

        final JdbcUserStorageImpl storage = new JdbcUserStorageImpl();

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

        final JdbcUserStorageImpl storage = new JdbcUserStorageImpl();
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