/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ssaid
 */
public class SistemaGrupos {

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
}
