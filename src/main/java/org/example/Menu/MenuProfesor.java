package org.example.Menu;

import org.example.Service.ProfesorService;
import org.example.Utils.Logger;

import java.util.Scanner;

public class MenuProfesor {
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    public MenuProfesor(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

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
                case "1" -> {
                    profesorService.verAsignaturasProfesor();
                    Logger.logInfo("Menú profesor: Ver mis asignaturas");
                }
                case "2" -> {
                    profesorService.añadirNota();
                    Logger.logInfo("Menú profesor: Añadir una nota");
                }
                case "0" -> {
                    System.out.println("Cerrando sesión...");
                    Logger.logInfo("Menú profesor: Cerrar sesión");
                }
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    Logger.logError("Menú profesor: Opción inválida");
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú Profesor: Menú cerrado");
    }
}
