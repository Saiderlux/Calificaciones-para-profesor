/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class SistemaAlumnos {

    private Map<String, Alumno> alumnos = new HashMap<>();

    public void darDeAltaAlumnos() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del grupo
        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        // Verificar si el grupo ya está dado de alta en el archivo
        if (!existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El grupo no está dado de alta.");
            return;
        }

        // Obtener el archivo del grupo
        File archivoGrupo = new File(nombreGrupo + ".txt");

        // Solicitar datos de los alumnos
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese los datos del alumno:");
            System.out.println("ID: ");
            int idAlumno = Integer.parseInt(scanner.nextLine());

            // Verificar si el alumno ya está dado de alta en el archivo
            if (existeAlumnoEnArchivo(nombreGrupo, idAlumno)) {
                System.out.println("El alumno con ID " + idAlumno + " ya está dado de alta.");
                continue;
            }

            System.out.println("Nombre: ");
            String nombreAlumno = scanner.nextLine();

            System.out.println("Calificacion1: ");
            double calificacion1 = Double.parseDouble(scanner.nextLine());

            System.out.println("Calificacion2: ");
            double calificacion2 = Double.parseDouble(scanner.nextLine());

            System.out.println("Calificacion3: ");
            double calificacion3 = Double.parseDouble(scanner.nextLine());

            // Calcular calificación final
            double calificacionFinal = (calificacion1 + calificacion2 + calificacion3) / 3.0;

            // Concatenar los datos del alumno con comas
            String datosAlumno = idAlumno + "," + nombreAlumno + "," + calificacion1 + "," + calificacion2 + "," + calificacion3 + "," + calificacionFinal;

            // Guardar los datos del alumno en el archivo
            try (FileWriter fileWriter = new FileWriter(archivoGrupo, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(datosAlumno);
                bufferedWriter.newLine();
                System.out.println("Alumno dado de alta correctamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar el alumno en el archivo.");
                e.printStackTrace();
            }

            // Preguntar si se desea agregar otro alumno
            System.out.println("¿Desea agregar otro alumno? (S/N): ");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("N")) {
                continuar = false;
            }
        }

        scanner.close();
    }

    private boolean existeAlumnoEnArchivo(String nombreArchivo, int numeroLista) {
        File archivo = new File(nombreArchivo + ".txt");

        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                // Verificar si la línea está vacía
                if (linea.isEmpty()) {
                    continue;
                }

                // Leer el número de lista (ID) de cada línea del archivo
                String[] datosAlumno = linea.split(",");
                if (datosAlumno.length > 0) {
                    int id;

                    // Verificar si el número de lista (ID) es un número válido
                    try {
                        id = Integer.parseInt(datosAlumno[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Número de lista no válido en el archivo.");
                        continue;
                    }

                    // Verificar si el número de lista (ID) coincide
                    if (id == numeroLista) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
        }

        return false;
    }

    private boolean existeGrupoEnArchivo(String nombreGrupo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("grupos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.equals(nombreGrupo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de grupos.");
            e.printStackTrace();
        }
        return false;
    }

    public void darDeBajaAlumno() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del grupo
        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        // Verificar si el grupo ya está dado de alta en el archivo
        if (!existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El grupo no está dado de alta.");
            return;
        }

        // Obtener el archivo del grupo
        File archivoGrupo = new File(nombreGrupo + ".txt");

        // Leer los datos del archivo y guardarlos en una lista
        List<String> lineas = new ArrayList<>();
        try (FileReader fileReader = new FileReader(archivoGrupo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del grupo.");
            e.printStackTrace();
            return;
        }

        // Mostrar los alumnos disponibles para dar de baja
        System.out.println("Alumnos disponibles:");
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            int idAlumno = Integer.parseInt(datos[0]);
            String nombreAlumno = datos[1];
            System.out.println("ID: " + idAlumno + " | Nombre: " + nombreAlumno);
        }

        // Solicitar el ID del alumno a dar de baja
        System.out.println("Ingrese el ID del alumno a dar de baja: ");
        int idAlumnoBaja = Integer.parseInt(scanner.nextLine());

        // Buscar el alumno en la lista de datos del archivo
        int indiceAlumnoBaja = -1;
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] datos = linea.split(",");
            int idAlumno = Integer.parseInt(datos[0]);
            if (idAlumno == idAlumnoBaja) {
                indiceAlumnoBaja = i;
                break;
            }
        }

        // Verificar si se encontró al alumno
        if (indiceAlumnoBaja == -1) {
            System.out.println("El alumno con ID " + idAlumnoBaja + " no está registrado en el grupo.");
            return;
        }

        // Eliminar al alumno de la lista
        lineas.remove(indiceAlumnoBaja);

        // Ajustar los ID de los alumnos restantes
        for (int i = indiceAlumnoBaja; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] datos = linea.split(",");
            int idAlumno = Integer.parseInt(datos[0]);
            idAlumno--;
            datos[0] = String.valueOf(idAlumno);
            lineas.set(i, String.join(",", datos));
        }

        // Guardar los datos actualizados en el archivo
        try (FileWriter fileWriter = new FileWriter(archivoGrupo); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String linea : lineas) {
                bufferedWriter.write(linea);
                bufferedWriter.newLine();
            }
            System.out.println("Alumno dado de baja correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios en el archivo del grupo.");
            e.printStackTrace();
        }

        scanner.close();
    }

    public void editarAlumno() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del grupo
        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        // Verificar si el grupo ya está dado de alta en el archivo
        if (!existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El grupo no está dado de alta.");
            return;
        }

        // Obtener el archivo del grupo
        File archivoGrupo = new File(nombreGrupo + ".txt");

        // Leer los datos del archivo y guardarlos en una lista
        List<String> lineas = new ArrayList<>();
        try (FileReader fileReader = new FileReader(archivoGrupo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del grupo.");
            e.printStackTrace();
            return;
        }

        // Mostrar los alumnos disponibles para editar
        System.out.println("Alumnos disponibles:");
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            int idAlumno = Integer.parseInt(datos[0]);
            String nombreAlumno = datos[1];
            double calificacion1 = Double.parseDouble(datos[2]);
            double calificacion2 = Double.parseDouble(datos[3]);
            double calificacion3 = Double.parseDouble(datos[4]);

            System.out.println("ID: " + idAlumno + " | Nombre: " + nombreAlumno + " | Parcial 1: " + calificacion1 + " | Parcial 2: " + calificacion2 + " | Parcial 3: " + calificacion3);
        }

        // Solicitar el ID del alumno a editar
        System.out.println("Ingrese el ID del alumno a editar: ");
        int idAlumnoEditar = Integer.parseInt(scanner.nextLine());

        // Buscar el alumno en la lista de datos del archivo
        int indiceAlumnoEditar = -1;
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] datos = linea.split(",");
            int idAlumno = Integer.parseInt(datos[0]);
            if (idAlumno == idAlumnoEditar) {
                indiceAlumnoEditar = i;
                break;
            }
        }

        // Verificar si se encontró al alumno
        if (indiceAlumnoEditar == -1) {
            System.out.println("El alumno con ID " + idAlumnoEditar + " no está registrado en el grupo.");
            return;
        }

        // Obtener los datos del alumno a editar
        String lineaAlumnoEditar = lineas.get(indiceAlumnoEditar);
        String[] datosAlumnoEditar = lineaAlumnoEditar.split(",");

        // Mostrar el menú de opciones de edición
        System.out.println("Opciones de edición:");
        System.out.println("1. Editar nombre");
        System.out.println("2. Editar calificación del Parcial 1");
        System.out.println("3. Editar calificación del Parcial 2");
        System.out.println("4. Editar calificación del Parcial 3");
        System.out.println("0. Salir");

        boolean continuarEdicion = true;
        while (continuarEdicion) {
            System.out.println("Ingrese el número de opción: ");
            int opcionEdicion = Integer.parseInt(scanner.nextLine());

            switch (opcionEdicion) {
                case 1:
                    // Editar nombre del alumno
                    System.out.println("Ingrese el nuevo nombre del alumno: ");
                    String nuevoNombre = scanner.nextLine();
                    datosAlumnoEditar[1] = nuevoNombre;
                    break;
                case 2:
                    // Editar calificación del Parcial 1
                    System.out.println("Ingrese la nueva calificación del Parcial 1: ");
                    double nuevaCalificacion1 = Double.parseDouble(scanner.nextLine());
                    datosAlumnoEditar[2] = String.valueOf(nuevaCalificacion1);
                    break;
                case 3:
                    // Editar calificación del Parcial 2
                    System.out.println("Ingrese la nueva calificación del Parcial 2: ");
                    double nuevaCalificacion2 = Double.parseDouble(scanner.nextLine());
                    datosAlumnoEditar[3] = String.valueOf(nuevaCalificacion2);
                    break;
                case 4:
                    // Editar calificación del Parcial 3
                    System.out.println("Ingrese la nueva calificación del Parcial 3: ");
                    double nuevaCalificacion3 = Double.parseDouble(scanner.nextLine());
                    datosAlumnoEditar[4] = String.valueOf(nuevaCalificacion3);
                    break;
                case 0:
                    // Salir del menú de edición
                    continuarEdicion = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }

        // Calcular la nueva calificación final
        double calificacion1 = Double.parseDouble(datosAlumnoEditar[2]);
        double calificacion2 = Double.parseDouble(datosAlumnoEditar[3]);
        double calificacion3 = Double.parseDouble(datosAlumnoEditar[4]);
        double calificacionFinal = (calificacion1 + calificacion2 + calificacion3) / 3.0;
        datosAlumnoEditar[5] = String.valueOf(calificacionFinal);

        // Actualizar la línea del alumno en la lista de datos del archivo
        String nuevaLineaAlumnoEditar = String.join(",", datosAlumnoEditar);
        lineas.set(indiceAlumnoEditar, nuevaLineaAlumnoEditar);

        // Guardar los cambios en el archivo del grupo
        try (FileWriter fileWriter = new FileWriter(archivoGrupo); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String linea : lineas) {
                bufferedWriter.write(linea);
                bufferedWriter.newLine();
            }

            System.out.println("Los datos del alumno se han actualizado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios en el archivo del grupo.");
            e.printStackTrace();
        }

        scanner.close();
    }

    public void consultarAlumno() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del grupo
        System.out.println("Ingrese el nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        // Verificar si el grupo ya está dado de alta en el archivo
        if (!existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El grupo no está dado de alta.");
            return;
        }

        // Obtener el archivo del grupo
        File archivoGrupo = new File(nombreGrupo + ".txt");

        // Solicitar el ID del alumno a consultar
        System.out.println("Ingrese el ID del alumno a consultar: ");
        int idAlumno = Integer.parseInt(scanner.nextLine());

        // Verificar si el alumno existe en el archivo del grupo
        boolean encontrado = false;
        try (FileReader fileReader = new FileReader(archivoGrupo); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                if (id == idAlumno) {
                    encontrado = true;
                    String nombre = datos[1];
                    double calificacion1 = Double.parseDouble(datos[2]);
                    double calificacion2 = Double.parseDouble(datos[3]);
                    double calificacion3 = Double.parseDouble(datos[4]);
                    double calificacionFinal = Double.parseDouble(datos[5]);

                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Calificación Parcial 1: " + calificacion1);
                    System.out.println("Calificación Parcial 2: " + calificacion2);
                    System.out.println("Calificación Parcial 3: " + calificacion3);
                    System.out.println("Calificación Final: " + calificacionFinal);

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del grupo.");
            e.printStackTrace();
            return;
        }

        if (!encontrado) {
            System.out.println("El alumno con ID " + idAlumno + " no está registrado en el grupo.");
        }

        scanner.close();
    }

}
