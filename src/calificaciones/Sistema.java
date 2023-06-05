/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class Sistema {

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

    public void guardarGrupo(Grupo grupo) {
        String nombreArchivo = grupo.getNombre() + ".txt";

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo " + nombreArchivo + " creado correctamente.");
            } else {
                System.out.println("El archivo " + nombreArchivo + " ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + nombreArchivo);
            e.printStackTrace();
        }
    }

    public void guardarProfesor(Profesor profesor) {
        String datosProfesor = profesor.getNombre() + "," + profesor.getMateria();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profesor.txt"))) {
            writer.write(datosProfesor);
        } catch (IOException e) {
        }
    }
}
