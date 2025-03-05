package org.example.Menu;

import org.example.Service.UsuarioService;
import org.example.Utils.Logger;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Menú de inicio de sesión para la aplicación.
 * Permite a los usuarios iniciar sesión, crear una nueva cuenta o salir de la aplicación.
 * Según la opción seleccionada, se invocan los métodos correspondientes del servicio de usuario.
 */
public class MenuInicioSesion {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioService usuarioService = new UsuarioService();

    /**
     * Muestra el menú de opciones para el inicio de sesión y maneja la selección de la opción ingresada.
     * Según la opción seleccionada, se invocan los métodos correspondientes del servicio de usuario.
     * El menú continúa mostrándose hasta que el usuario seleccione la opción para salir de la aplicación.
     *
     * @throws ParseException Si ocurre un error al analizar las fechas o formatos durante el inicio de sesión.
     */
    public void mostrarMenu() throws ParseException {
        String opcion;
        do {
            System.out.println("""
                --------------------------------
                === Qualitas Escuela Familia ===
                --------------------------------
                1. Iniciar sesión
                2. Crear cuenta
                0. Salir
                --------------------------------
                ================================
                --------------------------------
                """);
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" : {
                    Logger.logInfo("Menú de inicio de sesión: Iniciar sesión");
                    usuarioService.iniciarSesion();
                    break;
                }
                case "2" : {
                    Logger.logInfo("Menú de inicio de sesión: Crear cuenta");
                    usuarioService.crearCuenta();
                    break;
                }
                case "0" : {
                    Logger.logInfo("Menú de inicio de sesión: Salir");
                    System.out.println("Saliendo de la aplicación...");
                    break;
                }
                default : {
                    Logger.logError("Menú de inicio de sesión: Opción inválida");
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú inicio de sesión: Menú cerrado");
    }
}
