package org.fmat.model.Alumnos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ListaAlumnosTest {

    @Test
    void constructorVacioListaVacia() {
        ListaAlumnos lista = new ListaAlumnos();
        assertNotNull(lista.getListaDeAlumnos());
        assertTrue(lista.getListaDeAlumnos().isEmpty());
    }

    @Test
    void constructorConContenidoParseaAlumnos() {
        ArrayList<String> contenido = new ArrayList<>(Arrays.asList(
                "Alumnos,",
                "2345,Erick Manuel Vega Escobar",
                "9999,Juan Perez"
        ));

        ListaAlumnos lista = new ListaAlumnos(contenido);

        assertEquals(2, lista.getListaDeAlumnos().size());
        assertEquals("2345", lista.getListaDeAlumnos().get(0).getMatricula());
        assertEquals("Erick Manuel Vega Escobar", lista.getListaDeAlumnos().get(0).getNombreCompleto());
        assertEquals("9999", lista.getListaDeAlumnos().get(1).getMatricula());
    }

    @Test
    void constructorIgnoraLineasVaciasYMalformadas() {
        ArrayList<String> contenido = new ArrayList<>(Arrays.asList(
                "Alumnos,",
                "",
                "solo-matricula",
                "2345,Erick Vega"
        ));

        ListaAlumnos lista = new ListaAlumnos(contenido);

        assertEquals(1, lista.getListaDeAlumnos().size());
        assertEquals("2345", lista.getListaDeAlumnos().get(0).getMatricula());
    }

    @Test
    void agregarIncrementaTamano() {
        ListaAlumnos lista = new ListaAlumnos();
        lista.agregar(new Alumno("1", "A"));
        lista.agregar(new Alumno("2", "B"));

        assertEquals(2, lista.getListaDeAlumnos().size());
    }

    @Test
    void esExportableFalseSiHaySinCalificacion() {
        ListaAlumnos lista = new ListaAlumnos();
        Alumno a = new Alumno("1", "A");
        a.setCalFinal(80);
        lista.agregar(a);
        lista.agregar(new Alumno("2", "B")); // calFinal = -1

        assertFalse(lista.esExportable());
    }

    @Test
    void esExportableTrueSiTodosTienenCalificacion() {
        ListaAlumnos lista = new ListaAlumnos();
        Alumno a = new Alumno("1", "A");
        a.setCalFinal(0);
        Alumno b = new Alumno("2", "B");
        b.setCalFinal(100);
        lista.agregar(a);
        lista.agregar(b);

        assertTrue(lista.esExportable());
    }

    @Test
    void generarContenidoCSVNullSiNoExportable() {
        ListaAlumnos lista = new ListaAlumnos();
        lista.agregar(new Alumno("1", "A")); // sin calificacion

        assertNull(lista.generarContenidoCSV());
    }

    @Test
    void generarContenidoCSVConEncabezadoYLineas() {
        ListaAlumnos lista = new ListaAlumnos();
        Alumno a = new Alumno("1", "A");
        a.setCalFinal(90);
        lista.agregar(a);

        ArrayList<String> contenido = lista.generarContenidoCSV();

        assertNotNull(contenido);
        assertEquals("Alumnos", contenido.get(0));
        assertEquals("1,A,90", contenido.get(1));
    }

    @Test
    void generarContenidoPDFUnaLineaPorAlumno() {
        ListaAlumnos lista = new ListaAlumnos();
        Alumno a = new Alumno("1", "A");
        a.setCalFinal(90);
        lista.agregar(a);

        ArrayList<String> contenido = lista.generarContenidoPDF();

        assertNotNull(contenido);
        assertEquals(1, contenido.size());
        assertEquals("1 A 90", contenido.get(0));
    }
}
