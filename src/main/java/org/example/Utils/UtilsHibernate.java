package org.example.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public static <T> void eliminarPorId(Class<T> entidad, String dni) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T objeto = buscarPorDni(entidad, dni); // Se busca la entidad por DNI
            if (objeto != null) {
                session.remove(objeto);
                System.out.println("✅ " + entidad.getSimpleName() + " con ID " + dni + " eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró un " + entidad.getSimpleName() + " con ID " + dni);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("❌ Error al eliminar la entidad " + entidad.getSimpleName(), e);
        }
    }

    public static <T> void actualizarPorDni(Class<T> entidad, String dni, T datosActualizados) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Buscar el objeto existente en la base de datos
            T objetoExistente = buscarPorDni(entidad, dni);
            if (objetoExistente != null) {
                // Copiar los valores de datosActualizados a objetoExistente
                session.evict(objetoExistente); // Desvincular el objeto de la sesión
                copiarValores(objetoExistente, datosActualizados);
                session.merge(objetoExistente); // Guardar la actualización
                System.out.println("✅ " + entidad.getSimpleName() + " con DNI " + dni + " actualizado correctamente.");
            } else {
                System.out.println("⚠ No se encontró un " + entidad.getSimpleName() + " con DNI " + dni);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("❌ Error al actualizar la entidad " + entidad.getSimpleName(), e);
        }
    }

    private static <T> void copiarValores(T destino, T fuente) {
        try {
            for (var campo : destino.getClass().getDeclaredFields()) {
                campo.setAccessible(true);
                Object valor = campo.get(fuente);
                if (valor != null) { // Solo copiar valores no nulos
                    campo.set(destino, valor);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error al copiar valores entre entidades", e);
        }
    }

    public static <T> T buscarPorDni(Class<T> entidad, String dni) {
        try (Session session = getSessionFactory().openSession()) {
            return session.find(entidad, dni); // `find()` es recomendado en Hibernate 6
        } catch (Exception e) {
            throw new RuntimeException("❌ Error al buscar " + entidad.getSimpleName() + " con DNI: " + dni, e);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
