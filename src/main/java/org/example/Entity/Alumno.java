package org.example.Entity;
import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Alumno")
public class Alumno {

    @Id
    @Column(name = "DNI_Alumno")
    private String dni_Alumno;

    @Column(name = "Nombre_Alumno")
    private String nombre_Alumno;

    @Column(name = "Email_Alumno")
    private String email_Alumno;

    @Column(name = "FechaNacimiento_Alumno")
    private Date fechaNacimiento_Alumno;

    @Column(name = "Direccion_Alumno")
    private String direccion_Alumno;

    @Column(name = "Telefono_Alumno")
    private String telefono_Alumno;

    //Clave foránea
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TutorLegal", referencedColumnName = "DNI_Tutor", nullable = false)
    private Tutor tutor;


    public Alumno(String dni_Alumno, String nombre_Alumno, String email_Alumno, Date fechaNacimiento_Alumno, String direccion_Alumno, String telefono_Alumno, Tutor tutor) {
        this.dni_Alumno = dni_Alumno;
        this.nombre_Alumno = nombre_Alumno;
        this.email_Alumno = email_Alumno;
        this.fechaNacimiento_Alumno = fechaNacimiento_Alumno;
        this.direccion_Alumno = direccion_Alumno;
        this.telefono_Alumno = telefono_Alumno;
        this.tutor = tutor;
    }

    public Alumno() {

    }

    //GETTERS
    public String getDni_Alumno() {
        return dni_Alumno;
    }

    public String getEmail_Alumno() {
        return email_Alumno;
    }

    public String getNombre_Alumno() {
        return nombre_Alumno;
    }

    public Date getFechaNacimiento_Alumno() {
        return fechaNacimiento_Alumno;
    }

    public String getTelefono_Alumno() {
        return telefono_Alumno;
    }

    public String getDireccion_Alumno() {
        return direccion_Alumno;
    }

    public Tutor getTutor() {
        return tutor;
    }

    //SETTERS
    public void setDni_Alumno(String dni_Alumno) {
        this.dni_Alumno = dni_Alumno;
    }

    public void setNombre_Alumno(String nombre_Alumno) {
        this.nombre_Alumno = nombre_Alumno;
    }

    public void setEmail_Alumno(String email_Alumno) {
        this.email_Alumno = email_Alumno;
    }

    public void setFechaNacimiento_Alumno(Date fechaNacimiento_Alumno) {
        this.fechaNacimiento_Alumno = fechaNacimiento_Alumno;
    }

    public void setDireccion_Alumno(String direccion_Alumno) {
        this.direccion_Alumno = direccion_Alumno;
    }

    public void setTelefono_Alumno(String telefono_Alumno) {
        this.telefono_Alumno = telefono_Alumno;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
