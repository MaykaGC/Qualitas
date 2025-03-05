package org.example.Service;

import org.example.DAO.UsuarioDAO;
import org.example.Entity.Usuario;
import org.example.Menu.*;
import org.example.Utils.Logger;
import org.example.Utils.UtilsPassword;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Servicio para la gestión de usuarios en el sistema.
 * Permite iniciar sesión y crear cuentas de usuario con diferentes roles.
 */
public class UsuarioService {
    private final Scanner scanner = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Inicia sesión en el sistema verificando las credenciales del usuario.
     * Dependiendo del rol del usuario, se mostrará el menú correspondiente.
     */
    public void iniciarSesion() {
        System.out.print("Introduce tu DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = usuarioDAO.verificarCredenciales(dni, password);
        if (usuario != null) {
            String rolUsuario = usuario.getRol().toString();
            switch (rolUsuario) {
                case "Alumno" : {
                    Logger.logInfo("Iniciar sesión: opción alumno seleccionada");
                    new MenuAlumno(dni).mostrarMenu();
                    break;
                }
                case "Profesor" : {
                    Logger.logInfo("Iniciar sesión: opción profesor seleccionada");
                    new MenuProfesor(dni).mostrarMenu();
                    break;
                }
                case "Tutor" : {
                    Logger.logInfo("Iniciar sesión: opción tutor seleccionada");
                    new MenuTutor(dni).mostrarMenu();
                    break;
                }
                case "Administrador" : {
                    Logger.logInfo("Iniciar sesión: opción administrador seleccionada");
                    new MenuAdministrador().mostrarMenu();
                    break;
                }
                default : {
                    Logger.logWarning("Iniciar sesión: rol inválido");
                    System.out.println("⚠️ Rol inválido. Intente nuevamente.");
                    break;
                }
            }
        } else {
            System.out.println("❌ Credenciales incorrectas o usuario inexistente. Intente nuevamente.");
            Logger.logError("Iniciar sesión: credenciales incorrectas o usuario inexistente");
        }
    }

    /**
     * Crea una nueva cuenta de usuario en el sistema.
     * Dependiendo del código de vinculación, se asigna un rol específico al usuario.
     *
     * @throws ParseException Si la fecha de nacimiento ingresada no tiene el formato correcto.
     */
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
            case '1' : {
                Logger.logInfo("Crear cuenta: opción alumno seleccionada");
                new AlumnoService("").crearAlumno(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                break;
            }
            case '2' : {
                Logger.logInfo("Crear cuenta: opción profesor seleccionada");
                new ProfesorService("").crearProfesor(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                break;
            }
            case '3' : {
                Logger.logInfo("Crear cuenta: opción tutor seleccionada");
                new TutorService().crearTutor(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                break;
            }
            case '4' : {
                Logger.logInfo("Crear cuenta: opción administrador seleccionada");
                new AdministradorService().crearAdministrador(dni, nombre, correoElectronico, contrasenaEncriptada, fecha, direccion, telefono);
                break;
            }
            default : {
                Logger.logWarning("Crear cuenta: rol inválido");
                System.out.println("⚠️ Rol inválido. Intente nuevamente.");
                break;
            }
        }
    }
}
