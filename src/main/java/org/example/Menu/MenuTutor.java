package org.example.Menu;

import org.example.Service.AlumnoService;
import org.example.Service.TutorService;
import org.example.Utils.Logger;

import java.util.Scanner;

public class MenuTutor {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    public MenuTutor(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("""
                    --------------------------------
                    ========== Menú Tutor ==========
                    --------------------------------
                    1. Ver mis alumnos
                    2. Ver nota de un alumno
                    3. Ver horario de un alumno
                    0. Cerrar sesión
                    --------------------------------
                    ================================
                    --------------------------------
                    """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            TutorService tutorService = new TutorService();

            switch (opcion) {
                case 1 -> {
                    tutorService.obtenerAlumnosTutor(usuarioLogeado);
                    Logger.logInfo("Menú Tutor: Ver mis alumnos");
                }
                case 2 -> {
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verNotasAlumno();
                    Logger.logInfo("Menú Tutor: Ver nota de un alumno");
                }
                case 3 -> {
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verHorarioAlumno();
                    Logger.logInfo("Menú Tutor: Ver horario de un alumno");
                }
                case 0 -> {
                    System.out.println("Cerrando sesión...");
                    Logger.logInfo("Menú Tutor: Cerrar sesión");
                }
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    Logger.logError("Menú Tutor: Opción inválida");
                }
            }
        } while (opcion != 0);
        Logger.logInfo("Menú Tutor: Menú cerrado");
    }
}
