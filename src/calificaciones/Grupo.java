/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ssaid
 */
public class Grupo {
   
    private String nombre;
    private List<Alumno> alumnos;

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    // Métodos para consultar alumnos y calificaciones del grupo

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
}

