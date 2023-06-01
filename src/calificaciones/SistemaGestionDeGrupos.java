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

    private Profesor profesor;
    private List<Grupo> grupos;

    public SistemaGestionGrupos() {
        this.profesor = null;
        this.grupos = new ArrayList<>();
    }

    public void altaProfesor(String nombre, String apellido, String materia) {
        profesor = new Profesor(nombre, apellido, materia);
        // Guardar los datos del profesor en un archivo
        try {
            FileWriter writer = new FileWriter("profesor.txt");
            writer.write(profesor.getNombre() + "," + profesor.getApellido() + "," + profesor.getMateria());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del profesor.");
        }
    }

    public void bajaProfesor() {
        profesor = null;
        // Eliminar el archivo que contiene los datos del profesor
        File file = new File("profesor.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public void editarProfesor(String nombre, String apellido, String materia) {
        if (profesor != null) {
            profesor.setNombre(nombre);
            profesor.setApellido(apellido);
            profesor.setMateria(materia);
            // Actualizar los datos del profesor en el archivo
            try {
                FileWriter writer = new FileWriter("profesor.txt");
                writer.write(profesor.getNombre() + "," + profesor.getApellido() + "," + profesor.getMateria());
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al actualizar los datos del profesor.");
            }
        }
    }

    public void altaGrupo(String nombre) {
        Grupo grupo = new Grupo(nombre);
        grupos.add(grupo);
        // Crear un archivo para el grupo
        try {
            FileWriter writer = new FileWriter(nombre + ".txt");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del grupo.");
        }
    }

    public void bajaGrupo(String nombre) {
        Grupo grupo = buscarGrupo(nombre);
        if (grupo != null) {
            grupos.remove(grupo);
            // Eliminar el archivo del grupo
            File file = new File(nombre + ".txt");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void editarGrupo(String nombre, String nuevoNombre) {
        Grupo grupo = buscarGrupo(nombre);
        if (grupo != null) {
            grupo.setNombre(nuevoNombre);
            // Renombrar el archivo del grupo
            File file = new File(nombre + ".txt");
            if (file.exists()) {
                file.renameTo(new File(nuevoNombre + ".txt"));
            }
        }
    }

    public void consultarGrupo(String nombre) {
        Grupo grupo = buscarGrupo(nombre);
        if (grupo != null) {
            System.out.println("Nombre del grupo: " + grupo.getNombre());
            System.out.println("Alumnos:");
            for (Alumno alumno : grupo.getAlumnos()) {
                System.out.println("- Nombre: " + alumno.getNombre() + " " + alumno.getApellido());
                System.out.println("  Número de lista: " + alumno.getNumeroLista());
                System.out.println("  Parcial 1: " + alumno.getParcial1());
                System.out.println("  Parcial 2: " + alumno.getParcial2());
                System.out.println("  Parcial 3: " + alumno.getParcial3());
                System.out.println("  Promedio final: " + alumno.getPromedioFinal());
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void altaAlumno(String nombreGrupo, String nombre, String apellido, int numeroLista) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            Alumno alumno = new Alumno(nombre, apellido, numeroLista);
            grupo.agregarAlumno(alumno);
            // Guardar los datos del alumno en el archivo del grupo
            try {
                FileWriter writer = new FileWriter(nombreGrupo + ".txt", true);
                writer.write(alumno.getNumeroLista() + "," + alumno.getNombre() + "," + alumno.getApellido()
                        + "," + alumno.getParcial1() + "," + alumno.getParcial2() + ","
                        + alumno.getParcial3() + "," + alumno.getPromedioFinal() + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al guardar los datos del alumno.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void bajaAlumno(String nombreGrupo, int numeroLista) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            Alumno alumno = buscarAlumno(grupo, numeroLista);
            if (alumno != null) {
                grupo.eliminarAlumno(alumno);
                // Actualizar el archivo del grupo sin el alumno eliminado
                try {
                    FileWriter writer = new FileWriter(nombreGrupo + ".txt");
                    for (Alumno a : grupo.getAlumnos()) {
                        writer.write(a.getNumeroLista() + "," + a.getNombre() + "," + a.getApellido()
                                + "," + a.getParcial1() + "," + a.getParcial2() + ","
                                + a.getParcial3() + "," + a.getPromedioFinal() + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al actualizar los datos del grupo.");
                }
            } else {
                System.out.println("El alumno no existe en el grupo.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void editarAlumno(String nombreGrupo, int numeroLista, double parcial1, double parcial2, double parcial3) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            Alumno alumno = buscarAlumno(grupo, numeroLista);
            if (alumno != null) {
                alumno.setParcial1(parcial1);
                alumno.setParcial2(parcial2);
                alumno.setParcial3(parcial3);
                double promedioFinal = (parcial1 + parcial2 + parcial3) / 3;
                alumno.setPromedioFinal(promedioFinal);
                // Actualizar los datos del alumno en el archivo del grupo
                try {
                    FileWriter writer = new FileWriter(nombreGrupo + ".txt");
                    for (Alumno a : grupo.getAlumnos()) {
                        writer.write(a.getNumeroLista() + "," + a.getNombre() + "," + a.getApellido()
                                + "," + a.getParcial1() + "," + a.getParcial2() + ","
                                + a.getParcial3() + "," + a.getPromedioFinal() + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al actualizar los datos del grupo.");
                }
            } else {
                System.out.println("El alumno no existe en el grupo.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarCalificacionesParcial(String nombreGrupo, int parcial) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Calificaciones del parcial " + parcial + " del grupo " + grupo.getNombre() + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                double calificacionParcial;
                switch (parcial) {
                    case 1:
                        calificacionParcial = alumno.getParcial1();
                        break;
                    case 2:
                        calificacionParcial = alumno.getParcial2();
                        break;
                    case 3:
                        calificacionParcial = alumno.getParcial3();
                        break;
                    default:
                        calificacionParcial = 0;
                }
                System.out.println("- Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
                System.out.println("  Número de lista: " + alumno.getNumeroLista());
                System.out.println("  Calificación: " + calificacionParcial);
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarCalificacionesFinales(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Calificaciones finales del grupo " + grupo.getNombre() + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                System.out.println("- Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
                System.out.println("  Número de lista: " + alumno.getNumeroLista());
                System.out.println("  Calificación final: " + alumno.getPromedioFinal());
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarAlumnosReprobados(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Alumnos reprobados del grupo " + grupo.getNombre() + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                if (alumno.getPromedioFinal() < 6) {
                    System.out.println("- Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
                    System.out.println("  Número de lista: " + alumno.getNumeroLista());
                    System.out.println("  Calificación final: " + alumno.getPromedioFinal());
                }
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarAlumnosAprobados(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Alumnos aprobados del grupo " + grupo.getNombre() + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                if (alumno.getPromedioFinal() >= 6) {
                    System.out.println("- Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
                    System.out.println("  Número de lista: " + alumno.getNumeroLista());
                    System.out.println("  Calificación final: " + alumno.getPromedioFinal());
                }
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    private Grupo buscarGrupo(String nombre) {
        for (Grupo grupo : grupos) {
            if (grupo.getNombre().equals(nombre)) {
                return grupo;
            }
        }
        return null;
    }

    private Alumno buscarAlumno(Grupo grupo, int numeroLista) {
        for (Alumno alumno : grupo.getAlumnos()) {
            if (alumno.getNumeroLista() == numeroLista) {
                return alumno;
            }
        }
        return null;
    }
}
