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

        boolean salir = false;
        while (!salir) {
            System.out.println("\n**Opciones de profesor**\n");
            System.out.println("1. Dar de alta profesor");
            System.out.println("2. Baja profesor");
            System.out.println("3. Editar profesor");
            System.out.println("\n**Opciones de grupo**\n");
            System.out.println("4. Dar de alta grupo");
            System.out.println("5. Baja grupo");
            System.out.println("6. Editar grupo");
            System.out.println("\n**Opciones de alumno**\n");
            System.out.println("7. Dar de alta alumno en un grupo");
            System.out.println("8. Baja alumno de un grupo");
            System.out.println("9. Añadir calificaciones a un alumno");
            System.out.println("\n**Opciones de consulta de calificaciones**\n");
            System.out.println("10. Consultar calificaciones por parcial");
            System.out.println("11. Consultar calificaciones finales");
            System.out.println("12. Consultar alumnos reprobados");
            System.out.println("13. Consultar alumnos aprobados");
            System.out.println("0. Salir");

            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    System.out.print("Ingrese el nombre del profesor: ");
                    String nombreProfesor = scanner.nextLine();
                    System.out.print("Ingrese el apellido del profesor: ");
                    String apellidoProfesor = scanner.nextLine();
                    System.out.print("Ingrese la materia del profesor: ");
                    String materiaProfesor = scanner.nextLine();
                    sistema.altaProfesor(nombreProfesor, apellidoProfesor, materiaProfesor);
                    break;
                case 2:
                    sistema.bajaProfesor();
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo nombre del profesor: ");
                    String nuevoNombreProfesor = scanner.nextLine();
                    System.out.print("Ingrese el nuevo apellido del profesor: ");
                    String nuevoApellidoProfesor = scanner.nextLine();
                    System.out.print("Ingrese la nueva materia del profesor: ");
                    String nuevaMateriaProfesor = scanner.nextLine();
                    sistema.editarProfesor(nuevoNombreProfesor, nuevoApellidoProfesor, nuevaMateriaProfesor);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();
                    sistema.altaGrupo(nombreGrupo);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del grupo a dar de baja: ");
                    String nombreGrupoBaja = scanner.nextLine();
                    sistema.bajaGrupo(nombreGrupoBaja);
                    break;
                case 6:
                    System.out.print("Ingrese el nombre del grupo a editar: ");
                    String nombreGrupoEditar = scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del grupo: ");
                    String nuevoNombreGrupo = scanner.nextLine();
                    sistema.editarGrupo(nombreGrupoEditar, nuevoNombreGrupo);
                    break;
                case 7:
                    System.out.print("Ingrese el nombre del grupo donde dar de alta al alumno: ");
                    String nombreGrupoAltaAlumno = scanner.nextLine();
                    System.out.print("Ingrese el nombre del alumno: ");
                    String nombreAlumno = scanner.nextLine();
                    System.out.print("Ingrese el apellido del alumno: ");
                    String apellidoAlumno = scanner.nextLine();
                    System.out.print("Ingrese el número de lista del alumno: ");
                    int numeroListaAlumno = scanner.nextInt();
                    sistema.altaAlumno(nombreGrupoAltaAlumno, nombreAlumno, apellidoAlumno, numeroListaAlumno);
                    scanner.nextLine(); // Consumir salto de línea
                    break;
                case 8:
                    System.out.print("Ingrese el nombre del grupo donde dar de baja al alumno: ");
                    String nombreGrupoBajaAlumno = scanner.nextLine();
                    System.out.print("Ingrese el número de lista del alumno a dar de baja: ");
                    int numeroListaBajaAlumno = scanner.nextInt();
                    sistema.bajaAlumno(nombreGrupoBajaAlumno, numeroListaBajaAlumno);
                    scanner.nextLine(); // Consumir salto de línea
                    break;
                case 9:
                    System.out.print("Ingrese el nombre del grupo donde editar al alumno: ");
                    String nombreGrupoEditarAlumno = scanner.nextLine();
                    System.out.print("Ingrese el número de lista del alumno a editar: ");
                    int numeroListaEditarAlumno = scanner.nextInt();
                    System.out.print("Ingrese la calificación del primer parcial: ");
                    double parcial1EditarAlumno = scanner.nextDouble();
                    System.out.print("Ingrese la calificación del segundo parcial: ");
                    double parcial2EditarAlumno = scanner.nextDouble();
                    System.out.print("Ingrese la calificación del tercer parcial: ");
                    double parcial3EditarAlumno = scanner.nextDouble();
                    sistema.editarAlumno(nombreGrupoEditarAlumno, numeroListaEditarAlumno,
                            parcial1EditarAlumno, parcial2EditarAlumno, parcial3EditarAlumno);
                    scanner.nextLine(); // Consumir salto de línea
                    break;
                case 10:
                    System.out.print("Ingrese el nombre del grupo a consultar: ");
                    String nombreGrupoConsultarParcial = scanner.nextLine();
                    System.out.print("Ingrese el número del parcial a consultar (1, 2 o 3): ");
                    int parcialConsultar = scanner.nextInt();
                    sistema.consultarCalificacionesParcial(nombreGrupoConsultarParcial, parcialConsultar);
                    scanner.nextLine(); // Consumir salto de línea
                    break;
                case 11:
                    System.out.print("Ingrese el nombre del grupo a consultar: ");
                    String nombreGrupoConsultarFinales = scanner.nextLine();
                    sistema.consultarCalificacionesFinales(nombreGrupoConsultarFinales);
                    break;
                case 12:
                    System.out.print("Ingrese el nombre del grupo a consultar: ");
                    String nombreGrupoConsultarReprobados = scanner.nextLine();
                    sistema.consultarAlumnosReprobados(nombreGrupoConsultarReprobados);
                    break;
                case 13:
                    System.out.print("Ingrese el nombre del grupo a consultar: ");
                    String nombreGrupoConsultarAprobados = scanner.nextLine();
                    sistema.consultarAlumnosAprobados(nombreGrupoConsultarAprobados);
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
