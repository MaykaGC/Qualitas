package org.example.Service;

import org.example.DAO.AlumnoDAO;
import org.example.DAO.AsignaturaDAO;
import org.example.DAO.MatriculaDAO;
import org.example.DAO.ProfesorDAO;
import org.example.Entity.*;
import java.util.Date;
import java.util.Scanner;

public class ProfesorService {
    private static final ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    public ProfesorService(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void crearProfesor(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(dni);
        profesor.setNombreProfesor(nombre);
        profesor.setEmailProfesor(correoElectronico);
        profesor.setFechaNacimientoProfesor(miDate);
        profesor.setDireccionProfesor(direccion);
        profesor.setTelefonoProfesor(telefono);

        Usuario usuarioProfesor = new Usuario(dni, contrasena, Usuario.Rol.Profesor);
        try {
            profesorDAO.crearProfesor(profesor, usuarioProfesor);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("⚠️ El profesor ya existe en la base de datos.");
            } else
                System.out.println("❌ No se pudo crear el profesor: " + e.getMessage());
        }
    }

    public void verAsignaturasProfesor() {
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
            System.out.println("❌ Profesor no encontrado.");
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
                System.out.println("✅ Nota actualizada con éxito.");
            } else {
                System.out.println("❌ Asignatura no encontrada.");
            }
        } else {
            System.out.println("❌ Alumno no encontrado.");
        }
    }
}
