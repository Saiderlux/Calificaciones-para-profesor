/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;
// Clase para gestionar los grupos y profesores

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class SistemaGestionGrupos {
    private static final String RUTA_ARCHIVO_PROFESOR = "profesor.txt";
    private static final String RUTA_ARCHIVO_GRUPOS = "grupos.txt";

    private Profesor profesor;
    private List<Grupo> grupos;

    public SistemaGestionGrupos() {
        this.profesor = null;
        this.grupos = new ArrayList<>();
    }

    public void altaProfesor(String nombre, String apellido, String materia) {
        profesor = new Profesor(nombre, apellido, materia);
        guardarProfesor();
    }

    public void bajaProfesor() {
        profesor = null;
        eliminarArchivoProfesor();
    }

    public void altaGrupo(String nombreGrupo) {
        Grupo grupo = new Grupo(nombreGrupo);
        grupos.add(grupo);
        guardarGrupos();
    }

    public void bajaGrupo(String nombreGrupo) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombreGrupo().equals(nombreGrupo)) {
                grupos.remove(grupo);
                break;
            }
        }
        guardarGrupos();
        eliminarArchivoGrupo(nombreGrupo);
    }

    public void modificarGrupo(String nombreGrupo, String nuevoNombreGrupo) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombreGrupo().equals(nombreGrupo)) {
                grupo.nombreGrupo = nuevoNombreGrupo;
                break;
            }
        }
        guardarGrupos();
        renombrarArchivoGrupo(nombreGrupo, nuevoNombreGrupo);
    }

    public void altaAlumno(String nombreGrupo, String nombre, String apellido, int numeroLista) {
        Alumno alumno = new Alumno(nombre, apellido, numeroLista);
        Grupo grupo = obtenerGrupo(nombreGrupo);
        if (grupo != null) {
            grupo.agregarAlumno(alumno);
            guardarAlumnos(nombreGrupo, grupo);
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void bajaAlumno(String nombreGrupo, int numeroLista) {
        Grupo grupo = obtenerGrupo(nombreGrupo);
        if (grupo != null) {
            grupo.eliminarAlumno(numeroLista);
            guardarAlumnos(nombreGrupo, grupo);
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void modificarAlumno(String nombreGrupo, int numeroLista, String nombre, String apellido) {
        Grupo grupo = obtenerGrupo(nombreGrupo);
        if (grupo != null) {
            Alumno alumno = grupo.buscarAlumno(numeroLista);
            if (alumno != null) {
                alumno.nombre = nombre;
                alumno.apellido = apellido;
                guardarAlumnos(nombreGrupo, grupo);
            } else {
                System.out.println("El alumno no existe en el grupo.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarGrupo(String nombreGrupo) {
        Grupo grupo = obtenerGrupo(nombreGrupo);
        if (grupo != null) {
            List<Alumno> alumnos = grupo.obtenerAlumnos();
            if (!alumnos.isEmpty()) {
                System.out.println("Nombre del grupo: " + grupo.getNombreGrupo());
                System.out.println("Profesor: " + profesor.getNombre() + " " + profesor.getApellido());
                System.out.println("Materia: " + profesor.getMateria());
                System.out.println("Cantidad de alumnos: " + alumnos.size());
                System.out.println("----------------------------");
                System.out.println("Opciones de consulta:");
                System.out.println("1. Consultar lista con la calificación del primer parcial");
                System.out.println("2. Consultar lista con la calificación del segundo parcial");
                System.out.println("3. Consultar lista con la calificación del tercer parcial");
                System.out.println("4. Consultar lista con todas las calificaciones");
                System.out.println("5. Consultar lista de alumnos reprobados");
                System.out.println("6. Consultar lista de alumnos aprobados");
                System.out.println("----------------------------");
            } else {
                System.out.println("El grupo no tiene alumnos.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarLista(String nombreGrupo, int opcion) {
        Grupo grupo = obtenerGrupo(nombreGrupo);
        if (grupo != null) {
            List<Alumno> alumnos = grupo.obtenerAlumnos();
            if (!alumnos.isEmpty()) {
                System.out.println("Nombre del grupo: " + grupo.getNombreGrupo());
                System.out.println("Profesor: " + profesor.getNombre() + " " + profesor.getApellido());
                System.out.println("Materia: " + profesor.getMateria());
                System.out.println("----------------------------");
                switch (opcion) {
                    case 1:
                        System.out.println("Calificación del primer parcial:");
                        for (Alumno alumno : alumnos) {
                            System.out.println("Número de lista: " + alumno.getNumeroLista() + ", Calificación: " + alumno.getCalificacion1());
                        }
                        break;
                    case 2:
                        System.out.println("Calificación del segundo parcial:");
                        for (Alumno alumno : alumnos) {
                            System.out.println("Número de lista: " + alumno.getNumeroLista() + ", Calificación: " + alumno.getCalificacion2());
                        }
                        break;
                    case 3:
                        System.out.println("Calificación del tercer parcial:");
                        for (Alumno alumno : alumnos) {
                            System.out.println("Número de lista: " + alumno.getNumeroLista() + ", Calificación: " + alumno.getCalificacion3());
                        }
                        break;
                    case 4:
                        System.out.println("Todas las calificaciones:");
                        for (Alumno alumno : alumnos) {
                            System.out.println(alumno.toString());
                            System.out.println("-----------------------------");
                        }
                        break;
                    case 5:
                        System.out.println("Alumnos reprobados:");
                        for (Alumno alumno : alumnos) {
                            if (alumno.calcularPromedio() < 6) {
                                System.out.println(alumno.toString());
                                System.out.println("-----------------------------");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Alumnos aprobados:");
                        for (Alumno alumno : alumnos) {
                            if (alumno.calcularPromedio() >= 6) {
                                System.out.println(alumno.toString());
                                System.out.println("-----------------------------");
                            }
                        }
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("El grupo no tiene alumnos.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    private Grupo obtenerGrupo(String nombreGrupo) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombreGrupo().equals(nombreGrupo)) {
                return grupo;
            }
        }
        return null;
    }

    private void guardarProfesor() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_ARCHIVO_PROFESOR))) {
            writer.println(profesor.getNombre() + "," + profesor.getApellido() + "," + profesor.getMateria());
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del profesor.");
        }
    }

    private void eliminarArchivoProfesor() {
        File archivo = new File(RUTA_ARCHIVO_PROFESOR);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    private void guardarGrupos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_ARCHIVO_GRUPOS))) {
            for (Grupo grupo : grupos) {
                writer.println(grupo.getNombreGrupo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de los grupos.");
        }
    }

    private void eliminarArchivoGrupo(String nombreGrupo) {
        File archivo = new File(nombreGrupo + ".txt");
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    private void renombrarArchivoGrupo(String nombreGrupo, String nuevoNombreGrupo) {
        File archivoActual = new File(nombreGrupo + ".txt");
        File archivoNuevo = new File(nuevoNombreGrupo + ".txt");
        if (archivoActual.exists()) {
            archivoActual.renameTo(archivoNuevo);
        }
    }

    private void guardarAlumnos(String nombreGrupo, Grupo grupo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreGrupo + ".txt"))) {
            for (Alumno alumno : grupo.obtenerAlumnos()) {
                writer.println(alumno.getNumeroLista() + "," +
                        alumno.getNombre() + "," +
                        alumno.getApellido() + "," +
                        alumno.getCalificacion1() + "," +
                        alumno.getCalificacion2() + "," +
                        alumno.getCalificacion3());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de los alumnos.");
        }
    }
}