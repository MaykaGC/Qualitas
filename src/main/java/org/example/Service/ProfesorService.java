package org.example.Service;

import org.example.DAO.ProfesorDAO;
import org.example.Entity.Profesor;
import org.example.Entity.Usuario;
import java.util.Date;

public class ProfesorService {
    private static final ProfesorDAO profesorDAO = new ProfesorDAO();

    public void crearProfesor(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Profesor profesor = new Profesor();
        profesor.setDniProfesor(dni);
        profesor.setNombreProfesor(nombre);
        profesor.setEmailProfesor(correoElectronico);
        profesor.setFechaNacimientoProfesor(miDate);
        profesor.setDireccionProfesor(direccion);
        profesor.setTelefonoProfesor(telefono);

        // Crear también el usuario para el profesor
        Usuario usuarioProfesor = new Usuario(dni, contrasena, Usuario.Rol.Profesor);
        try {
            profesorDAO.crearProfesor(profesor, usuarioProfesor);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("El profesor ya existe en la base de datos.");
            } else
                System.out.println("No se pudo crear el profesor: " + e.getMessage());
        }
    }
}
