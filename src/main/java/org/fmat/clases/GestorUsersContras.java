package org.fmat.clases;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.regex.Pattern;

public class GestorUsersContras {

    private static final  Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

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

    public boolean guardarUsuario(String user, String passwd){
        if(false){  // usuario existe
            return false;
        }
        String hash = encriptar(passwd);
        String usuarioCSV = usuarioToCSV(user, hash);
        //escribir en csv()
        return true;
    }
    public boolean loginCorrecto(String user, String passwd) {
        if (false) { //usuario no existe
            return false;
        }
        //obtener hash
        //if(contraEsValida(hash, passwd.toCharArray() && usuario existe){
        // return true}

        return true;
    }


}
