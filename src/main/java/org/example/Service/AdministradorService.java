package org.example.Service;

import org.example.DAO.AdministradorDAO;
import org.example.Entity.*;
import org.example.Utils.Logger;
import org.example.Utils.UtilsHibernate;

import java.util.Date;
import java.util.Scanner;

/**
 * Clase encargada de gestionar las operaciones relacionadas con los administradores en el sistema.
 * Esta clase incluye funcionalidades para crear administradores, asignar asignaturas a profesores,
 * matricular alumnos en asignaturas, eliminar y actualizar entidades, entre otras.
 */
public class AdministradorService {
    private static final AdministradorDAO administradorDAO = new AdministradorDAO();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Crea un nuevo administrador en el sistema.
     * Este método también crea un usuario administrador asociado.
     *
     * @param dni               El DNI del administrador.
     * @param nombre            El nombre del administrador.
     * @param correoElectronico El correo electrónico del administrador.
     * @param contrasena        La contraseña del administrador.
     * @param miDate            La fecha de nacimiento del administrador.
     * @param direccion         La dirección del administrador.
     * @param telefono          El número de teléfono del administrador.
     */
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
                System.out.println("⚠️ El administrador ya existe en la base de datos.");
                Logger.logWarning("Crear administrador: El administrador ya existe en la base de datos.");
            } else {
                System.out.println("❌ No se pudo crear el administrador: " + e.getMessage());
                Logger.logError("Crear administrador: No se pudo crear el administrador -> " + e.getMessage());
            }
        }
    }

    /**
     * Asigna una asignatura a un profesor en el sistema.
     * El método solicita el DNI del profesor y el ID de la asignatura para realizar la asignación.
     */
    public void asignarAsignaturaProfesor() {
        System.out.print("DNI del profesor: ");
        String dniProfesor = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            if (administradorDAO.asignarAsignaturaProfesor(idAsignatura, dniProfesor) != null) {
                System.out.println("✅ Asignatura asignada correctamente al profesor.");
                Logger.logInfo("Asignar asignatura al profesor: Asignatura asignada correctamente");
            } else {
                System.out.println("❌ Error al asignar la asignatura al profesor.");
                Logger.logError("Asignar asignatura al profesor: Error al asignar la asignatura al profesor");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al asignar la asignatura: " + e.getMessage());
            Logger.logError("Asignar asignatura al profesor: Error al asignar la asignatura -> " + e.getMessage());
        }
    }

    /**
     * Matricula a un alumno en una asignatura en el sistema.
     * El método solicita el DNI del alumno y el ID de la asignatura para realizar la matrícula.
     */
    public void matricularAlumnoEnAsignatura() {
        System.out.print("DNI del alumno: ");
        String dniAlumno = scanner.nextLine();
        System.out.print("ID de la asignatura: ");
        int idAsignatura = scanner.nextInt();
        scanner.nextLine();

        try {
            if (administradorDAO.matricularAlumnoEnAsignatura(dniAlumno, idAsignatura) != null) {
                System.out.println("✅ Alumno matriculado correctamente en la asignatura.");
                Logger.logInfo("Matricular alumno en asignatura: Alumno matriculado correctamente");
            } else {
                System.out.println("❌ Error al matricular al alumno.");
                Logger.logError("Matricular alumno en asignatura: Error al matricular al alumno");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al matricular al alumno: " + e.getMessage());
            Logger.logError("Matricular alumno en asignatura: Error al matricular al alumno -> " + e.getMessage());
        }
    }

    /**
     * Crea una nueva asignatura en el sistema.
     * El método también permite asignar un profesor a la asignatura.
     */
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
            System.out.println("❌ Error al crear la asignatura: " + e.getMessage());
            Logger.logError("Crear asignatura: Error al crear la asignatura -> " + e.getMessage());
        }
    }

    /**
     * Elimina una entidad del sistema (Alumno, Profesor, Tutor) basada en la clase proporcionada.
     *
     * @param <T> La clase de la entidad que se desea eliminar.
     * @param entidad La clase de la entidad que se eliminará.
     */
    public <T> void eliminarEntidad(Class<T> entidad) {
        System.out.println("Introduce el DNI del usuario a eliminar:");
        String dni = scanner.nextLine();
        UtilsHibernate.eliminarPorId(entidad, dni);
        System.out.println("✅ Usuario eliminado correctamente.");
        Logger.logInfo("Eliminar entidad: Usuario eliminado correctamente");
    }

    /**
     * Actualiza los datos de una entidad en el sistema.
     * El método solicita el DNI del usuario y los nuevos datos para actualizar la entidad.
     *
     * @param <T>          La clase de la entidad que se desea actualizar.
     * @param entidad      La clase de la entidad que se actualizará.
     * @param dni          El DNI del usuario que se desea actualizar.
     * @param datosActualizados Los nuevos datos de la entidad.
     */
    public <T> void actualizarEntidad(Class<T> entidad, String dni, T datosActualizados) {
        UtilsHibernate.actualizarPorDni(entidad, dni, datosActualizados);
        System.out.println("✅ Usuario actualizado correctamente.");
        Logger.logInfo("Actualizar entidad: Usuario actualizado correctamente");
    }

    /**
     * Elimina un usuario del sistema según su tipo (Alumno, Profesor, Tutor).
     * El usuario selecciona el tipo de usuario a eliminar.
     *
     * @param administradorService Instancia del servicio de administrador para ejecutar la eliminación.
     */
    public void eliminarUsuario(AdministradorService administradorService) {
        System.out.println("¿Qué tipo de usuario deseas eliminar? (Alumno/Profesor/Tutor)");
        String tipo = scanner.nextLine().toLowerCase();

        switch (tipo) {
            case "alumno" -> {
                administradorService.eliminarEntidad(Alumno.class);
                Logger.logInfo("Eliminar usuario: opción alumno seleccionada");
            }
            case "profesor" -> {
                administradorService.eliminarEntidad(Profesor.class);
                Logger.logInfo("Eliminar usuario: opción profesor seleccionada");
            }
            case "tutor" -> {
                administradorService.eliminarEntidad(Tutor.class);
                Logger.logInfo("Eliminar usuario: opción tutor seleccionada");
            }
            default -> {
                System.out.println("⚠️ Tipo de usuario inválido.");
                Logger.logWarning("Eliminar usuario: Tipo de usuario inválido");
            }
        }
    }

    /**
     * Actualiza los datos de un usuario según su tipo (Alumno, Profesor, Tutor).
     * El administrador selecciona el tipo de usuario y luego actualiza sus datos.
     *
     * @param administradorService Instancia del servicio de administrador para ejecutar la actualización.
     */
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
                Logger.logInfo("Actualizar usuario: opción alumno seleccionada");
            }
            case "profesor" -> {
                Profesor profesor = new Profesor();
                profesor.setDniProfesor(dni);
                System.out.println("Nuevo nombre: ");
                profesor.setNombreProfesor(scanner.nextLine());
                administradorService.actualizarEntidad(Profesor.class, dni, profesor);
                Logger.logInfo("Actualizar usuario: opción profesor seleccionada");
            }
            case "tutor" -> {
                Tutor tutor = new Tutor();
                tutor.setDniTutor(dni);
                System.out.println("Nuevo nombre: ");
                tutor.setNombreTutor(scanner.nextLine());
                administradorService.actualizarEntidad(Tutor.class, dni, tutor);
                Logger.logInfo("Actualizar usuario: opción tutor seleccionada");
            }
            default -> {
                System.out.println("⚠️Tipo de usuario inválido.");
                Logger.logWarning("Actualizar usuario: Tipo de usuario inválido");
            }
        }
    }
}
