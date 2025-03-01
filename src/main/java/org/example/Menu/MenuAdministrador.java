package org.example.Menu;

import org.example.DAO.AdministradorDAO;
import org.example.DAO.ProfesorDAO;
import org.example.Entity.Asignatura;
import org.example.Entity.Profesor;
import java.util.Scanner;
import static org.example.Menu.MenuAlumno.verHorarioAlumno;

public class MenuAdministrador {
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final String usuarioLogeado;
    private static final Scanner scanner = new Scanner(System.in);

    public MenuAdministrador(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

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
                    0. Cerrar sesión
                    ------------------------------------
                    ====================================
                    ------------------------------------
                    """);
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> asignarAsignaturaProfesor();
                case "2" -> matricularAlumnoEnAsignatura();
                case "3" -> verAsignaturasProfesor();
                case "4" -> verHorarioAlumno();
                case "5" -> crearAsignatura();
                case "0" -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }

    private void asignarAsignaturaProfesor() {
        System.out.print("DNI del profesor: ");
        String dniProfesor = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            if (administradorDAO.asignarAsignaturaProfesor(idAsignatura, dniProfesor) != null) {
                System.out.println("Asignatura asignada correctamente al profesor.");
            } else {
                System.out.println("Error al asignar la asignatura al profesor.");
            }
        } catch (Exception e) {
            System.out.println("Error al asignar la asignatura: " + e.getMessage());
        }
    }

    private void matricularAlumnoEnAsignatura() {
        System.out.print("DNI del alumno: ");
        String dniAlumno = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            if (administradorDAO.matricularAlumnoEnAsignatura(dniAlumno, idAsignatura) != null){
                System.out.println("Alumno matriculado correctamente en la asignatura.");
            } else {
                System.out.println("Error al matricular al alumno.");
            }
        } catch (Exception e) {
            System.out.println("Error al matricular al alumno: " + e.getMessage());
        }
    }

    public void verAsignaturasProfesor() {
        System.out.println("Introduce el DNI del profesor: ");
        String dniProfesor = new Scanner(System.in).nextLine();
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(dniProfesor);
        profesor = profesorDAO.obtenerProfesorPorDni(profesor);

        if (profesor != null) {
            System.out.println("Asignaturas de " + profesor.getNombreProfesor() + ":");
            if (profesor.getAsignaturas().isEmpty()) {
                System.out.println("No tiene asignaturas asignadas.");
            } else {
                for (Asignatura asignatura : profesor.getAsignaturas()) {
                    System.out.println("Asignatura: " + asignatura.getNombreAsignatura());
                }
            }
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    private void crearAsignatura() {
        System.out.print("Nombre de la asignatura: ");
        String nombre = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        String dniProfesor = null;
        System.out.print("¿Quieres asignar un profesor a esta asignatura ahora mismo? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Introduce el DNI del Profesor: ");
            dniProfesor = scanner.nextLine();
        }

        // Crear la asignatura con o sin profesor
        try {
            Asignatura asignatura = new Asignatura();
            asignatura.setNombreAsignatura(nombre);
            asignatura.setCurso(curso);

            administradorDAO.crearAsignaturaConProfesor(asignatura, dniProfesor);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la asignatura: " + e.getMessage());
        }
    }


}
