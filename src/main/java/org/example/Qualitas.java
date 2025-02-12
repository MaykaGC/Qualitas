package org.example;

import org.example.Entity.Alumno;

import java.util.Scanner;

public class Qualitas {
    Scanner sc = new Scanner(System.in);
    //Métod0 para el registro de usuario




    public void registrarAlumno(){
        System.out.println("Bienvenido al perfil de alumno. Por favor ingrese los siguientes datos");
        System.out.println("------------------------------------------------------------------------");
        String DNI = sc.nextLine();
        System.out.println("DNI: ");
        String nombre = sc.nextLine();
        System.out.println("Nombre: ");
        String email = sc.nextLine();
        System.out.println("Email: ");;
        String fechaNacimiento = sc.nextLine();
        System.out.println("Fecha de nacimiento: ");
        String direccion = sc.nextLine();
        System.out.println("Dirección: ");
        String telefono= sc.nextLine();
        System.out.println("Teléfono: ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("¡INFORMACIÓN AÑADIDA CON ÉXITO!");






    }


    public void registrarTuto(){
        System.out.println("Bienvenido al perfil de tutor. Por favor ingrese los siguientes datos");
        System.out.println("------------------------------------------------------------------------");
        String DNI = sc.nextLine();
        System.out.println("DNI: ");
        String nombre= sc.nextLine();
        System.out.println("Nombre: ");
        String fechaNacimiento = sc.nextLine();
        System.out.println("Fecha de nacimiento: ");
        String direccion = sc.nextLine();
        System.out.println("Dirección: ");
        String telefono= sc.nextLine();
        System.out.println("Teléfono: ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("¡INFORMACIÓN AÑADIDA CON ÉXITO!");



    }


    public void registrarProfesor(){
        System.out.println("Bienvenido al perfil de profesor. Por favor ingrese los siguientes datos");
        System.out.println("------------------------------------------------------------------------");
        String DNI = sc.nextLine();
        System.out.println("DNI: ");
        String nombre= sc.nextLine();
        String email = sc.nextLine();
        System.out.println("Email: ");;
        System.out.println("Nombre: ");
        String fechaNacimiento = sc.nextLine();
        System.out.println("Fecha de nacimiento: ");
        String telefono = sc.nextLine();
        System.out.println("Teléfono: ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("¡INFORMACIÓN AÑADIDA CON ÉXITO!");







    }
}
