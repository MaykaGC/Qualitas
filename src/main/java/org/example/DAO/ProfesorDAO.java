package org.example.DAO;

import org.example.Entity.Profesor;
import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProfesorDAO {

    private final UsuarioDAO usuarioDAO;

    public ProfesorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Métod0 para obtener un profesor por su DNI
    public Profesor obtenerProfesorPorDni(Profesor profesor) {
        Transaction transaction = null;
        Profesor result = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Profesor> query = session.createQuery(
                    "FROM Profesor p LEFT JOIN FETCH p.asignaturas WHERE p.dniProfesor = :dni", Profesor.class);
            query.setParameter("dni", profesor.getDniProfesor());
            result = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return result;
    }

    // Métod0 para crear un nuevo profesor, incluyendo su usuario
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
            System.out.println("✅ Cuenta de profesor creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}