package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Representa a un tutor en el sistema.
 * Esta clase contiene la información personal del tutor, así como su relación con los usuarios y los alumnos que supervisa.
 */
@Entity
@Table(name = "Tutor")
public class Tutor {

    /**
     * DNI del tutor, que sirve como identificador único.
     */
    @Id
    @Column(name = "DNI_Tutor")
    private String dniTutor;

    /**
     * Nombre del tutor.
     */
    @Column(name = "Nombre_Tutor")
    private String nombreTutor;

    /**
     * Correo electrónico del tutor.
     */
    @Column(name = "Email_Tutor")
    private String emailTutor;

    /**
     * Fecha de nacimiento del tutor.
     */
    @Column(name = "FechaNacimiento_Tutor")
    private Date fechaNacimientoTutor;

    /**
     * Dirección del tutor.
     */
    @Column(name = "Direccion_Tutor")
    private String direccionTutor;

    /**
     * Número de teléfono del tutor.
     */
    @Column(name = "Telefono_Tutor")
    private String telefonoTutor;

    /**
     * Relación con el usuario correspondiente a este tutor.
     * Un tutor tiene asociado un usuario para el acceso al sistema.
     */
    @OneToOne
    @JoinColumn(name = "DNI_Tutor", referencedColumnName = "DNI")
    private Usuario usuario;

    /**
     * Relación con los alumnos que están bajo la tutela del tutor.
     * Un tutor puede supervisar a varios alumnos.
     */
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    /**
     * Constructor vacío de la clase {@link Tutor}.
     * Se utiliza para crear una instancia de Tutor sin inicializar ningún valor.
     */
    public Tutor() {
    }

    /**
     * Obtiene el DNI del tutor.
     *
     * @return El DNI del tutor.
     */
    public String getDniTutor() {
        return dniTutor;
    }

    /**
     * Establece el DNI del tutor.
     *
     * @param dniTutor El DNI del tutor a establecer.
     */
    public void setDniTutor(String dniTutor) {
        this.dniTutor = dniTutor;
    }

    /**
     * Obtiene el nombre del tutor.
     *
     * @return El nombre del tutor.
     */
    public String getNombreTutor() {
        return nombreTutor;
    }

    /**
     * Establece el nombre del tutor.
     *
     * @param nombreTutor El nombre del tutor a establecer.
     */
    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    /**
     * Obtiene el correo electrónico del tutor.
     *
     * @return El correo electrónico del tutor.
     */
    public String getEmailTutor() {
        return emailTutor;
    }

    /**
     * Establece el correo electrónico del tutor.
     *
     * @param emailTutor El correo electrónico del tutor a establecer.
     */
    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    /**
     * Obtiene la fecha de nacimiento del tutor.
     *
     * @return La fecha de nacimiento del tutor.
     */
    public Date getFechaNacimientoTutor() {
        return fechaNacimientoTutor;
    }

    /**
     * Establece la fecha de nacimiento del tutor.
     *
     * @param fechaNacimientoTutor La fecha de nacimiento del tutor a establecer.
     */
    public void setFechaNacimientoTutor(Date fechaNacimientoTutor) {
        this.fechaNacimientoTutor = fechaNacimientoTutor;
    }

    /**
     * Obtiene la dirección del tutor.
     *
     * @return La dirección del tutor.
     */
    public String getDireccionTutor() {
        return direccionTutor;
    }

    /**
     * Establece la dirección del tutor.
     *
     * @param direccionTutor La dirección del tutor a establecer.
     */
    public void setDireccionTutor(String direccionTutor) {
        this.direccionTutor = direccionTutor;
    }

    /**
     * Obtiene el número de teléfono del tutor.
     *
     * @return El número de teléfono del tutor.
     */
    public String getTelefonoTutor() {
        return telefonoTutor;
    }

    /**
     * Establece el número de teléfono del tutor.
     *
     * @param telefonoTutor El número de teléfono del tutor a establecer.
     */
    public void setTelefonoTutor(String telefonoTutor) {
        this.telefonoTutor = telefonoTutor;
    }

    /**
     * Obtiene el usuario asociado al tutor.
     *
     * @return El usuario asociado al tutor.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al tutor.
     *
     * @param usuario El usuario a asociar con el tutor.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la lista de alumnos supervisados por el tutor.
     *
     * @return La lista de alumnos.
     */
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * Establece la lista de alumnos supervisados por el tutor.
     *
     * @param alumnos La lista de alumnos a establecer.
     */
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
