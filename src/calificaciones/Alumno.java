/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

// Clase Alumno que hereda de Persona
class Alumno extends Persona {

    private int numeroLista;
    private double calificacion1;
    private double calificacion2;
    private double calificacion3;

    public Alumno(String nombre, String apellido, int numeroLista) {
        super(nombre, apellido);
        this.numeroLista = numeroLista;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public double getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(double calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public double getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(double calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public double getCalificacion3() {
        return calificacion3;
    }

    public void setCalificacion3(double calificacion3) {
        this.calificacion3 = calificacion3;
    }

    public double calcularPromedio() {
        return (calificacion1 + calificacion2 + calificacion3) / 3;
    }

    @Override
    public String toString() {
        return "Número de lista: " + numeroLista + "\n"
                + "Nombre: " + nombre + " " + apellido + "\n"
                + "Calificación 1: " + calificacion1 + "\n"
                + "Calificación 2: " + calificacion2 + "\n"
                + "Calificación 3: " + calificacion3 + "\n"
                + "Promedio: " + calcularPromedio();
    }
}
