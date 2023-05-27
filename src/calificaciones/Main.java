/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

// Clase principal para el programa

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGestionGrupos sistema = new SistemaGestionGrupos();

        int opcion;
        do {
            System.out.println("Menú Principal");
            System.out.println("1. Dar de alta profesor y materia");
            System.out.println("2. Dar de baja profesor");
            System.out.println("3. Dar de alta grupo");
            System.out.println("4. Dar de baja grupo");
            System.out.println("5. Modificar grupo");
            System.out.println("6. Dar de alta alumno");
            System.out.println("7. Dar de baja alumno");
            System.out.println("8. Modificar alumno");
            System.out.println("9. Consultar grupo");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del profesor: ");
                    String nombreProfesor = scanner.next();
                    System.out.print("Apellido del profesor: ");
                    String apellidoProfesor = scanner.next();
                    System.out.print("Materia: ");
                    String materia = scanner.next();
                    sistema.altaProfesor(nombreProfesor, apellidoProfesor, materia);
                    System.out.println("Profesor dado de alta correctamente.");
                    break;
                case 2:
                    sistema.bajaProfesor();
                    System.out.println("Profesor dado de baja correctamente.");
                    break;
                case 3:
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.next();
                    sistema.altaGrupo(nombreGrupo);
                    System.out.println("Grupo dado de alta correctamente.");
                    break;
                case 4:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    sistema.bajaGrupo(nombreGrupo);
                    System.out.println("Grupo dado de baja correctamente.");
                    break;
                case 5:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    System.out.print("Nuevo nombre del grupo: ");
                    String nuevoNombreGrupo = scanner.next();
                    sistema.modificarGrupo(nombreGrupo, nuevoNombreGrupo);
                    System.out.println("Grupo modificado correctamente.");
                    break;
                case 6:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    System.out.print("Nombre del alumno: ");
                    String nombreAlumno = scanner.next();
                    System.out.print("Apellido del alumno: ");
                    String apellidoAlumno = scanner.next();
                    System.out.print("Número de lista: ");
                    int numeroLista = scanner.nextInt();
                    sistema.altaAlumno(nombreGrupo, nombreAlumno, apellidoAlumno, numeroLista);
                    System.out.println("Alumno dado de alta correctamente.");
                    break;
                case 7:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    System.out.print("Número de lista del alumno: ");
                    numeroLista = scanner.nextInt();
                    sistema.bajaAlumno(nombreGrupo, numeroLista);
                    System.out.println("Alumno dado de baja correctamente.");
                    break;
                case 8:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    System.out.print("Número de lista del alumno: ");
                    numeroLista = scanner.nextInt();
                    System.out.print("Nuevo nombre del alumno: ");
                    nombreAlumno = scanner.next();
                    System.out.print("Nuevo apellido del alumno: ");
                    apellidoAlumno = scanner.next();
                    sistema.modificarAlumno(nombreGrupo, numeroLista, nombreAlumno, apellidoAlumno);
                    System.out.println("Alumno modificado correctamente.");
                    break;
                case 9:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.next();
                    sistema.consultarGrupo(nombreGrupo);
                    System.out.print("Seleccione una opción de consulta: ");
                    int opcionConsulta = scanner.nextInt();
                    sistema.consultarLista(nombreGrupo, opcionConsulta);
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 10);

        scanner.close();
    }
}