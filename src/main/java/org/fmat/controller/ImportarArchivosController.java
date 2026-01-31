package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImportarArchivosController extends Controller implements ImportarArchivos{
    @FXML
    private Button btnValidarNombreArchivo;
    @FXML
    private Button btnVolverMenu;
    @FXML
    private TextField lectorArchivos;

    private final Alert alertFileNameNoExiste = new Alert(Alert.AlertType.WARNING);
    private final Alert alertFileNameExiste = new Alert(Alert.AlertType.WARNING);
    private final ButtonType buttonAceptar = new ButtonType("ACEPTAR");
    private final ButtonType buttonAceptarM = new ButtonType("VOLVER AL MENÚ");

    private static final String rutaAlumnos = "src/main/resources";


    @FXML
    public void validarArchivos() {
        String fileName = lectorArchivos.getText() + ".csv";
        Path ruta = Paths.get(rutaAlumnos,fileName);
        if (!Files.exists(ruta)){
            lanzarErrorArchivoNoExiste();
        } else {
            lanzarArchivoExiste();
        }
    }

    @FXML
    public void volverMenu() {
        cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
    }



    private void lanzarErrorArchivoNoExiste() {
        alertFileNameNoExiste.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                lectorArchivos.clear();
                alertFileNameNoExiste.close();
            }
        });
    }

    private void lanzarArchivoExiste() {
        alertFileNameExiste.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptarM) {
                lectorArchivos.clear();
                alertFileNameExiste.close();
                volverMenu();
            }
        });
    }

    @FXML
    private void initialize(){  //esta funcion se ejecta al abrir la ventana
        configureAlerts();
    }

    private void configureAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().setAll(buttonAceptar, buttonAceptarM);
    }

    private void configureAlerts() {
        configureAlert(alertFileNameNoExiste, "Error Archivo", "El archivo no existe", "");
        configureAlert(alertFileNameExiste, "", "El archivo se cargó correctamente", "");

    }


}
