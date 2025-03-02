package org.example.Menu;

import org.example.Service.AlumnoService;

import java.util.Scanner;

public class MenuAlumno {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    public MenuAlumno(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

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
                case "1" -> alumnoService.verNotasAlumno();
                case "2" -> alumnoService.verHorarioAlumno();
                case "0" -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }
}
