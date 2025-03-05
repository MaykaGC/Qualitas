package org.example.Menu;

import org.example.Service.AdministradorService;
import org.example.Service.AlumnoService;
import org.example.Service.ProfesorService;
import org.example.Utils.Logger;

import java.util.Scanner;

/**
 * Menú interactivo para las acciones del administrador.
 * Esta clase permite al administrador realizar diversas acciones de gestión de usuarios y asignaturas,
 * como asignar asignaturas a profesores, matricular alumnos, ver las asignaturas de alumnos y profesores,
 * crear y eliminar asignaturas y actualizar o eliminar usuarios.
 */
public class MenuAdministrador {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú de opciones para el administrador y maneja la selección de la opción ingresada.
     * Según la opción seleccionada, se invocan los métodos correspondientes de los servicios de administrador,
     * alumno y profesor.
     * El menú continúa mostrándose hasta que el administrador seleccione la opción para cerrar sesión.
     */
    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("""
                    ------------------------------------
                    ======== Menú Administrador ========
                    ------------------------------------
                    1. Asignar asignatura a profesor
                    2. Matricular alumno en asignatura
                    3. Ver asignaturas de profesor
                    4. Ver asignaturas de alumno
                    5. Crear asignatura
                    6. Actualizar usuario
                    7. Eliminar usuario
                    0. Cerrar sesión
                    ------------------------------------
                    ====================================
                    ------------------------------------
                    """);
            opcion = scanner.nextLine();

            AdministradorService administradorService = new AdministradorService();

            switch (opcion) {
                case "1" : {
                    Logger.logInfo("Menú Administrador: Asignar asignatura a profesor");
                    administradorService.asignarAsignaturaProfesor();
                    break;
                }
                case "2" : {
                    Logger.logInfo("Menú Administrador: Matricular alumno en asignatura");
                    administradorService.matricularAlumnoEnAsignatura();
                    break;
                }
                case "3" : {
                    Logger.logInfo("Menú Administrador: Ver asignaturas de profesor");
                    System.out.println("Introduce el DNI del profesor");
                    String dniProfesor = scanner.nextLine();
                    new ProfesorService(dniProfesor).verAsignaturasProfesor();
                    break;
                }
                case "4" : {
                    Logger.logInfo("Menú Administrador: Ver asignaturas de alumno");
                    System.out.println("Introduce el DNI del alumno");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verAsignaturasAlumno();
                    break;
                }
                case "5" : {
                    Logger.logInfo("Menú Administrador: Crear asignatura");
                    administradorService.crearAsignatura();
                    break;
                }
                case "6" : {
                    Logger.logInfo("Menú Administrador: Actualizar usuario");
                    administradorService.actualizarUsuario(administradorService);
                    break;
                }
                case "7" : {
                    Logger.logInfo("Menú Administrador: Eliminar usuario");
                    administradorService.eliminarUsuario(administradorService);
                    break;
                }
                case "0" : {
                    Logger.logInfo("Menú Administrador: Cerrar sesión");
                    System.out.println("Cerrando sesión...");
                    break;
                }
                default : {
                    Logger.logError("Menú Administrador: Opción inválida");
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Administrador: Menú cerrado");
    }
}
