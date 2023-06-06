/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class MainPruebas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        SistemaAlumnos alumnos = new SistemaAlumnos();
        SistemaGrupos grupos = new SistemaGrupos();
        SistemaProfesores profesores = new SistemaProfesores();
        do {
            System.out.println("********** Opciones de Alumnos **********");
            System.out.println("1. Opciones de profesores");
            System.out.println("2. Opciones de grupos");
            System.out.println("3. Opciones de alumnos");
            System.out.println("4. Opciones de consulta");
            System.out.println("0. Salir");
            System.out.println("Ingrese la opción deseada:");

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    profesores.opcionesProfesores();
                    break;
                case 2:
                    grupos.opcionesGrupos();
                    break;
                case 3:
                    alumnos.opcionesAlumnos();
                    break;
                case 4:
                    alumnos.opcionesConsulta();
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
}
