package org.example.Menu;

import org.example.DAO.TutorDAO;
import org.example.Service.AlumnoService;
import org.example.Service.TutorService;
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
                case 1 -> tutorService.obtenerAlumnosTutor(usuarioLogeado);
                case 2 -> {
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verNotasAlumno();
                }
                case 3 -> {
                    System.out.println("Introduce el DNI del alumno: ");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verHorarioAlumno();
                }
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }
}
