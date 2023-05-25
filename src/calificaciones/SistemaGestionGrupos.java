/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestionGrupos {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Grupo> grupos = new ArrayList<>();
    private static Profesor profesor = null;

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    registrarProfesor();
                    break;
                case 2:
                    if (profesor != null) {
                        crearGrupo();
                    } else {
                        System.out.println("Debe registrar al profesor primero.");
                    }
                    break;
                case 3:
                    if (grupos.isEmpty()) {
                        System.out.println("No hay grupos registrados.");
                    } else {
                        consultarGrupos();
                    }
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("----- Menú Principal -----");
        System.out.println("1. Registrar profesor");
        System.out.println("2. Crear grupo");
        System.out.println("3. Consultar grupos");
        System.out.println("4. Salir");
        System.out.println("--------------------------");
    }

    private static int obtenerOpcion() {
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    private static void registrarProfesor() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el nombre del profesor:");
        String nombreProfesor = scanner.nextLine();
        System.out.println("Ingrese la materia del profesor:");
        String materiaProfesor = scanner.nextLine();
        profesor = new Profesor(nombreProfesor, materiaProfesor);
        System.out.println("Profesor registrado correctamente.");
    }

    private static void crearGrupo() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el nombre del grupo:");
        String nombreGrupo = scanner.nextLine();
        Grupo grupo = new Grupo(nombreGrupo);
        grupos.add(grupo);
        System.out.println("Grupo creado correctamente.");

        boolean salir = false;
        while (!salir) {
            mostrarMenuGrupo();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    agregarAlumno(grupo);
                    break;
                case 2:
                    eliminarAlumno(grupo);
                    break;
                case 3:
                    modificarCalificacionParcial(grupo);
                    break;
                case 4:
                    consultarAlumno(grupo);
                    break;
                case 5:
                    consultarListaPorParcial(grupo);
                    break;
                case 6:
                    consultarListaCompleta(grupo);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static void mostrarMenuGrupo() {
        System.out.println("\n----- Menú del Grupo -----");
        System.out.println("1. Agregar alumno");
        System.out.println("2. Eliminar alumno");
        System.out.println("3. Modificar calificación parcial");
        System.out.println("4. Consultar alumno");
        System.out.println("5. Consultar lista de alumnos por parcial");
        System.out.println("6. Consultar lista completa de alumnos");
        System.out.println("7. Volver al menú principal");
        System.out.println("--------------------------");
    }

    private static void agregarAlumno(Grupo grupo) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el número de lista del alumno:");
        int numeroLista = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el nombre del alumno:");
        String nombreAlumno = scanner.nextLine();
        Alumno alumno = new Alumno(numeroLista, nombreAlumno);
        grupo.agregarAlumno(alumno);
        System.out.println("Alumno agregado correctamente.");
    }

    private static void eliminarAlumno(Grupo grupo) {
        System.out.println("Ingrese el número de lista del alumno a eliminar:");
        int numeroLista = scanner.nextInt();
        grupo.eliminarAlumno(numeroLista);
        System.out.println("Alumno eliminado correctamente.");
    }

    private static void modificarCalificacionParcial(Grupo grupo) {
        System.out.println("Ingrese el número de lista del alumno:");
        int numeroLista = scanner.nextInt();
        System.out.println("Ingrese el número de parcial (1, 2 o 3):");
        int numeroParcial = scanner.nextInt();
        System.out.println("Ingrese la nueva calificación:");
        double calificacion = scanner.nextDouble();
        grupo.modificarCalificacionParcial(numeroLista, numeroParcial, calificacion);
        System.out.println("Calificación modificada correctamente.");
    }

    private static void consultarAlumno(Grupo grupo) {
        System.out.println("Ingrese el número de lista del alumno:");
        int numeroLista = scanner.nextInt();
        grupo.consultarAlumno(numeroLista);
    }

    private static void consultarListaPorParcial(Grupo grupo) {
        System.out.println("Ingrese el número de parcial (1, 2 o 3):");
        int numeroParcial = scanner.nextInt();
        grupo.consultarListaPorParcial(numeroParcial);
    }

    private static void consultarListaCompleta(Grupo grupo) {
        grupo.consultarListaCompleta();
    }

    private static void consultarGrupos() {
        System.out.println("\n----- Grupos Registrados -----");
        for (Grupo grupo : grupos) {
            System.out.println(grupo.getNombreArchivo());
        }
        System.out.println("--------------------------");
    }
}
