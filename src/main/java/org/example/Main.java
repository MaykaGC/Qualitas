package org.example;

import org.example.DAO.*;
import org.example.Entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private static final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private static final MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static final ProfesorDAO profesorDAO = new ProfesorDAO();
    private static final TutorDAO tutorDAO = new TutorDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private static String usuarioLogeado;
    private static String rolUsuario;

    public static void main(String[] args) throws ParseException {
        int opcion;
        do {
            mostrarMenuInicioSesion();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    crearCuenta();
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 3);
    }

    public static void mostrarMenuInicioSesion() {
        System.out.println("\n===== MENÚ DE INICIO DE SESIÓN =====");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Crear cuenta");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public static void iniciarSesion() {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu rol (Alumno/Profesor/Tutor): ");
        String rol = scanner.nextLine();

        if (rol.equalsIgnoreCase("Alumno")) {
            Alumno alumno = new Alumno();
            alumno.setDniAlumno(dni);
            alumno = alumnoDAO.obtenerAlumnoPorDni(alumno);
            if (alumno != null) {
                usuarioLogeado = dni;
                rolUsuario = "Alumno";
                menuAlumno();
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } else if (rol.equalsIgnoreCase("Profesor")) {
            Profesor profesor = new Profesor();
            profesor.setDniProfesor(dni);
            profesor = profesorDAO.obtenerProfesorPorDni(profesor);
            if (profesor != null) {
                usuarioLogeado = dni;
                rolUsuario = "Profesor";
                menuProfesor();
            } else {
                System.out.println("Profesor no encontrado.");
            }
        } else if (rol.equalsIgnoreCase("Tutor")) {
            Tutor tutor = new Tutor();
            tutor.setDniTutor(dni);
            tutor = tutorDAO.obtenerTutorPorDni(tutor);
            if (tutor != null) {
                usuarioLogeado = dni;
                rolUsuario = "Tutor";
                menuTutor();
            } else {
                System.out.println("Tutor no encontrado.");
            }
        } else {
            System.out.println("Rol no válido. Intente nuevamente.");
        }
    }

    public static void crearCuenta() throws ParseException {
        System.out.print("""
                -----------------------
                ==== Nuevo usuario ====
                -----------------------
                """);
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce tu correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Introduce tu fecha de nacimiento (yyyy/MM/dd): ");
        String fechaNacimiento = scanner.nextLine();
        Date miDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
        System.out.print("Introduce tu dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Introduce tu número de teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Introduce tu rol (Alumno/Profesor/Tutor): ");
        String rol = scanner.nextLine();
        System.out.print("""
                -----------------------
                =======================
                -----------------------
                """);

        if (rol.equalsIgnoreCase("Alumno")) {
            Alumno alumno = new Alumno();
            alumno.setDniAlumno(dni);
            alumno.setNombreAlumno(nombre);
            alumno.setEmailAlumno(correoElectronico);
            alumno.setFechaNacimientoAlumno(miDate);
            alumno.setDireccionAlumno(direccion);
            alumno.setTelefonoAlumno(telefono);

            // Al crear el alumno pedir el DNI del tutor

            // Crear también el usuario para el alumno
            Usuario usuarioAlumno = new Usuario(dni, contrasena, Usuario.Rol.Alumno);
            alumnoDAO.crearAlumno(alumno, usuarioAlumno);
            System.out.println("Cuenta de alumno creada con éxito.");

        } else if (rol.equalsIgnoreCase("Profesor")) {
            Profesor profesor = new Profesor();
            profesor.setDniProfesor(dni);
            profesor.setNombreProfesor(nombre);
            profesor.setEmailProfesor(correoElectronico);
            profesor.setFechaNacimientoProfesor(miDate);
            profesor.setDireccionProfesor(direccion);
            profesor.setTelefonoProfesor(telefono);

            // Crear también el usuario para el profesor
            Usuario usuarioProfesor = new Usuario(dni, contrasena, Usuario.Rol.Profesor);
            profesorDAO.crearProfesor(profesor, usuarioProfesor);
            System.out.println("Cuenta de profesor creada con éxito.");

        } else if (rol.equalsIgnoreCase("Tutor")) {
            Tutor tutor = new Tutor();
            tutor.setDniTutor(dni);
            tutor.setNombreTutor(nombre);
            tutor.setEmailTutor(correoElectronico);
            tutor.setFechaNacimientoTutor(miDate);
            tutor.setDireccionTutor(direccion);
            tutor.setTelefonoTutor(telefono);

            // Crear también el usuario para el tutor
            Usuario usuarioTutor = new Usuario(dni, contrasena, Usuario.Rol.Tutor);
            tutorDAO.crearTutor(tutor, usuarioTutor);
            System.out.println("Cuenta de tutor creada con éxito.");
        } else {
            System.out.println("Rol no válido. Intente nuevamente.");
        }
    }

    // Menú de Alumno
    public static void menuAlumno() {
        int opcion;
        do {
            System.out.println("""
                    -----------------------
                    ===== Menú alumno =====
                    -----------------------
                    1. Ver mis notas
                    0. Cerrar sesión
                    -----------------------
                    =======================
                    -----------------------
                    """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verNotasAlumno();
                    break;
                case 2:
                    usuarioLogeado = null;
                    rolUsuario = null;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void verNotasAlumno() {
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

    // Menú de Profesor
    public static void menuProfesor() {
        int opcion;
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
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verAsignaturasProfesor();
                    break;
                case 2:
                    añadirNota();
                    break;
                case 3:
                    usuarioLogeado = null;
                    rolUsuario = null;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void verAsignaturasProfesor() {
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(usuarioLogeado);
        profesor = profesorDAO.obtenerProfesorPorDni(profesor);

        if (profesor != null) {
            System.out.println("Asignaturas de " + profesor.getNombreProfesor() + ":");
            for (Asignatura asignatura : profesor.getAsignaturas()) {
                System.out.println("Asignatura: " + asignatura.getNombreAsignatura());
            }
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    public static void añadirNota() {
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

                // Actualizar o crear la matrícula
                Matricula matricula = new Matricula();
                matricula.setAlumno(alumno);
                matricula.setAsignatura(asignatura);
                matricula.setNota(nota);

                matriculaDAO.actualizarMatricula(matricula);  // O crear si no existe
                System.out.println("Nota actualizada con éxito.");
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    // Menú de Tutor (por implementar según lo que necesites)
    public static void menuTutor() {
        // Similar a los menús anteriores. Adaptar según el rol y las acciones disponibles para un Tutor.
    }
}
