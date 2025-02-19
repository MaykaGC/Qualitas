package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatriculaDAO {

    // Métod0 para obtener una matrícula por su ID
    public Matricula obtenerMatriculaPorId(Matricula matricula) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Matricula.class, matricula.getIdMatricula());
        }
    }

    // Métod0 para crear una nueva matrícula
    public void crearMatricula(Matricula matricula) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(matricula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener una matrícula por alumno y asignatura en la clase MatriculaDAO
    public Matricula obtenerMatriculaPorAlumnoYAsignatura(Alumno alumno, Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matricula WHERE alumno = :alumno AND asignatura = :asignatura", Matricula.class)
                    .setParameter("alumno", alumno)
                    .setParameter("asignatura", asignatura)
                    .uniqueResult();
        }
    }

    // Métod0 para actualizar los datos de una matrícula en la clase MatriculaDAO
    public void actualizarMatricula(Matricula matricula) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(matricula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para eliminar una matrícula
    public void eliminarMatricula(Matricula matricula) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Matricula matriculaToDelete = session.get(Matricula.class, matricula.getIdMatricula());
            if (matriculaToDelete != null) {
                session.delete(matriculaToDelete);
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
