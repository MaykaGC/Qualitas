package org.example.Menu;

import org.example.DAO.AlumnoDAO;
import org.example.DAO.AsignaturaDAO;
import org.example.DAO.MatriculaDAO;
import org.example.DAO.ProfesorDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Entity.Profesor;

import java.util.Scanner;

public class MenuProfesor {
    private final String usuarioLogeado;
    private static final ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private static final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private static final MatriculaDAO matriculaDAO = new MatriculaDAO();
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

            switch (opcion) {
                case "1" -> verAsignaturasProfesorProfesor();
                case "2" -> añadirNota();
                case "0" -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (!opcion.equals("0"));
    }

    public void verAsignaturasProfesorProfesor() {
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(usuarioLogeado);
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

    public void añadirNota() {
        System.out.print("Introduce el DNI del alumno: ");
        String dni = scanner.nextLine();
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(dni);
        alumno = alumnoDAO.obtenerAlumnoPorDni(alumno);

        if (alumno != null) {
            System.out.print("Introduce el ID de la asignatura: ");
            int asignaturaId = scanner.nextInt();
            Asignatura asignatura = new Asignatura();
            asignatura.setIdAsignatura(asignaturaId);
            asignatura = asignaturaDAO.obtenerAsignaturaPorId(asignatura);

            if (asignatura != null) {
                System.out.print("Introduce la nueva nota para la asignatura " + asignatura.getNombreAsignatura() + ": ");
                double nota = scanner.nextDouble();

                // Cargar la matrícula existente desde la base de datos
                Matricula matricula = matriculaDAO.obtenerMatriculaPorAlumnoYAsignatura(alumno, asignatura);
                if (matricula == null) {
                    matricula = new Matricula();
                    matricula.setAlumno(alumno);
                    matricula.setAsignatura(asignatura);
                }
                matricula.setNota(nota);

                matriculaDAO.actualizarMatricula(matricula);
                System.out.println("Nota actualizada con éxito.");
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }
}
