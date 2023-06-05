/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ssaid
 */
public class Sistema {

    public void guardarGrupo(Grupo grupo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(grupo.getNombre() + ".txt"))) {
            for (Alumno alumno : grupo.getAlumnos()) {
                String datosAlumno = alumno.getNumeroLista() + ","
                        + alumno.getCalificacion1() + ","
                        + alumno.getCalificacion2() + ","
                        + alumno.getCalificacion3() + ","
                        + alumno.getCalificacionFinal();
                writer.write(datosAlumno);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarProfesor(Profesor profesor) {
        String datosProfesor = profesor.getNombre() + "," + profesor.getMateria();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profesor.txt"))) {
            writer.write(datosProfesor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
