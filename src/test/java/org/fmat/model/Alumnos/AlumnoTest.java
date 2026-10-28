package org.fmat.model.Alumnos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoTest {

    @Test
    void constructorAsignaValoresPorDefecto() {
        Alumno alumno = new Alumno("2345", "Erick Manuel Vega Escobar");

        assertEquals("2345", alumno.getMatricula());
        assertEquals("Erick Manuel Vega Escobar", alumno.getNombreCompleto());
        assertEquals("Diseño de Software", alumno.getAsignatura());
        assertEquals(-1, alumno.getCalFinal());
    }

    @Test
    void setCalFinalValido() {
        Alumno alumno = new Alumno("2345", "Erick");

        alumno.setCalFinal(0);
        assertEquals(0, alumno.getCalFinal());

        alumno.setCalFinal(100);
        assertEquals(100, alumno.getCalFinal());

        alumno.setCalFinal(-1);
        assertEquals(-1, alumno.getCalFinal());
    }

    @Test
    void setCalFinalInvalidoLanzaExcepcion() {
        Alumno alumno = new Alumno("2345", "Erick");

        assertThrows(IllegalArgumentException.class, () -> alumno.setCalFinal(-2));
        assertThrows(IllegalArgumentException.class, () -> alumno.setCalFinal(101));
    }

    @Test
    void getCalFinalFormateado() {
        Alumno alumno = new Alumno("2345", "Erick");

        assertEquals("S/C", alumno.getCalFinalFormateado());

        alumno.setCalFinal(87);
        assertEquals("87", alumno.getCalFinalFormateado());
    }

    @Test
    void infoFormato() {
        Alumno alumno = new Alumno("2345", "Erick");
        alumno.setCalFinal(87);

        String esperado = String.format("%-10s | %-40s | %-10s | %d", "2345", "Erick", "Diseño de Software", 87);
        assertEquals(esperado, alumno.info());
    }
}
