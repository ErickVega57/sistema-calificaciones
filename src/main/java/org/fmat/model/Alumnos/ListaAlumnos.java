package org.fmat.model.Alumnos;
import java.util.ArrayList;
import java.util.List;

public class ListaAlumnos {

    private ArrayList<Alumno> listaDeAlumnos = new ArrayList<>();

    public ListaAlumnos(ArrayList<String> contenido){
        this.listaDeAlumnos = llenarLista(contenido);
    } // se llena la lista con el contenido de un archivo

    private ArrayList<Alumno> llenarLista (ArrayList<String> contenido){
        ArrayList<Alumno> l = new ArrayList<>() ;
        int limite;
        for (int i = 1; i < contenido.size(); i++){
            String linea = contenido.get(i);// obtiene linea

            List<String> argumentos = List.of(linea.split(" ")); // divide la linea en argumentos
            limite = (argumentos.size() > 4) ? 5 : 4; // ve cuanto mide la linea (para los que tienen 1 o 2 nombres)
            List<String> nombresL = argumentos.subList(1,limite); // separa los nombres y apellidos

            String nombre = String.join(" ",nombresL); // los vuelve un String
            String matricula = argumentos.get(0); // primer argumento = matricula
            Alumno alumno = new Alumno(matricula, nombre); //crea alumno
            l.add(alumno); //agrega alumno
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


}
