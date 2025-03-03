package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAsignatura")
    private int idAsignatura;

    @Column(name = "Nombre_Asignatura")
    private String nombreAsignatura;

    @Column(name = "Curso")
    private String curso;

    @ManyToOne
    @JoinColumn(name = "Profesor", referencedColumnName = "DNI_Profesor")
    private Profesor profesor;

    public Asignatura() {
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
