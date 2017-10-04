package ru.krotov.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.krotov.dao.PetDao;
import ru.krotov.models.Pet;
import ru.krotov.models.User;

import java.util.Collection;
import java.util.List;

public class HibernatePetDaoImpl implements PetDao {
    private final SessionFactory factory;

    public HibernatePetDaoImpl() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Pet> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Pet").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int add(final Pet pet) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(pet);
            return pet.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(Pet pet) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(pet);
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
    public Pet get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (Pet) session.get(Pet.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Pet findByName(String name) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Pet as pet where pet.name=:name");
            query.setString("name", name);
            return (Pet) query.iterate().next();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<Pet> getByOwner(User user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Pet as pet where pet.user.id=:userId");
            query.setInteger("userId", user.getId());
            return (List<Pet>) query.list();
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
