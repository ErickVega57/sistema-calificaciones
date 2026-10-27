package org.fmat.model.Archivos;

import org.fmat.model.Alumnos.Alumno;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVManipulation implements Exporter {

    private BufferedReader lector; //servirá para leer el archivo
    private String linea; //servirá para recibir cada linea

    //metodo para leer archivos
    public ArrayList<String> readFile(Path ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<>(); // variable guarda las lineas


        lector = new BufferedReader(new FileReader(ruta.toFile()));
        while((linea = lector.readLine()) != null){//mientras que el archivo tenga lineas
            contenido.add(linea);
        }
        return contenido; // retorna todas las lineas del archivo
    }

    public void appendFile(Path ruta, String linea) throws IOException {
        try(FileWriter escritor = new FileWriter(ruta.toFile() , true)) {
            escritor.write(linea + '\n');
        }
    }

    /*
    MOVE METHOD
     */
    @Override
    public void exportarArchivo(String nombreArchivo, ArrayList<String> contenido) throws IOException {
        Path ruta = Paths.get("ArchivosExportados", nombreArchivo + ".csv");
        Files.createDirectories(ruta.getParent());
        if (Files.exists(ruta)) {
            System.out.println("El archivo ya existe. Se sobrescribirá.");
        }
        try (BufferedWriter escritor = Files.newBufferedWriter(ruta)) {
            for (String linea : contenido) {
                escritor.write(linea); escritor.newLine();
            }
        }
    }

    public static String AlumnoToCSV (Alumno alumno){
        //formato CSV
        int calFinal = alumno.getCalFinal();

        String matricula = alumno.getMatricula();
        String nombreCompleto = alumno.getNombreCompleto();
        String calFinalcsv = (calFinal == -1) ? "No registrado" : String.valueOf(calFinal);

        return String.format("%s,%s,%s", matricula,nombreCompleto,calFinalcsv);
    }
}
