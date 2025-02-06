package org.example.DAO;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.example.Entity.*;
import org.example.Utils.*;

public class NotaDAO {
    public void addMark(Nota nota) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(nota);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }


}
