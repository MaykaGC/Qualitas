package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlumnoDAO {

    private final UsuarioDAO usuarioDAO;

    public AlumnoDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Métod0 para obtener un alumno por su DNI
    public Alumno obtenerAlumnoPorDni(Alumno alumno) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Alumno.class, alumno.getDniAlumno());
        }
    }

    // Métod0 para crear un nuevo alumno
    public void crearAlumno(Alumno alumno, Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Primero, verificar si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(usuario);
            } else {
                // Si el usuario ya existe, podemos usar el usuario existente
                usuario = usuarioExistente;
            }

            // Establecemos el usuario al alumno
            alumno.setUsuario(usuario);

            // Ahora persistimos al alumno
            session.persist(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para actualizar los datos de un alumno
    public void actualizarAlumno(Alumno alumno) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para eliminar un alumno
    public void eliminarAlumno(Alumno alumno) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Alumno alumnoToDelete = session.get(Alumno.class, alumno.getDniAlumno());
            if (alumnoToDelete != null) {
                session.delete(alumnoToDelete);
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
