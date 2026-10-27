package org.fmat.clases;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.regex.Pattern;

public class SeguridadPassword {

    private static final  Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public String encriptar(char[] passwd) {
        return null;
    }

    public boolean contraEsValida (String hash, char[] passwd) {
        return argon2.verify(hash, passwd);
    }
}
