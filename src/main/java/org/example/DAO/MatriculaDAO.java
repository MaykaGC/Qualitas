package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona las operaciones de base de datos relacionadas con las matrículas.
 */
public class MatriculaDAO {

    /**
     * Obtiene una matrícula correspondiente a un alumno y una asignatura específicos.
     * Este método busca una matrícula en la base de datos usando el alumno y la asignatura como parámetros.
     *
     * @param alumno    El objeto {@link Alumno} que representa al alumno en la matrícula.
     * @param asignatura El objeto {@link Asignatura} que representa la asignatura en la matrícula.
     * @return El objeto {@link Matricula} que representa la matrícula de ese alumno en la asignatura,
     *         o null si no existe tal matrícula.
     */
    public Matricula obtenerMatriculaPorAlumnoYAsignatura(Alumno alumno, Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matricula WHERE alumno = :alumno AND asignatura = :asignatura", Matricula.class)
                    .setParameter("alumno", alumno)
                    .setParameter("asignatura", asignatura)
                    .uniqueResult();
        }
    }

    /**
     * Actualiza la información de una matrícula existente en la base de datos.
     * Este método utiliza la operación de "merge" para actualizar los cambios en la matrícula.
     *
     * @param matricula El objeto {@link Matricula} que contiene la información actualizada a persistir.
     */
    public void actualizarMatricula(Matricula matricula) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(matricula);  // Se realiza el merge para actualizar la matrícula
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre algún error, se hace rollback
            }
            throw new RuntimeException(e);  // Lanzamos una excepción si algo sale mal
        }
    }
}
