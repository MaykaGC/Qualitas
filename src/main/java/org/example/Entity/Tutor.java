package org.example.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TUTOR")
public class Tutor {

    @Id
    @Column(name = "TUTOR")
    private String dni_Tutor;

    @Column(name = "Nombre_Tutor")
    private String nombre_Tutor;

    @Column(name = "FechaNacimiento_Tutor")
    private Date fechaNacimiento_Tutor;

    @Column(name = "Telefono_Tutor")
    private String telefono_Tutor;

    @Column(name = "Direccion_Tutor")
    private String direccion_Tutor;


    //GETTERS
    public String getDni_Tutor() {
        return dni_Tutor;
    }

    public String getNombre_Tutor() {
        return nombre_Tutor;
    }

    public Date getFechaNacimiento_Tutor() {
        return fechaNacimiento_Tutor;
    }

    public String getTelefono_Tutor() {
        return telefono_Tutor;
    }

    public String getDireccion_Tutor() {
        return direccion_Tutor;
    }


    //SETTERS
    public void setDni_Tutor(String dni_Tutor) {
        this.dni_Tutor = dni_Tutor;
    }

    public void setNombre_Tutor(String nombre_Tutor) {
        this.nombre_Tutor = nombre_Tutor;
    }

    public void setFechaNacimiento_Tutor(Date fechaNacimiento_Tutor) {
        this.fechaNacimiento_Tutor = fechaNacimiento_Tutor;
    }

    public void setTelefono_Tutor(String telefono_Tutor) {
        this.telefono_Tutor = telefono_Tutor;
    }

    public void setDireccion_Tutor(String direccion_Tutor) {
        this.direccion_Tutor = direccion_Tutor;
    }
}
