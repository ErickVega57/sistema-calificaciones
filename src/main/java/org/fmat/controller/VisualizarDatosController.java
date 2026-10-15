package org.fmat.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.fmat.model.Alumnos.Alumno;
import org.fmat.model.Alumnos.ListaAlumnos;
import org.fmat.model.Archivos.CSVManipulation;
import org.fmat.model.Archivos.PDFManipulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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


    private final Alert alertCalificacionNoValida = new Alert(Alert.AlertType.WARNING);
    private final Alert alertExportarCsvNoValido = new Alert(Alert.AlertType.WARNING);
    private final Alert getAlertExportarPDFNoValido = new Alert(Alert.AlertType.WARNING);
    private final Alert alertArchivoExportado = new Alert(Alert.AlertType.CONFIRMATION);
    private final ButtonType buttonAceptar = new ButtonType("ACEPTAR");

    @FXML
    @Override
    public void exportarCSV() {
        for (Alumno a : Controller.alumnos){
            if(a.getCalFinal() == -1){
                lanzarErrorExportarCSV();
                return;
            }else{
                ListaAlumnos alumnoslista = new ListaAlumnos();
                for (Alumno a1 : Controller.alumnos){
                    alumnoslista.agregar(a1);
                }
                ArrayList<String> contenidoCSV = alumnoslista.generarContenidoCSV();
                CSVManipulation c = new CSVManipulation();
                String nombreArchivo = pantallaNombre();
                if(nombreArchivo == null){
                    lanzarErrorExportarCSV();
                    return;
                }else
                    try {
                        c.writeFile(nombreArchivo, contenidoCSV);
                        lanzarArchivoExportadoCorrectamente();
                    }catch (IOException e){
                        lanzarErrorExportarCSV();
                        return;
                    }
            }
        }
    }


    @FXML
    @Override
    public void exportarPDF() {
        ListaAlumnos alumnoslista = new ListaAlumnos();
        for (Alumno a : Controller.alumnos){
            alumnoslista.agregar(a);
        }
        ArrayList<String> contenidoPDF = alumnoslista.generarContenidoPDF();
        PDFManipulation c = new PDFManipulation();
        String nombreArchivo = pantallaNombre();
        if(nombreArchivo == null){
            lanzarErrorExportarPDF();
        }else
            try {
                c.writePDF(nombreArchivo, contenidoPDF);
                lanzarArchivoExportadoCorrectamente();
            }catch (FileNotFoundException e){
                lanzarErrorExportarPDF();
            }
    }

    private String pantallaNombre(){
        TextInputDialog panelNombreArhivo = new TextInputDialog();
        panelNombreArhivo.setTitle("Guardar Archivo");
        panelNombreArhivo.setHeaderText("Nombre del archivo");
        panelNombreArhivo.setContentText("¿Qué nombre deseas ponerle al archivo?");
        Optional<String> nombre = panelNombreArhivo.showAndWait();

        if(nombre.isPresent()){
            String nombreArchivo = nombre.get().trim();
            if (!nombreArchivo.isEmpty()) {
                return nombreArchivo;
            }else {
                return null;
            }
        }else
            return null;
    }


    @FXML
    public void volverMenu() {
        cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
    }

    public void initialize() {
        setupTableView();
        configureAlerts();
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
                        lanzarErrorCalificacionNoValida();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Calificación no válida: ");
                }
            }

            tableReport.refresh(); // refresca la tabla para mostrar el cambio
        });

        tableReport.setEditable(true);

    }


    private void configureAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().setAll(buttonAceptar);
    }

    private void configureAlerts() {
        configureAlert(alertCalificacionNoValida, "Error", "La calificación no es valida", "Presione ACEPTAR para continuar");
        configureAlert(alertExportarCsvNoValido, "Error al exportar", "El archivo CSV no puede ser exportado", "Presione ACEPTAR para continuar");
        configureAlert(getAlertExportarPDFNoValido, "Error al exportar", "Error al exportar PDF", "Presione ACEPTAR para continuar");
        configureAlert(alertArchivoExportado, "Archivo Exportado", "Archivo exportado correctamente","Presione ACEPTAR para continuar");
    }


    private void lanzarErrorCalificacionNoValida() {
        alertCalificacionNoValida.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                alertCalificacionNoValida.close();
            }
        });
    }
    private void lanzarErrorExportarPDF() {
        getAlertExportarPDFNoValido.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                getAlertExportarPDFNoValido.close();
            }
        });
    }
    private void lanzarErrorExportarCSV() {
        alertExportarCsvNoValido.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                alertExportarCsvNoValido.close();
            }
        });
    }
    private void lanzarArchivoExportadoCorrectamente() {
        alertArchivoExportado.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                alertArchivoExportado.close();
            }
        });
    }

}
