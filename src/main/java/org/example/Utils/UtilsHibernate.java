package org.example.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Utilidades para la gestión de sesiones y operaciones con Hibernate.
 * Esta clase proporciona métodos para crear sesiones, buscar, eliminar y actualizar entidades en la base de datos.
 * También se encarga de manejar la configuración y el ciclo de vida de las sesiones de Hibernate.
 */
public class UtilsHibernate {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Crea una factoría de sesiones de Hibernate utilizando la configuración definida en el archivo "hibernate.cfg.xml".
     * En caso de error al crear la factoría, se registra un mensaje de error.
     *
     * @return La factoría de sesiones de Hibernate.
     * @throws ExceptionInInitializerError Si ocurre un error durante la inicialización de la factoría.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            Logger.logInfo("Build session factory: Creando la factoría de sesiones de Hibernate");
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            Logger.logError("Build session factory: Error al crear la factoría de sesiones de Hibernate -> " + e);
            throw new ExceptionInInitializerError("❌ Error al crear la factoría de sesiones de Hibernate" +e);
        }
    }

    /**
     * Elimina una entidad de la base de datos por su identificador (DNI).
     * Si la entidad no se encuentra, se registra un mensaje de advertencia.
     *
     * @param entidad La clase de la entidad que se desea eliminar.
     * @param dni El identificador (DNI) de la entidad a eliminar.
     * @param <T> El tipo de la entidad.
     */
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

    /**
     * Actualiza los datos de una entidad en la base de datos, identificada por su DNI.
     * Si la entidad no existe, se registra un mensaje de advertencia.
     *
     * @param entidad La clase de la entidad que se desea actualizar.
     * @param dni El identificador (DNI) de la entidad a actualizar.
     * @param datosActualizados El objeto con los nuevos datos para actualizar la entidad.
     * @param <T> El tipo de la entidad.
     */
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

    /**
     * Copia los valores de los campos de un objeto fuente a un objeto destino.
     * Este método se utiliza para actualizar una entidad con nuevos datos.
     *
     * @param destino El objeto destino que recibirá los valores.
     * @param fuente El objeto fuente del que se copiarán los valores.
     * @param <T> El tipo de la entidad.
     */
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

    /**
     * Busca una entidad en la base de datos por su identificador (DNI).
     *
     * @param entidad La clase de la entidad a buscar.
     * @param dni El identificador (DNI) de la entidad a buscar.
     * @param <T> El tipo de la entidad.
     * @return El objeto de la entidad encontrado, o null si no se encuentra.
     */
    public static <T> T buscarPorDni(Class<T> entidad, String dni) {
        try (Session session = getSessionFactory().openSession()) {
            return session.find(entidad, dni); // `find()` es recomendado en Hibernate 6
        } catch (Exception e) {
            Logger.logError("Buscar por id: Error al buscar la entidad -> " + e);
            throw new RuntimeException("❌ Error al buscar " + entidad.getSimpleName() + " con DNI: " + dni, e);
        }
    }

    /**
     * Obtiene la factoría de sesiones de Hibernate.
     *
     * @return La factoría de sesiones de Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        Logger.logInfo("Get session factory: Obteniendo la factoría de sesiones de Hibernate");
        return sessionFactory;
    }

    /**
     * Cierra la factoría de sesiones de Hibernate.
     * Se debe llamar cuando ya no se necesiten más sesiones.
     */
    public static void shutdown() {
        Logger.logInfo("Shutdown: Cerrando la factoría de sesiones de Hibernate");
        getSessionFactory().close();
    }
}
