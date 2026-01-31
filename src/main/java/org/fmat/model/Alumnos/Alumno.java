package org.fmat.model.Alumnos;

public class Alumno {

    private String matricula;
    private String nombreCompleto;
    private String asignatura;
    private int calFinal;

    public Alumno(String matricula, String nombreCompleto) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.asignatura = "Diseño de Software"; // por defecto
        this.calFinal = -1; // por defecto sin setear
    }

    public String info () {
        //formato tabla
        return String.format("%-10s | %-40s | %-10s | %d", matricula, nombreCompleto, asignatura, calFinal);
    }

    //Transforma al alumnos en formato CSV
    public String toCSV (){
        //formato CSV
        String calFinalcsv = (calFinal == -1) ? "No registrado" : String.valueOf(calFinal);
        return String.format("%s,%s,%s", matricula,nombreCompleto,calFinalcsv);
    }

    public String toPDF(){
        String calFinalcsv = (calFinal == -1) ? "S/C" : String.valueOf(calFinal);
        return String.format("%s %s %s", matricula,nombreCompleto,calFinalcsv);
    }

    public void setCalFinal(int calFinal) {
        if(calFinal >= 0 && calFinal <= 100)
            this.calFinal = calFinal;
    }

    public int getCalFinal() {
        return calFinal;
    }

    public String getCalFinalFormateado() {
        return (calFinal == -1) ? "S/C" : String.valueOf(calFinal);
    }


    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public String getAsignatura(){
        return asignatura;
    }
}
