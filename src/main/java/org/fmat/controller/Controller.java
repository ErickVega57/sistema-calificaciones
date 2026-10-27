package org.fmat.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.fmat.model.Alumnos.Alumno;
import org.fmat.vistas.Vistas;

import java.io.IOException;

public class Controller {

/*
    public static final String VIEWS_DIRECTORY = "/Views/";
    public static final String LOGIN_VIEW_FXML = VIEWS_DIRECTORY + "LoginView.fxml";
    public static final String MENU_VIEW_FXML = VIEWS_DIRECTORY + "MenuView.fxml";
    public static final String IMPORT_FILE_FXML = VIEWS_DIRECTORY + "ImportarArchivosView.fxml";
    public static final String VISUALIZE_FILE_FXML = VIEWS_DIRECTORY + "VisualizarDatosView.fxml";
*/
    public static ObservableList<Alumno> alumnos;

    private final Stage stage;

    public Controller(){
        this.stage = new Stage();
    }

    public void abrirVentana (Vistas vista) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(vista.getRutaVista()));
        Parent root = loader.load();

        Stage stage = getStage();
        stage.setTitle(vista.getTitulo());
        stage.setScene(new Scene(root));


        stage.centerOnScreen();
        stage.show();
    }

    /*
    * REFACTORIZACION : EXTRACT METHOD
    *
    * RENAME METHOD CAMBIAR VISTA
    *
    */
    public void cambiarVista(Vistas vista, Button botonCerrar){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista.getRutaVista()));
            Parent root = loader.load();
            Stage stage = (Stage) botonCerrar.getScene().getWindow(); //mismo stage
            stage.setTitle(vista.getTitulo());
            stage.setScene(new Scene(root));


            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarVolverAlMenu(Button botonCerrar){
        cambiarVista(Vistas.MENU_VIEW, botonCerrar);
    }


    public Stage getStage() {
        return stage;
    }
}
