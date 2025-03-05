package org.example.Menu;

import org.example.Service.AlumnoService;
import org.example.Utils.Logger;

import java.util.Scanner;

/**
 * Menú interactivo para las acciones del alumno.
 * Esta clase permite a los alumnos ver sus notas y asignaturas.
 * Además, les permite cerrar sesión al finalizar su interacción.
 */
public class MenuAlumno {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor de la clase MenuAlumno.
     * Inicializa el menú con el usuario que ha iniciado sesión.
     *
     * @param usuarioLogeado El identificador del usuario logeado.
     */
    public MenuAlumno(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Muestra el menú de opciones para el alumno y maneja la selección de la opción ingresada.
     * Según la opción seleccionada, se invocan los métodos correspondientes del servicio de alumno.
     * El menú continúa mostrándose hasta que el alumno seleccione la opción para cerrar sesión.
     */
    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("""
                    --------------------------------
                    ========= Menú Alumno ==========
                    --------------------------------
                    1. Ver notas
                    2. Ver asignaturas
                    0. Cerrar sesión
                    --------------------------------
                    ================================
                    --------------------------------
                    """);
            opcion = scanner.nextLine();

            AlumnoService alumnoService = new AlumnoService(usuarioLogeado);

            switch (opcion) {
                case "1" : {
                    Logger.logInfo("Menú Alumno: Ver notas");
                    alumnoService.verNotasAlumno();
                    break;
                }
                case "2" : {
                    Logger.logInfo("Menú Alumno: Ver asignaturas");
                    alumnoService.verAsignaturasAlumno();
                    break;
                }
                case "0" : {
                    Logger.logInfo("Menú Alumno: Cerrar sesión");
                    System.out.println("Cerrando sesión...");
                    break;
                }
                default : {
                    Logger.logError("Menú Alumno: Opción inválida");
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Alumno: Menú cerrado");
    }
}
