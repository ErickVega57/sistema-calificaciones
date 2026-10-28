package org.fmat.model.Archivos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExporterTest {

    private final Exporter exporterPDF = new PDFManipulation();
    private final Exporter exporterCSV = new CSVManipulation();

    private final String nombreArchivoTest = "archivoTest";

    private final Path rutaEsperadaPDF = Paths.get("ArchivosExportados", nombreArchivoTest + ".pdf");
    private final Path rutaEsperadaCSV = Paths.get("ArchivosExportados", nombreArchivoTest + ".csv");

    @AfterEach
    void limpiarArchivosDePrueba() throws IOException {
        Files.deleteIfExists(rutaEsperadaPDF);
        Files.deleteIfExists(rutaEsperadaCSV);
    }

    @Test
    void exportarArchivoPDF() throws IOException {

        ArrayList<String> contenido = new ArrayList<>(Arrays.asList("Línea 1 del PDF", "Línea 2 del PDF"));

        exporterPDF.exportarArchivo(nombreArchivoTest, contenido);

        assertTrue(Files.exists(rutaEsperadaPDF), "El archivo PDF debería haberse creado");
        assertTrue(Files.size(rutaEsperadaPDF) > 0, "El archivo PDF no debería estar vacío");
    }

    @Test
    void exportarArchivoCSV() throws IOException {

        ArrayList<String> contenido = new ArrayList<>(Arrays.asList("Línea,1,CSV", "Línea,2,CSV"));

        exporterCSV.exportarArchivo(nombreArchivoTest, contenido);

        assertTrue(Files.exists(rutaEsperadaCSV), "El archivo CSV debería haberse creado");
        assertTrue(Files.size(rutaEsperadaCSV) > 0, "El archivo CSV no debería estar vacío");
    }
}