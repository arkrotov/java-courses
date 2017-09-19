package ru.parsentev.dao.memory;

import ru.parsentev.dao.RoleDao;
import ru.parsentev.models.Role;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryRoleDaoImpl implements RoleDao {

    private final AtomicInteger ids = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Role> roles = new ConcurrentHashMap<>();

    public Collection<Role> values() {
        return this.roles.values();
    }

    public int generateId() {
        return this.ids.incrementAndGet();
    }

    public int add(final Role role) {
        return this.roles.put(role.getId(), role).getId();
    }

    public void edit(final Role role) {
        this.roles.replace(role.getId(), role);
    }

    public void delete(final int id) {
        this.roles.remove(id);
    }

    public Role get(final int id) {
        return this.roles.get(id);
    }

    @Override
    public Role findByName(String name) {

        for (Role role : roles.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        throw new IllegalStateException(String.format("Role with name: %s not found", name));
    }

    public void clear () {
        this.roles.clear();
    }

    @Deprecated
    @Override
    public void close() {

    }
}
