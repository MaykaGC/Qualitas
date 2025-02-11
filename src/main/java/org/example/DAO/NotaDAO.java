package org.example.DAO;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.example.Entity.*;
import org.example.Utils.*;

import java.util.List;

public class NotaDAO {
    // Métod0 para añadir una nota a la base de datos
    public void addMark(Nota nota) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nota);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener todas las notas de un alumno
    public List<Nota> getMarksByDniAlumno(Alumno alumno) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Nota WHERE alumno.dni_Alumno = :dniAlumno", Nota.class)
                    .setParameter("dniAlumno", alumno.getDni_Alumno())
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
