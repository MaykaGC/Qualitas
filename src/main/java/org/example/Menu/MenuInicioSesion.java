package org.example.Menu;

import org.example.Service.UsuarioService;
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
                case "1" -> usuarioService.iniciarSesion();
                case "2" -> usuarioService.crearCuenta();
                case "0" -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }
}

