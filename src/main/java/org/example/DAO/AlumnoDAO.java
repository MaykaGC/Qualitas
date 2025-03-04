package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Matricula;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;
import org.example.Utils.Logger;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona las operaciones de base de datos relacionadas con el alumno,
 * como la creación de cuentas de alumno y la obtención de información sobre alumnos.
 */
public class AlumnoDAO {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor de la clase {@link AlumnoDAO}.
     * Inicializa una instancia de {@link UsuarioDAO} para gestionar operaciones de usuario.
     */
    public AlumnoDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Obtiene un alumno por su DNI.
     * Este método también inicializa las matriculas y las asignaturas asociadas al alumno.
     *
     * @param alumno El objeto {@link Alumno} con el DNI a buscar.
     * @return El objeto {@link Alumno} correspondiente al DNI proporcionado, o null si no existe.
     */
    public Alumno obtenerAlumnoPorDni(Alumno alumno) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            Alumno result = session.get(Alumno.class, alumno.getDniAlumno());
            if (result != null) {
                // Inicializamos las matriculas y las asignaturas asociadas
                Hibernate.initialize(result.getMatriculas());
                for (Matricula matricula : result.getMatriculas()) {
                    Hibernate.initialize(matricula.getAsignatura());
                }
            }
            return result;
        }
    }

    /**
     * Crea una cuenta de alumno en la base de datos.
     * Si el tutor o el usuario no existen, se crean. Luego, se vincula el alumno con el tutor y el usuario.
     *
     * @param alumno El objeto {@link Alumno} a ser creado.
     * @param usuario El objeto {@link Usuario} asociado al alumno.
     * @param dniTutor El DNI del tutor que será asignado al alumno.
     */
    public void crearAlumno(Alumno alumno, Usuario usuario, String dniTutor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Verificamos si el tutor existe
            Tutor tutor = session.get(Tutor.class, dniTutor);
            if (tutor == null) {
                System.out.println("El tutor con DNI " + dniTutor + " no existe");
                Logger.logError("El tutor con DNI - Se oculta por privacidad - no existe");
                return;
            }

            // Verificar si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                // Si el usuario ya existe, usamos el usuario existente
                usuario = usuarioExistente;
            }

            // Establecemos el usuario al alumno
            alumno.setUsuario(usuario);

            // Establecemos el tutor al alumno
            alumno.setTutor(tutor);

            // Ahora persistimos al alumno en la base de datos
            session.persist(alumno);
            transaction.commit();
            System.out.println("✅ Cuenta de alumno creada con éxito.");
            Logger.logInfo("Cuenta de alumno creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    throw new RuntimeException(rollbackEx);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
