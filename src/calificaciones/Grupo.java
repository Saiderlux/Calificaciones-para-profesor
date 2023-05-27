/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;
// Clase Grupo que representa un grupo de estudiantes

import java.util.ArrayList;
import java.util.List;

class Grupo {

    String nombreGrupo;
    private List<Alumno> alumnos;

    public Grupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
        this.alumnos = new ArrayList<>();
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void eliminarAlumno(int numeroLista) {
        for (Alumno alumno : alumnos) {
            if (alumno.getNumeroLista() == numeroLista) {
                alumnos.remove(alumno);
                break;
            }
        }
    }

    public Alumno buscarAlumno(int numeroLista) {
        for (Alumno alumno : alumnos) {
            if (alumno.getNumeroLista() == numeroLista) {
                return alumno;
            }
        }
        return null;
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnos;
    }
}
