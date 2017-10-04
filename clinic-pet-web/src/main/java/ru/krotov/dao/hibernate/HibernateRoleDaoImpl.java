package ru.krotov.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.krotov.dao.RoleDao;
import ru.krotov.models.Role;

import java.util.Collection;


public class HibernateRoleDaoImpl implements RoleDao{

    private final SessionFactory factory;

    public HibernateRoleDaoImpl() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Role> values() {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            return session.createQuery("from Role").list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public int add(Role role) {

        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        // TODO: Дубликат. Есть возмоность исправить через Base class с полем id. Стоит ли?
        try {
            session.save(role);
            return role.getId();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void edit(Role role) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.update(role);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.delete(get(id));
        } finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public Role get(int id) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            return (Role) session.get(Role.class, id);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Role findByName(String name) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            return (Role) session.createQuery("FROM Role as role WHERE role.name=:name");
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }
}
