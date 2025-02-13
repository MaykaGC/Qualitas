package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Profesor")
public class Profesor {

    @Id
    @Column(name = "DNI_Profesor")
    private String dniProfesor;

    @Column(name = "Nombre_Profesor")
    private String nombreProfesor;

    @Column(name = "Email_Profesor")
    private String emailProfesor;

    @Column(name = "FechaNacimiento_Profesor")
    private Date fechaNacimientoProfesor;

    @Column(name = "Direccion_Profesor")
    private String direccionProfesor;

    @Column(name = "Telefono_Profesor")
    private String telefonoProfesor;

    @OneToOne
    @JoinColumn(name = "DNI_Profesor", referencedColumnName = "DNI")
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private List<Asignatura> asignaturas;

    public Profesor() {
    }

    public Profesor(String dniProfesor, String nombreProfesor, String emailProfesor, Date fechaNacimientoProfesor, String direccionProfesor, String telefonoProfesor, Usuario usuario, List<Asignatura> asignaturas) {
        this.dniProfesor = dniProfesor;
        this.nombreProfesor = nombreProfesor;
        this.emailProfesor = emailProfesor;
        this.fechaNacimientoProfesor = fechaNacimientoProfesor;
        this.direccionProfesor = direccionProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.usuario = usuario;
        this.asignaturas = asignaturas;
    }

    // Getters and Setters

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Date getFechaNacimientoProfesor() {
        return fechaNacimientoProfesor;
    }

    public void setFechaNacimientoProfesor(Date fechaNacimientoProfesor) {
        this.fechaNacimientoProfesor = fechaNacimientoProfesor;
    }

    public String getDireccionProfesor() {
        return direccionProfesor;
    }

    public void setDireccionProfesor(String direccionProfesor) {
        this.direccionProfesor = direccionProfesor;
    }
}
