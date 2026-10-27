package org.fmat.model.Archivos;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.fmat.model.Alumnos.Alumno;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PDFManipulation implements Exporter {

    public PDFManipulation(){}

    public void writePDF(String nombreArchivo,ArrayList<String> contenido ) throws FileNotFoundException {
       return;
    }

    @Override
    public void exportarArchivo(String nombreArchivo, ArrayList<String> contenido) throws IOException {
        Path ruta = Paths.get("ArchivosExportados/" + nombreArchivo);
        if (Files.exists(ruta)) {
            System.out.println("El archivo ya existe. Se sobrescribirá.");
        }

        String c = String.join("\n", contenido);

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("ArchivosExportados/" + nombreArchivo + ".pdf"));
        doc.open();
        doc.add(new Paragraph(c));
        doc.close();
    }

    public static String AlumnoToPDF(Alumno alumno){
        int calFinal = alumno.getCalFinal();

        String matricula = alumno.getMatricula();
        String nombreCompleto = alumno.getNombreCompleto();
        String calFinalpdf = (calFinal == -1) ? "S/C" : String.valueOf(calFinal);

        return String.format("%s %s %s", matricula,nombreCompleto,calFinalpdf);
    }




    /*public static void main(String[] args) throws FileNotFoundException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("ArchivosExportados/HolaMundo.pdf"));
        doc.open();
        doc.add(new Paragraph("¡Hola mundo! Este es un PDF fácil con OpenPDF."));
        doc.close();
        System.out.println("PDF creado!");
    }*/
}
