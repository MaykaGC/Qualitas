package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;
import org.example.Utils.Logger;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Clase encargada de las operaciones de base de datos relacionadas con los objetos de tipo {@link Tutor}.
 */
public class TutorDAO {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa la instancia del {@link UsuarioDAO}.
     */
    public TutorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Crea un nuevo {@link Tutor} en la base de datos.
     * Si el usuario asociado al tutor no existe, se crea un nuevo usuario antes de asociarlo al tutor.
     *
     * @param tutor   El objeto {@link Tutor} que se va a crear.
     * @param usuario El objeto {@link Usuario} que representa al usuario asociado al tutor.
     */
    public void crearTutor(Tutor tutor, Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {

            // Verificamos si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                // Si el usuario ya existe, usamos el usuario existente
                usuario = usuarioExistente;
            }

            // Asociamos el usuario al tutor
            tutor.setUsuario(usuario);

            // Iniciamos la transacción
            transaction = session.beginTransaction();

            // Persistimos al tutor
            session.persist(tutor);
            transaction.commit();
            System.out.println("✅ Cuenta de tutor creada con éxito.");
            Logger.logInfo("Cuenta de tutor creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, revertimos la transacción
            }
            throw new RuntimeException(e);  // Lanza una excepción si ocurre un error
        }
    }

    /**
     * Obtiene la lista de alumnos asociados a un tutor específico, identificado por su DNI.
     * Este método realiza una consulta HQL para obtener al tutor y, mediante un LEFT JOIN, también obtiene
     * los alumnos asociados a él.
     *
     * @param dniTutor El DNI del tutor cuyas alumnos se desean obtener.
     * @return Una lista de objetos {@link Alumno} que representan a los alumnos asociados al tutor.
     * Si el tutor no se encuentra, retorna una lista vacía.
     */
    public List<Alumno> obtenerAlumnosTutor(String dniTutor) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            // Consulta HQL con LEFT JOIN para obtener al tutor y sus alumnos
            Tutor tutor = session.createQuery(
                            "SELECT t FROM Tutor t LEFT JOIN FETCH t.alumnos WHERE t.dniTutor = :dni", Tutor.class)
                    .setParameter("dni", dniTutor)
                    .uniqueResult();

            if (tutor != null) {
                return tutor.getAlumnos();  // Si el tutor existe, retornamos su lista de alumnos
            }
            return List.of();  // Retorna lista vacía si no se encuentra el tutor
        } catch (Exception e) {
            System.out.println("❌ Error al obtener los alumnos del tutor: " + e.getMessage());
            Logger.logError("Error al obtener los alumnos del tutor: " + e.getMessage());
            return List.of();  // En caso de error, retornamos una lista vacía
        }
    }
}
