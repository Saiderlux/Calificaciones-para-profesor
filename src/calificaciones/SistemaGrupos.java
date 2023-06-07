/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ssaid
 */
public class SistemaGrupos {

    public void opcionesGrupos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        SistemaAlumnos alumnos = new SistemaAlumnos();
        File archivoProfesor = new File("profesor.txt");
        File archivoGrupos = new File("grupos.txt");

        if (!archivoProfesor.exists() || archivoProfesor.length() == 0) {
            System.out.println("Primero debes dar de alta un profesor");
        } else {
            do {
                if (!archivoGrupos.exists()) {
                    try {
                        archivoGrupos.createNewFile();
                        System.out.println("Se ha creado el archivo grupos.txt.");
                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo grupos.txt: " + e.getMessage());
                    }
                }
                System.out.println("********** Opciones de Grupos **********");
                System.out.println("1. Dar de alta grupo");
                System.out.println("2. Dar de baja grupo");
                System.out.println("3. Editar nombre de un grupo");
                System.out.println("0. Salir");
                System.out.println("Ingrese la opción deseada:");

                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        Grupo grupo = ingresarDatosGrupo();
                        guardarGrupo(grupo);
                        break;
                    case 2:
                        alumnos.mostrarGruposDisponibles();
                        Grupo grupo1 = ingresarDatosGrupo();
                        darDeBajaGrupo(grupo1);
                        break;
                    case 3:
                        alumnos.mostrarGruposDisponibles();
                        System.out.println("Ingrese el nombre del grupo a editar: ");
                        String nombreAnterior = scanner.next();
                        System.out.println("Ingrese el nuevo nombre del grupo: ");
                        String nuevoNombre = scanner.next();
                        editarNombreGrupo(nombreAnterior, nuevoNombre);
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

    private static Grupo ingresarDatosGrupo() {
        Grupo grupo = new Grupo();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del grupo:");
        String nombre = scanner.nextLine();
        grupo.setNombre(nombre);

        return grupo;
    }

    private boolean existeGrupoEnArchivo(String nombreGrupo) {
        try (FileReader fileReader = new FileReader("grupos.txt"); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
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

    public void guardarGrupo(Grupo grupo) {
        String nombreGrupo = grupo.getNombre();

        if (existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El nombre del grupo ya está registrado.");
            return;
        }

        File archivoGrupo = new File(nombreGrupo + ".txt");
        if (archivoGrupo.exists()) {
            System.out.println("Error: Ya existe un grupo con el mismo nombre.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("grupos.txt", true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(nombreGrupo);
            bufferedWriter.newLine();
            System.out.println("El grupo se ha guardado en el archivo correctamente.");

            archivoGrupo.createNewFile();
            System.out.println("Se ha creado el archivo para el grupo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el grupo en el archivo.");
            e.printStackTrace();
        }
    }

    public ArrayList<Grupo> obtenerGrupos() {
        ArrayList<Grupo> grupos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("grupos.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                Grupo grupo = new Grupo();
                grupo.setNombre(linea);
                grupos.add(grupo);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de grupos.");
            e.printStackTrace();
        }

        return grupos;
    }

    public void darDeBajaGrupo(Grupo grupo) {
        // Obtener la lista de grupos

        ArrayList<Grupo> grupos = obtenerGrupos();

        // Verificar si el grupo existe en la lista
        if (!grupos.contains(grupo)) {
            System.out.println("El grupo no está registrado.");
            return;
        }

        // Eliminar el grupo de la lista
        grupos.remove(grupo);

        // Guardar la lista actualizada de grupos en el archivo grupos.txt
        try (FileWriter fileWriter = new FileWriter("grupos.txt"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Grupo g : grupos) {
                bufferedWriter.write(g.getNombre());
                bufferedWriter.newLine();
            }
            System.out.println("El grupo se ha dado de baja correctamente.");
        } catch (IOException e) {
            System.out.println("Error al dar de baja el grupo.");
            e.printStackTrace();
            return;
        }

        // Eliminar el archivo del grupo
        File archivoGrupo = new File(grupo.getNombre() + ".txt");

        if (archivoGrupo.exists()) {
            if (archivoGrupo.delete()) {
                System.out.println("Archivo del grupo eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el archivo del grupo.");
            }
        } else {
            System.out.println("El archivo del grupo no existe.");
        }
    }

    public void editarNombreGrupo(String nombreAnterior, String nuevoNombre) {
        // Validar si el nuevo nombre ya existe como archivo de grupo
        File archivoGrupoNuevo = new File(nuevoNombre + ".txt");
        if (archivoGrupoNuevo.exists()) {
            System.out.println("Error: Ya existe un grupo con el mismo nombre de archivo.");
            return;
        }

        // Validar si el nuevo nombre ya existe como registro en grupos.txt
        File archivoGrupos = new File("grupos.txt");
        if (existeGrupoEnArchivo(nuevoNombre)) {
            System.out.println("Error: Ya existe un grupo con el mismo nombre en el archivo grupos.txt.");
            return;
        }

        // Modificar el nombre del archivo del grupo
        File archivoGrupoAnterior = new File(nombreAnterior + ".txt");
        if (archivoGrupoAnterior.renameTo(archivoGrupoNuevo)) {
            System.out.println("El nombre del archivo del grupo se ha modificado correctamente.");
        } else {
            System.out.println("Error al modificar el nombre del archivo del grupo.");
        }

        // Modificar el nombre del grupo en el archivo grupos.txt
        try {
            FileReader fileReader = new FileReader(archivoGrupos);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder contenido = new StringBuilder();

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.equals(nombreAnterior)) {
                    contenido.append(nuevoNombre).append("\n");
                } else {
                    contenido.append(linea).append("\n");
                }
            }

            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(archivoGrupos);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(contenido.toString());
            bufferedWriter.close();

            System.out.println("El nombre del grupo en el archivo grupos.txt se ha modificado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al modificar el nombre del grupo en el archivo grupos.txt.");
            e.printStackTrace();
        }
    }

    
}
