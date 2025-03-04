package org.example.Entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Clase que representa a un Administrador en el sistema.
 * Esta clase es una entidad JPA mapeada a la tabla "Administrador" en la base de datos.
 * Un Administrador está asociado a un Usuario a través de una relación uno a uno.
 */
@Entity
@Table(name = "Administrador")
public class Administrador {

    /**
     * DNI del administrador. Este es el identificador único de un Administrador.
     */
    @Id
    @Column(name = "DNI_Administrador")
    private String dniAdministrador;

    /**
     * Nombre del Administrador.
     */
    @Column(name = "Nombre_Administrador")
    private String nombreAdministrador;

    /**
     * Correo electrónico del Administrador.
     */
    @Column(name = "Email_Administrador")
    private String emailAdministrador;

    /**
     * Fecha de nacimiento del Administrador.
     */
    @Column(name = "FechaNacimiento_Administrador")
    private Date fechaNacimientoAdministrador;

    /**
     * Dirección del Administrador.
     */
    @Column(name = "Direccion_Administrador")
    private String direccionAdministrador;

    /**
     * Teléfono del Administrador.
     */
    @Column(name = "Telefono_Administrador")
    private String telefonoAdministrador;

    /**
     * Relación uno a uno con la entidad Usuario, donde el Administrador es asociado con un Usuario específico.
     * El campo "DNI_Administrador" actúa como clave foránea en la tabla de Usuarios.
     */
    @OneToOne
    @JoinColumn(name = "DNI_Administrador", referencedColumnName = "DNI")
    private Usuario usuario;

    /**
     * Constructor vacío de la clase Administrador.
     */
    public Administrador() {
    }

    /**
     * Obtiene el DNI del Administrador.
     *
     * @return El DNI del Administrador.
     */
    public String getDniAdministrador() {
        return dniAdministrador;
    }

    /**
     * Establece el DNI del Administrador.
     *
     * @param dniAdministrador El DNI del Administrador.
     */
    public void setDniAdministrador(String dniAdministrador) {
        this.dniAdministrador = dniAdministrador;
    }

    /**
     * Obtiene el nombre del Administrador.
     *
     * @return El nombre del Administrador.
     */
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    /**
     * Establece el nombre del Administrador.
     *
     * @param nombreAdministrador El nombre del Administrador.
     */
    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    /**
     * Obtiene el correo electrónico del Administrador.
     *
     * @return El correo electrónico del Administrador.
     */
    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    /**
     * Establece el correo electrónico del Administrador.
     *
     * @param emailAdministrador El correo electrónico del Administrador.
     */
    public void setEmailAdministrador(String emailAdministrador) {
        this.emailAdministrador = emailAdministrador;
    }

    /**
     * Obtiene la fecha de nacimiento del Administrador.
     *
     * @return La fecha de nacimiento del Administrador.
     */
    public Date getFechaNacimientoAdministrador() {
        return fechaNacimientoAdministrador;
    }

    /**
     * Establece la fecha de nacimiento del Administrador.
     *
     * @param fechaNacimientoAdministrador La fecha de nacimiento del Administrador.
     */
    public void setFechaNacimientoAdministrador(Date fechaNacimientoAdministrador) {
        this.fechaNacimientoAdministrador = fechaNacimientoAdministrador;
    }

    /**
     * Obtiene la dirección del Administrador.
     *
     * @return La dirección del Administrador.
     */
    public String getDireccionAdministrador() {
        return direccionAdministrador;
    }

    /**
     * Establece la dirección del Administrador.
     *
     * @param direccionAdministrador La dirección del Administrador.
     */
    public void setDireccionAdministrador(String direccionAdministrador) {
        this.direccionAdministrador = direccionAdministrador;
    }

    /**
     * Obtiene el teléfono del Administrador.
     *
     * @return El teléfono del Administrador.
     */
    public String getTelefonoAdministrador() {
        return telefonoAdministrador;
    }

    /**
     * Establece el teléfono del Administrador.
     *
     * @param telefonoAdministrador El teléfono del Administrador.
     */
    public void setTelefonoAdministrador(String telefonoAdministrador) {
        this.telefonoAdministrador = telefonoAdministrador;
    }

    /**
     * Obtiene el Usuario asociado al Administrador.
     *
     * @return El Usuario asociado al Administrador.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el Usuario asociado al Administrador.
     *
     * @param usuario El Usuario asociado al Administrador.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
