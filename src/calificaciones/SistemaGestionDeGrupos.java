/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

// Clase para gestionar los grupos y profesores
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
            // Leer los datos de los alumnos desde el archivo del grupo
            try {
                BufferedReader reader = new BufferedReader(new FileReader(nombre + ".txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] alumnoData = line.split(",");
                    int numeroLista = Integer.parseInt(alumnoData[0]);
                    String nombreAlumno = alumnoData[1];
                    String apellidoAlumno = alumnoData[2];
                    double parcial1 = Double.parseDouble(alumnoData[3]);
                    double parcial2 = Double.parseDouble(alumnoData[4]);
                    double parcial3 = Double.parseDouble(alumnoData[5]);
                    double promedioFinal = Double.parseDouble(alumnoData[6]);
                    Alumno alumno = new Alumno(nombreAlumno, apellidoAlumno, numeroLista);
                    alumno.setParcial1(parcial1);
                    alumno.setParcial2(parcial2);
                    alumno.setParcial3(parcial3);
                    alumno.setPromedioFinal(promedioFinal);
                    grupo.agregarAlumno(alumno);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer los datos del grupo.");
            }

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
            // Verificar si el número de lista ya existe en el archivo
            if (existeNumeroLista(nombreGrupo, numeroLista)) {
                System.out.println("El número de lista ya existe en el grupo.");
                return;
            }

            Alumno alumno = new Alumno(nombre, apellido, numeroLista);
            grupo.agregarAlumno(alumno);
            // Guardar los datos del alumno en el archivo del grupo (modo append)
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

// Método para verificar si el número de lista ya existe en el archivo
    private boolean existeNumeroLista(String nombreGrupo, int numeroLista) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nombreGrupo + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                int numeroListaAlumno = Integer.parseInt(datos[0]);
                if (numeroListaAlumno == numeroLista) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del grupo.");
        }
        return false;
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
                        break;
                }
                System.out.println("- " + alumno.getNombre() + " " + alumno.getApellido() + ": " + calificacionParcial);
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    private Grupo buscarGrupo(String nombre) {
        String nombreArchivo = nombre + ".txt";
        File file = new File(nombreArchivo);

        if (file.exists()) {
            Grupo grupo = new Grupo(nombre);
            // Leer los datos del archivo y agregar los alumnos al grupo
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] datosAlumno = linea.split(",");
                    int numeroLista = Integer.parseInt(datosAlumno[0]);
                    String nombreAlumno = datosAlumno[1];
                    String apellidoAlumno = datosAlumno[2];
                    double parcial1 = Double.parseDouble(datosAlumno[3]);
                    double parcial2 = Double.parseDouble(datosAlumno[4]);
                    double parcial3 = Double.parseDouble(datosAlumno[5]);
                    double promedioFinal = Double.parseDouble(datosAlumno[6]);

                    Alumno alumno = new Alumno(nombreAlumno, apellidoAlumno, numeroLista);
                    alumno.setParcial1(parcial1);
                    alumno.setParcial2(parcial2);
                    alumno.setParcial3(parcial3);
                    alumno.setPromedioFinal(promedioFinal);

                    grupo.agregarAlumno(alumno);
                }
                scanner.close();

                return grupo;
            } catch (FileNotFoundException e) {
                System.out.println("Error al leer el archivo del grupo.");
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

    public void consultarCalificacionesFinales(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Calificaciones finales del grupo " + nombreGrupo + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                System.out.println("- " + alumno.getNombre() + " " + alumno.getApellido() + ": " + alumno.getPromedioFinal());
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarAlumnosReprobados(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Alumnos reprobados del grupo " + nombreGrupo + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                if (alumno.getPromedioFinal() < 6) {
                    System.out.println("- " + alumno.getNombre() + " " + alumno.getApellido() + " Calificación Final: " + alumno.getPromedioFinal());
                }
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void consultarAlumnosAprobados(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);
        if (grupo != null) {
            System.out.println("Alumnos aprobados del grupo " + nombreGrupo + ":");
            for (Alumno alumno : grupo.getAlumnos()) {
                if (alumno.getPromedioFinal() >= 6) {
                    System.out.println("- " + alumno.getNombre() + " " + alumno.getApellido() + " Calificación Final: " + alumno.getPromedioFinal());
                }
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

    public void generarArchivoTxt(String nombreGrupo) {
        Grupo grupo = buscarGrupo(nombreGrupo);

        if (grupo != null) {
            String nombreArchivo = "calificaciones.txt";

            try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
                // Obtener la lista de alumnos y ordenarlos por número de lista
                List<Alumno> alumnos = grupo.getAlumnos();
                Collections.sort(alumnos, Comparator.comparingInt(Alumno::getNumeroLista));

                // Definir el ancho fijo para cada campo
                final int ANCHO_NUMERO = 15;
                final int ANCHO_NOMBRE = 20;
                final int ANCHO_APELLIDO = 20;
                final int ANCHO_PARCIAL = 10;
                final int ANCHO_PROMEDIO = 15;

                // Escribir las calificaciones en el archivo
                writer.printf("%-" + ANCHO_NUMERO + "s %-" + ANCHO_NOMBRE + "s %-" + ANCHO_APELLIDO + "s %-" + ANCHO_PARCIAL + "s %-" + ANCHO_PARCIAL + "s %-" + ANCHO_PARCIAL + "s %-" + ANCHO_PROMEDIO + "s%n",
                        "Número de lista", "Nombre", "Apellido", "Parcial 1", "Parcial 2", "Parcial 3", "Promedio Final");
                for (Alumno alumno : alumnos) {
                    writer.printf("%-" + ANCHO_NUMERO + "d %-" + ANCHO_NOMBRE + "s %-" + ANCHO_APELLIDO + "s %-" + ANCHO_PARCIAL + ".2f %-" + ANCHO_PARCIAL + ".2f %-" + ANCHO_PARCIAL + ".2f %-" + ANCHO_PROMEDIO + ".2f%n",
                            alumno.getNumeroLista(),
                            alumno.getNombre(),
                            alumno.getApellido(),
                            alumno.getParcial1(),
                            alumno.getParcial2(),
                            alumno.getParcial3(),
                            alumno.getPromedioFinal());
                }

                System.out.println("Archivo de calificaciones generado exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al generar el archivo de calificaciones.");
            }
        } else {
            System.out.println("El grupo no existe.");
        }
    }

}
