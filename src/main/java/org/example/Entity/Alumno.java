package org.example.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa a un Alumno en el sistema.
 * Esta clase es una entidad JPA mapeada a la tabla "Alumno" en la base de datos.
 * Un Alumno tiene un tutor, un usuario asociado y una lista de matrículas.
 */
@Entity
@Table(name = "Alumno")
public class Alumno {

    /**
     * DNI del alumno. Este es el identificador único de un Alumno.
     */
    @Id
    @Column(name = "DNI_Alumno")
    private String dniAlumno;

    /**
     * Nombre del Alumno.
     */
    @Column(name = "Nombre_Alumno")
    private String nombreAlumno;

    /**
     * Correo electrónico del Alumno.
     */
    @Column(name = "Email_Alumno")
    private String emailAlumno;

    /**
     * Fecha de nacimiento del Alumno.
     */
    @Column(name = "FechaNacimiento_Alumno")
    private Date fechaNacimientoAlumno;

    /**
     * Dirección del Alumno.
     */
    @Column(name = "Direccion_Alumno")
    private String direccionAlumno;

    /**
     * Teléfono del Alumno.
     */
    @Column(name = "Telefono_Alumno")
    private String telefonoAlumno;

    /**
     * Relación muchos a uno con la entidad Tutor.
     * Un Alumno tiene un Tutor Legal, referenciado por el DNI del tutor.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TutorLegal", referencedColumnName = "DNI_Tutor")
    private Tutor tutor;

    /**
     * Relación uno a uno con la entidad Usuario.
     * Cada Alumno está asociado con un Usuario único en el sistema.
     */
    @OneToOne
    @JoinColumn(name = "DNI_Alumno", referencedColumnName = "DNI")
    private Usuario usuario;

    /**
     * Relación uno a muchos con la entidad Matrícula.
     * Un Alumno puede tener varias Matrículas asociadas.
     */
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Matricula> matriculas;

    /**
     * Constructor vacío de la clase Alumno.
     */
    public Alumno() {
    }

    /**
     * Obtiene el DNI del Alumno.
     *
     * @return El DNI del Alumno.
     */
    public String getDniAlumno() {
        return dniAlumno;
    }

    /**
     * Establece el DNI del Alumno.
     *
     * @param dniAlumno El DNI del Alumno.
     */
    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    /**
     * Obtiene el nombre del Alumno.
     *
     * @return El nombre del Alumno.
     */
    public String getNombreAlumno() {
        return nombreAlumno;
    }

    /**
     * Establece el nombre del Alumno.
     *
     * @param nombreAlumno El nombre del Alumno.
     */
    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    /**
     * Obtiene el correo electrónico del Alumno.
     *
     * @return El correo electrónico del Alumno.
     */
    public String getEmailAlumno() {
        return emailAlumno;
    }

    /**
     * Establece el correo electrónico del Alumno.
     *
     * @param emailAlumno El correo electrónico del Alumno.
     */
    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    /**
     * Obtiene la fecha de nacimiento del Alumno.
     *
     * @return La fecha de nacimiento del Alumno.
     */
    public Date getFechaNacimientoAlumno() {
        return fechaNacimientoAlumno;
    }

    /**
     * Establece la fecha de nacimiento del Alumno.
     *
     * @param fechaNacimientoAlumno La fecha de nacimiento del Alumno.
     */
    public void setFechaNacimientoAlumno(Date fechaNacimientoAlumno) {
        this.fechaNacimientoAlumno = fechaNacimientoAlumno;
    }

    /**
     * Obtiene la dirección del Alumno.
     *
     * @return La dirección del Alumno.
     */
    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    /**
     * Establece la dirección del Alumno.
     *
     * @param direccionAlumno La dirección del Alumno.
     */
    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }

    /**
     * Obtiene el teléfono del Alumno.
     *
     * @return El teléfono del Alumno.
     */
    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    /**
     * Establece el teléfono del Alumno.
     *
     * @param telefonoAlumno El teléfono del Alumno.
     */
    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    /**
     * Obtiene el Tutor Legal asociado al Alumno.
     *
     * @return El Tutor del Alumno.
     */
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * Establece el Tutor Legal del Alumno.
     *
     * @param tutor El Tutor Legal del Alumno.
     */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    /**
     * Obtiene el Usuario asociado al Alumno.
     *
     * @return El Usuario asociado al Alumno.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el Usuario asociado al Alumno.
     *
     * @param usuario El Usuario asociado al Alumno.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la lista de Matrículas asociadas al Alumno.
     *
     * @return La lista de Matrículas asociadas al Alumno.
     */
    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * Establece la lista de Matrículas asociadas al Alumno.
     *
     * @param matriculas La lista de Matrículas asociadas al Alumno.
     */
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * Devuelve una representación en forma de cadena de texto del Alumno.
     *
     * @return Una cadena con el DNI y nombre del Alumno.
     */
    @Override
    public String toString() {
        return "Alumno: " + "dni: " + getDniAlumno() +
                ", nombre: " + getNombreAlumno();
    }
}
