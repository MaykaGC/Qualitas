package org.example.Service;

import org.example.DAO.TutorDAO;
import org.example.Entity.Tutor;
import org.example.Entity.Usuario;

import java.util.Date;

public class TutorService {
    private static final TutorDAO tutorDAO = new TutorDAO();

    public void crearTutor(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Tutor tutor = new Tutor();
        tutor.setDniTutor(dni);
        tutor.setNombreTutor(nombre);
        tutor.setEmailTutor(correoElectronico);
        tutor.setFechaNacimientoTutor(miDate);
        tutor.setDireccionTutor(direccion);
        tutor.setTelefonoTutor(telefono);

        // Crear también el usuario para el tutor
        Usuario usuarioTutor = new Usuario(dni, contrasena, Usuario.Rol.Tutor);
        try {
            tutorDAO.crearTutor(tutor, usuarioTutor);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("El tutor ya existe en la base de datos.");
            } else
                System.out.println("No se pudo crear el tutor: " + e.getMessage());
        }
    }
}
