package org.example.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name= "ALUMNO")
public class Alumno {

@Id
@Column(name="DNI_Alumno")
private String DNI_Alumno;

@Column(name="Nombre_Alumno")
private String Nombre_Alumno;

@Column(name="Email_Alumno")
private String Email_Alumno;

@Column(name="FechaNacimiento_Alumno")
private Date FechaNacimiento_Alumno;

@Column(name="Direccion_Alumno")
private String Direccion_Alumno;

@Column(name="Telefono_Alumno")
private String Telefono_Alumno;

@Column(name="TutorLegal")
private String TutorLegal;


//Clave foránea
@OneToMany(fetch =FetchType.LAZY)
@JoinColumn(name="TutorLegal", referencedColumnName = "TutorLegal", nullable = false)
Tutor tutor;















}
