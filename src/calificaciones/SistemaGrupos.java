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
import java.util.ArrayList;

/**
 *
 * @author Ssaid
 */
public class SistemaGrupos {

    public void guardarGrupoEnArchivo(Grupo grupo) {
        String nombreGrupo = grupo.getNombre();

        if (existeGrupoEnArchivo(nombreGrupo)) {
            System.out.println("El nombre del grupo ya está registrado.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("grupos.txt", true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(nombreGrupo);
            bufferedWriter.newLine();
            System.out.println("El grupo se ha guardado en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el grupo en el archivo.");
            e.printStackTrace();
        }
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
        // Obtener el nombre del grupo a guardar
        String nombreGrupo = grupo.getNombre();

        // Verificar si ya existe un archivo con el mismo nombre
        File archivoGrupo = new File(nombreGrupo + ".txt");
        if (archivoGrupo.exists()) {
            System.out.println("Error: Ya existe un grupo con el mismo nombre.");
            return;
        }

        // Crear el archivo para el grupo
        try {
            archivoGrupo.createNewFile();
            System.out.println("Se ha creado el archivo para el grupo.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del grupo.");
            e.printStackTrace();
            return;
        }
    }

    public void editarNombreGrupo(String nombreAnterior, String nuevoNombre) {
        // Verificar si ya existe un archivo con el nuevo nombre
        File archivoNuevo = new File(nuevoNombre + ".txt");
        if (archivoNuevo.exists()) {
            System.out.println("Error: Ya existe un grupo con el nuevo nombre.");
            return;
        }

        // Obtener el archivo del grupo con el nombre anterior
        File archivoAnterior = new File(nombreAnterior + ".txt");

        // Verificar si el archivo existe
        if (!archivoAnterior.exists()) {
            System.out.println("Error: No existe un grupo con el nombre anterior.");
            return;
        }

        // Renombrar el archivo
        if (archivoAnterior.renameTo(archivoNuevo)) {
            System.out.println("Se ha cambiado el nombre del grupo exitosamente.");
        } else {
            System.out.println("Error al cambiar el nombre del grupo.");
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

}
