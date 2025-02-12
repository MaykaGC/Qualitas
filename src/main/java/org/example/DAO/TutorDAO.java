package org.example.DAO;

import org.example.Entity.Tutor;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TutorDAO {

    // Métod0 para obtener un tutor por su DNI
    public Tutor obtenerTutorPorDni(Tutor tutor) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Tutor.class, tutor.getDniTutor());
        }
    }

    // Métod0 para crear un nuevo tutor
    public void crearTutor(Tutor tutor) {
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

    // Métod0 para actualizar los datos de un tutor
    public void actualizarTutor(Tutor tutor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tutor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para eliminar un tutor
    public void eliminarTutor(Tutor tutor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tutor tutorToDelete = session.get(Tutor.class, tutor.getDniTutor());
            if (tutorToDelete != null) {
                session.delete(tutorToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
