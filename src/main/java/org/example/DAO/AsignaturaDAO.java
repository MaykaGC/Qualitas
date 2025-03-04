package org.example.DAO;

import org.example.Entity.Asignatura;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;

/**
 * Clase que gestiona las operaciones de base de datos relacionadas con las asignaturas.
 */
public class AsignaturaDAO {

    /**
     * Obtiene una asignatura por su ID.
     * Este método busca una asignatura en la base de datos utilizando su identificador único.
     *
     * @param asignatura El objeto {@link Asignatura} que contiene el ID de la asignatura a buscar.
     * @return El objeto {@link Asignatura} correspondiente al ID proporcionado, o null si no existe.
     */
    public Asignatura obtenerAsignaturaPorId(Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Asignatura.class, asignatura.getIdAsignatura());
        }
    }
}
