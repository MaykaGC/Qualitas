package org.example.Menu;

import org.example.Service.ProfesorService;
import org.example.Utils.Logger;

import java.util.Scanner;

/**
 * Menú del profesor para la aplicación.
 * Permite a los profesores ver sus asignaturas, añadir una nota o cerrar sesión.
 * Según la opción seleccionada, se invocan los métodos correspondientes del servicio del profesor.
 */
public class MenuProfesor {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor para el menú del profesor.
     *
     * @param usuarioLogeado El identificador del usuario que ha iniciado sesión.
     */
    public MenuProfesor(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Muestra el menú de opciones para el profesor y maneja la selección de la opción ingresada.
     * Según la opción seleccionada, se invocan los métodos correspondientes del servicio del profesor.
     * El menú continúa mostrándose hasta que el usuario seleccione la opción para cerrar sesión.
     */
    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("""
                    -----------------------
                    ==== Menú profesor ====
                    -----------------------
                    1. Ver mis asignaturas
                    2. Añadir una nota
                    0. Cerrar sesión
                    -----------------------
                    =======================
                    -----------------------
                    """);
            opcion = scanner.nextLine();

            ProfesorService profesorService = new ProfesorService(usuarioLogeado);

            switch (opcion) {
                case "1" : {
                    Logger.logInfo("Menú profesor: Ver mis asignaturas");
                    profesorService.verAsignaturasProfesor();
                    break;
                }
                case "2" : {
                    Logger.logInfo("Menú profesor: Añadir una nota");
                    profesorService.añadirNota();
                    break;
                }
                case "0" : {
                    Logger.logInfo("Menú profesor: Cerrar sesión");
                    System.out.println("Cerrando sesión...");
                    break;
                }
                default : {
                    Logger.logError("Menú profesor: Opción inválida");
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Profesor: Menú cerrado");
    }
}
