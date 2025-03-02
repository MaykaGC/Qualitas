package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tutor")
public class Tutor {

    @Id
    @Column(name = "DNI_Tutor")
    private String dniTutor;

    @Column(name = "Nombre_Tutor")
    private String nombreTutor;

    @Column(name = "Email_Tutor")
    private String emailTutor;

    @Column(name = "FechaNacimiento_Tutor")
    private Date fechaNacimientoTutor;

    @Column(name = "Direccion_Tutor")
    private String direccionTutor;

    @Column(name = "Telefono_Tutor")
    private String telefonoTutor;

    @OneToOne
    @JoinColumn(name = "DNI_Tutor", referencedColumnName = "DNI")
    private Usuario usuario;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    public Tutor() {
    }

    public Tutor(String dniTutor, String nombreTutor, String emailTutor, Date fechaNacimientoTutor, String direccionTutor, String telefonoTutor, Usuario usuario, List<Alumno> alumnos) {
        this.dniTutor = dniTutor;
        this.nombreTutor = nombreTutor;
        this.emailTutor = emailTutor;
        this.fechaNacimientoTutor = fechaNacimientoTutor;
        this.direccionTutor = direccionTutor;
        this.telefonoTutor = telefonoTutor;
        this.usuario = usuario;
        this.alumnos = alumnos;
    }

    public Tutor(String dniTutor, String nombreTutor, String emailTutor, Date fechaNacimientoTutor, String direccionTutor, String telefonoTutor, Usuario usuario) {
        this.dniTutor = dniTutor;
        this.nombreTutor = nombreTutor;
        this.emailTutor = emailTutor;
        this.fechaNacimientoTutor = fechaNacimientoTutor;
        this.direccionTutor = direccionTutor;
        this.telefonoTutor = telefonoTutor;
        this.usuario = usuario;
    }

    public String getDniTutor() {
        return dniTutor;
    }

    public void setDniTutor(String dniTutor) {
        this.dniTutor = dniTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public Date getFechaNacimientoTutor() {
        return fechaNacimientoTutor;
    }

    public void setFechaNacimientoTutor(Date fechaNacimientoTutor) {
        this.fechaNacimientoTutor = fechaNacimientoTutor;
    }

    public String getDireccionTutor() {
        return direccionTutor;
    }

    public void setDireccionTutor(String direccionTutor) {
        this.direccionTutor = direccionTutor;
    }

    public String getTelefonoTutor() {
        return telefonoTutor;
    }

    public void setTelefonoTutor(String telefonoTutor) {
        this.telefonoTutor = telefonoTutor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
