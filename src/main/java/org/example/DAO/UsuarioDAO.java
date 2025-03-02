package org.example.DAO;

import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.example.Utils.UtilsPassword;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioDAO {

    // Métod0 para obtener un usuario por su DNI
    public Usuario obtenerUsuarioPorDni(String dni) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Usuario.class, dni);
        }
    }

    // Métod0 para crear un nuevo usuario
    public void crearUsuario(Session session, Usuario usuario) {
        //Transaction transaction = null;
        //try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            //transaction = session.beginTransaction();
            session.persist(usuario);
            //transaction.commit();
        //} catch (Exception e) {
            //if (transaction != null) {
                //transaction.rollback();
            //}
            //throw new RuntimeException(e);
        //}
    }

//    public void crearUsuario(Usuario usuario) {
//        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            session.persist(usuario);
//            session.getTransaction().commit();
//        }
//    }

    public Usuario verificarCredenciales(String dni, String passwordIngresada) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            Usuario usuario = session.createQuery("FROM Usuario WHERE dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();

            if (usuario == null) {
                return null;
            }

            // Verifica la contraseña ingresada con la almacenada
            if (UtilsPassword.checkPassword(passwordIngresada, usuario.getPassword())) {
                return usuario; // Contraseña válida, retorna el usuario
            } else {
                return null; // Contraseña incorrecta
            }
        }
    }

    // Métod0 para actualizar los datos de un usuario
    public void actualizarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    // Métod0 para eliminar un usuario
    public void eliminarUsuario(String dni) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, dni);
            if (usuario != null) {
                session.delete(usuario);
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
