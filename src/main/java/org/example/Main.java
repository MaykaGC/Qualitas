package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("""
                        ----------------------
                            Menú principal
                        ----------------------
                        1. Iniciar sesión
                        2. Crear cuenta
                        0. Salir
                        ----------------------
                        Seleccione una opción:
                        ----------------------
                        """);
                String opcion = scanner.next();
                scanner.nextLine();
                switch (opcion) {
                    case "1":
                        //iniciarSesion(scanner);
                        break;
                    case "2":
                        //crearCuenta(scanner);
                        break;
                    case "0":
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
