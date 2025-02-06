package org.example.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilsHibernate {

    //con esta clase iniciamos una sesión para establecer conexión con la BBDD
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Initial SessionFactory creation failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
