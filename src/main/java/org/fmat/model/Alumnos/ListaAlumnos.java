package org.fmat.model.Alumnos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlumnos {

    private ArrayList<Alumno> listaDeAlumnos = null;


    public ListaAlumnos(){
        this.listaDeAlumnos = new ArrayList<>();
    }

    public ListaAlumnos(ArrayList<String> contenido){
        this.listaDeAlumnos = llenarLista(contenido);
    } // se llena la lista con el contenido de un archivo

    public void agregar(Alumno alumno){
        listaDeAlumnos.add(alumno);
    }



    private ArrayList<Alumno> llenarLista(ArrayList<String> contenido){
        ArrayList<Alumno> l = new ArrayList<>();
        for (int i = 1; i < contenido.size(); i++) {
            String linea = contenido.get(i).trim();
            System.out.println(linea);
            if (linea.isEmpty()) continue;

            String[] argumentos = linea.split(",");

            if (argumentos.length < 2) continue; // saltar si no hay matrícula + nombre

            // Limitar subList al tamaño real de argumentos
            int limite = Math.min(argumentos.length, 5);
            List<String> nombresL = Arrays.asList(argumentos).subList(1, limite);

            String nombre = String.join(" ", nombresL);
            String matricula = argumentos[0];

            l.add(new Alumno(matricula, nombre));
        }
        return l;
    }

    public ArrayList<Alumno> getListaDeAlumnos() {
        return listaDeAlumnos;  //devuelve la lista
    }

    public void verAlumnos(){
        for (Alumno a : listaDeAlumnos) {
            System.out.println(a.toCSV());  //mostrar como se imprime CSV
        }
    }
    public boolean esExportable(){
        for (Alumno a : listaDeAlumnos){
            if (a.getCalFinal() < 0) // valida que todos tengan calificacion mayor a -1 (por defecto)
                return false;
        }
        return true;
    }

    public ArrayList<String> generarContenidoCSV(){
        ArrayList<String> contenido = new ArrayList<>(); // crea una lista para las lineas

        if(esExportable()){
            contenido.add("Alumnos"); // agrega el titulo primera linea

            for (Alumno a : listaDeAlumnos){
                // por cada alumno lo agrega formato CSV
                contenido.add(a.toCSV());
            }
            return contenido;  // devuelve el contenido

        }else
            return null; // si no es exportable devuelve null
    }

    public ArrayList<String> generarContenidoPDF(){
        ArrayList<String> contenido = new ArrayList<>();
        for (Alumno a : listaDeAlumnos){
            contenido.add(a.toPDF());
        }
        return contenido;
    }


}
