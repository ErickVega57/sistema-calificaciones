package org.fmat.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredencialDeUsuarioTest {

    @Test
    void fromCsvLineParseaUsuarioYHash() {
        CredencialDeUsuario cred = CredencialDeUsuario.fromCsvLine("user,\"$argon2id$v=19$m=65536,t=3,p=1$abc\"");

        assertEquals("user", cred.getUsuario());
        assertEquals("$argon2id$v=19$m=65536,t=3,p=1$abc", cred.getHash());
    }

    @Test
    void fromCsvLineEliminaComillasYEspacios() {
        CredencialDeUsuario cred = CredencialDeUsuario.fromCsvLine("erick,  \"hash123\"  ");

        assertEquals("erick", cred.getUsuario());
        assertEquals("hash123", cred.getHash());
    }

    @Test
    void fromCsvLineConComaEnHash() {
        CredencialDeUsuario cred = CredencialDeUsuario.fromCsvLine("user,hash,con,comas");

        assertEquals("user", cred.getUsuario());
        assertEquals("hash,con,comas", cred.getHash());
    }

    @Test
    void gettersDevuelvenValores() {
        CredencialDeUsuario cred = new CredencialDeUsuario("u", "h");

        assertEquals("u", cred.getUsuario());
        assertEquals("h", cred.getHash());
    }
}
