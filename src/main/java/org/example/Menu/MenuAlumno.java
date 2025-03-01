package org.example.Menu;

import org.example.DAO.AlumnoDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;

import java.util.Scanner;

public class MenuAlumno {
    private final String usuarioLogeado;
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final Scanner scanner = new Scanner(System.in);

    public MenuAlumno(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("""
                --------------------------------
                === Menú Alumno ===
                --------------------------------
                1. Ver notas
                2. Ver asignaturas
                0. Cerrar sesión
                --------------------------------
                """);
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> verNotasAlumno();
                case "2" -> verHorarioAlumno();
                case "0" -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }

    public static void verHorarioAlumno() {
        System.out.println("Introduce el DNI del alumno: ");
        String dniAlumno = new Scanner(System.in).nextLine();
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(dniAlumno);
        alumno = alumnoDAO.obtenerAlumnoPorDni(alumno);

        if (alumno != null) {
            System.out.println("Horario de " + alumno.getNombreAlumno() + ":");
            for (Matricula matricula : alumno.getMatriculas()) {
                Asignatura asignatura = matricula.getAsignatura();
                System.out.println("Asignatura: " + asignatura.getNombreAsignatura() + " - Curso: " + asignatura.getCurso());
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    public void verNotasAlumno() {
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(usuarioLogeado);
        alumno = alumnoDAO.obtenerAlumnoPorDni(alumno);

        if (alumno != null) {
            System.out.println("Notas de " + alumno.getNombreAlumno() + ":");
            for (Matricula matricula : alumno.getMatriculas()) {
                Asignatura asignatura = matricula.getAsignatura();
                System.out.println("Asignatura: " + asignatura.getNombreAsignatura() + " - Nota: " + matricula.getNota());
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }
}
