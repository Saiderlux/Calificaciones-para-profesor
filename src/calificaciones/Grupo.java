/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.util.ArrayList;
class Grupo {
    private String nombreArchivo;
    private ArrayList<Alumno> listaAlumnos;

    public Grupo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.listaAlumnos = new ArrayList<>();
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void agregarAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    public void eliminarAlumno(int numeroLista) {
        Alumno alumno = buscarAlumno(numeroLista);
        if (alumno != null) {
            listaAlumnos.remove(alumno);
        }
    }

    public void modificarCalificacionParcial(int numeroLista, int numeroParcial, double calificacion) {
        Alumno alumno = buscarAlumno(numeroLista);
        if (alumno != null) {
            switch (numeroParcial) {
                case 1:
                    alumno.setCalificacionParcial1(calificacion);
                    break;
                case 2:
                    alumno.setCalificacionParcial2(calificacion);
                    break;
                case 3:
                    alumno.setCalificacionParcial3(calificacion);
                    break;
                default:
                    System.out.println("Número de parcial inválido.");
                    break;
            }
        }
    }

    public void consultarAlumno(int numeroLista) {
        Alumno alumno = buscarAlumno(numeroLista);
        if (alumno != null) {
            System.out.println("Número de lista: " + alumno.getNumeroLista());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Calificación Parcial 1: " + alumno.getCalificacionParcial1());
            System.out.println("Calificación Parcial 2: " + alumno.getCalificacionParcial2());
            System.out.println("Calificación Parcial 3: " + alumno.getCalificacionParcial3());
            System.out.println("Promedio Final: " + alumno.calcularPromedioFinal());
        } else {
            System.out.println("No se encontró ningún alumno con el número de lista proporcionado.");
        }
    }

    public void consultarListaPorParcial(int numeroParcial) {
        System.out.println("Lista de alumnos con calificación del Parcial " + numeroParcial + ":");
        for (Alumno alumno : listaAlumnos) {
            double calificacionParcial = 0;
            switch (numeroParcial) {
                case 1:
                    calificacionParcial = alumno.getCalificacionParcial1();
                    break;
                case 2:
                    calificacionParcial = alumno.getCalificacionParcial2();
                    break;
                case 3:
                    calificacionParcial = alumno.getCalificacionParcial3();
                    break;
                default:
                    System.out.println("Número de parcial inválido.");
                    return;
            }
            System.out.println("Número de lista: " + alumno.getNumeroLista() + ", Nombre: " + alumno.getNombre() +
                    ", Calificación Parcial " + numeroParcial + ": " + calificacionParcial);
        }
    }

    public void consultarListaCompleta() {
        System.out.println("Lista completa de alumnos:");
        for (Alumno alumno : listaAlumnos) {
            System.out.println("Número de lista: " + alumno.getNumeroLista());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Calificación Parcial 1: " + alumno.getCalificacionParcial1());
            System.out.println("Calificación Parcial 2: " + alumno.getCalificacionParcial2());
            System.out.println("Calificación Parcial 3: " + alumno.getCalificacionParcial3());
            System.out.println("Promedio Final: " + alumno.calcularPromedioFinal());
            System.out.println("-------------------------------------");
        }
    }

    private Alumno buscarAlumno(int numeroLista) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getNumeroLista() == numeroLista) {
                return alumno;
            }
        }
        return null;
    }
}