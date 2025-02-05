package org.example.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PROFESOR")
public class Profesor {

    @Id
    @Column(name = "DNI_Profesor")
    private String dni_Profesor;

    @Column(name = "Nombre_Tutor")
    private String nombre_Profesor;

    @Column(name = "Email_Tutor")
    private String email_Profesor;

    @Column(name = "FechaNacimiento_Tutor")
    private Date fechaNacimiento_Profesor;

    @Column(name = "Telefono_Tutor")
    private String telefono_Profesor;


    //GETTERS
    public String getDni_Profesor() {
        return dni_Profesor;
    }

    public String getNombre_Profesor() {
        return nombre_Profesor;
    }

    public String getEmail_Profesor() {
        return email_Profesor;
    }

    public Date getFechaNacimiento_Profesor() {
        return fechaNacimiento_Profesor;
    }

    public String getTelefono_Profesor() {
        return telefono_Profesor;
    }


    //SETTERS
    public void setDni_Profesor(String dni_Profesor) {
        this.dni_Profesor = dni_Profesor;
    }

    public void setNombre_Profesor(String nombre_Profesor) {
        this.nombre_Profesor = nombre_Profesor;
    }

    public void setFechaNacimiento_Profesor(Date fechaNacimiento_Profesor) {
        this.fechaNacimiento_Profesor = fechaNacimiento_Profesor;
    }

    public void setEmail_Profesor(String email_Profesor) {
        this.email_Profesor = email_Profesor;
    }

    public void setTelefono_Profesor(String telefono_Profesor) {
        this.telefono_Profesor = telefono_Profesor;
    }
}
