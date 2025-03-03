package org.example.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UtilsHibernate {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Logger.logInfo("Build session factory: Creando la factoría de sesiones de Hibernate");
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            Logger.logError("Build session factory: Error al crear la factoría de sesiones de Hibernate -> " + e);
            throw new ExceptionInInitializerError("❌ Error al crear la factoría de sesiones de Hibernate" +e);
        }
    }

    public static <T> void eliminarPorId(Class<T> entidad, String dni) {
        Transaction transaction = null;
        try (Session session = UtilsHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T objeto = buscarPorDni(entidad, dni);
            if (objeto != null) {
                session.remove(objeto);
                System.out.println("✅ " + entidad.getSimpleName() + " con ID " + dni + " eliminado correctamente.");
                Logger.logInfo("Eliminar por id: entidad eliminada correctamente");
            } else {
                System.out.println("⚠️ No se encontró un " + entidad.getSimpleName() + " con ID " + dni);
                Logger.logWarning("Eliminar por id: entidad no encontrada");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.logError("Eliminar por id: Error al eliminar la entidad -> " + e);
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
                Logger.logInfo("Actualizar por id: entidad actualizada correctamente");
            } else {
                System.out.println("⚠️ No se encontró un " + entidad.getSimpleName() + " con DNI " + dni);
                Logger.logWarning("Actualizar por id: entidad no encontrada");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                Logger.logError("Actualizar por id: Error al actualizar la entidad se hace rollback-> " + e);
            }
            Logger.logError("Actualizar por id: Error al actualizar la entidad -> " + e + " | " + entidad.getSimpleName());
            throw new RuntimeException("❌ Error al actualizar la entidad " + entidad.getSimpleName(), e);
        }
    }

    private static <T> void copiarValores(T destino, T fuente) {
        try {
            for (var campo : destino.getClass().getDeclaredFields()) {
                campo.setAccessible(true);
                Object valor = campo.get(fuente);
                if (valor != null) {
                    campo.set(destino, valor);
                }
            }
        } catch (IllegalAccessException e) {
            Logger.logError("Copiar valores: Error al copiar valores entre entidades -> " + e);
            throw new RuntimeException("❌ Error al copiar valores entre entidades", e);
        }
    }

    public static <T> T buscarPorDni(Class<T> entidad, String dni) {
        try (Session session = getSessionFactory().openSession()) {
            return session.find(entidad, dni); // `find()` es recomendado en Hibernate 6
        } catch (Exception e) {
            Logger.logError("Buscar por id: Error al buscar la entidad -> " + e);
            throw new RuntimeException("❌ Error al buscar " + entidad.getSimpleName() + " con DNI: " + dni, e);
        }
    }

    public static SessionFactory getSessionFactory() {
        Logger.logInfo("Get session factory: Obteniendo la factoría de sesiones de Hibernate");
        return sessionFactory;
    }

    // Cerrar la sesión de Hibernate, se debe llamar al no usar try-with-resources para abrir una sesión
    public static void shutdown() {
        Logger.logInfo("Shutdown: Cerrando la factoría de sesiones de Hibernate");
        getSessionFactory().close();
    }
}
