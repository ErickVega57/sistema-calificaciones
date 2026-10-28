package org.fmat.clases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GestorUsersContrasTest {

    private final GestorUsersContras gestorUsersContras = new GestorUsersContras();

    /*
     * TEST REFACTORIZACION-IMPL#1
     * REPLACED NESTED CONDITIONAL WITH GUARD CLAUSES
     *
     */
    @Test
    void entradaPasswdValida() {
        assertTrue(gestorUsersContras.entradaPasswdEsValida("Contraseña12"));
        assertTrue(gestorUsersContras.entradaPasswdEsValida("HOLA?12"));
        assertTrue(gestorUsersContras.entradaPasswdEsValida("abc12@"));
    }

    @Test
    void entradaPasswdNoValida() {
        assertFalse(gestorUsersContras.entradaPasswdEsValida("Contraseña"));
        assertFalse(gestorUsersContras.entradaPasswdEsValida("HOLA1"));
        assertFalse(gestorUsersContras.entradaPasswdEsValida("1eee@"));
        assertFalse(gestorUsersContras.entradaPasswdEsValida(""));
    }
    //--------------------------------------------------------------------

    @Test
    void entradaUsuarioValida() {
        assertTrue(gestorUsersContras.entradaUsuarioEsValida("user"));
        assertTrue(gestorUsersContras.entradaUsuarioEsValida("erick"));
        assertTrue(gestorUsersContras.entradaUsuarioEsValida("UsuArio12"));
        assertTrue(gestorUsersContras.entradaUsuarioEsValida("u2345"));
    }

    @Test
    void entradaUsuarioNoValida() {
        assertFalse(gestorUsersContras.entradaUsuarioEsValida("    "));
        assertFalse(gestorUsersContras.entradaUsuarioEsValida(" "));
        assertFalse(gestorUsersContras.entradaUsuarioEsValida("Usu"));
        assertFalse(gestorUsersContras.entradaUsuarioEsValida("a"));
    }

}