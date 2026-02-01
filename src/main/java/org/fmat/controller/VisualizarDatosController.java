package org.fmat.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.fmat.model.Alumnos.Alumno;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.fmat.model.Archivos.PDFManipulation;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizarDatosController extends Controller implements VisualizarDatos {
    @FXML
    private Button btnExportarCSV;
    @FXML
    private Button btnExportarDatos;
    @FXML
    private Button btnVolverMenu;

    @FXML
    private TableView<Alumno> tableReport;
    @FXML
    private TableColumn<Alumno,String> colNombreCompleto;
    @FXML
    private TableColumn<Alumno,String> colAsignatura;
    @FXML
    private TableColumn<Alumno,String> colCalificacion;


    @FXML
    @Override
    public void exportarCSV() {

    }

    @FXML
    @Override
    public void exportarPDF() {

    }

    @FXML
    public void volverMenu() {
        cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
    }

    public void initialize() {
        setupTableView();
    }


    @FXML
    private void setupTableView() {
        colNombreCompleto.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getNombreCompleto())
        );

        colAsignatura.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getAsignatura())
        );

        colCalificacion.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getCalFinalFormateado())
        );

        tableReport.setItems(Controller.alumnos);
        tableReport.setEditable(true); // activa edición en toda la tabla
        colCalificacion.setEditable(true); // activa edición en la columna específica

        //Volver columna editable y modificar la linea
        colCalificacion.setCellFactory(TextFieldTableCell.forTableColumn());

        colCalificacion.setOnEditCommit(event -> {
            Alumno alumno = event.getRowValue();
            String nuevaCal = event.getNewValue().trim();

            if (nuevaCal.equalsIgnoreCase("S/C")) {
                alumno.setCalFinal(-1); // vuelve al valor por defecto
            } else {
                try {
                    int cal = Integer.parseInt(nuevaCal);
                    if (cal >= 0 && cal <= 100) {
                        alumno.setCalFinal(cal);
                    } else {
                        //Debe ir una advertencia
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Calificación no válida: ");
                }
            }

            tableReport.refresh(); // refresca la tabla para mostrar el cambio
        });

        tableReport.setEditable(true);

    }

}
