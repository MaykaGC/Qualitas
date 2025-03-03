package org.example.Menu;

import org.example.Service.UsuarioService;
import org.example.Utils.Logger;

import java.text.ParseException;
import java.util.Scanner;

public class MenuInicioSesion {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioService usuarioService = new UsuarioService();

    public void mostrarMenu() throws ParseException {
        String opcion;
        do {
            System.out.println("""
                --------------------------------
                === Qualitas Escuela Familia ===
                --------------------------------
                1. Iniciar sesión
                2. Crear cuenta
                0. Salir
                --------------------------------
                ================================
                --------------------------------
                """);
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    usuarioService.iniciarSesion();
                    Logger.logInfo("Menú de inicio de sesión: Iniciar sesión");
                }
                case "2" -> {
                    usuarioService.crearCuenta();
                    Logger.logInfo("Menú de inicio de sesión: Crear cuenta");
                }
                case "0" -> {
                    System.out.println("Saliendo de la aplicación...");
                    Logger.logInfo("Menú de inicio de sesión: Salir");
                }
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    Logger.logError("Menú de inicio de sesión: Opción inválida");
                }
            }
        } while (!opcion.equals("0"));
        Logger.logInfo("Menú inicio de sesión: Menú cerrado");
    }
}

