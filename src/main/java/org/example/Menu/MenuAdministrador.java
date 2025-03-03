package org.example.Menu;

import org.example.Service.AdministradorService;
import org.example.Service.AlumnoService;
import org.example.Service.ProfesorService;

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
                    4. Ver horario de alumno
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
                case "1" -> administradorService.asignarAsignaturaProfesor();
                case "2" -> administradorService.matricularAlumnoEnAsignatura();
                case "3" -> {
                    System.out.println("Introduce el DNI del profesor");
                    String dniProfesor = scanner.nextLine();
                    new ProfesorService(dniProfesor).verAsignaturasProfesor();
                }
                case "4" -> {
                    System.out.println("Introduce el DNI del alumno");
                    String dniAlumno = scanner.nextLine();
                    new AlumnoService(dniAlumno).verHorarioAlumno();
                }
                case "5" -> administradorService.crearAsignatura();
                case "6" -> administradorService.actualizarUsuario(administradorService);
                case "7" -> administradorService.eliminarUsuario(administradorService);
                case "0" -> System.out.println("Cerrando sesión...");
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }
}