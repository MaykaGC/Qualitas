package org.example.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="NOTA")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdNota")
    private int idNota;

    @Column(name="Nota")
    private int nota;

    //Clave foránea 1
    @ManyToOne(fetch =FetchType.LAZY) //con estA líneas mejoramos el rendimiento a la hora de realizar consultas
    @JoinColumn(name="Alumno", referencedColumnName = "DNI_Alumno", nullable = false)
    private Alumno alumno;

    //Clave foránea 2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Asignatura", referencedColumnName = "IdAsignatura", nullable = false)
    private Asignatura asignatura;


    //GETTERS
    public int getIdNota() {
        return idNota;
    }

    public int getNota() {
        return nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }



    //SETTERS
    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
