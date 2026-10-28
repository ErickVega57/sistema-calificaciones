package org.fmat.model.Archivos;

import org.fmat.model.Alumnos.Alumno;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVManipulationTest {

    @Test
    void alumnoConCalificacion() {
        Alumno alumno = new Alumno("2345", "Erick Manuel Vega Escobar");
        alumno.setCalFinal(87);

        String formatoEsperado = "2345,Erick Manuel Vega Escobar,87";
        assertEquals(formatoEsperado, CSVManipulation.AlumnoToCSV(alumno));
    }

    @Test
    void alumnoSinCalificacion() {
        Alumno alumno = new Alumno("2345", "Erick Manuel Vega Escobar");
        alumno.setCalFinal(-1);

        String formatoEsperado = "2345,Erick Manuel Vega Escobar,No registrado";
        assertEquals(formatoEsperado, CSVManipulation.AlumnoToCSV(alumno));
    }

    @Test
    void readFileDevuelveLineas(@TempDir Path tempDir) throws IOException {
        Path archivo = tempDir.resolve("datos.csv");
        Files.writeString(archivo, "linea1\nlinea2\n");

        CSVManipulation csv = new CSVManipulation();
        ArrayList<String> contenido = csv.readFile(archivo);

        assertEquals(2, contenido.size());
        assertEquals("linea1", contenido.get(0));
        assertEquals("linea2", contenido.get(1));
    }

    @Test
    void readFileArchivoInexistenteLanzaIOException(@TempDir Path tempDir) {
        CSVManipulation csv = new CSVManipulation();
        Path archivo = tempDir.resolve("noexiste.csv");

        assertThrows(IOException.class, () -> csv.readFile(archivo));
    }

    @Test
    void appendFileAgregaLinea(@TempDir Path tempDir) throws IOException {
        Path archivo = tempDir.resolve("datos.csv");
        Files.writeString(archivo, "primera\n");

        CSVManipulation csv = new CSVManipulation();
        csv.appendFile(archivo, "segunda");

        List<String> lineas = Files.readAllLines(archivo);
        assertEquals(2, lineas.size());
        assertEquals("segunda", lineas.get(1));
    }

    @Test
    void exportarArchivoCreaYSobrescribe(@TempDir Path tempDir) throws IOException {
        // exportarArchivo usa ruta fija "ArchivosExportados", por lo que se prueba en el directorio de trabajo.
        CSVManipulation csv = new CSVManipulation();
        ArrayList<String> contenido = new ArrayList<>(List.of("a,b", "c,d"));

        csv.exportarArchivo("prueba_csv_test", contenido);

        Path ruta = Path.of("ArchivosExportados", "prueba_csv_test.csv");
        assertTrue(Files.exists(ruta));
        assertEquals(contenido, Files.readAllLines(ruta));

        // sobrescribir
        ArrayList<String> nuevo = new ArrayList<>(List.of("x"));
        csv.exportarArchivo("prueba_csv_test", nuevo);
        assertEquals(nuevo, Files.readAllLines(ruta));

        Files.deleteIfExists(ruta);
    }
}
