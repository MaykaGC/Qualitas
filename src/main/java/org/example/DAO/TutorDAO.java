package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TutorDAO {

    private final UsuarioDAO usuarioDAO;

    public TutorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Métod0 para obtener un tutor por su DNI
    public Tutor obtenerTutorPorDni(Tutor tutor) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Tutor.class, tutor.getDniTutor());
        }
    }

    // Métod0 para crear un nuevo tutor
    public void crearTutor(Tutor tutor, Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {


            // Primero, verificar si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                // Si el usuario ya existe, podemos usar el usuario existente
                usuario = usuarioExistente;
            }

            tutor.setUsuario(usuario);

            transaction = session.beginTransaction();
            session.persist(tutor);
            transaction.commit();
            System.out.println("Cuenta de tutor creada con éxito.");
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

    // Métod0 para obtener los alumnos asignados a un tutor
    public List<Alumno> obtenerAlumnosTutor(String dniTutor) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            // Obtener el tutor y sus alumnos en una sola consulta
            Tutor tutor = session.createQuery(
                            "SELECT t FROM Tutor t LEFT JOIN FETCH t.alumnos WHERE t.dniTutor = :dni", Tutor.class)
                    .setParameter("dni", dniTutor)
                    .uniqueResult();

            if (tutor != null) {
                return tutor.getAlumnos();
            }
            return List.of(); // Retorna lista vacía si no se encuentra el tutor
        } catch (Exception e) {
            System.out.println("Error al obtener los alumnos del tutor: " + e.getMessage());
            return List.of();
        }
    }
}
