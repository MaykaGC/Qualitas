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
            System.out.println("✅ Cuenta de administrador creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

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
            // Se hace merge de los objetos para que se actualicen en la base de datos, de todas formas no es necesario porque Hibernate detecta los cambios automáticamente al hacer commit()
            session.merge(profesor);
            session.merge(asignatura);

            transaction.commit();
            return asignatura;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

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
                    return;
                }
                asignatura.setProfesor(profesor);
                session.merge(asignatura);
            }

            transaction.commit();
            System.out.println("✅ Asignatura creada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear la asignatura: " + e.getMessage());
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}