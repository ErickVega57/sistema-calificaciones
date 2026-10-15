package org.fmat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.fmat.controller.Controller;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controller c = new Controller();
        c.abrirVentana("Iniciar Sesión", Controller.LOGIN_VIEW_FXML);
    }


    public static void main(String[] args) {
        launch(args);
    }

}