package org.example.Service;

import org.example.DAO.AlumnoDAO;
import org.example.Entity.Alumno;
import org.example.Entity.Usuario;
import java.util.Date;

public class AlumnoService {
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();

    public void crearAlumno(String dni, String nombre, String correo, String contrasena, Date fecha, String direccion, String telefono) {
        Alumno alumno = new Alumno();
        alumno.setDniAlumno(dni);
        alumno.setNombreAlumno(nombre);
        alumno.setEmailAlumno(correo);
        alumno.setFechaNacimientoAlumno(fecha);
        alumno.setDireccionAlumno(direccion);
        alumno.setTelefonoAlumno(telefono);

        Usuario usuarioAlumno = new Usuario(dni, contrasena, Usuario.Rol.Alumno);
        try {
            alumnoDAO.crearAlumno(alumno, usuarioAlumno, dni);
        } catch (RuntimeException e) {
            System.out.println("No se pudo crear el alumno: " + e.getMessage());
        }
    }
}
