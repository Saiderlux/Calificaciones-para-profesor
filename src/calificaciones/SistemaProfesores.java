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
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class SistemaProfesores {

    public void opcionesProfesores() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("********** Opciones de Profesores **********");
            System.out.println("1. Guardar profesor");
            System.out.println("2. Editar profesor");
            System.out.println("0. Salir");
            System.out.println("Ingrese la opción deseada:");

            opcion = scanner.nextInt();

            switch (opcion) {
               
                case 1:
                    Profesor profesor = ingresarDatosProfesor();
                    guardarProfesor(profesor);
                    break;
                case 2:
                    editarProfesor();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

            System.out.println();
        } while (opcion != 0);
    }
    
    private static final String ARCHIVO_PROFESOR = "profesor.txt";//Variable constante, con el nombre del archivo profesor.

    private static Profesor ingresarDatosProfesor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del profesor:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la materia que imparte el profesor:");
        String materia = scanner.nextLine();

        return new Profesor(nombre, materia);
    }

    public void validarArchivoProfesor() {// método para verificar si en el sistema ya está dado de alta un profesor
        File archivo = new File(ARCHIVO_PROFESOR);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo profesor.txt no existe o está vacío.");
            System.out.println("Ingrese los datos del profesor para guardarlo en el archivo:");

            Profesor profesor = ingresarDatosProfesor();

            guardarProfesor(profesor);
        } else {
            System.out.println("El archivo profesor.txt existe y contiene datos. Continuando con el programa...");
        }
    }

    public void guardarProfesor(Profesor profesor) {
        String datosProfesor = profesor.getNombre() + "," + profesor.getMateria();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profesor.txt"))) {
            writer.write(datosProfesor);
        } catch (IOException e) {
        }
    }

    public void editarProfesor() {
        try {
            // Leer el contenido actual del archivo
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PROFESOR))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append(System.lineSeparator());
                }
            }

            // Mostrar el contenido actual del archivo
            System.out.println("Contenido actual del archivo:");
            System.out.println(contenido);

            // Dividir el contenido por comas y guardar en un arreglo
            String[] atributos = contenido.toString().split(",");

            // Solicitar al usuario el atributo a editar
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el número del atributo que desea editar:");
            System.out.println("1. Nombre");
            System.out.println("2. Materia");
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 2) {
                scanner.nextLine(); // Consumir el salto de línea pendiente

                // Solicitar al usuario el nuevo valor para el atributo seleccionado
                System.out.println("Ingrese el nuevo valor para el atributo seleccionado:");
                String nuevoValor = scanner.nextLine();

                // Actualizar el valor del atributo seleccionado en el arreglo
                if (opcion == 1) {
                    atributos[0] = nuevoValor; // Nombre
                } else {
                    atributos[1] = nuevoValor; // Materia
                }

                // Construir el contenido actualizado
                String contenidoActualizado = String.join(",", atributos);

                // Escribir el contenido actualizado en el archivo
                try (FileWriter writer = new FileWriter(ARCHIVO_PROFESOR)) {
                    writer.write(contenidoActualizado);
                }

                System.out.println("El atributo ha sido actualizado en el archivo.");
            } else {
                System.out.println("Opción inválida.");
            }
        } catch (IOException e) {
            System.out.println("Error al editar el archivo profesores.txt");
            e.printStackTrace();
        }
    }

}
