package org.example.Service;

import org.example.DAO.*;
import org.example.Entity.Usuario;
import org.example.Menu.*;
import org.example.Utils.Logger;
import org.example.Utils.UtilsPassword;

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
                case "Alumno" -> {
                    new MenuAlumno(dni).mostrarMenu();
                    Logger.logInfo("Iniciar sesión: opción alumno seleccionada");
                }
                case "Profesor" -> {
                    new MenuProfesor(dni).mostrarMenu();
                    Logger.logInfo("Iniciar sesión: opción profesor seleccionada");
                }
                case "Tutor" -> {
                    new MenuTutor(dni).mostrarMenu();
                    Logger.logInfo("Iniciar sesión: opción tutor seleccionada");
                }
                case "Administrador" -> {
                    new MenuAdministrador().mostrarMenu();
                    Logger.logInfo("Iniciar sesión: opción administrador seleccionada");
                }
                default -> {
                    System.out.println("⚠️ Rol inválido. Intente nuevamente.");
                    Logger.logWarning("Iniciar sesión: rol inválido");
                }
            }
        } else {
            System.out.println("❌ Credenciales incorrectas o usuario inexistente. Intente nuevamente.");
            Logger.logError("Iniciar sesión: credenciales incorrectas o usuario inexistente");
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
        String contrasenaEncriptada = UtilsPassword.hashPassword(contrasena);
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
            case '1' -> {
                new AlumnoService("").crearAlumno(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                Logger.logInfo("Crear cuenta: opción alumno seleccionada");
            }
            case '2' -> {
                new ProfesorService("").crearProfesor(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                Logger.logInfo("Crear cuenta: opción profesor seleccionada");
            }
            case '3' -> {
                new TutorService().crearTutor(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                Logger.logInfo("Crear cuenta: opción tutor seleccionada");
            }
            case '4' -> {
                new AdministradorService().crearAdministrador(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                Logger.logInfo("Crear cuenta: opción administrador seleccionada");
            }
            default -> {
                System.out.println("⚠️ Rol inválido. Intente nuevamente.");
                Logger.logWarning("Crear cuenta: rol inválido");
            }
        }
    }
}
