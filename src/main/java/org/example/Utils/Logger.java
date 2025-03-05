package org.example.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase Logger para registrar eventos en un archivo de log.
 */
public class Logger {
    private static String LOG_FILE = generarNombreArchivo();

    /**
     * Genera el nombre del archivo con fecha y hora (cambia cada hora).
     */
    private static String generarNombreArchivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH");
        String fechaHora = sdf.format(new Date());
        return "log_" + fechaHora + ".txt";
    }

    /**
     * Registra un mensaje de información.
     *
     * @param message Mensaje a registrar.
     */
    public static void logInfo(String message) {
        log("INFO", message);
    }

    /**
     * Registra un mensaje de advertencia.
     *
     * @param message Mensaje a registrar.
     */
    public static void logWarning(String message) {
        log("WARNING", message);
    }

    /**
     * Registra un mensaje de error.
     *
     * @param message Mensaje a registrar.
     */
    public static void logError(String message) {
        log("ERROR", message);
    }

    /**
     * Método interno para escribir en el archivo de log.
     *
     * @param level   Nivel del log (INFO, WARNING, ERROR).
     * @param message Mensaje a registrar.
     */
    private static void log(String level, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = String.format("[%s] [%s] %s%n", timestamp, level, message);

        // Verifica si ha cambiado la hora para generar un nuevo archivo
        String nuevoArchivo = generarNombreArchivo();
        if (!nuevoArchivo.equals(LOG_FILE)) {
            LOG_FILE = nuevoArchivo;
        }

        // Escribir en el archivo de log
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("❌ Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
