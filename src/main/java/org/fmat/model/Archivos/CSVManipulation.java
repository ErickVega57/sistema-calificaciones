package org.fmat.model.Archivos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVManipulation {

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

    public void imprimirDatos(Path ruta) throws IOException {
        // lee el archivo y guarda las lineas
        ArrayList<String> c = readFile(ruta);
        // join sirve para separar los elementos de una lista con un caracter en este caso '\n'
        System.out.println(String.join("\n", c));
    }

    public void writeFile(String fileName , ArrayList<String> contenido) throws IOException {
        Path ruta = Paths.get("ArchivosExportados", fileName + ".csv");
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
    public void appendFile(Path ruta, String linea) throws IOException {
        try(FileWriter escritor = new FileWriter(ruta.toFile() , true)) {
            escritor.write(linea + '\n');
        }
    }

}
