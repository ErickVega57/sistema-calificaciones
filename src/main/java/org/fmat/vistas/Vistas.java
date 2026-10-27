package org.fmat.vistas;


/*
* REFACTORIZACION-IMPL#8
* INTRODUCE PARAMETER OBJECT
* parametros relacionados en un objeto propio
*
 */
public enum Vistas {
    LOGIN_VIEW("Iniciar Sesión", "LoginView.fxml"),
    MENU_VIEW("Menú", "MenuView.fxml" ),
    IMPORT_VIEW("Importar Archivo", "ImportarArchivosView.fxml"),
    VISUALIZE_FILE("Visualizar Archivo","VisualizarDatosView.fxml");

    private static final String VIEWS_DIRECTORY = "/Views/";

    private final String titulo;
    private final String rutaVista;

    private Vistas(String titulo, String rutaVista){
        this.titulo = titulo;
        this.rutaVista = rutaVista;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getRutaVista() {
        return VIEWS_DIRECTORY + rutaVista;
    }
}
