package org.example;

import com.google.protobuf.TextFormat;
import org.example.DAO.*;
import org.example.Entity.*;

import java.text.DateFormat;
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
        //Menú principal con opciones
        do {
            System.out.println("\n===== MENÚ DE INICIO DE SESIÓN =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    crearCuenta();
                    break;
                case 3:
                    System.out.println("...Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija entre las 3 opciones disponibles.");
            }

        } while (opcion != 3);
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

        System.out.println("-----------------------------------------------------");
        System.out.print("Introduce los datos requeridos para crear la cuenta:  \n");
        System.out.println("-----------------------------------------------------");
        //Datos requeridos para crear cuenta equivalentes a los atributos de la BD

        //DNI
        System.out.println("1. Dni: ");
        String dni = scanner.nextLine();

        //Nombre
        System.out.print("2. Nombre: ");
        String nombre = scanner.nextLine();

        //Email
        System.out.println("3. Email: ");
        String email = scanner.nextLine();

        //Dirección
        System.out.println("4. Dirección: ");
        String direccion = scanner.nextLine();

        //Fecha de nacimiento
        System.out.println("5. Fecha de nacimiento (dd/MM/yyyy): ");
        String nacimiento = scanner.next();

        //Convertimos la fecha de String a Date que es el tipo de dato de la BD
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(nacimiento);
        System.out.println("La fecha ha sido convertida al formato establecido.");

        //Teléfono
        System.out.println("6. Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("------------------------------------------------------------");
        String rol = scanner.nextLine();
        System.out.print("A continuación, introduce tu rol (Alumno, profesor o tutor): ");

        //Realizamos un if para comprobar el rol y, así, poder asignarle el rol pertinente
        //al usuario que ha ingresado los datos
        if (rol.equalsIgnoreCase("Alumno")) {
            Alumno alumno = new Alumno();
            alumno.setDniAlumno(dni);
            alumno.setNombreAlumno(nombre);
            alumnoDAO.crearAlumno(alumno);
            // Crear también el usuario para el alumno
            Usuario usuarioAlumno = new Usuario(dni, "password123", Usuario.Rol.Alumno);
            usuarioDAO.crearUsuario(usuarioAlumno);
            System.out.println("Cuenta de alumno creada con éxito.");

        } else if (rol.equalsIgnoreCase("Profesor")) {
            //Creamos objeto profesor
            Profesor profesor = new Profesor();
            //1
            profesor.setDniProfesor(dni);
            //2
            profesor.setNombreProfesor(nombre);
            //3
            profesor.setEmailProfesor(email);
            //4
            profesor.setDireccionProfesor(direccion);
            //5
            profesor.setFechaNacimientoProfesor(date);
            //6
            profesor.setTelefonoProfesor(telefono);
            // Crear también el usuario para el profesor
            Usuario usuarioProfesor = new Usuario(dni, "password123", Usuario.Rol.Profesor);
            profesorDAO.crearProfesor(profesor, usuarioProfesor);
            System.out.println("Cuenta de profesor creada con éxito.");


        } else if (rol.equalsIgnoreCase("Tutor")) {
            Tutor tutor = new Tutor();
            tutor.setDniTutor(dni);
            tutor.setNombreTutor(nombre);
            // Crear también el usuario para el tutor
            Usuario usuarioTutor = new Usuario(dni, "password123", Usuario.Rol.Tutor);
            usuarioDAO.crearUsuario(usuarioTutor);
            System.out.println("Cuenta de tutor creada con éxito.");
        } else {
            System.out.println("Rol no válido. Intente nuevamente.");
        }
    }

    // Menú de Alumno
    public static void menuAlumno() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ ALUMNO =====");
            System.out.println("1. Ver mis notas");
            System.out.println("2. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

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
        } while (opcion != 2);
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
            System.out.println("\n===== MENÚ PROFESOR =====");
            System.out.println("1. Ver mis asignaturas");
            System.out.println("2. Añadir una nota");
            System.out.println("3. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

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
        } while (opcion != 3);
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
