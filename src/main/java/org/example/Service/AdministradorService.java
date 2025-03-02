package org.example.Service;

import org.example.DAO.AdministradorDAO;
import org.example.Entity.*;
import org.example.Utils.UtilsHibernate;

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

    public <T> void eliminarEntidad(Class<T> entidad) {
        System.out.println("Introduce el DNI del usuario a eliminar:");
        String dni = scanner.nextLine();
        UtilsHibernate.eliminarPorId(entidad, dni);
        System.out.println("✅ Usuario eliminado correctamente.");
    }

    public <T> void actualizarEntidad(Class<T> entidad, String dni, T datosActualizados) {
        UtilsHibernate.actualizarPorDni(entidad,dni,datosActualizados);
        System.out.println("✅ Usuario actualizado correctamente.");
    }

    public void eliminarUsuario(AdministradorService administradorService) {
        System.out.println("¿Qué tipo de usuario deseas eliminar? (Alumno/Profesor/Tutor)");
        String tipo = scanner.nextLine().toLowerCase();

        switch (tipo) {
            case "alumno" -> administradorService.eliminarEntidad(Alumno.class);
            case "profesor" -> administradorService.eliminarEntidad(Profesor.class);
            case "tutor" -> administradorService.eliminarEntidad(Tutor.class);
            default -> System.out.println("Tipo de usuario no válido.");
        }
    }

    public void actualizarUsuario(AdministradorService administradorService) {
        System.out.println("¿Qué tipo de usuario deseas actualizar? (Alumno/Profesor/Tutor)");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.println("Introduce el DNI del usuario a actualizar:");
        String dni = scanner.nextLine();

        switch (tipo) {
            case "alumno" -> {
                Alumno alumno = new Alumno();
                alumno.setDniAlumno(dni);
                System.out.println("Nuevo nombre: ");
                alumno.setNombreAlumno(scanner.nextLine());
                administradorService.actualizarEntidad(Alumno.class, dni, alumno);
            }
            case "profesor" -> {
                Profesor profesor = new Profesor();
                profesor.setDniProfesor(dni);
                System.out.println("Nuevo nombre: ");
                profesor.setNombreProfesor(scanner.nextLine());
                administradorService.actualizarEntidad(Profesor.class, dni, profesor);
            }
            case "tutor" -> {
                Tutor tutor = new Tutor();
                tutor.setDniTutor(dni);
                System.out.println("Nuevo nombre: ");
                tutor.setNombreTutor(scanner.nextLine());
                administradorService.actualizarEntidad(Tutor.class, dni, tutor);
            }
            default -> System.out.println("Tipo de usuario no válido.");
        }
    }
}