package org.fmat.clases;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class GestorContras {

    private static final  Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public String encriptar(char [] passwd){

        int iterations = 3;
        int memory = 65536;
        int parallelism = 1;

        return argon2.hash(iterations, memory, parallelism, passwd);
    }

    public boolean contraEsValida (String hash, char[] passwd) {
        return argon2.verify(hash, passwd);
    }

    public void limpiarMemoria (char[] passwd){
        argon2.wipeArray(passwd);
    }


    public String usuarioToCSV(String usuario, String hash){
        return usuario + "," + "\"" + hash + "\"";
    }

}
