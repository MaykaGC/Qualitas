package org.example.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "ASIGNATURA")
public class Asignatura {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //con GeneratedValue marcamos que el id es autoincremental
    @Column(name = "IdAsignatura")
    private int idAsignatura;


    @Column(name = "Nombre_Asignatura")
    private String nombre_Asignatura;

    @Column(name = "Curso")
    private String curso;


    //Clave foránea
    //Varias asignaturas pueden ser impartidas por un único profesor
    @ManyToOne(fetch = FetchType.LAZY)
    //con nullable nos referimos a que no se permite que no se permite null en la columna profesor
    @JoinColumn(name = "Profesor", referencedColumnName = "DNI_Profesor", nullable = false)
    private Profesor profesor;


    //GETTERS
    public int getIdAsignatura() {
        return idAsignatura;
    }

    public String getNombre_Asignatura() {
        return nombre_Asignatura;
    }

    public String getCurso() {
        return curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }


    //SETTERS
    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public void setNombre_Asignatura(String nombre_Asignatura) {
        this.nombre_Asignatura = nombre_Asignatura;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
