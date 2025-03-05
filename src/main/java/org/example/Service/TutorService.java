package org.example.Service;

import org.example.DAO.TutorDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;
import org.example.Utils.Logger;

import java.util.Date;
import java.util.List;

/**
 * Servicio para la gestión de tutores en el sistema.
 * Permite crear nuevos tutores y obtener la lista de alumnos asignados a un tutor.
 */
public class TutorService {
    private static final TutorDAO tutorDAO = new TutorDAO();

    /**
     * Crea un nuevo tutor en el sistema.
     *
     * @param dni               DNI del tutor.
     * @param nombre            Nombre del tutor.
     * @param correoElectronico Correo electrónico del tutor.
     * @param contrasena        Contraseña del tutor.
     * @param miDate            Fecha de nacimiento del tutor.
     * @param direccion         Dirección del tutor.
     * @param telefono          Teléfono del tutor.
     */
    public void crearTutor(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Tutor tutor = new Tutor();
        tutor.setDniTutor(dni);
        tutor.setNombreTutor(nombre);
        tutor.setEmailTutor(correoElectronico);
        tutor.setFechaNacimientoTutor(miDate);
        tutor.setDireccionTutor(direccion);
        tutor.setTelefonoTutor(telefono);

        Usuario usuarioTutor = new Usuario(dni, contrasena, Usuario.Rol.Tutor);
        try {
            tutorDAO.crearTutor(tutor, usuarioTutor);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("⚠️ El tutor ya existe en la base de datos.");
                Logger.logWarning("Crear tutor: El tutor ya existe en la base de datos.");
            } else {
                System.out.println("❌ No se pudo crear el tutor: " + e.getMessage());
                Logger.logError("Crear tutor: Error al crear el tutor -> " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene y muestra la lista de alumnos asignados a un tutor específico.
     *
     * @param dniTutor DNI del tutor cuyos alumnos se desean obtener.
     */
    public void obtenerAlumnosTutor(String dniTutor) {
        List<Alumno> alumnos = tutorDAO.obtenerAlumnosTutor(dniTutor);
        if (alumnos.isEmpty()) {
            System.out.println("No tienes alumnos asignados.");
        } else {
            System.out.println("Alumnos asignados a " + dniTutor + ":");
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }
}
