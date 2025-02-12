package org.example.DAO;

import org.example.Entity.Asignatura;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AsignaturaDAO {
    // Métod0 para añadir una asignatura a la base de datos
    public void addSubject(Asignatura asignatura) {
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

    // Métod0 para obtener todas las asignaturas
    public List<Asignatura> getSubjects() {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Asignatura", Asignatura.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener una asignatura por su id
    public Asignatura getSubjectById(Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Asignatura WHERE idAsignatura = :id", Asignatura.class)
                    .setParameter("id", asignatura.getIdAsignatura())
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
