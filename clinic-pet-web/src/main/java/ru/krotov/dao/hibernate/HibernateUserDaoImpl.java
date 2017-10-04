package ru.krotov.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.JTASessionContext;
import org.hibernate.context.internal.ManagedSessionContext;
import ru.krotov.dao.UserDao;
import ru.krotov.models.Role;
import ru.krotov.models.User;

import java.util.Collection;
import java.util.List;

// TODO: Почитать про getCurrentSession openSession, JTASessionContext, ManagedSessionContext

public class HibernateUserDaoImpl implements UserDao {
    private final SessionFactory factory;

    public HibernateUserDaoImpl() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<User> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from User").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int add(final User user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            return user.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(User user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(user);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(get(id));
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public User get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (User) session.get(User.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public User findByLogin(String login) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.login=:login");
            query.setString("login", login);
            return (User) query.iterate().next();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<User> getByRoles(Role role) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.role.id=:roleId");
            query.setInteger("roleId", role.getId());
            return (List<User>) query.list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
