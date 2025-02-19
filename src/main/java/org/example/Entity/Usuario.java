package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @Column(name = "DNI")
    private String dni;

    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol")
    private Rol rol;

    public enum Rol {
        Alumno, Tutor, Profesor, Administrador
    }

    public Usuario() {}

    public Usuario(String dni, String password, Rol rol) {
        this.dni = dni;
        this.password = password;
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
