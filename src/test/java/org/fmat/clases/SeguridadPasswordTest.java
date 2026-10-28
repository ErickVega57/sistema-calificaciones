package org.fmat.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeguridadPasswordTest {

    @Test
    void encriptarDevuelveHashNoNulo() {
        SeguridadPassword seguridad = new SeguridadPassword();
        char[] passwd = "Secreta12".toCharArray();

        String hash = seguridad.encriptar(passwd);

        assertNotNull(hash);
        assertFalse(hash.isBlank());
        assertTrue(hash.startsWith("$argon2id$"));
    }

    @Test
    void encriptarLimpiaArreglo() {
        SeguridadPassword seguridad = new SeguridadPassword();
        char[] passwd = "Secreta12".toCharArray();

        seguridad.encriptar(passwd);

        for (char c : passwd) {
            assertEquals('\0', c);
        }
    }

    @Test
    void contraEsValidaCorrecta() {
        SeguridadPassword seguridad = new SeguridadPassword();
        char[] passwd = "Secreta12".toCharArray();
        String hash = seguridad.encriptar(passwd.clone());

        assertTrue(seguridad.contraEsValida(hash, "Secreta12".toCharArray()));
    }

    @Test
    void contraEsValidaIncorrecta() {
        SeguridadPassword seguridad = new SeguridadPassword();
        char[] passwd = "Secreta12".toCharArray();
        String hash = seguridad.encriptar(passwd.clone());

        assertFalse(seguridad.contraEsValida(hash, "OtraClave99".toCharArray()));
    }
}
