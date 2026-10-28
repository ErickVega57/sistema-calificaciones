package org.fmat.vistas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VistasTest {

    @Test
    void getTitulo() {
        assertEquals("Iniciar Sesión", Vistas.LOGIN_VIEW.getTitulo());
        assertEquals("Menú", Vistas.MENU_VIEW.getTitulo());
        assertEquals("Importar Archivo", Vistas.IMPORT_VIEW.getTitulo());
        assertEquals("Visualizar Archivo", Vistas.VISUALIZE_FILE.getTitulo());
    }

    @Test
    void getRutaVista() {
        assertEquals("/Views/LoginView.fxml", Vistas.LOGIN_VIEW.getRutaVista());
        assertEquals("/Views/MenuView.fxml", Vistas.MENU_VIEW.getRutaVista());
        assertEquals("/Views/ImportarArchivosView.fxml", Vistas.IMPORT_VIEW.getRutaVista());
        assertEquals("/Views/VisualizarDatosView.fxml", Vistas.VISUALIZE_FILE.getRutaVista());
    }
}
