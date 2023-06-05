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
        String nombreArchivo = grupo.getNombre() + ".txt";

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo " + nombreArchivo + " creado correctamente.");
            } else {
                System.out.println("El archivo " + nombreArchivo + " ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + nombreArchivo);
            e.printStackTrace();
        }
    }
    
}
