package org.example.Menu;

import org.example.DAO.AlumnoDAO;
import org.example.DAO.TutorDAO;

import java.util.Scanner;

public class MenuTutor {
    private final String usuarioLogeado;
    private static final TutorDAO tutorDAO = new TutorDAO();
    private final Scanner scanner = new Scanner(System.in);

    public MenuTutor(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("""
                --------------------------------
                === Menú Tutor ===
                --------------------------------
                1. Ver progreso del alumno
                2. Revisar comunicados de profesores
                3. Solicitar reunión con un profesor
                0. Cerrar sesión
                --------------------------------
                """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> verProgreso();
                case 2 -> revisarComunicados();
                case 3 -> solicitarReunion();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void verProgreso() {
        System.out.println("Mostrando progreso del alumno...");
        // Implementar lógica
    }

    private void revisarComunicados() {
        System.out.println("Revisando comunicados...");
        // Implementar lógica
    }

    private void solicitarReunion() {
        System.out.println("Solicitando reunión con un profesor...");
        // Implementar lógica
    }
}
