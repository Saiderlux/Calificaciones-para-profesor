/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

class Alumno extends Persona {

    private int numeroLista;
    private double calificacionParcial1;
    private double calificacionParcial2;
    private double calificacionParcial3;

    public Alumno(int numeroLista, String nombre) {
        super(nombre);
        this.numeroLista = numeroLista;
        this.calificacionParcial1 = 0;
        this.calificacionParcial2 = 0;
        this.calificacionParcial3 = 0;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public double getCalificacionParcial1() {
        return calificacionParcial1;
    }

    public double getCalificacionParcial2() {
        return calificacionParcial2;
    }

    public double getCalificacionParcial3() {
        return calificacionParcial3;
    }

    public void setCalificacionParcial1(double calificacionParcial1) {
        this.calificacionParcial1 = calificacionParcial1;
    }

    public void setCalificacionParcial2(double calificacionParcial2) {
        this.calificacionParcial2 = calificacionParcial2;
    }

    public void setCalificacionParcial3(double calificacionParcial3) {
        this.calificacionParcial3 = calificacionParcial3;
    }

    public double calcularPromedioFinal() {
        return (calificacionParcial1 + calificacionParcial2 + calificacionParcial3) / 3;
    }

    public String toCSVString() {
        return numeroLista + "," + nombre + "," + calificacionParcial1 + "," + calificacionParcial2 + "," + calificacionParcial3;
    }
}
