package org.example.DAO;

import org.example.Entity.*;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdministradorDAO {
    private final UsuarioDAO usuarioDAO;

    public AdministradorDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

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
            System.out.println("Cuenta de administrador creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void asignarAsignaturaProfesor(int idAsignatura, String dniProfesor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, dniProfesor);
            Asignatura asignatura = session.get(Asignatura.class, idAsignatura);
            if (profesor != null && asignatura != null) {
                profesor.getAsignaturas().add(asignatura);
                asignatura.setProfesor(profesor);
                session.update(profesor);
                session.update(asignatura);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    // Método para asignar un profesor a una asignatura
    public void asignarProfesorAAsignatura(int idAsignatura, String dniProfesor) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, dniProfesor);
            Asignatura asignatura = session.get(Asignatura.class, idAsignatura);
            if (profesor != null && asignatura != null) {
                asignatura.setProfesor(profesor);
                session.update(asignatura);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void matricularAlumnoEnAsignatura(String dniAlumno, int idAsignatura) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, dniAlumno);
            Asignatura asignatura = session.get(Asignatura.class, idAsignatura);
            if (alumno != null && asignatura != null) {
                Matricula matricula = new Matricula();
                matricula.setAlumno(alumno);
                matricula.setAsignatura(asignatura);
                session.persist(matricula);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public void crearAsignatura(Asignatura asignatura) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(asignatura);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}