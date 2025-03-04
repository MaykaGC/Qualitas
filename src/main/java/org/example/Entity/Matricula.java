package org.example.Entity;

import jakarta.persistence.*;

/**
 * Representa la matrícula de un alumno en una asignatura.
 * Contiene la relación entre un alumno, la asignatura en la que se matricula y la nota obtenida.
 */
@Entity
@Table(name = "Matricula")
public class Matricula {

    /**
     * Identificador único de la matrícula.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMatricula")
    private int idMatricula;

    /**
     * Relación con el alumno matriculado.
     * Un alumno puede estar matriculado en varias asignaturas.
     */
    @ManyToOne
    @JoinColumn(name = "IdAlumno", referencedColumnName = "DNI_Alumno")
    private Alumno alumno;

    /**
     * Relación con la asignatura en la que se ha matriculado el alumno.
     * Una asignatura puede tener varios alumnos matriculados.
     */
    @ManyToOne
    @JoinColumn(name = "IdAsignatura", referencedColumnName = "IdAsignatura")
    private Asignatura asignatura;

    /**
     * Nota obtenida por el alumno en la asignatura.
     */
    @Column(name = "Nota")
    private double nota;

    /**
     * Constructor vacío de la clase {@link Matricula}.
     * Se utiliza para crear una instancia de Matrícula sin inicializar ningún valor.
     */
    public Matricula() {}

    /**
     * Obtiene el identificador de la matrícula.
     *
     * @return El identificador de la matrícula.
     */
    public int getIdMatricula() {
        return idMatricula;
    }

    /**
     * Establece el identificador de la matrícula.
     *
     * @param idMatricula El identificador de la matrícula a establecer.
     */
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    /**
     * Obtiene el alumno asociado a la matrícula.
     *
     * @return El alumno asociado a la matrícula.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece el alumno asociado a la matrícula.
     *
     * @param alumno El alumno a asociar con la matrícula.
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * Obtiene la asignatura en la que está matriculado el alumno.
     *
     * @return La asignatura en la que el alumno está matriculado.
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * Establece la asignatura en la que el alumno se matricula.
     *
     * @param asignatura La asignatura a asociar con la matrícula.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Obtiene la nota obtenida por el alumno en la asignatura.
     *
     * @return La nota del alumno en la asignatura.
     */
    public double getNota() {
        return nota;
    }

    /**
     * Establece la nota obtenida por el alumno en la asignatura.
     *
     * @param nota La nota a establecer para el alumno en la asignatura.
     */
    public void setNota(double nota) {
        this.nota = nota;
    }
}
