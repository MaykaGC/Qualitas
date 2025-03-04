package org.example.Menu;

import org.example.Service.AlumnoService;
import org.example.Utils.Logger;

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
                case "1" -> {
                    alumnoService.verNotasAlumno();
                    Logger.logInfo("Menú Alumno: Ver notas");
                }
                case "2" -> {
                    alumnoService.verAsignaturasAlumno();
                    Logger.logInfo("Menú Alumno: Ver asignaturas");
                }
                case "0" -> {
                    System.out.println("Cerrando sesión...");
                    Logger.logInfo("Menú Alumno: Cerrar sesión");
                }
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    Logger.logError("Menú Alumno: Opción inválida");
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Alumno: Menú cerrado");
    }
}
