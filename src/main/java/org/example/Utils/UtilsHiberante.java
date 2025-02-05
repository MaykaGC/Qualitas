package org.example.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilsHiberante {

//con esta clase iniciamos sesion para trabajar con la base de datos

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable e){
            System.out.println("Initial SessionFactory creation failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return  sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
