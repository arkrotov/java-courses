package ru.parsentev.store;

import org.junit.Test;
import ru.parsentev.models.User;

import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.05.2015
 */
public class HibernateStorageTest {

    @Test
    public void testCRUD() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        final int id = storage.add(new User(-1,
                "hibernate",
                "name",
                "secondname",
                "male",
                "+7(999)123-12-12",
                "test@test.com"));
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
        storage.close();
    }
}