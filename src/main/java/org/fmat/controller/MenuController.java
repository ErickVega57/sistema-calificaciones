package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.fmat.vistas.Vistas;


public class MenuController extends Controller implements Menu{
    @FXML
    private Button btnImportarDatos;
    @FXML
    private Button btnVerDatos;


    @FXML
    public void importarDatos() {
        cambiarVista(Vistas.IMPORT_VIEW, btnVerDatos);
    }

    @FXML
    public void visualizarDatos() {
        cambiarVista(Vistas.VISUALIZE_FILE, btnImportarDatos);
    }


}
