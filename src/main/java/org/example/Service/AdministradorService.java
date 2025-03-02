package org.example.Service;

import org.example.DAO.AdministradorDAO;
import org.example.Entity.Administrador;
import org.example.Entity.Asignatura;
import org.example.Entity.Usuario;
import java.util.Date;
import java.util.Scanner;

public class AdministradorService {
    private static final AdministradorDAO administradorDAO = new AdministradorDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public void crearAdministrador(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Administrador administrador = new Administrador();
        administrador.setDniAdministrador(dni);
        administrador.setNombreAdministrador(nombre);
        administrador.setEmailAdministrador(correoElectronico);
        administrador.setFechaNacimientoAdministrador(miDate);
        administrador.setDireccionAdministrador(direccion);
        administrador.setTelefonoAdministrador(telefono);

        Usuario usuarioAdministrador = new Usuario(dni, contrasena, Usuario.Rol.Administrador);
        try {
            administradorDAO.crearAdministrador(administrador, usuarioAdministrador);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("El administrador ya existe en la base de datos.");
            } else
                System.out.println("No se pudo crear el administrador: " + e.getMessage());
        }
    }

    public void asignarAsignaturaProfesor() {
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

    public void matricularAlumnoEnAsignatura() {
        System.out.print("DNI del alumno: ");
        String dniAlumno = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            if (administradorDAO.matricularAlumnoEnAsignatura(dniAlumno, idAsignatura) != null) {
                System.out.println("Alumno matriculado correctamente en la asignatura.");
            } else {
                System.out.println("Error al matricular al alumno.");
            }
        } catch (Exception e) {
            System.out.println("Error al matricular al alumno: " + e.getMessage());
        }
    }

    public void crearAsignatura() {
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
