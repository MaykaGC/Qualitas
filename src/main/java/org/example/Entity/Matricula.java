package org.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMatricula")
    private int idMatricula;

    // Relación con Alumno
    @ManyToOne
    @JoinColumn(name = "IdAlumno", referencedColumnName = "DNI_Alumno")
    private Alumno alumno;

    // Relación con Asignatura
    @ManyToOne
    @JoinColumn(name = "IdAsignatura", referencedColumnName = "IdAsignatura")
    private Asignatura asignatura;

    @Column(name = "Nota")
    private double nota;

    public Matricula() {}

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
