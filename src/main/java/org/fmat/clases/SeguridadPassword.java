package org.fmat.clases;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.regex.Pattern;

public class SeguridadPassword {

    private static final  Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    private static final int ITERACIONES = 3;
    private static final int MEMORIA = 65536;
    private static final int PARALELISMO = 1;

    public String encriptar(char[] passwd) {

        String hash = argon2.hash(ITERACIONES, MEMORIA, PARALELISMO, passwd);

        argon2.wipeArray(passwd);

        return hash;
    }

    public boolean contraEsValida (String hash, char[] passwd) {
        return argon2.verify(hash, passwd);
    }
}
