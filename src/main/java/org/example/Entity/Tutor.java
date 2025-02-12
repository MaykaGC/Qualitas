package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tutor")
public class Tutor {

    @Id
    @Column(name = "DNI_Tutor")
    private String dniTutor;

    @Column(name = "Nombre_Tutor")
    private String nombreTutor;

    @Column(name = "Telefono_Tutor")
    private String telefonoTutor;

    @Column(name = "Direccion_Tutor")
    private String direccionTutor;

    @OneToOne
    @JoinColumn(name = "DNI_Tutor", referencedColumnName = "DNI")
    private Usuario usuario;

    public Tutor() {
    }

    public Tutor(String dniTutor, String nombreTutor, String telefonoTutor, String direccionTutor, Usuario usuario) {
        this.dniTutor = dniTutor;
        this.nombreTutor = nombreTutor;
        this.telefonoTutor = telefonoTutor;
        this.direccionTutor = direccionTutor;
        this.usuario = usuario;
    }

    // Getters and Setters

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

    public String getTelefonoTutor() {
        return telefonoTutor;
    }

    public void setTelefonoTutor(String telefonoTutor) {
        this.telefonoTutor = telefonoTutor;
    }

    public String getDireccionTutor() {
        return direccionTutor;
    }

    public void setDireccionTutor(String direccionTutor) {
        this.direccionTutor = direccionTutor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
