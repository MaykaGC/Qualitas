package org.example.DAO;

import org.example.Entity.Tutor;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TutorDAO {
    // Métod0 para añadir un tutor a la base de datos
    public void addTutor(Tutor tutor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tutor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener todos los tutores
    public List<Tutor> getTutors() {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tutor", Tutor.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener un tutor por su dni
    public Tutor getTutorByDni(String dni) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tutor WHERE dni_Tutor = :dni", Tutor.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}