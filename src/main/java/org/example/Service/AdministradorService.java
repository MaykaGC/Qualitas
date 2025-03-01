package org.example.Service;

import org.example.DAO.AdministradorDAO;
import org.example.Entity.Administrador;
import org.example.Entity.Usuario;
import java.util.Date;

public class AdministradorService {
    private static final AdministradorDAO administradorDAO = new AdministradorDAO();

    public void crearAdministrador(String dni, String nombre, String correoElectronico, String contrasena, Date miDate, String direccion, String telefono) {
        Administrador administrador = new Administrador();
        administrador.setDniAdministrador(dni);
        administrador.setNombreAdministrador(nombre);
        administrador.setEmailAdministrador(correoElectronico);
        administrador.setFechaNacimientoAdministrador(miDate);
        administrador.setDireccionAdministrador(direccion);
        administrador.setTelefonoAdministrador(telefono);

        // Crear también el usuario para el administrador
        Usuario usuarioAdministrador = new Usuario(dni, contrasena, Usuario.Rol.Administrador);
        try {
            administradorDAO.crearAdministrador(administrador, usuarioAdministrador);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("El administrador ya existe en la base de datos.");
            } else
                System.out.println("No se pudo crear el administrador: " + e.getMessage());
        }
    }
}
