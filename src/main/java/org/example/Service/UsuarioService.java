package org.example.Service;

import org.example.DAO.*;
import org.example.Entity.Usuario;
import org.example.Menu.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UsuarioService {
    private final Scanner scanner = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void iniciarSesion() {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = usuarioDAO.verificarCredenciales(dni, password);
        if (usuario != null) {
            String rolUsuario = usuario.getRol().toString();
            switch (rolUsuario) {
                case "Alumno" -> new MenuAlumno(dni).mostrarMenu();
                case "Profesor" -> new MenuProfesor(dni).mostrarMenu();
                case "Tutor" -> new MenuTutor(dni).mostrarMenu();
                case "Administrador" -> new MenuAdministrador(dni).mostrarMenu();
                default -> System.out.println("Rol inválido. Intente nuevamente.");
            }
        } else {
            System.out.println("Credenciales incorrectas o usuario inexistente. Intente nuevamente.");
        }
    }

    public void crearCuenta() throws ParseException {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine().trim();
        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Introduce tu correo electrónico: ");
        String correoElectronico = scanner.nextLine().trim();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine().trim();
        System.out.print("Introduce tu fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine().trim();
        Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
        System.out.print("Introduce tu dirección: ");
        String direccion = scanner.nextLine().trim();
        System.out.print("Introduce tu número de teléfono: ");
        String telefono = scanner.nextLine().trim();
        System.out.print("Introduce el código de vinculación: ");
        String codVinculacion = scanner.nextLine().toLowerCase().trim();

        char rol = codVinculacion.charAt(0);
        switch (rol) {
            case '1' -> new AlumnoService().crearAlumno(dni, nombre, correoElectronico, contrasena, fecha, direccion, telefono);
            case '2' -> new ProfesorService().crearProfesor(dni, nombre, correoElectronico, contrasena, fecha, direccion, telefono);
            case '3' -> new TutorService().crearTutor(dni, nombre, correoElectronico, contrasena, fecha, direccion, telefono);
            case '4' -> new AdministradorService().crearAdministrador(dni, nombre, correoElectronico, contrasena, fecha, direccion, telefono);
            default -> System.out.println("Rol no válido. Intente nuevamente.");
        }
    }
}
