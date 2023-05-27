/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        }
    }

    public void consultarAlumnosPorParcial(int numeroParcial) {
        System.out.println("Lista de alumnos con calificación en el Parcial " + numeroParcial + ":");
        for (Alumno alumno : listaAlumnos) {
            double calificacion = 0;
            switch (numeroParcial) {
                case 1:
                    calificacion = alumno.getCalificacionParcial1();
                    break;
                case 2:
                    calificacion = alumno.getCalificacionParcial2();
                    break;
                case 3:
                    calificacion = alumno.getCalificacionParcial3();
                    break;
                default:
                    System.out.println("Número de parcial inválido.");
                    return;
            }
            System.out.println("Número de lista: " + alumno.getNumeroLista() + ", Calificación: " + calificacion);
        }
    }

    public void consultarAlumnosConCalificaciones() {
        System.out.println("Lista de alumnos con todas las calificaciones:");
        for (Alumno alumno : listaAlumnos) {
            System.out.println("Número de lista: " + alumno.getNumeroLista());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Calificación Parcial 1: " + alumno.getCalificacionParcial1());
            System.out.println("Calificación Parcial 2: " + alumno.getCalificacionParcial2());
            System.out.println("Calificación Parcial 3: " + alumno.getCalificacionParcial3());
            System.out.println("Promedio Final: " + alumno.calcularPromedioFinal());
            System.out.println();
        }
    }

    public void consultarAlumnosAprobados() {
        System.out.println("Lista de alumnos aprobados:");
        for (Alumno alumno : listaAlumnos) {
            double promedioFinal = alumno.calcularPromedioFinal();
            if (promedioFinal >= 6) {
                System.out.println("Número de lista: " + alumno.getNumeroLista());
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Promedio Final: " + promedioFinal);
                System.out.println();
            }
        }
    }

    public void consultarAlumnosReprobados() {
        System.out.println("Lista de alumnos reprobados:");
        for (Alumno alumno : listaAlumnos) {
            double promedioFinal = alumno.calcularPromedioFinal();
            if (promedioFinal < 6) {
                System.out.println("Número de lista: " + alumno.getNumeroLista());
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Promedio Final: " + promedioFinal);
                System.out.println();
            }
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

    public void guardarDatos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Alumno alumno : listaAlumnos) {
                writer.println(alumno.toCSVString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del grupo.");
        }
    }

    public void cargarDatos() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                int numeroLista = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double calificacionParcial1 = Double.parseDouble(datos[2]);
                double calificacionParcial2 = Double.parseDouble(datos[3]);
                double calificacionParcial3 = Double.parseDouble(datos[4]);

                Alumno alumno = new Alumno(numeroLista, nombre);
                alumno.setCalificacionParcial1(calificacionParcial1);
                alumno.setCalificacionParcial2(calificacionParcial2);
                alumno.setCalificacionParcial3(calificacionParcial3);

                listaAlumnos.add(alumno);
            }
        }    }
}
