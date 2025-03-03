package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Administrador")
public class Administrador {

    @Id
    @Column(name = "DNI_Administrador")
    private String dniAdministrador;

    @Column(name = "Nombre_Administrador")
    private String nombreAdministrador;

    @Column(name = "Email_Administrador")
    private String emailAdministrador;

    @Column(name = "FechaNacimiento_Administrador")
    private Date fechaNacimientoAdministrador;

    @Column(name = "Direccion_Administrador")
    private String direccionAdministrador;

    @Column(name = "Telefono_Administrador")
    private String telefonoAdministrador;

    @OneToOne
    @JoinColumn(name = "DNI_Administrador", referencedColumnName = "DNI")
    private Usuario usuario;

    public Administrador() {
    }

    public String getDniAdministrador() {
        return dniAdministrador;
    }

    public void setDniAdministrador(String dniAdministrador) {
        this.dniAdministrador = dniAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    public void setEmailAdministrador(String emailAdministrador) {
        this.emailAdministrador = emailAdministrador;
    }

    public Date getFechaNacimientoAdministrador() {
        return fechaNacimientoAdministrador;
    }

    public void setFechaNacimientoAdministrador(Date fechaNacimientoAdministrador) {
        this.fechaNacimientoAdministrador = fechaNacimientoAdministrador;
    }

    public String getDireccionAdministrador() {
        return direccionAdministrador;
    }

    public void setDireccionAdministrador(String direccionAdministrador) {
        this.direccionAdministrador = direccionAdministrador;
    }

    public String getTelefonoAdministrador() {
        return telefonoAdministrador;
    }

    public void setTelefonoAdministrador(String telefonoAdministrador) {
        this.telefonoAdministrador = telefonoAdministrador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}