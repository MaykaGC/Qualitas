package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatriculaDAO {

    public Matricula obtenerMatriculaPorAlumnoYAsignatura(Alumno alumno, Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matricula WHERE alumno = :alumno AND asignatura = :asignatura", Matricula.class)
                    .setParameter("alumno", alumno)
                    .setParameter("asignatura", asignatura)
                    .uniqueResult();
        }
    }

    public void actualizarMatricula(Matricula matricula) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(matricula);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}