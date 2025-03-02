package org.example.Service;

import org.example.DAO.AlumnoDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Asignatura;
import org.example.Entity.Matricula;
import org.example.Entity.Usuario;
import java.util.Date;
import java.util.Scanner;

public class AlumnoService {
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final String usuarioLogeado;

    public AlumnoService(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

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
            System.out.println("No se pudo crear el alumno: " + e.getMessage());
        }
    }

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