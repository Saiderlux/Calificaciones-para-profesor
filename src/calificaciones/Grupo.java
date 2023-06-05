/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.util.ArrayList;
import java.util.Objects;

public class Grupo {

    private String nombre;
    private ArrayList<Alumno> alumnos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public boolean equals(Object obj) {
        // Comprobar si los objetos son el mismo en memoria
        if (this == obj) {
            return true;
        }

        // Comprobar si el objeto es nulo o de una clase diferente
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Hacer un casting del objeto a la clase Grupo
        Grupo otroGrupo = (Grupo) obj;

        // Comparar los nombres de los grupos utilizando el método equals de la clase String
        // Si los nombres son iguales, se considera que los grupos son iguales
        return Objects.equals(nombre, otroGrupo.nombre);
    }
}
