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
        // Crear instancia de Sistema
        SistemaGrupos sistema = new SistemaGrupos();

        // Pedir al usuario que ingrese los datos del grupo
        Grupo grupo = ingresarDatosGrupo();

        System.out.println("Ingrese el nuevo nombre del grupo");
        String nuevoNombre = scanner.next();
        sistema.editarNombreGrupo(grupo.getNombre(), nuevoNombre);
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
