package ru.parsentev.store;

import org.junit.Test;
import ru.parsentev.models.User;

import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 29.04.2015
 */
public class JdbcStorageTest {

    private static int id;

    @Test
    public void testAddAndGet() throws Exception {

        final JdbcStorage storage = new JdbcStorage();

        id = storage.add(new User(-1,
                "login",
                "name",
                "secondname",
                "male",
                "+7(999)123-12-12",
                "test@test.com"));

        final User user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }

    @Test
    public void testEdit() throws Exception {

        final JdbcStorage storage = new JdbcStorage();

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

        final JdbcStorage storage = new JdbcStorage();
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