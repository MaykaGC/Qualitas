package org.example.DAO;

import org.example.Entity.*;
import org.example.Utils.Logger;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona las operaciones de base de datos relacionadas con el administrador,
 * como la creación de cuentas de administrador, asignación de asignaturas a profesores,
 * matriculación de alumnos y creación de asignaturas.
 */
public class AdministradorDAO {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor de la clase {@link AdministradorDAO}.
     * Inicializa una instancia de {@link UsuarioDAO} para gestionar operaciones de usuario.
     */
    public AdministradorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Crea una cuenta de administrador en la base de datos.
     * Si el usuario no existe, se crea un nuevo usuario. Si ya existe, se reutiliza el usuario.
     *
     * @param administrador El objeto {@link Administrador} a ser creado.
     * @param usuario El objeto {@link Usuario} asociado al administrador.
     */
    public void crearAdministrador(Administrador administrador, Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                usuario = usuarioExistente;
            }
            administrador.setUsuario(usuario);
            transaction = session.beginTransaction();
            session.persist(administrador);
            transaction.commit();
            System.out.println("✅ Cuenta de administrador creada con éxito.");
            Logger.logInfo("Cuenta de administrador creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * Asigna una asignatura a un profesor en la base de datos.
     * Si el profesor o la asignatura no existen, se realiza un rollback y retorna null.
     *
     * @param idAsignatura El ID de la asignatura a asignar.
     * @param dniProfesor El DNI del profesor al que se asignará la asignatura.
     * @return La asignatura asignada al profesor o null si hubo un error.
     */
    public Asignatura asignarAsignaturaProfesor(int idAsignatura, String dniProfesor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, dniProfesor);
            Asignatura asignatura = session.get(Asignatura.class, idAsignatura);

            if (profesor == null || asignatura == null) {
                transaction.rollback();
                return null;
            }

            profesor.getAsignaturas().add(asignatura);
            asignatura.setProfesor(profesor);
            session.merge(profesor);
            session.merge(asignatura);

            transaction.commit();
            return asignatura;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * Matricula a un alumno en una asignatura.
     * Si el alumno o la asignatura no existen, se realiza un rollback y retorna null.
     *
     * @param dniAlumno El DNI del alumno a matricular.
     * @param idAsignatura El ID de la asignatura en la que el alumno será matriculado.
     * @return El objeto {@link Matricula} creado o null si hubo un error.
     */
    public Matricula matricularAlumnoEnAsignatura(String dniAlumno, int idAsignatura) {
        Transaction transaction = null;
        Matricula matricula = null;

        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, dniAlumno);
            Asignatura asignatura = session.get(Asignatura.class, idAsignatura);

            if (alumno != null && asignatura != null) {
                matricula = new Matricula();
                matricula.setAlumno(alumno);
                matricula.setAsignatura(asignatura);
                session.persist(matricula);
                transaction.commit();
            } else {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }

        return matricula;
    }

    /**
     * Crea una nueva asignatura en la base de datos y la asigna a un profesor si el DNI del profesor es válido.
     * Si el profesor no existe, la asignatura se crea sin asignarla a ningún profesor.
     *
     * @param asignatura El objeto {@link Asignatura} a crear.
     * @param dniProfesor El DNI del profesor al que se asignará la asignatura.
     */
    public void crearAsignaturaConProfesor(Asignatura asignatura, String dniProfesor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(asignatura);

            if (dniProfesor != null && !dniProfesor.isEmpty()) {
                Profesor profesor = session.get(Profesor.class, dniProfesor);
                if (profesor == null) {
                    System.out.println("El profesor con DNI " + dniProfesor + " no existe.\nPuedes asignarlo después desde el menú.");
                    System.out.println("✅ Asignatura creada correctamente.");
                    Logger.logInfo("Asignatura creada correctamente.");
                    return;
                }
                asignatura.setProfesor(profesor);
                session.merge(asignatura);
            }

            transaction.commit();
            System.out.println("✅ Asignatura creada correctamente.");
            Logger.logInfo("Asignatura creada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear la asignatura: " + e.getMessage());
            Logger.logError("Error al crear la asignatura: " + e.getMessage());
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}
