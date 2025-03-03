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
            System.out.println("✅ Cuenta de tutor creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

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
            System.out.println("❌ Error al obtener los alumnos del tutor: " + e.getMessage());
            return List.of();
        }
    }
}
