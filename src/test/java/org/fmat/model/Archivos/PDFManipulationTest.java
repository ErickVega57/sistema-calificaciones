package org.fmat.model.Archivos;

import org.fmat.model.Alumnos.Alumno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFManipulationTest {

    @Test
    void alumnoConCalificacion() {

        PDFManipulation pdf = new PDFManipulation();
        Alumno alumno = new Alumno(
                "2345",
                "Erick Manuel Vega Escobar"
                );

        alumno.setCalFinal(87);

        String formatoEsperado = "2345 Erick Manuel Vega Escobar 87";
        assertEquals(formatoEsperado, PDFManipulation.AlumnoToPDF(alumno));

    }

    @Test
    void alumnoSinCalificacion() {

        PDFManipulation pdf = new PDFManipulation();
        Alumno alumno = new Alumno(
                "2345",
                "Erick Manuel Vega Escobar"
        );

        alumno.setCalFinal(-1);

        String formatoEsperado = "2345 Erick Manuel Vega Escobar S/C";
        assertEquals(formatoEsperado, PDFManipulation.AlumnoToPDF(alumno));
    }

}