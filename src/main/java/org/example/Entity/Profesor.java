package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Representa a un profesor en el sistema.
 * Esta clase contiene la información personal del profesor, así como su relación con los usuarios y las asignaturas que imparte.
 */
@Entity
@Table(name = "Profesor")
public class Profesor {

    /**
     * DNI del profesor, que sirve como identificador único.
     */
    @Id
    @Column(name = "DNI_Profesor")
    private String dniProfesor;

    /**
     * Nombre del profesor.
     */
    @Column(name = "Nombre_Profesor")
    private String nombreProfesor;

    /**
     * Correo electrónico del profesor.
     */
    @Column(name = "Email_Profesor")
    private String emailProfesor;

    /**
     * Fecha de nacimiento del profesor.
     */
    @Column(name = "FechaNacimiento_Profesor")
    private Date fechaNacimientoProfesor;

    /**
     * Dirección del profesor.
     */
    @Column(name = "Direccion_Profesor")
    private String direccionProfesor;

    /**
     * Número de teléfono del profesor.
     */
    @Column(name = "Telefono_Profesor")
    private String telefonoProfesor;

    /**
     * Relación con el usuario correspondiente a este profesor.
     * Un profesor tiene asociado un usuario para el acceso al sistema.
     */
    @OneToOne
    @JoinColumn(name = "DNI_Profesor", referencedColumnName = "DNI")
    private Usuario usuario;

    /**
     * Relación con las asignaturas que imparte el profesor.
     * Un profesor puede enseñar varias asignaturas.
     */
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private List<Asignatura> asignaturas;

    /**
     * Constructor vacío de la clase {@link Profesor}.
     * Se utiliza para crear una instancia de Profesor sin inicializar ningún valor.
     */
    public Profesor() {
    }

    /**
     * Obtiene el DNI del profesor.
     *
     * @return El DNI del profesor.
     */
    public String getDniProfesor() {
        return dniProfesor;
    }

    /**
     * Establece el DNI del profesor.
     *
     * @param dniProfesor El DNI del profesor a establecer.
     */
    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    /**
     * Obtiene el nombre del profesor.
     *
     * @return El nombre del profesor.
     */
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    /**
     * Establece el nombre del profesor.
     *
     * @param nombreProfesor El nombre del profesor a establecer.
     */
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    /**
     * Obtiene el correo electrónico del profesor.
     *
     * @return El correo electrónico del profesor.
     */
    public String getEmailProfesor() {
        return emailProfesor;
    }

    /**
     * Establece el correo electrónico del profesor.
     *
     * @param emailProfesor El correo electrónico del profesor a establecer.
     */
    public void setEmailProfesor(String emailProfesor) {
        this.emailProfesor = emailProfesor;
    }

    /**
     * Obtiene la fecha de nacimiento del profesor.
     *
     * @return La fecha de nacimiento del profesor.
     */
    public Date getFechaNacimientoProfesor() {
        return fechaNacimientoProfesor;
    }

    /**
     * Establece la fecha de nacimiento del profesor.
     *
     * @param fechaNacimientoProfesor La fecha de nacimiento del profesor a establecer.
     */
    public void setFechaNacimientoProfesor(Date fechaNacimientoProfesor) {
        this.fechaNacimientoProfesor = fechaNacimientoProfesor;
    }

    /**
     * Obtiene la dirección del profesor.
     *
     * @return La dirección del profesor.
     */
    public String getDireccionProfesor() {
        return direccionProfesor;
    }

    /**
     * Establece la dirección del profesor.
     *
     * @param direccionProfesor La dirección del profesor a establecer.
     */
    public void setDireccionProfesor(String direccionProfesor) {
        this.direccionProfesor = direccionProfesor;
    }

    /**
     * Obtiene el número de teléfono del profesor.
     *
     * @return El número de teléfono del profesor.
     */
    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    /**
     * Establece el número de teléfono del profesor.
     *
     * @param telefonoProfesor El número de teléfono del profesor a establecer.
     */
    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    /**
     * Obtiene el usuario asociado al profesor.
     *
     * @return El usuario asociado al profesor.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al profesor.
     *
     * @param usuario El usuario a asociar con el profesor.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la lista de asignaturas que imparte el profesor.
     *
     * @return La lista de asignaturas.
     */
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * Establece la lista de asignaturas que imparte el profesor.
     *
     * @param asignaturas La lista de asignaturas a establecer.
     */
    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
