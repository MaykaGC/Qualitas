package org.example.Menu;

import org.example.Service.AlumnoService;
import org.example.Service.TutorService;
import org.example.Utils.Logger;

import java.util.Scanner;

/**
 * Menú del tutor para la aplicación.
 * Permite a los tutores ver sus alumnos, las notas y las asignaturas de los alumnos,
 * o cerrar sesión.
 * Según la opción seleccionada, se invocan los métodos correspondientes del servicio de tutor o de alumno.
 */
public class MenuTutor {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor para el menú del tutor.
     *
     * @param usuarioLogeado El identificador del usuario que ha iniciado sesión.
     */
    public MenuTutor(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Muestra el menú de opciones para el tutor y maneja la selección de la opción ingresada.
     * Según la opción seleccionada, se invocan los métodos correspondientes del servicio del tutor o de alumno.
     * El menú continúa mostrándose hasta que el usuario seleccione la opción para cerrar sesión.
     */
    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println(""" 
                    --------------------------------
                    ========== Menú Tutor ==========
                    --------------------------------
                    1. Ver mis alumnos
                    2. Ver notas de un alumno
                    3. Ver asignaturas de un alumno
                    0. Cerrar sesión
                    --------------------------------
                    ===============================
                    --------------------------------
                    """);
            opcion = scanner.nextLine();

            TutorService tutorService = new TutorService();

            switch (opcion) {
                case "1" : {
                    Logger.logInfo("Menú Tutor: Ver mis alumnos");
                    tutorService.obtenerAlumnosTutor(usuarioLogeado);
                    break;
                }
                case "2" : {
                    Logger.logInfo("Menú Tutor: Ver nota de un alumno");
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verNotasAlumno();
                    break;
                }
                case "3" : {
                    Logger.logInfo("Menú Tutor: Ver asignaturas de un alumno");
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verAsignaturasAlumno();
                    break;
                }
                case "0" : {
                    Logger.logInfo("Menú Tutor: Cerrar sesión");
                    System.out.println("Cerrando sesión...");
                    break;
                }
                default : {
                    Logger.logError("Menú Tutor: Opción inválida");
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Tutor: Menú cerrado");
    }
}
