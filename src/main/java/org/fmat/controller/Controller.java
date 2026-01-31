package org.fmat.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.fmat.MainApp;

import java.io.IOException;

public class Controller {


    public static final String VIEWS_DIRECTORY = "/Views/";
    public static final String LOGIN_VIEW_FXML = VIEWS_DIRECTORY + "LoginView.fxml";
    public static final String MENU_VIEW_FXML = VIEWS_DIRECTORY + "MenuView.fxml";
    public static final String IMPORT_FILE_FXML = VIEWS_DIRECTORY + "ImportarArchivosView.fxml";
    public static final String VISUALIZE_FILE_FXML = VIEWS_DIRECTORY + "VisualizarDatosView.fxml";


    public Controller(){}

    public void abrirVentana (String titulo, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));


        stage.centerOnScreen();
        stage.show();
    }

    public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.close();
        try {
            abrirVentana(titulo, fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarVolverAlMenu(String titulo, String fxml, Button botonCerrar){
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.close();
        try {
            abrirVentana(titulo, fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
