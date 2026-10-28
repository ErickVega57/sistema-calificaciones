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

class UsuarioRepositoryTest {

    private final UsuarioRepository repo = new UsuarioRepository();
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
    void userExisteFalseSiArchivoNoExiste() throws IOException {
        Files.deleteIfExists(ruta);
        assertFalse(repo.userExiste("usuarioQueNoExiste"));
    }

    @Test
    void userExisteFalseSiArchivoVacio() throws IOException {
        Files.createDirectories(ruta.getParent());
        Files.writeString(ruta, "");
        assertFalse(repo.userExiste("usuarioQueNoExiste"));
    }

    @Test
    void guardarUsuarioAgregaYUserExiste() throws IOException {
        String user = "testUser_" + UUID.randomUUID();

        assertTrue(repo.guardarUsuario(user, "hash123"));
        assertTrue(repo.userExiste(user));
        assertEquals("hash123", repo.obtenerHashDeUsuario(user));
    }

    @Test
    void obtenerHashDeUsuarioNullSiNoExiste() throws IOException {
        Files.createDirectories(ruta.getParent());
        if (!Files.exists(ruta)) {
            Files.writeString(ruta, "");
        }
        assertNull(repo.obtenerHashDeUsuario("usuarioInexistente_" + UUID.randomUUID()));
    }
}
