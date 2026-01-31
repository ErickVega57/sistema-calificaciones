package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MenuController extends Controller implements Menu{
    @FXML
    private Button btnImportarDatos;
    @FXML
    private Button btnVerDatos;


    @FXML
    public void importarDatos() {
        cerrarYAbrirNuevaVentana("Importar Archivos", IMPORT_FILE_FXML, btnImportarDatos);
    }

    @FXML
    public void visualizarDatos() {
        cerrarYAbrirNuevaVentana("Visualizar Datos", VISUALIZE_FILE_FXML, btnVerDatos);
    }


}
