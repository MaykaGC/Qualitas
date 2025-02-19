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
    private static final AdministradorDAO administradorDAO = new AdministradorDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private static String usuarioLogeado;
    private static String rolUsuario;

    public static void main(String[] args) throws ParseException {
        int opcion;
        do {
            //Se muestra el inicio de sesión
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
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void mostrarMenuInicioSesion() {
        System.out.println("""
                ------------------------
                === Inicio de sesión ===
                ------------------------
                1. Iniciar sesión
                2. Crear cuenta
                0. Salir
                ------------------------
                ========================
                ------------------------
                """);
    }

    public static void iniciarSesion() {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = usuarioDAO.verificarCredenciales(dni, password);
        if (usuario != null) {
            usuarioLogeado = dni;
            rolUsuario = usuario.getRol().toString();
            switch (rolUsuario) {
                case "Alumno":
                    menuAlumno();
                    break;
                case "Profesor":
                    menuProfesor();
                    break;
                case "Tutor":
                    menuTutor();
                    break;
                case "Administrador":
                    menuAdministrador();
                    break;
                default:
                    System.out.println("Rol no válido. Intente nuevamente.");
            }
        } else {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
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
        System.out.print("Introduce tu fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        Date miDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
        System.out.print("Introduce tu dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Introduce tu número de teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Introduce tu rol (Alumno/Profesor/Tutor): ");
        String rol = scanner.nextLine().toLowerCase().trim();
        System.out.print("""
                -----------------------
                =======================
                -----------------------
                """);

        switch (rol) {
            case "alumno":
                System.out.print("Introduce el DNI del tutor: ");
                String dniTutor = scanner.nextLine();

                Alumno alumno = new Alumno();
                alumno.setDniAlumno(dni);
                alumno.setNombreAlumno(nombre);
                alumno.setEmailAlumno(correoElectronico);
                alumno.setFechaNacimientoAlumno(miDate);
                alumno.setDireccionAlumno(direccion);
                alumno.setTelefonoAlumno(telefono);

                // Crear también el usuario para el alumno
                Usuario usuarioAlumno = new Usuario(dni, contrasena, Usuario.Rol.Alumno);
                try {
                    alumnoDAO.crearAlumno(alumno, usuarioAlumno, dniTutor);
                } catch (RuntimeException e) {
                    System.out.println("No se pudo crear el alumno: " + e.getMessage());
                    return;
                }
                break;

            case "profesor":
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
                break;

            case "tutor":
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
                break;
            case "administrador":
                Administrador administrador = new Administrador();
                administrador.setDniAdministrador(dni);
                administrador.setNombreAdministrador(nombre);
                administrador.setEmailAdministrador(correoElectronico);
                administrador.setFechaNacimientoAdministrador(miDate);
                administrador.setDireccionAdministrador(direccion);
                administrador.setTelefonoAdministrador(telefono);

                // Crear también el usuario para el administrador
                Usuario usuarioAdministrador = new Usuario(dni, contrasena, Usuario.Rol.Administrador);
                administradorDAO.crearAdministrador(administrador, usuarioAdministrador);
                break;

            default:
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
                    2. Ver mi horario
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
                    verHorarioAlumno();
                    break;
                case 0:
                    usuarioLogeado = null;
                    rolUsuario = null;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
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
                    verAsignaturasProfesorProfesor();
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
        System.out.println("Introduce el DNI del profesor: ");
        String dniProfesor = new Scanner(System.in).nextLine();
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(dniProfesor);
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

    public static void verAsignaturasProfesorProfesor() {
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

    public static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("""
                    ------------------------------------
                    ======== Menú Administrador ========
                    ------------------------------------
                    1. Asignar asignatura a profesor
                    2. Matricular alumno en asignatura
                    3. Ver asignaturas de profesor
                    4. Ver horario de alumno
                    5. Crear asignatura
                    0. Cerrar sesión
                    ------------------------------------
                    ====================================
                    ------------------------------------
                    """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> asignarAsignaturaProfesor();
                case 2 -> matricularAlumnoEnAsignatura();
                case 3 -> verAsignaturasProfesor();
                case 4 -> verHorarioAlumno();
                case 5 -> crearAsignatura();
                case 0 -> {
                    usuarioLogeado = null;
                    rolUsuario = null;
                    System.out.println("Sesión cerrada.");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void asignarAsignaturaProfesor() {
        System.out.print("DNI del profesor: ");
        String dniProfesor = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            administradorDAO.asignarAsignaturaProfesor(idAsignatura, dniProfesor);
            System.out.println("Asignatura asignada correctamente al profesor.");
        } catch (Exception e) {
            System.out.println("Error al asignar la asignatura: " + e.getMessage());
        }
    }

    private static void matricularAlumnoEnAsignatura() {
        System.out.print("DNI del alumno: ");
        String dniAlumno = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            administradorDAO.matricularAlumnoEnAsignatura(dniAlumno, idAsignatura);
            System.out.println("Alumno matriculado correctamente en la asignatura.");
        } catch (Exception e) {
            System.out.println("Error al matricular al alumno: " + e.getMessage());
        }
    }

    private static void crearAsignatura() {
        System.out.print("Nombre de la asignatura: ");
        String nombre = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        try {
            Asignatura asignatura = new Asignatura();
            asignatura.setNombreAsignatura(nombre);
            asignatura.setCurso(curso);

            administradorDAO.crearAsignatura(asignatura);

            // Si el Administrador desea asignar un Profesor más tarde
            System.out.print("¿Deseas asignar un profesor a esta asignatura ahora? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("DNI del Profesor: ");
                String dniProfesor = scanner.nextLine();
                administradorDAO.asignarProfesorAAsignatura(asignatura.getIdAsignatura(), dniProfesor);
                System.out.println("Profesor asignado correctamente.");
            }

            System.out.println("Asignatura creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la asignatura: " + e.getMessage());
        }
    }

    // Menú de Tutor (por implementar según lo que necesites)
    public static void menuTutor() {
        int opcion;
        do {
            System.out.println("""
                    -----------------------
                    ===== Menú tutor =====
                    -----------------------
                    1. Ver notas.
                    2. Ver horario.
                    0. Cerrar sesión.
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
                    verHorarioAlumno();
                    break;
                case 0:
                    usuarioLogeado = null;                   rolUsuario = null;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intentelo de nuevo.");
            }
        } while (opcion != 0);
    }
}
