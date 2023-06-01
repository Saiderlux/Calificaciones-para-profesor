/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;


class Alumno extends Persona {
    private int numeroLista;
    private double[] calificaciones;
    private double calificacionFinal;

    public Alumno(String nombre, int numeroLista) {
        super(nombre);
        this.numeroLista = numeroLista;
        this.calificaciones = new double[3]; // 3 parciales
        this.calificacionFinal = 0.0;
    }

    // Métodos para establecer y obtener calificaciones y calificación final

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public double[] getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(double[] calificaciones) {
        this.calificaciones = calificaciones;
    }

    public double getCalificacionFinal() {
        return calificacionFinal;
    }

    public void setCalificacionFinal(double calificacionFinal) {
        this.calificacionFinal = calificacionFinal;
    }
    
}
