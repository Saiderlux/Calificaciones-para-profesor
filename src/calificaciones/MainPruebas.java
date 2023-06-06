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
         SistemaGrupos sistema = new SistemaGrupos();
         sistema.mostrarGruposDisponibles();
    }
    
    

    private static Profesor ingresarDatosProfesor() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del profesor:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la materia que imparte el profesor:");
        String materia = scanner.nextLine();

        Profesor profesor = new Profesor(nombre, materia);
        return profesor;
    }

    private static Grupo ingresarDatosGrupo() {
        Grupo grupo = new Grupo();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del grupo:");
        String nombre = scanner.nextLine();
        grupo.setNombre(nombre);

        return grupo;
    }
}
