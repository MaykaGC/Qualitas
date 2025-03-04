package org.example.Entity;

import jakarta.persistence.*;

/**
 * Representa a un usuario en el sistema.
 * La clase contiene la información básica de un usuario, como su DNI, contraseña y rol.
 * Los roles pueden ser de tipo {@link Rol}, que incluye Alumno, Tutor, Profesor y Administrador.
 */
@Entity
@Table(name = "Usuarios")
public class Usuario {

    /**
     * DNI del usuario, que sirve como identificador único.
     */
    @Id
    @Column(name = "DNI")
    private String dni;

    /**
     * Contraseña del usuario para el acceso al sistema.
     */
    @Column(name = "Password")
    private String password;

    /**
     * Rol del usuario en el sistema. Este campo usa un {@link Enum} para definir los posibles roles.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Rol")
    private Rol rol;

    /**
     * Enum que define los roles posibles de un usuario.
     * Los roles pueden ser:
     * - Alumno
     * - Tutor
     * - Profesor
     * - Administrador
     */
    public enum Rol {
        Alumno, Tutor, Profesor, Administrador;
    }

    /**
     * Constructor vacío de la clase {@link Usuario}.
     * Se utiliza para crear una instancia de Usuario sin inicializar ningún valor.
     */
    public Usuario() {
    }

    /**
     * Constructor de la clase {@link Usuario} que inicializa el DNI, la contraseña y el rol.
     *
     * @param dni      El DNI del usuario.
     * @param contrasena La contraseña del usuario.
     * @param rol      El rol del usuario.
     */
    public Usuario(String dni, String contrasena, Rol rol) {
        this.dni = dni;
        this.password = contrasena;
        this.rol = rol;
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return El DNI del usuario.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del usuario.
     *
     * @param dni El DNI del usuario a establecer.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario a establecer.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
