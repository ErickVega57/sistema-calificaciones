package org.fmat.clases;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.fmat.model.Archivos.CSVManipulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class GestorUsersContras {

    private static final  Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    private static final Path rutaUsuarios = Paths.get("Usuarios/Usuarios.csv");

    private String encriptar(String passwd){

        int iterations = 3;
        int memory = 65536;
        int parallelism = 1;

        return argon2.hash(iterations, memory, parallelism, passwd.toCharArray());
    }

    private boolean contraEsValida (String hash, char[] passwd) {
        return argon2.verify(hash, passwd);
    }

    public void limpiarMemoria (char[] passwd){
        argon2.wipeArray(passwd);
    }

    private String usuarioToCSV(String usuario, String hash){
        return usuario + "," + "\"" + hash + "\"";
    }

    public boolean entradaPasswdEsValida(String passwd){
        String regex = "^(?=(?:.*\\d){2,}).{5,}$";
        return Pattern.matches(regex, passwd);
    }

    public boolean entradaUsuarioEsValida(String user) {
        return user.length() >= 4;
    }

    public boolean guardarUsuario(String user, String passwd) throws IOException {
        if(false){  // usuario existe
            return false;
        }
        String hash = encriptar(passwd);
        String usuarioCSV = usuarioToCSV(user, hash);
        //escribir en csv()
        CSVManipulation csv = new CSVManipulation();
        csv.appendFile(Paths.get("Usuarios/Usuarios.csv"), usuarioCSV);
        return true;
    }
    public boolean loginCorrecto(String user, String passwd) throws IOException {
        if (!userExiste(user)){
            return false;
        }
        String userHash = hashDeUsuario(user);
        if (!contraEsValida(userHash,passwd.toCharArray())){
            return false;
        }
        limpiarMemoria(passwd.toCharArray());
        return true;
    }
    private boolean userExiste(String user) throws IOException {
        CSVManipulation csv = new CSVManipulation();
        if (!Files.exists(rutaUsuarios)){
            System.out.println("No hay usuarios registrados");
            return false;
        }
        ArrayList<String> usuarios = csv.readFile(rutaUsuarios);
        if (usuarios == null || usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados");
            return false;
        }
        for(String u : usuarios){
            String [] args = u.split(",",2);
            if (Objects.equals(user, args[0])){
                return true;
            }
        }
        return false;
    }

    private String hashDeUsuario(String usuario) throws IOException {
        CSVManipulation csv = new CSVManipulation();
        ArrayList<String> usuarios = csv.readFile(rutaUsuarios);
        for(String u : usuarios){
            String [] args = u.split(",",2);
            if (Objects.equals(usuario, args[0])){
                return args[1].replace("\"","").strip(); //reemplaza las comillas con nada
            }                                                             //strip quita espacios y \n
        }
        return null;
    }



}
