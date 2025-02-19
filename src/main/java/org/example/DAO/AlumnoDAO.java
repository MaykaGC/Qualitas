package org.example.DAO;

import org.example.Entity.Alumno;
import org.example.Entity.Matricula;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlumnoDAO {

    private final UsuarioDAO usuarioDAO;

    public AlumnoDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Métod0 para crear un nuevo alumno
    public void crearAlumno(Alumno alumno, Usuario usuario, String dniTutor) {

        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            // Verificamos si el tutor existe
            Tutor tutor = session.get(Tutor.class, dniTutor);
            if (tutor == null) {
                System.out.println("El tutor con DNI " + dniTutor + " no existe");
                return;
            }

            // Verificar si el usuario ya existe en la base de datos
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorDni(usuario.getDni());

            // Si el usuario no existe, lo creamos
            if (usuarioExistente == null) {
                usuarioDAO.crearUsuario(session, usuario);
            } else {
                // Si el usuario ya existe, podemos usar el usuario existente
                usuario = usuarioExistente;
            }

            // Establecemos el usuario al alumno
            alumno.setUsuario(usuario);

            // Establecemos el tutor al alumno
            alumno.setTutor(tutor);

            // Ahora persistimos al alumno
            session.persist(alumno);
            transaction.commit();
            System.out.println("Cuenta de alumno creada con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para obtener un alumno por su DNI
    public Alumno obtenerAlumnoPorDni(Alumno alumno) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            Alumno result = session.get(Alumno.class, alumno.getDniAlumno());
            if (result != null) {
                Hibernate.initialize(result.getMatriculas());
                for (Matricula matricula : result.getMatriculas()) {
                    Hibernate.initialize(matricula.getAsignatura());
                }
            }
            return result;
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
