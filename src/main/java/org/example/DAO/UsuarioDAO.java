package org.example.DAO;

import org.example.Entity.Usuario;
import org.example.Utils.UtilsHibernate;
import org.example.Utils.UtilsPassword;
import org.hibernate.Session;

/**
 * Clase encargada de las operaciones de base de datos relacionadas con los objetos de tipo {@link Usuario}.
 */
public class UsuarioDAO {

    /**
     * Obtiene un {@link Usuario} a partir de su DNI.
     * Este método realiza una consulta en la base de datos para obtener un usuario según el DNI proporcionado.
     *
     * @param dni El DNI del usuario que se desea obtener.
     * @return El objeto {@link Usuario} correspondiente al DNI, o null si no se encuentra en la base de datos.
     */
    public Usuario obtenerUsuarioPorDni(String dni) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Usuario.class, dni);  // Devuelve el usuario o null si no existe
        }
    }

    /**
     * Crea un nuevo {@link Usuario} en la base de datos.
     * Este método persiste un objeto {@link Usuario} en la sesión de Hibernate.
     *
     * @param session La sesión de Hibernate que se utilizará para persistir el usuario.
     * @param usuario El objeto {@link Usuario} que se va a crear.
     */
    public void crearUsuario(Session session, Usuario usuario) {
        session.persist(usuario);  // Persiste al usuario en la base de datos
    }

    /**
     * Verifica las credenciales de un usuario a partir de su DNI y la contraseña ingresada.
     * Este método busca al usuario en la base de datos por su DNI y valida la contraseña proporcionada.
     *
     * @param dni El DNI del usuario.
     * @param passwordIngresada La contraseña que se desea verificar.
     * @return El objeto {@link Usuario} si las credenciales son correctas, o null si las credenciales son incorrectas o el usuario no existe.
     */
    public Usuario verificarCredenciales(String dni, String passwordIngresada) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            // Buscamos al usuario por su DNI
            Usuario usuario = session.createQuery("FROM Usuario WHERE dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();

            // Si el usuario no existe, retornamos null
            if (usuario == null) {
                return null;
            }

            // Verifica si la contraseña ingresada coincide con la almacenada
            if (UtilsPassword.checkPassword(passwordIngresada, usuario.getPassword())) {
                return usuario;  // Contraseña correcta, retorna el usuario
            } else {
                return null;  // Contraseña incorrecta
            }
        }
    }
}
