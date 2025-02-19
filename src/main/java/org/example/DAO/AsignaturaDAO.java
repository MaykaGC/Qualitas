package org.example.DAO;

import org.example.Entity.Asignatura;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AsignaturaDAO {

    // Métod0 para obtener una asignatura por su ID
    public Asignatura obtenerAsignaturaPorId(Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Asignatura.class, asignatura.getIdAsignatura());
        }
    }

    // Métod0 para crear una nueva asignatura
    public void crearAsignatura(Asignatura asignatura) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(asignatura);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para actualizar los datos de una asignatura
    public void actualizarAsignatura(Asignatura asignatura) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(asignatura);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para eliminar una asignatura
    public void eliminarAsignatura(Asignatura asignatura) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Asignatura asignaturaToDelete = session.get(Asignatura.class, asignatura.getIdAsignatura());
            if (asignaturaToDelete != null) {
                session.delete(asignaturaToDelete);
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
