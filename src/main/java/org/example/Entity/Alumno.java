package org.example.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Alumno")
public class Alumno {

    @Id
    @Column(name = "DNI_Alumno")
    private String dniAlumno;

    @Column(name = "Nombre_Alumno")
    private String nombreAlumno;

    @Column(name = "Email_Alumno")
    private String emailAlumno;

    @Column(name = "FechaNacimiento_Alumno")
    private Date fechaNacimientoAlumno;

    @Column(name = "Direccion_Alumno")
    private String direccionAlumno;

    @Column(name = "Telefono_Alumno")
    private String telefonoAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TutorLegal", referencedColumnName = "DNI_Tutor")
    private Tutor tutor;

    @OneToOne
    @JoinColumn(name = "DNI_Alumno", referencedColumnName = "DNI")
    private Usuario usuario;

    // Relación con la tabla Matrícula
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Matricula> matriculas;

    public Alumno() {}

    public Alumno(String dniAlumno, String nombreAlumno, String emailAlumno, Date fechaNacimientoAlumno, String direccionAlumno, String telefonoAlumno, Tutor tutor, Usuario usuario, List<Matricula> matriculas) {
        this.dniAlumno = dniAlumno;
        this.nombreAlumno = nombreAlumno;
        this.emailAlumno = emailAlumno;
        this.fechaNacimientoAlumno = fechaNacimientoAlumno;
        this.direccionAlumno = direccionAlumno;
        this.telefonoAlumno = telefonoAlumno;
        this.tutor = tutor;
        this.usuario = usuario;
        this.matriculas = matriculas;
    }

    // Getters y Setters

    public String getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    public Date getFechaNacimientoAlumno() {
        return fechaNacimientoAlumno;
    }

    public void setFechaNacimientoAlumno(Date fechaNacimientoAlumno) {
        this.fechaNacimientoAlumno = fechaNacimientoAlumno;
    }

    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }

    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
