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
import java.util.HashMap;
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
        try (FileWriter fileWriter = new FileWriter(archivoGrupo, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
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
}
