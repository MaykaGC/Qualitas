package org.example.DAO;

import org.example.Entity.Asignatura;
import org.example.Utils.UtilsHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AsignaturaDAO {

    public Asignatura obtenerAsignaturaPorId(Asignatura asignatura) {
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            return session.get(Asignatura.class, asignatura.getIdAsignatura());
        }
    }
}
