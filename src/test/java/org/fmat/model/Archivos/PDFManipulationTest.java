package org.fmat.model.Archivos;

import org.fmat.model.Alumnos.Alumno;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PDFManipulationTest {

    @Test
    void alumnoConCalificacion() {
        Alumno alumno = new Alumno("2345", "Erick Manuel Vega Escobar");
        alumno.setCalFinal(87);

        String formatoEsperado = "2345 Erick Manuel Vega Escobar 87";
        assertEquals(formatoEsperado, PDFManipulation.AlumnoToPDF(alumno));
    }

    @Test
    void alumnoSinCalificacion() {
        Alumno alumno = new Alumno("2345", "Erick Manuel Vega Escobar");
        alumno.setCalFinal(-1);

        String formatoEsperado = "2345 Erick Manuel Vega Escobar S/C";
        assertEquals(formatoEsperado, PDFManipulation.AlumnoToPDF(alumno));
    }

    @Test
    void exportarArchivoCreaPDFNoVacio() throws IOException {
        PDFManipulation pdf = new PDFManipulation();
        ArrayList<String> contenido = new ArrayList<>(Arrays.asList("Linea 1", "Linea 2"));

        String nombre = "prueba_pdf_test";
        pdf.exportarArchivo(nombre, contenido);

        Path ruta = Paths.get("ArchivosExportados", nombre + ".pdf");
        assertTrue(Files.exists(ruta));
        assertTrue(Files.size(ruta) > 0);

        Files.deleteIfExists(ruta);
    }
}
