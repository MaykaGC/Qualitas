package org.example.Menu;

import org.example.Service.AdministradorService;
import org.example.Service.AlumnoService;
import org.example.Service.ProfesorService;
import org.example.Utils.Logger;

import java.util.Scanner;

public class MenuAdministrador {
    private static final Scanner scanner = new Scanner(System.in);

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
                case "1" -> {
                    administradorService.asignarAsignaturaProfesor();
                    Logger.logInfo("Menú Administrador: Asignar asignatura a profesor");
                }
                case "2" -> {
                    administradorService.matricularAlumnoEnAsignatura();
                    Logger.logInfo("Menú Administrador: Matricular alumno en asignatura");
                }
                case "3" -> {
                    System.out.println("Introduce el DNI del profesor");
                    String dniProfesor = scanner.nextLine();
                    new ProfesorService(dniProfesor).verAsignaturasProfesor();
                    Logger.logInfo("Menú Administrador: Ver asignaturas de profesor");
                }
                case "4" -> {
                    System.out.println("Introduce el DNI del alumno");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verAsignaturasAlumno();
                    Logger.logInfo("Menú Administrador: Ver asignaturas de alumno");
                }
                case "5" -> {
                    administradorService.crearAsignatura();
                    Logger.logInfo("Menú Administrador: Crear asignatura");
                }
                case "6" -> {
                    administradorService.actualizarUsuario(administradorService);
                    Logger.logInfo("Menú Administrador: Actualizar usuario");
                }
                case "7" -> {
                    administradorService.eliminarUsuario(administradorService);
                    Logger.logInfo("Menú Administrador: Eliminar usuario");
                }
                case "0" -> {
                    System.out.println("Cerrando sesión...");
                    Logger.logInfo("Menú Administrador: Cerrar sesión");
                }
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    Logger.logError("Menú Administrador: Opción inválida");
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Administrador: Menú cerrado");
    }
}