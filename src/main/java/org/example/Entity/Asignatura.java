package org.example.Entity;

import jakarta.persistence.*;

/**
 * Representa una asignatura en el sistema.
 * Contiene la información relevante sobre una asignatura como su nombre,
 * el curso en el que se imparte y el profesor que la enseña.
 */
@Entity
@Table(name = "Asignatura")
public class Asignatura {

    /**
     * Identificador único de la asignatura.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAsignatura")
    private int idAsignatura;

    /**
     * Nombre de la asignatura.
     */
    @Column(name = "Nombre_Asignatura")
    private String nombreAsignatura;

    /**
     * Curso al que pertenece la asignatura.
     */
    @Column(name = "Curso")
    private String curso;

    /**
     * Profesor encargado de impartir la asignatura.
     */
    @ManyToOne
    @JoinColumn(name = "Profesor", referencedColumnName = "DNI_Profesor")
    private Profesor profesor;

    /**
     * Constructor vacío de la clase {@link Asignatura}.
     * Se utiliza para crear una instancia de Asignatura sin inicializar ningún valor.
     */
    public Asignatura() {
    }

    /**
     * Obtiene el identificador de la asignatura.
     *
     * @return El identificador de la asignatura.
     */
    public int getIdAsignatura() {
        return idAsignatura;
    }

    /**
     * Establece el identificador de la asignatura.
     *
     * @param idAsignatura El identificador de la asignatura a establecer.
     */
    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return El nombre de la asignatura.
     */
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    /**
     * Establece el nombre de la asignatura.
     *
     * @param nombreAsignatura El nombre de la asignatura a establecer.
     */
    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    /**
     * Obtiene el curso de la asignatura.
     *
     * @return El curso de la asignatura.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Establece el curso de la asignatura.
     *
     * @param curso El curso de la asignatura a establecer.
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Obtiene el profesor que imparte la asignatura.
     *
     * @return El profesor de la asignatura.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Establece el profesor encargado de impartir la asignatura.
     *
     * @param profesor El profesor que se asignará a la asignatura.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
