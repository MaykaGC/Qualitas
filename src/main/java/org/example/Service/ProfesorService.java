package org.example.Service;

import org.example.DAO.AlumnoDAO;
import org.example.DAO.AsignaturaDAO;
import org.example.DAO.MatriculaDAO;
import org.example.DAO.ProfesorDAO;
import org.example.Entity.*;
import org.example.Utils.Logger;

import java.util.Date;
import java.util.Scanner;

/**
 * Servicio para la gestión de profesores en el sistema.
 * Proporciona métodos para crear profesores, ver sus asignaturas y añadir notas a los alumnos.
 */
public class ProfesorService {
    private static final ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final String usuarioLogeado;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor de ProfesorService.
     *
     * @param usuarioLogeado DNI del profesor actualmente autenticado.
     */
    public ProfesorService(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Crea un nuevo profesor en el sistema.
     *
     * @param dni               DNI del profesor.
     * @param nombre            Nombre del profesor.
     * @param correoElectronico Correo electrónico del profesor.
     * @param contrasena        Contraseña del profesor.
     * @param miDate            Fecha de nacimiento del profesor.
     * @param direccion         Dirección del profesor.
     * @param telefono          Teléfono del profesor.
     */
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
                Logger.logWarning("Crear profesor: El profesor ya existe en la base de datos.");
            } else {
                System.out.println("❌ No se pudo crear el profesor: " + e.getMessage());
                Logger.logError("Crear profesor: No se pudo crear el profesor: " + e.getMessage());
            }
        }
    }

    /**
     * Muestra las asignaturas del profesor autenticado.
     */
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
            Logger.logError("Ver asignaturas profesor: Profesor no encontrado.");
        }
    }

    /**
     * Permite al profesor añadir una nota a un alumno en una asignatura específica.
     */
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
                Logger.logInfo("Añadir nota: Nota actualizada con éxito.");
            } else {
                System.out.println("❌ Asignatura no encontrada.");
                Logger.logError("Añadir nota: Asignatura no encontrada.");
            }
        } else {
            System.out.println("❌ Alumno no encontrado.");
            Logger.logError("Añadir nota: Alumno no encontrado.");
        }
    }
}
