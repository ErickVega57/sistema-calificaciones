package org.fmat.clases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GestorUsersContrasTest {

    @Test
    void guardarUsuario() throws IOException {
        GestorUsersContras gu = new GestorUsersContras();
        Assertions.assertTrue(gu.guardarUsuario("Erick", "CONTRASENA123"));
    }
}