package org.fmat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.fmat.controller.Controller;
import org.fmat.vistas.Vistas;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controller c = new Controller();
        c.abrirVentana(Vistas.LOGIN_VIEW);
    }


    public static void main(String[] args) {
        launch(args);
    }

}