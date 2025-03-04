package org.example.Service;

import org.example.DAO.AlumnoDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Entity.Usuario;
import org.example.Utils.Logger;

import java.util.Date;
import java.util.Scanner;

/**
 * Clase encargada de gestionar las operaciones relacionadas con los alumnos en el sistema.
 * Esta clase incluye funcionalidades para crear alumnos, ver el horario y las notas de un alumno.
 */
public class AlumnoService {
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final String usuarioLogeado;

    /**
     * Constructor de la clase AlumnoService.
     *
     * @param usuarioLogeado El DNI del usuario actualmente logueado (alumno).
     */
    public AlumnoService(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Crea un nuevo alumno en el sistema.
     * Este método también crea un usuario alumno asociado y asocia un tutor al alumno.
     *
     * @param dni       El DNI del alumno.
     * @param nombre    El nombre del alumno.
     * @param correo    El correo electrónico del alumno.
     * @param contrasena La contraseña del alumno.
     * @param fecha     La fecha de nacimiento del alumno.
     * @param direccion La dirección del alumno.
     * @param telefono  El teléfono del alumno.
     */
    public void crearAlumno(String dni, String nombre, String correo, String contrasena, Date fecha, String direccion, String telefono) {
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(dni);
        alumno.setNombreAlumno(nombre);
        alumno.setEmailAlumno(correo);
        alumno.setFechaNacimientoAlumno(fecha);
        alumno.setDireccionAlumno(direccion);
        alumno.setTelefonoAlumno(telefono);

        Usuario usuarioAlumno = new Usuario(dni, contrasena, Usuario.Rol.Alumno);
        try {
            System.out.println("Introduce el DNI del tutor: ");
            String dniTutor = new Scanner(System.in).nextLine();
            alumnoDAO.crearAlumno(alumno, usuarioAlumno, dniTutor);
        } catch (RuntimeException e) {
            System.out.println("❌ No se pudo crear el alumno: " + e.getMessage());
            Logger.logError("Crear alumno: No se pudo crear el alumno -> " + e.getMessage());
        }
    }

    /**
     * Muestra el horario del alumno actualmente logueado.
     * Se obtienen las asignaturas en las que el alumno está matriculado y se muestran.
     */
    public void verHorarioAlumno() {
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(usuarioLogeado);
        alumno = alumnoDAO.obtenerAlumnoPorDni(alumno);

        if (alumno != null) {
            System.out.println("Horario de " + alumno.getNombreAlumno() + ":");
            for (Matricula matricula : alumno.getMatriculas()) {
                Asignatura asignatura = matricula.getAsignatura();
                System.out.println("Asignatura: " + asignatura.getNombreAsignatura() + " - Curso: " + asignatura.getCurso());
            }
        } else {
            System.out.println("⚠️ Alumno no encontrado.");
            Logger.logWarning("Ver horario del alumno: Alumno no encontrado");
        }
    }

    /**
     * Muestra las notas del alumno actualmente logueado.
     * Se obtienen las asignaturas en las que el alumno está matriculado y sus respectivas notas.
     */
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
            System.out.println("⚠️ Alumno no encontrado.");
            Logger.logWarning("Ver notas del alumno: Alumno no encontrado");
        }
    }
}
