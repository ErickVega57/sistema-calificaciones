package org.fmat.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.fmat.MainApp;

import java.io.IOException;

public class Controller {


    public static final String VIEWS_DIRECTORY = "/Views/";
    public static final String LOGIN_VIEW_FXML = VIEWS_DIRECTORY + "LoginView.fxml";

    public Controller(){}

    public void abrirVentana (String titulo, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(),900,600);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.centerOnScreen();
        stage.show();
    }

    public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.close();
        try {
            Controller c = new Controller();
            c.abrirVentana(titulo,fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
