/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

import java.io.FileWriter;
import java.io.IOException;

class Alumno extends Persona {

    private int numeroLista;
    private double parcial1;
    private double parcial2;
    private double parcial3;
    private double promedioFinal;

    public Alumno(String nombre, String apellido, int numeroLista) {
        super(nombre, apellido);
        this.numeroLista = numeroLista;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public double getParcial1() {
        return parcial1;
    }

    public double getParcial2() {
        return parcial2;
    }

    public double getParcial3() {
        return parcial3;
    }

    public double getPromedioFinal() {
        return promedioFinal;
    }

    public void setParcial1(double parcial1) {
        this.parcial1 = parcial1;
    }

    public void setParcial2(double parcial2) {
        this.parcial2 = parcial2;
    }

    public void setParcial3(double parcial3) {
        this.parcial3 = parcial3;
    }

    public void setPromedioFinal(double promedioFinal) {
        this.promedioFinal = promedioFinal;
    }
}
