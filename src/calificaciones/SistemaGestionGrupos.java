/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestionGrupos {

   private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        Profesor profesor = obtenerDatosProfesor();
        Grupo grupo = crearGrupo();

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            switch (opcion) {
                case 1:
                    grupo.agregarAlumno(crearAlumno());
                    break;
                case 2:
                    grupo.eliminarAlumno(obtenerNumeroLista());
                    break;
                case 3:
                    modificarCalificacionParcial(grupo);
                    break;
                case 4:
                    grupo.consultarAlumno(obtenerNumeroLista());
                    break;
                case 5:
                    grupo.consultarAlumnosPorParcial(obtenerNumeroParcial());
                    break;
                case 6:
                    grupo.consultarAlumnosConCalificaciones();
                    break;
                case 7:
                    grupo.consultarAlumnosReprobados();
                    break;
                case 8:
                    grupo.consultarAlumnosAprobados();
                    break;
                case 9:
                    grupo.guardarDatos();
                    System.out.println("Datos guardados exitosamente.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static Profesor obtenerDatosProfesor() {
        System.out.println("Ingrese el nombre del profesor:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la materia que imparte el profesor:");
        String materia = scanner.nextLine();
        return new Profesor(nombre, materia);
    }

    private static Grupo crearGrupo() throws FileNotFoundException {
        System.out.println("Ingrese el nombre del archivo para el grupo:");
        String nombreArchivo = scanner.nextLine();
        Grupo grupo = new Grupo(nombreArchivo);
        grupo.cargarDatos();
        return grupo;
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("------ Menú ------");
        System.out.println("1. Dar de alta un alumno");
        System.out.println("2. Dar de baja un alumno");
        System.out.println("3. Modificar calificación parcial de un alumno");
        System.out.println("4. Consultar datos de un alumno");
        System.out.println("5. Consultar lista de alumnos con calificación en un parcial");
        System.out.println("6. Consultar lista de alumnos con todas las calificaciones");
        System.out.println("7. Consultar lista de alumnos reprobados");
        System.out.println("8. Consultar lista de alumnos aprobados");
        System.out.println("9. Guardar datos del grupo");
        System.out.println("0. Salir");
        System.out.println("-------------------");
        System.out.println();
    }

    private static int obtenerOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextInt();
    }

    private static Alumno crearAlumno() {
        System.out.println("Ingrese el número de lista del alumno:");
        int numeroLista = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();
        return new Alumno(numeroLista, nombre);
    }

    private static int obtenerNumeroLista() {
        System.out.println("Ingrese el número de lista del alumno:");
        return scanner.nextInt();
    }

    private static int obtenerNumeroParcial() {
        System.out.println("Ingrese el número de parcial (1, 2 o 3):");
        return scanner.nextInt();
    }

    private static void modificarCalificacionParcial(Grupo grupo) {
        int numeroLista = obtenerNumeroLista();
        int numeroParcial = obtenerNumeroParcial();
        System.out.println("Ingrese la nueva calificación:");
        double calificacion = scanner.nextDouble();
        grupo.modificarCalificacionParcial(numeroLista, numeroParcial, calificacion);
    }
}