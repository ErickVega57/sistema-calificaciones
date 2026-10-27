package org.fmat.clases;

import org.fmat.model.Archivos.CSVManipulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class UsuarioRepository {
    private final Path rutaUsuarios = Paths.get("Usuarios/Usuarios.csv");
    private final CSVManipulation csv = new CSVManipulation();

    public boolean guardarUsuario(String user, String hash) throws IOException {
        String usuarioCSV = user + ",\"" + hash + "\"";
        csv.appendFile(rutaUsuarios, usuarioCSV);
        return true;
    }

    /*
    * REFACTORIZACION-IMPL#6
    * CONSOLIDATE CONDITIONAL EXPRESSION
    *
    * en el metodo userExiste se puede unir en un solo bloque condicional
     */
    public boolean userExiste(String user) throws IOException {
        if (!Files.exists(rutaUsuarios)) return false;

        ArrayList<String> usuarios = csv.readFile(rutaUsuarios);
        if (usuarios == null || usuarios.isEmpty()) return false;

        for(String u : usuarios){
            String[] args = u.split(",", 2);
            if (Objects.equals(user, args[0])) return true;
        }

        return false;
    }

    /*
     * REFACTORIZACION-IMPL#6
     * REPLACE ARRAY WITH OBJECT
     * se creo el objeto credencial de usuario
     */

    public String obtenerHashDeUsuario(String usuario) throws IOException {
        ArrayList<String> contenido = csv.readFile(rutaUsuarios);
        if (contenido != null) {
            for(String linea : contenido){
                CredencialDeUsuario credencialDeUsuario = CredencialDeUsuario.fromCsvLine(linea);
                if (Objects.equals(usuario, credencialDeUsuario.getUsuario())){
                    return credencialDeUsuario.getHash();
                }
            }
        }
        return null;
    }
}
