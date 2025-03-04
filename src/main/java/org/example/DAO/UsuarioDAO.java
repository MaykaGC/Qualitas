package org.example.DAO;

import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.example.Utils.UtilsPassword;
import org.hibernate.Session;

public class UsuarioDAO {

    public Usuario obtenerUsuarioPorDni(String dni) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Usuario.class, dni);
        }
    }

    public void crearUsuario(Session session, Usuario usuario) {
        session.persist(usuario);
    }

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
                return usuario; // Contraseña válida
            } else {
                return null; // Contraseña incorrecta
            }
        }
    }
}
