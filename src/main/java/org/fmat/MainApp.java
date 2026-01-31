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
        c.abrirVentana("Login", Controller.LOGIN_VIEW_FXML);
    }

    public static void main(String[] args) {
        launch(args);
    }

    /* configuracion de alertas.
    private void configureAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        //alert.getButtonTypes().setAll(buttonTypeAccept, buttonTypeCancel);
    }
    private void configureAlerts() {
        configureAlert(alertCustomer, "New customer", "The customer doesn't exist", "Do you want to add it?");
        configureAlert(alertProduct, "New Product", "The product doesn't exist", "Do you want to add it?");
    }
    */



}