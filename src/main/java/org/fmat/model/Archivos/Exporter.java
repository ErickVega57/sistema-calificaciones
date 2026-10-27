package org.fmat.model.Archivos;

import java.io.IOException;
import java.util.ArrayList;


/*
* REFACTORIZACION-IMPL#4
* EXTRACT INTERFACE
*
 */
public interface Exporter {
    public void exportarArchivo(String nombreArchivo, ArrayList<String> contenido ) throws IOException;
}
