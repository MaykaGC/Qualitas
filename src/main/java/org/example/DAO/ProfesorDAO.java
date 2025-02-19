package org.example.DAO;

import org.example.Entity.Profesor;
import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProfesorDAO {

    private final UsuarioDAO usuarioDAO;

    public ProfesorDAO() {
        this.usuarioDAO = new UsuarioDAO(); // Inicializamos el DAO de Usuario
    }

    // Método para obtener un profesor por su DNI
    public Profesor obtenerProfesorPorDni(Profesor profesor) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Profesor.class, profesor.getDniProfesor());
        }
    }

    // Método para crear un nuevo profesor, incluyendo su usuario
    public void crearProfesor(Profesor profesor, Usuario usuario) {
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

            // Establecemos el usuario al profesor
            profesor.setUsuario(usuario);

            // Iniciamos la transacción después de haber definido el usuario
            transaction = session.beginTransaction();

            // Ahora persistimos al profesor
            session.persist(profesor);
            transaction.commit();
            System.out.println("Cuenta de profesor creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Método para actualizar los datos de un profesor
    public void actualizarProfesor(Profesor profesor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(profesor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Método para eliminar un profesor
    public void eliminarProfesor(String dni) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, dni);
            if (profesor != null) {
                session.delete(profesor);
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
