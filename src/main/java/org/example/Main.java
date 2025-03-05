package org.example;

import org.example.Menu.MenuInicioSesion;
import org.example.Utils.Logger;

import java.text.ParseException;

/**
 * Clase principal que inicia la aplicación.
 * Esta clase es el punto de entrada del programa y se encarga de mostrar el menú de inicio de sesión al iniciar la aplicación.
 */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Registra en el log que la aplicación está comenzando y luego muestra el menú de inicio de sesión.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     * @throws ParseException Si ocurre un error al parsear datos en el proceso de inicio de sesión.
     */
    public static void main(String[] args) throws ParseException {
        Logger.logInfo("Iniciando la aplicación");
        new MenuInicioSesion().mostrarMenu();
    }
}
