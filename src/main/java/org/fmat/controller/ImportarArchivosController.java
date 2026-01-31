package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ImportarArchivosController extends Controller implements ImportarArchivos{
    @FXML
    private Button btnValidarNombreArchivo;
    @FXML
    private Button btnVolverMenu;
    @FXML
    private TextField lectorArchivos;

    @FXML
    public void validarArchivos() {

    }

    @FXML
    public void volverMenu() {
        cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
    }



}
