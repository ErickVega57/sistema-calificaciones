package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VisualizarDatosController extends Controller implements VisualizarDatos {
    @FXML
    private Button btnExportarCSV;
    @FXML
    private Button btnExportarDatos;
    @FXML
    private Button btnVolverMenu;

    @FXML
    @Override
    public void exportarCSV() {

    }

    @FXML
    @Override
    public void exportarPDF() {

    }

    @FXML
    public void volverMenu() {
        cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
    }
}
