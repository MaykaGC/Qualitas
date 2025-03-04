package org.example.DAO;

import org.example.Entity.Profesor;
import org.example.Entity.Usuario;
import org.example.Utils.Logger;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Clase encargada de las operaciones de base de datos relacionadas con los objetos de tipo {@link Profesor}.
 */
public class ProfesorDAO {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa la instancia del {@link UsuarioDAO}.
     */
    public ProfesorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Obtiene un {@link Profesor} de la base de datos usando su DNI.
     * Este método realiza una consulta HQL para recuperar un profesor junto con sus asignaturas.
     *
     * @param profesor El objeto {@link Profesor} que contiene el DNI del profesor que se quiere buscar.
     * @return El objeto {@link Profesor} que corresponde al DNI proporcionado, o null si no se encuentra.
     */
    public Profesor obtenerProfesorPorDni(Profesor profesor) {
        Transaction transaction = null;
        Profesor result = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Consulta HQL con LEFT JOIN para obtener las asignaturas relacionadas con el profesor
            Query<Profesor> query = session.createQuery(
                    "FROM Profesor p LEFT JOIN FETCH p.asignaturas WHERE p.dniProfesor = :dni", Profesor.class);
            query.setParameter("dni", profesor.getDniProfesor());
            result = query.uniqueResult();  // Retorna un único resultado
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Si ocurre un error, realiza el rollback
            throw new RuntimeException(e);
        }
        return result;  // Retorna el profesor obtenido
    }

    /**
     * Crea un nuevo {@link Profesor} en la base de datos.
     * Si el usuario asociado al profesor no existe, se crea un nuevo usuario antes de asociarlo al profesor.
     *
     * @param profesor El objeto {@link Profesor} que se va a crear.
     * @param usuario  El objeto {@link Usuario} que representa al usuario asociado al profesor.
     */
    public void crearProfesor(Profesor profesor, Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {

            // Verificamos si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                // Si el usuario ya existe, podemos usar el usuario existente
                usuario = usuarioExistente;
            }

            // Asociamos el usuario al profesor
            profesor.setUsuario(usuario);

            // Iniciamos la transacción después de haber establecido el usuario
            transaction = session.beginTransaction();

            // Persistimos al profesor en la base de datos
            session.persist(profesor);
            transaction.commit();
            System.out.println("✅ Cuenta de profesor creada con éxito.");
            Logger.logInfo("Cuenta de profesor creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, se hace rollback de la transacción
            }
            throw new RuntimeException(e);  // Lanzamos una excepción en caso de error
        }
    }
}
