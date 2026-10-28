package org.fmat.clases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServicioAutenticacionTest {

    private final ServicioAutenticacion auth = new ServicioAutenticacion();
    private final Path ruta = Paths.get("Usuarios", "Usuarios.csv");

    private byte[] backup;
    private boolean existia;

    @BeforeEach
    void respaldar() throws IOException {
        if (Files.exists(ruta)) {
            backup = Files.readAllBytes(ruta);
            existia = true;
        } else {
            backup = null;
            existia = false;
        }
    }

    @AfterEach
    void restaurar() throws IOException {
        if (existia) {
            Files.createDirectories(ruta.getParent());
            Files.write(ruta, backup);
        } else {
            Files.deleteIfExists(ruta);
        }
    }

    @Test
    void registrarUsuarioNuevoDevuelveTrue() throws IOException {
        String user = "svcUser_" + UUID.randomUUID();
        assertTrue(auth.registrarUsuario(user, "Secreta12"));
        assertTrue(new UsuarioRepository().userExiste(user));
    }

    @Test
    void registrarUsuarioDuplicadoDevuelveFalse() throws IOException {
        String user = "svcUser_" + UUID.randomUUID();
        assertTrue(auth.registrarUsuario(user, "Secreta12"));
        assertFalse(auth.registrarUsuario(user, "Secreta12"));
    }

    @Test
    void loginCorrectoUsuarioInexistenteDevuelveFalse() throws IOException {
        assertFalse(auth.loginCorrecto("noexiste_" + UUID.randomUUID(), "Secreta12"));
    }

    @Test
    void loginCorrectoContrasenaCorrectaDevuelveTrue() throws IOException {
        String user = "svcUser_" + UUID.randomUUID();
        assertTrue(auth.registrarUsuario(user, "Secreta12"));
        assertTrue(auth.loginCorrecto(user, "Secreta12"));
    }

    @Test
    void loginCorrectoContrasenaIncorrectaDevuelveFalse() throws IOException {
        String user = "svcUser_" + UUID.randomUUID();
        assertTrue(auth.registrarUsuario(user, "Secreta12"));
        assertFalse(auth.loginCorrecto(user, "OtraClave99"));
    }
}
